package mx.uv;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mx.uv.conexion.Cliente;
import mx.uv.conexion.DAOCliente;
import spark.Request;

import java.util.ArrayList;
import java.util.UUID;

public class App {

    static Gson gson = new Gson();
    static DAOCliente daoCliente = new DAOCliente();

    public static void main(String[] args) {

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        put("/editar", (request, response) -> {
            response.type("application/json");
            String res = request.body();
            Cliente cliente = gson.fromJson(res, Cliente.class);
            JsonObject respuesta = new JsonObject();
            if (daoCliente.createUser(cliente)){
                respuesta.addProperty("msj", "Se ha editado correctamente");
            }else{
                respuesta.addProperty("msj", "Hubo un error al editar");
            }
            return respuesta;
        });

        delete("/eliminar", (request, response) -> {
            response.type("application/json");
            String res = request.queryParams("id");
            JsonObject respuesta = new JsonObject();
            if (daoCliente.deleteUser(res)){
                respuesta.addProperty("msj", "Se ha eliminado correctamente");
            }else{
                respuesta.addProperty("msj", "Hubo un error al eliminar");
            }
            return respuesta;
        });

        post("/agregar", (request, response) -> {
            response.type("application/json");
            String res = request.body();
            Cliente cliente = gson.fromJson(res, Cliente.class);
            String id = UUID.randomUUID().toString();
            cliente.setId(id);
            System.out.println(cliente.getId());
            JsonObject respuesta = new JsonObject();
            if (daoCliente.createUser(cliente)){
                respuesta.addProperty("msj", "Se ha agregado correctamente");
            }else{
                respuesta.addProperty("msj", "Hubo un error al agregar");
            }
            return respuesta;
        });

        get("/todos", ((request, response) -> {
            response.type("application/json");
            return gson.toJson(daoCliente.getClientes());
        }));

    }

}
