package controladorFacturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Detallefactura;
import modeloEntidades.VistaDetallefactura;
import modeloEntidades.VistaFactura;

public class ControladorFacturarDAO {
	

	private static final String ULTIMA_FACTURA = "Select count(numerofactura) from factura";
	  private static final String LISTAR_DETALLE = "Select * from vista_detallefactura";
	    private static final String EXTRAER_FACTURA = "Select * from vista_factura";
	
    public int num_factura() throws SQLException
    { 
        Conexion con = new Conexion();
        Connection connection= con.ObtenerConexion();
      int x =0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado=statement.executeQuery(ULTIMA_FACTURA);//susceptible de sql injection
            while(resultado.next())
            { 
   
            	
                x= (resultado.getInt(1));
            }

        } catch (SQLException e)
        {
          e.printStackTrace();
        }
        return x;


}
    
  
        public ArbolAVL<VistaDetallefactura> listarDetalleFactura(String numFact) throws SQLException
        { 
            Conexion con = new Conexion();
            Connection connection= con.ObtenerConexion();
            ArbolAVL<VistaDetallefactura> listaDetallefactuara= new ArbolAVL<>();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultado=statement.executeQuery(LISTAR_DETALLE + " where numerofactura = '"+numFact+"'");//susceptible de sql injection
                while(resultado.next())
                {
                	VistaDetallefactura oe= new VistaDetallefactura(); 
       
                	oe.setNumerofactura(resultado.getString(1));
                	 oe.setIdproducto(resultado.getInt(2));
                	 oe.setNombre(resultado.getString(3));
                	 oe.setCantidad(resultado.getInt(4));
                	 oe.setPreciounitario(resultado.getDouble(5));
                    oe.setPreciototal(resultado.getDouble(6));
                   
                    
                    listaDetallefactuara.add(oe);
                  
            }

            } catch (SQLException e)
            {
              e.printStackTrace();
            }
           
            return listaDetallefactuara;

    }
    
        
        public VistaFactura getFactura(String numFact) throws SQLException
        { 
            Conexion con = new Conexion();
            Connection connection= con.ObtenerConexion();
            VistaFactura oe= null;
            try {
                Statement statement = connection.createStatement();
              
                ResultSet resultado=statement.executeQuery(EXTRAER_FACTURA + " where numerofactura = '"+numFact+"'");//susceptible de sql injection
                while(resultado.next())
                {
                	 oe= new VistaFactura(); 
       
                     
	                 	oe.setNumerofactura(resultado.getString(1));
	                 	oe.setClienteCedula(resultado.getString(2));
	                 	oe.setNombre(resultado.getString(3));
	                 	oe.setApellido(resultado.getString(4));
	                 	oe.setDireccion(resultado.getString(5));
	                 	oe.setTelefono(resultado.getString(6));
	                 	
	                 			
	                 	oe.setFecha(resultado.getDate(7));
	                 	
	                 	
	                 	oe.setSubtotal(resultado.getDouble(8));
	                 	oe.setIva(resultado.getDouble(9));
	                 	oe.setTotal(resultado.getDouble(10));
	                 	oe.setCredito(resultado.getBoolean(11));
                    
               
                  
            }

            } catch (SQLException e)
            {
              e.printStackTrace();
            }
           
            return oe;

    }


		public ArbolAVL<VistaFactura> listarFacturas() throws SQLException {
			
			 Conexion con = new Conexion();
	            Connection connection= con.ObtenerConexion();
	            ArbolAVL<VistaFactura> listaDetallefactuara= new ArbolAVL<>();
	            try {
	                Statement statement = connection.createStatement();
	                ResultSet resultado=statement.executeQuery(EXTRAER_FACTURA);//susceptible de sql injection
	                while(resultado.next())
	                {
	                	VistaFactura oe= new VistaFactura(); 
	                     
	                 	oe.setNumerofactura(resultado.getString(1));
	                 	oe.setClienteCedula(resultado.getString(2));
	                 	oe.setNombre(resultado.getString(3));
	                 	oe.setApellido(resultado.getString(4));
	                 	oe.setDireccion(resultado.getString(5));
	                 	oe.setTelefono(resultado.getString(6));
	                 	
	                 			
	                 	oe.setFecha(resultado.getDate(7));
	                 	
	                 	
	                 	oe.setSubtotal(resultado.getDouble(8));
	                 	oe.setIva(resultado.getDouble(9));
	                 	oe.setTotal(resultado.getDouble(10));
	                 	oe.setCredito(resultado.getBoolean(11));
	                 	
	                    listaDetallefactuara.add(oe);
	                  
	            }

	            } catch (SQLException e)
	            {
	              e.printStackTrace();
	            }
	           
	            return listaDetallefactuara;
	    
		}
    
    

}
