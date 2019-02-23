package controladorReportes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.VistaCuentasPorCobrar;

public class ControladorReportesCuentasCobradasDAO {
private static final String LISTAR = "Select * from vista_cuentas_cobradas";
    
    public ArbolAVL<VistaCuentasPorCobrar> listarCuentasCobradas() throws SQLException
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
    
    public DefaultTableModel mostrarTablaVistaCuentasCobradas(ArbolAVL<VistaCuentasPorCobrar> arbol)
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
}
