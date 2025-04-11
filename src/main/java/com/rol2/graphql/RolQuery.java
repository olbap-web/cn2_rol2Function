package com.rol2.graphql;

import com.rol2.dao.RolDAO;
import com.rol2.model.Rol;
import com.rol2.model.Usuario;

import graphql.schema.DataFetcher;
import java.util.List;

public class RolQuery {

    public DataFetcher<Rol> obtenerRol() {
        return dataFetchingEnvironment -> {
            int id = dataFetchingEnvironment.getArgument("id");
            return RolDAO.obtenerRol(id);
        };
    }

    public DataFetcher<List<Rol>> listarRoles() {
        return dataFetchingEnvironment -> RolDAO.listarRoles();
    }
    
    public DataFetcher<List<Usuario>> getUsuariosByRol(){
        return dataFetchingEnvironment -> {
            
            int id = dataFetchingEnvironment.getArgument("id");
            
            return RolDAO.getUsuariosByRol(id);

        };
    }
}

