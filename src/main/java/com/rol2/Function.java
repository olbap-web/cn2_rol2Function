package com.rol2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.rol2.dao.RolDAO;
import com.rol2.graphql.GraphQLProvider;
import com.rol2.model.Rol;

import java.util.*;
import java.sql.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * create, update & delete
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("rol_awe")
    public HttpResponseMessage graphqlHandler(
        @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Map<String, Object>> request,
        final ExecutionContext context
    ) {
        GraphQLProvider.init();

        String query = (String) request.getBody().get("query");
        Map<String, Object> variables = (Map<String, Object>) request.getBody().get("variables");

        var executionResult = GraphQLProvider.getGraphQL().execute(builder ->
            builder.query(query).variables(variables != null ? variables : Map.of())
        );

        return request.createResponseBuilder(HttpStatus.OK)
            .header("Content-Type", "application/json")
            .body(executionResult.toSpecification())
            .build();
    }

}
