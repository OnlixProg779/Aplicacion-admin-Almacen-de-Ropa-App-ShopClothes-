package controladorReportes;

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
import modeloEntidades.CuentasPorPagar;


public class ControladorReportesCuentasPagadasDAO {
private static final String LISTAR = "Select * from vista_cuentas_pagadas";
    
    public ArbolAVL<CuentasPorPagar> listarCuentasPagadas() throws SQLException
    { 
        Conexion con=new Conexion();
        Connection connection= con.ObtenerConexion();
        ArbolAVL<CuentasPorPagar> listaVistaCuentasPagadas= new ArbolAVL<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultado=statement.executeQuery(LISTAR);//susceptible de sql injection
            while(resultado.next())
            {
            	CuentasPorPagar oe= new CuentasPorPagar(); 
                oe.setIdcuentaxpagar(resultado.getInt(1));
                oe.setFecha((resultado.getDate(2)));

                oe.setValor(resultado.getDouble(3));
                oe.setDescripcion(resultado.getString(4));
                oe.setEstado(resultado.getString(5));
                listaVistaCuentasPagadas.add(oe);
        }
        } catch (SQLException e)
        {
            e.printStackTrace(); 
        }
        return listaVistaCuentasPagadas;
}
    
    public DefaultTableModel mostrarTablaVistaPagados(ArbolAVL<CuentasPorPagar> arbol)
    {
        String [] titulos = {"IdCuentaPorPagar","Fecha","Valor","Descripcion","Estado"};
        String[] registro = new String[6]; 
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        for(CuentasPorPagar oe : arbol.inOrdenList())
        {
            registro[0]= String.valueOf(oe.getIdcuentaxpagar());
            registro[1]= String.valueOf(oe.getFecha());
            registro[2]= String.valueOf(oe.getValor());
            registro[3]= oe.getDescripcion();
            registro[4]= oe.getEstado();
            modelo.addRow(registro);         
        }
     return modelo;
}
}
