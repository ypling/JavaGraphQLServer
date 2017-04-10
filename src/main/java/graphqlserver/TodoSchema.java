package graphqlserver;

import graphql.schema.*;

import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLNonNull.nonNull;

public class TodoSchema {

  private GraphQLSchema schema;


  public TodoSchema() {
    createSchema();
  }


  private void createSchema() {
    GraphQLObjectType todoType = newObject()
      .name("Todo")
      .field(newFieldDefinition()
        .name("id")
        .type(GraphQLID))
      .field(newFieldDefinition()
        .name("content")
        .type(GraphQLString))
      .build();

    GraphQLObjectType queryType = newObject()
      .name("QueryType")
      .field(newFieldDefinition()
        .type(list(todoType))
        .name("todos")
        .staticValue(null))
      .build();

    schema = GraphQLSchema.newSchema()
      .query(queryType)
      .build();
  }

  public GraphQLSchema getSchema() {
    return schema;
  }
}