package controladorFacturacion;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controladorCRUD.ControladorCRUD_DAO;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Detallefactura;
import modeloEntidades.DevolucionDeCliente;
import modeloEntidades.Factura;
import modeloEntidades.Notaentrega;
import modeloEntidades.VistaDetalleNotaentrega;
import modeloEntidades.VistaDetallefactura;
import modeloEntidades.VistaDevolucionDeCliente;

public class TablasTemporales {

	public static ArbolAVL<VistaDetallefactura> detalleFactura = new ArbolAVL<>();
	public static List<Detallefactura> lista  = new ArrayList<>();
	public static Factura factura;
	
	
	public static ArbolAVL<VistaDetalleNotaentrega> detalleNotaentregaTemporal = new ArbolAVL<>();
	public static List<modeloEntidades.NotaentregaBodega> listaNotaEntrega  = new ArrayList<>();
	public static Notaentrega notaEntrega;
	
	public static ArbolAVL<VistaDevolucionDeCliente> detalleDevolucionFactura = new ArbolAVL<>();
	public static List<DevolucionDeCliente> listaDevolucionFactura  = new ArrayList<>();
	
	
public static ArbolAVL<VistaDetalleNotaentrega> agregarANotaEntrega(modeloEntidades.NotaentregaBodega detalle, VistaDetalleNotaentrega vistaDetalle) {
		
		
		if(detalleNotaentregaTemporal.add(vistaDetalle)) {
			listaNotaEntrega.add(detalle);
		}else {
			JOptionPane.showMessageDialog(null, "Producto Repetido", "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		return null;
	}


	
	
	public static ArbolAVL<VistaDetallefactura> agregarAFactura(Detallefactura detalle, VistaDetallefactura vistaDetalle) {
		
		
		if(detalleFactura.add(vistaDetalle)) {
			lista.add(detalle);
		}else {
			JOptionPane.showMessageDialog(null, "Producto Repetido", "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		return null;
	}
	

	

	
	 public static LocalDate asLocalDate(java.util.Date date) {
	        return asLocalDate(date, ZoneId.systemDefault());
	    }
	  private static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
	        if (date == null)
	            return null;

	        if (date instanceof java.sql.Date)
	            return ((java.sql.Date) date).toLocalDate();
	        else
	            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
	    }
	  
	   public static java.util.Date asUtilDate(Object date, ZoneId zone) {
	        if (date == null)
	            return null;

	        if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp)
	            return new java.util.Date(((java.util.Date) date).getTime());
	        if (date instanceof java.util.Date)
	            return (java.util.Date) date;
	        if (date instanceof LocalDate)
	            return java.util.Date.from(((LocalDate) date).atStartOfDay(zone).toInstant());
	        if (date instanceof LocalDateTime)
	            return java.util.Date.from(((LocalDateTime) date).atZone(zone).toInstant());
	        if (date instanceof ZonedDateTime)
	            return java.util.Date.from(((ZonedDateTime) date).toInstant());
	        if (date instanceof Instant)
	            return java.util.Date.from((Instant) date);

	        throw new UnsupportedOperationException("Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
	    }




	public static void agregarADevolucionDeFactura(DevolucionDeCliente devolucion,
			VistaDevolucionDeCliente vistaDevolucion) {
		
		if(detalleDevolucionFactura.add(vistaDevolucion)) {
			listaDevolucionFactura.add(devolucion);
		}else {
			JOptionPane.showMessageDialog(null, "Producto Repetido", "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
}
