package controladorCuentas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.VistaCaja;


public class ControladorCuentasCajaDAO {
	
	
	
	private static final String LISTAR = "Select * from vista_caja";
	private static final String LLAMAR_SALDO_DE_CAJA = "Select * from total_caja";
	
	
    public ArbolAVL<VistaCaja> listarCaja() throws SQLException
    { 
        Conexion con = new Conexion();
        Connection connection= con.ObtenerConexion();
        ArbolAVL<VistaCaja> listaCaja= new ArbolAVL<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR);//susceptible de sql injection
            while(resultado.next())
            {
            	VistaCaja oe= new VistaCaja(); 
   
            	
                oe.setIdcaja(resultado.getInt(1));
                oe.setFecha((resultado.getDate(2))); 
                oe.setHora(resultado.getTime(3));
                oe.setReferencia(resultado.getString(4));
                oe.setNumerofactura(resultado.getString(5));
                oe.setValor(resultado.getDouble(6));
               
                
                listaCaja.add(oe);
        }

        } catch (SQLException e)
        {
          e.printStackTrace();
        }
        return listaCaja;


}
    

    
    
    
    
    public VistaCaja FiltarCajaFechaIngreso(String c)
     {
        Conexion con=new Conexion();
        VistaCaja oe=null;
        Statement stmt;
     
        try {
             Connection connection= con.ObtenerConexion();
             Statement statement=connection.createStatement();
             ResultSet resultado = statement.executeQuery("select * from vista_caja"+c);
            while (resultado.next()){
               
            	  oe.setIdcaja(resultado.getInt(1));
                  oe.setFecha((resultado.getDate(2))); 
                  oe.setHora(resultado.getTime(3));
                  oe.setReferencia(resultado.getString(4));
                  oe.setNumerofactura(resultado.getString(5));
                  oe.setValor(resultado.getDouble(6));
	                
            }
        } catch (SQLException ex) 
        {
               ex.printStackTrace();
        }
        return oe;
    }


    public double getSaldoDeCaja() throws SQLException {
		
		Conexion con = new Conexion();
        Connection connection= con.ObtenerConexion();
        double total =0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado=statement.executeQuery(LLAMAR_SALDO_DE_CAJA);//susceptible de sql injection
            while(resultado.next())
            {
   
            	
               total = (resultado.getDouble(1));
                
        }

        } catch (SQLException e)
        {
          e.printStackTrace();
        }
        return total;
		
		
	}
}
