package com.rol2.graphql;

import com.rol2.dao.RolDAO;
import com.rol2.model.Rol;
import com.rol2.model.Usuario;

import graphql.schema.DataFetcher;

import java.util.List;

public class RolDataFetcher {

    public static DataFetcher<Rol> obtenerRol() {
        return env -> {
            int id = env.getArgument("id");
            return RolDAO.obtenerRol(id);
        };
    }

    public static DataFetcher<List<Rol>> listarRoles() {
        return env -> RolDAO.listarRoles();
    }

    public static DataFetcher<List<Usuario>> usuariosByRol() {
        return env -> {
            int id = env.getArgument("id");
            return RolDAO.getUsuariosByRol(id);
        };
    }

    public static DataFetcher<Rol> crearRol() {
        return env -> {
            return RolDAO.crearRol(new Rol(
                env.getArgument("id"),
                env.getArgument("descripcion"),
                env.getArgument("estado")
            ));
        };
    }

    public static DataFetcher<Rol> actualizarRol() {
        return env -> {
            return RolDAO.actualizarRol(new Rol(
                env.getArgument("id"),
                env.getArgument("descripcion"),
                env.getArgument("estado")
            ));
        };
    }

    public static DataFetcher<Boolean> eliminarRol() {
        return env -> {
            int id = env.getArgument("id");
            return RolDAO.eliminarRol(id);
        };
    }
}
