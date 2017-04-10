package graphqlserver;

/**
 * Created by leonardli on 3/25/17.
 */

import com.sun.corba.se.impl.orbutil.graph.Graph;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class GraphQLController {
  TodoSchema todoSchema = new TodoSchema();
  GraphQL graphql = GraphQL.newGraphQL(todoSchema.getSchema()).build();

  private static final Logger log = LoggerFactory.getLogger(GraphQLController.class);

  @RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object executeOperation(@RequestBody Map body) {
    String query = (String) body.get("query");
    Map<String, Object> variables = (Map<String, Object>) body.get("variables") == null ? new LinkedHashMap<String, Object>() : (Map<String, Object>) body.get("variables");
    ExecutionResult executionResult = graphql.execute(query, (Object) null, variables);
    Map<String, Object> result = new LinkedHashMap<>();
    if (executionResult.getErrors().size() > 0) {
      result.put("errors", executionResult.getErrors());
      log.error("Errors: {}", executionResult.getErrors());
    }
    result.put("data", executionResult.getData());
    return result;
  }
}
