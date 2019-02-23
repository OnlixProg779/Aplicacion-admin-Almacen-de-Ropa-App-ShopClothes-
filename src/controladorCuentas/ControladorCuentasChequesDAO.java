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
import modeloEntidades.Telefono;
import modeloEntidades.VistaCheque;

public class ControladorCuentasChequesDAO {

    private static final String LISTAR = "Select * from vista_cheques";
    
    public ArbolAVL<VistaCheque> listarCheques() throws SQLException
    { 
        Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        ArbolAVL<VistaCheque> listaVistaCheques= new ArbolAVL<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR);//susceptible de sql injection
            while(resultado.next())
            {
            	VistaCheque oe= new VistaCheque(); 
            	
                oe.setIdcheque(resultado.getInt(1));
                oe.setNCheque(resultado.getInt(2));
                oe.setChequeBanco(resultado.getString(3));
                oe.setTitularCuenta(resultado.getString(4));
                oe.setFechaIngreso((resultado.getDate(5)));
                oe.setFechaDeposito((resultado.getDate(6)));
                oe.setValor(resultado.getDouble(7));
                oe.setDescripcion(resultado.getString(8));
                oe.setNFactura(resultado.getString(9));
                
            
                listaVistaCheques.add(oe);
        }

        } catch (SQLException e)
        {
            e.printStackTrace(); 
        }
        return listaVistaCheques;
}
    
   
    
    public static VistaCheque buscarCheque(int id) throws SQLException
    { 
    	Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        VistaCheque cheque = null;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR+" where idcheque = "+"'"+id+"'");//susceptible de sql injection
            while(resultado.next())
            {
            	VistaCheque oe= new VistaCheque(); 
                oe.setIdcheque(resultado.getInt(1));
                oe.setNCheque(resultado.getInt(2));
                oe.setChequeBanco(resultado.getString(3));
                oe.setTitularCuenta(resultado.getString(4));
                oe.setFechaIngreso((resultado.getDate(5)));
                oe.setFechaDeposito((resultado.getDate(6)));
                oe.setValor(resultado.getDouble(7));
                oe.setDescripcion(resultado.getString(8));
                oe.setNFactura(resultado.getString(9));
                
               cheque = oe;
        }

        } catch (SQLException e)
        {
            e.printStackTrace(); 
        }
        
        return cheque;


}
    

}
