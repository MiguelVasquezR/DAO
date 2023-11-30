package mx.uv.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/usuario";
    private String user = "root";
    private String password = "FormulaUno";
    Connection connection;

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return connection;
    }


}
