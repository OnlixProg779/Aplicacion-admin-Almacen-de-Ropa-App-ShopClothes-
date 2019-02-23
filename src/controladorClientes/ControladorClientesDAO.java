package controladorClientes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Cliente;



public class ControladorClientesDAO {

private static final String LISTAR = "Select * from cliente";
    
    public ArbolAVL<Cliente> listarClientes() throws SQLException
    { 
        Conexion con = new Conexion();
        Connection connection= con.ObtenerConexion();
        ArbolAVL<Cliente> listaClientes= new ArbolAVL<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR);//susceptible de sql injection
            while(resultado.next())
            {
            	Cliente oe= new Cliente(); 
   
            	
                oe.setCedula(resultado.getString(1));
                oe.setNombre(resultado.getString(2));
                oe.setApellido(resultado.getString(3));
                oe.setDireccion(resultado.getString(4));
                oe.setTelefono(resultado.getString(5));
               
                
                listaClientes.add(oe);
              
        }

        } catch (SQLException e)
        {
          e.printStackTrace();
        }
       
        return listaClientes;


}
    
    
   
    
    
   
}
