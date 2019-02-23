package controladorVistaFacturacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexion.AbstractReports;
import controladorBodega.ControladorBodegaDAO;
import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Cliente;
import modeloEntidades.Detallefactura;
import modeloEntidades.Factura;
import modeloEntidades.VistaBodega;
import modeloEntidades.VistaDetallefactura;
import pruebas.Iniciador;
import vista.Menu_1;

public class ControladorVentanaNuevaFactura extends MouseAdapter implements ActionListener {

	private Menu_1 vista;

	public ControladorVentanaNuevaFactura(Menu_1 vista) {
		
		super();
		this.vista = vista;
		ControladorListasYArboles.actualizarArbolClientes(vista);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().txtProductoAdd
				.setText(vista.internalFrameFactura.getPanelFactura().getPanel_productos()
						.getPanelProductosTabla().tableProductos
								.getValueAt(vista.internalFrameFactura.getPanelFactura().getPanel_productos()
										.getPanelProductosTabla().tableProductos.getSelectedRow(), 1)
								.toString());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Add producto a Tabla")) {

			addProductosALaTabla();
			limpiarItem();

		} else

		if (e.getActionCommand().equals("buscar Cliente")) {
			if(!vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtCedula.getText().equals("")) {
				String cedula = null;
			
				cedula = vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtCedula.getText();
	
				Cliente cliente = getCliente(cedula);
			if(cliente != null) {
				
				vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtNombre.setText(cliente.getNombre() + " "+ cliente.getApellido());
				vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtDireccion.setText(cliente.getDireccion());
				vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtTelefono.setText(cliente.getTelefono());
				
				
			}else {
				JOptionPane.showMessageDialog(null, "Cedula no encontrada", "**Aviso**", JOptionPane.INFORMATION_MESSAGE);
			}
			}else {
				JOptionPane.showMessageDialog(null, "Ingrese el numero de cedula", "**Aviso**", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getActionCommand().equals("guardar factura")) {

			guardarFactura();
			limpiarFactura();
			

		}else 
		if (e.getActionCommand().equals("imprimir factura")) {
			
			String num= vista.internalFrameFactura.getPanelFactura().getPanelEncabezado().lblSerie.getText().substring(6, 15);

			
			ControladorBodegaDAO contDAO= new ControladorBodegaDAO();
			String nf= contDAO.getNumFact(num);
			
			if(nf.equals(num)) {
				
				guardarFactura();
				
				
				conexion.Conexion con = new conexion.Conexion();
				
				try(Connection connection = con.ObtenerConexion()){
					
					Map<String,Object> parameters= new HashMap<>();
					parameters.put("numfact", new String(nf));
					
					AbstractReports.crearReporte(connection, "src\\ReportesAlmacen\\Factura.jasper",parameters);
					AbstractReports.showViewer();			
					limpiarFactura();
	
					
				}catch (SQLException ex) {
					
					ex.printStackTrace();
						ex.getMessage();
					
					}
			}else {
				JOptionPane.showMessageDialog(null, "Factura en blanco","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
			

		}else 
		if (e.getActionCommand().equals("añadir nuevo cliente")) {
			Iniciador.controladorPrincipal.openClientesNuevoCliente();

		}
		else 
			if (e.getActionCommand().equals("imprimirDeReporte")) {
			
				generarReporte();

			}

	}
	
	
	public void limpiarFactura() {
		vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtCedula.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtNombre.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtDireccion.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtTelefono.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().txtSubTotal.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().txtIva.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().txtTotal.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().chckbxCredito.setSelected(false);
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().tableFactura
		.setModel(mostrarTablaFactura(TablasTemporales.detalleFactura));
		
	}
	
	public void limpiarItem() {
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().txtCantidadAdd.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().txtPrecioAdd.setText("");
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().txtProductoAdd.setText("");
	}
	
	
	public void generarReporte() {
		conexion.Conexion con = new conexion.Conexion();
		String numero= Iniciador.controladorPrincipal.getNum();
		
		
			try(Connection connection = con.ObtenerConexion()){
				
				
				Map<String,Object> parameters= new HashMap<>();
				parameters.put("numfact", new String(numero));
				
				AbstractReports.crearReporte(connection, "src\\ReportesAlmacen\\Factura.jasper",parameters);
				AbstractReports.showViewer();		
				Iniciador.controladorPrincipal.setNum(null);	
			}catch (SQLException ex) {		
			ex.printStackTrace();
				ex.getMessage();		
			}
		
		
		
	}

	private void guardarFactura() {

		if (!vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtCedula.getText().equals("")
				&& !vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtNombre.getText()
						.equals("")) {
			double sub = getSubTotalFactura();
			double iva = sub * 0.12;
			double tot = iva + sub;

			try {
				TablasTemporales.factura = new Factura(getNumeroFactura(),
						vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().txtCedula.getText(),
						vista.internalFrameFactura.getPanelFactura().getPanel_datosFactura().chckbxCredito.isSelected(),
						java.sql.Date.valueOf(getFecha()), iva, sub, tot);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
			cont.insertarSGBD(TablasTemporales.factura);
			TablasTemporales.detalleFactura.vaciar();

			cont.insetarDetalleFactura(TablasTemporales.lista);

			TablasTemporales.lista.clear();
			TablasTemporales.factura = null;
			JOptionPane.showMessageDialog(null, "Guardado.!", "YES", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private void addProductosALaTabla() {
		
		if (!vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().txtCantidadAdd
				.getText().equals("")
				&& !vista.internalFrameFactura.getPanelFactura().getPanel_productos()
						.getPanelProductosTabla().txtPrecioAdd.getText().equals("")
				&& !vista.internalFrameFactura.getPanelFactura().getPanel_productos()
						.getPanelProductosTabla().txtProductoAdd.getText().equals("")) {

			String nfactura = "";
			double precioUnitario = 0;
			double precioTotal = 0;
			double subt = 0;
			double iva = 0;
			int cantidad = 0;
			int idProducto = 0;
			String nombre = "";

			cantidad = Integer.parseInt(vista.internalFrameFactura.getPanelFactura().getPanel_productos()
					.getPanelProductosTabla().txtCantidadAdd.getText());
			idProducto = Integer
					.parseInt(
							vista.internalFrameFactura.getPanelFactura().getPanel_productos()
									.getPanelProductosTabla().tableProductos
											.getValueAt(
													vista.internalFrameFactura.getPanelFactura().getPanel_productos()
															.getPanelProductosTabla().tableProductos.getSelectedRow(),
													0)
											.toString());
			nombre = vista.internalFrameFactura.getPanelFactura().getPanel_productos()
					.getPanelProductosTabla().tableProductos
							.getValueAt(vista.internalFrameFactura.getPanelFactura().getPanel_productos()
									.getPanelProductosTabla().tableProductos.getSelectedRow(), 1)
							.toString();

			try {
				nfactura = getNumeroFactura();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			precioUnitario = Double.parseDouble(vista.internalFrameFactura.getPanelFactura().getPanel_productos()
					.getPanelProductosTabla().txtPrecioAdd.getText());
			precioTotal = precioUnitario * cantidad;

			VistaDetallefactura vistas = new VistaDetallefactura(cantidad, idProducto, nombre, nfactura, precioTotal,
					precioUnitario);

			Detallefactura detalles = new Detallefactura(cantidad, idProducto, nfactura, precioTotal, precioUnitario);

			TablasTemporales.agregarAFactura(detalles, vistas);
			subt = getSubTotalFactura();
			vista.internalFrameFactura.getPanelFactura().getPanel_productos().txtSubTotal.setText(String.valueOf(subt));
			iva = (subt * 0.12);
			vista.internalFrameFactura.getPanelFactura().getPanel_productos().txtIva
					.setText(String.valueOf(String.format("%.3f", iva)));

			vista.internalFrameFactura.getPanelFactura().getPanel_productos().txtTotal
					.setText(String.valueOf(String.format("%.3f", (iva + subt))));

			vista.internalFrameFactura.getPanelFactura().getPanel_productos().tableFactura
					.setModel(mostrarTablaFactura(TablasTemporales.detalleFactura));

		} else {

			JOptionPane.showMessageDialog(null, "Faltan completar campos (productos-precio-cantidad)", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}

	public DefaultTableModel mostrarTablaFactura(ArbolAVL<VistaDetallefactura> arbol) {
		String[] titulos = { "Cantidad", "Producto", "Precio", "Total" };
		String[] registro = new String[4];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaDetallefactura oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getCantidad());
			registro[1] = oe.getNombre();
			registro[2] = String.valueOf(oe.getPreciounitario());
			registro[3] = String.valueOf(oe.getPreciototal());

			modelo.addRow(registro);
			
		}
		return modelo;
	}

	public DefaultTableModel mostrarTablaBodega(ArbolAVL<VistaBodega> arbol) {
		String[] titulos = { "Codigo", "Producto", "Costo", "Stock" };
		String[] registro = new String[4];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaBodega oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getIdproducto());
			registro[1] = oe.getNombre();
			registro[2] = String.valueOf(oe.getCosto());
			registro[3] = String.valueOf(oe.getStock());

			modelo.addRow(registro);

		}
		return modelo;
	}
	
	public Cliente getCliente(String cedula) {
		ArbolAVL<Cliente> arbol = ControladorListasYArboles.arboVistaClientes;
		for(int i=0; i< arbol.size();i++) {
			if(arbol.getPreOrden(i).getCedula().equals(cedula)) {
				return arbol.getPreOrden(i);
			}
			
		}
		
		return null;	
	}

	public String getFecha() {
		return String.valueOf(LocalDate.now());
	}

	public String getNumeroFactura() throws SQLException {
		return vista.internalFrameFactura.getPanelFactura().getPanelEncabezado().lblSerie.getText().substring(6);
	}

	public double getSubTotalFactura() {
		double x = 0;

		for (int i = 0; i < TablasTemporales.detalleFactura.size(); i++) {
			x += TablasTemporales.detalleFactura.getPreOrden(i).getPreciototal();
		}

		return x;
	}

}
