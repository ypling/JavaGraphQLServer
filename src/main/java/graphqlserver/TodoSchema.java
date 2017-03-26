package graphqlserver;

import graphql.schema.*;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class TodoSchema {

  private GraphQLSchema schema;


  public TodoSchema() {
    createSchema();
  }


  private void createSchema() {
    GraphQLObjectType queryType = newObject()
      .name("helloWorldQuery")
      .field(newFieldDefinition()
        .type(GraphQLString)
        .name("hello")
        .staticValue("world"))
      .build();

    schema = GraphQLSchema.newSchema()
      .query(queryType)
      .build();
  }

  public GraphQLSchema getSchema() {
    return schema;
  }
}