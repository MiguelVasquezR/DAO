package mx.uv.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOCliente {

    static Conexion conexion = new Conexion();
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection;

    public List<Cliente> getClientes(){
        try{
            ArrayList<Cliente> clienteArrayList = new ArrayList<>();
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select * from usuario");
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                clienteArrayList.add(cliente);
            }
            return clienteArrayList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean createUser(Cliente cliente){
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("insert into usuario(id, nombre, correo, telefono) values (?,?,?,?)");
            ps.setString(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            int res = ps.executeUpdate();
            if (res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(String id){
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("delete from usuario where usuario.id = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateUser(Cliente cliente){
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("update usuario set correo='?', telefono='?' where nombre = "+cliente.getNombre());
            ps.setString(1, cliente.getCorreo());
            ps.setString(2, cliente.getTelefono());
            int res = ps.executeUpdate();
            if (res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }





}
