package controladorCuentas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.VistaCuentasPorCobrar;

public class ControladorCuentasCuentasPorCobrarDAO {

private static final String LISTAR = "Select * from vista_cuentas_por_cobrar";
    
    public ArbolAVL<VistaCuentasPorCobrar> listarCuentasPorCobrar() throws SQLException
    { 
        Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        ArbolAVL<VistaCuentasPorCobrar> listaVistaCuentasPorCobrar= new ArbolAVL<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR);//susceptible de sql injection
            while(resultado.next())
            {
            	VistaCuentasPorCobrar oe= new VistaCuentasPorCobrar(); 
                oe.setIdcuentaPorCobrar(resultado.getInt(1));
                oe.setNumerofactura(resultado.getString(2));
                oe.setClienteCedula(resultado.getString(3));
                oe.setValorpendiente(resultado.getDouble(4));
                oe.setDescripcion(resultado.getString(5));
                oe.setEstado(resultado.getString(6));
                listaVistaCuentasPorCobrar.add(oe);
        }

        } catch (SQLException e)
        {
            e.printStackTrace(); 
        }
        return listaVistaCuentasPorCobrar;
}
    
    public DefaultTableModel mostrarTablaVistaCheques(ArbolAVL<VistaCuentasPorCobrar> arbol)
    {
        String [] titulos = {"IdCuentaPorCobrar","Num.Factura","Cedula Cliente","Valor Pendiente","Descripcion","Estado"};
        String[] registro = new String[6]; 
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        for(VistaCuentasPorCobrar oe : arbol.inOrdenList())
        {
            registro[0]= String.valueOf(oe.getIdcuentaPorCobrar());
            registro[1]= oe.getNumerofactura();
            registro[2]= oe.getClienteCedula();
            registro[3]= String.valueOf(oe.getValorpendiente());
            registro[4]= oe.getDescripcion();
            registro[5]= oe.getEstado();
            modelo.addRow(registro);         
        }
     return modelo;
}
    
    public boolean actualizarCuenta(int idcuenta) throws SQLException {
    	boolean op=false;
    	Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        PreparedStatement statement=connection.prepareStatement("update cuenta_por_cobrar set estado='PAGADO' where idcuenta_por_cobrar= '"+idcuenta+"'");
        int n =statement.executeUpdate();
        if(n!=0)
        {
            op = true;
        }
		return op;
    }
    
    public VistaCuentasPorCobrar getCuentaPorCobrar(String numFact) throws SQLException
    { 
        Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        VistaCuentasPorCobrar oe = null;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR + " where numerofactura = '"+ numFact +"'");//susceptible de sql injection
            while(resultado.next())
            {
            	oe= new VistaCuentasPorCobrar(); 
               
            	oe.setIdcuentaPorCobrar(resultado.getInt(1));
                oe.setNumerofactura(resultado.getString(2));
                oe.setClienteCedula(resultado.getString(3));
                oe.setValorpendiente(resultado.getDouble(4));
                oe.setDescripcion(resultado.getString(5));
                oe.setEstado(resultado.getString(6));
              
        }

        } catch (SQLException e)
        {
            e.printStackTrace(); 
        }
        return oe;
}
    
    public ArbolAVL<VistaCuentasPorCobrar> listarCuentasPorCobrarDeCliente(String cedulaCliente) throws SQLException
    { 
        Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        ArbolAVL<VistaCuentasPorCobrar> listaVistaCuentasPorCobrar= new ArbolAVL<>();
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultado=statement.executeQuery(LISTAR + " where cliente_cedula = '"+ cedulaCliente +"'");  //susceptible de sql injection
            
            while(resultado.next())
            {
            	VistaCuentasPorCobrar oe= new VistaCuentasPorCobrar(); 
                oe.setIdcuentaPorCobrar(resultado.getInt(1));
                oe.setNumerofactura(resultado.getString(2));
                oe.setClienteCedula(resultado.getString(3));
                oe.setValorpendiente(resultado.getDouble(4));
                oe.setDescripcion(resultado.getString(5));
                oe.setEstado(resultado.getString(6));
                listaVistaCuentasPorCobrar.add(oe);
        }

        } catch (SQLException e)
        {
            e.printStackTrace(); 
        }
        return listaVistaCuentasPorCobrar;
}
    
}
