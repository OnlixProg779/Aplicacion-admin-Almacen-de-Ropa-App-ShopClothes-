package controladorVistaBodega;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import conexion.AbstractReports;
import controladorBodega.ControladorBodegaDAO;
import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Banco;
import modeloEntidades.Categoria;
import modeloEntidades.Detallefactura;
import modeloEntidades.Factura;
import modeloEntidades.Notaentrega;
import modeloEntidades.NotaentregaBodega;
import modeloEntidades.Socio;
import modeloEntidades.VistaBodega;
import modeloEntidades.VistaDetalleNotaentrega;
import modeloEntidades.VistaDetallefactura;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaBodega.JFrameBodegaNuevoProducto;

public class ControladorVentanaNotaDeEntrega extends MouseAdapter implements ActionListener, ItemListener {

	private Menu_1 vista;
	private Socio socio;

	public ControladorVentanaNotaDeEntrega(Menu_1 vista) {
		super();
		this.vista = vista;// TODO Auto-generated constructor stub
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		this.socio = (Socio) e.getItem();

		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos
				.setModel(mostrarTablaBodega(ControladorListasYArboles.arboVistaBodega, socio));

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtProductoAdd.setText(
				vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos.getValueAt(
						vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos
								.getSelectedRow(),
						1).toString());

		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtPrecioAdd.setText(
				vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos.getValueAt(
						vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos
								.getSelectedRow(),
						2).toString());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Guardar Compra")) {
			System.out.println("Guardar Compra");

		} else if (e.getActionCommand().equals("Ingresar Nuevo Producto")) {

			openBodegaNuevoProducto();

		} else if (e.getActionCommand().equals("Add producto a Tabla")) {

			addProductosALaTabla();
			limpiarItem();

		} else if (e.getActionCommand().equals("Guardar Nota Entrega")) {
			
			ControladorBodegaDAO contbodega= new ControladorBodegaDAO();
			
			
			String num= vista.internalFrameIngresarFacturaCompra.getPanel().txtN_Nota.getText();
			
			if(!num.equals("")) {
				
				guardarNotaEntrega();
			int idnota= contbodega.getIdNotaEntrega(num);
			
			conexion.Conexion con = new conexion.Conexion();			
			try(Connection connection = con.ObtenerConexion()){
				
				Map<String,Object> parameters= new HashMap<>();
				
				parameters.put("idnotaentrega", new Integer(idnota));				
				AbstractReports.crearReporte(connection, "src\\ReportesAlmacen\\NotaEntrega.jasper",parameters);
				AbstractReports.showViewer();	
			
				limpiarNotaDeEntrega();

			}catch (SQLException ex) {				
				ex.printStackTrace();
					ex.getMessage();	
					
				} 
			}else {
				JOptionPane.showMessageDialog(null, "Nota de Entrega vacía","*** AVISO ***", JOptionPane.INFORMATION_MESSAGE);
			}

	}
		}
	
	public void limpiarNotaDeEntrega() {
		vista.internalFrameIngresarFacturaCompra.getPanel().txtN_Nota.setText("");
		vista.internalFrameIngresarFacturaCompra.getPanel().txtReceptor.setText("");
		vista.internalFrameIngresarFacturaCompra.getPanel().comboBoxSocios.setModel(actualizarItemsSocios());
		vista.internalFrameIngresarFacturaCompra.getPanel().dateChooserFecha.setDate(null);
		vista.internalFrameIngresarFacturaCompra.getPanel().table_1
		.setModel(mostrarTablaNotaEntrega(TablasTemporales.detalleNotaentregaTemporal));
	}
	
	public void limpiarItem() {
		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtCantidadAdd.setText("");
		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtPrecioAdd.setText("");
		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtProductoAdd.setText("");
		vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtProductoBusqueda.setText("");
	}
	
	

	private void guardarNotaEntrega() {
		Integer idNotaEntrega = null;
		if (vista.internalFrameIngresarFacturaCompra.getPanel().table_1.getRowCount() > 0) {

			if (!vista.internalFrameIngresarFacturaCompra.getPanel().txtN_Nota.getText().equals("")
					&& !vista.internalFrameIngresarFacturaCompra.getPanel().txtReceptor.getText().equals("")
					&& vista.internalFrameIngresarFacturaCompra.getPanel().comboBoxSocios.getSelectedIndex() > 0
					&& !vista.internalFrameIngresarFacturaCompra.getPanel().dateChooserFecha.getDate().toString()
							.equals("")) {

				try {

					idNotaEntrega = ControladorListasYArboles.controladorNotaDeEntregaDAO.num_NotaEntrega();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				TablasTemporales.notaEntrega = new Notaentrega(idNotaEntrega,
						vista.internalFrameIngresarFacturaCompra.getPanel().dateChooserFecha.getDate(),
						((Socio) vista.internalFrameIngresarFacturaCompra.getPanel().comboBoxSocios.getSelectedItem())
								.getIdsocio(),
						vista.internalFrameIngresarFacturaCompra.getPanel().txtN_Nota.getText(),
						vista.internalFrameIngresarFacturaCompra.getPanel().txtReceptor.getText());

				if (ControladorListasYArboles.controladorNotaDeEntregaDAO
						.insetarNotaEntrega(TablasTemporales.notaEntrega)) {
					ControladorListasYArboles.controladorNotaDeEntregaDAO
							.insetarDetalleNotaDeEntrega(TablasTemporales.listaNotaEntrega, idNotaEntrega);
					TablasTemporales.listaNotaEntrega.clear();
					TablasTemporales.detalleNotaentregaTemporal.vaciar();
					TablasTemporales.notaEntrega = null;
					JOptionPane.showMessageDialog(null, "Guardado.!", "YES", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "ha surgido un error al guardar", "ERROR",
							JOptionPane.INFORMATION_MESSAGE);

				}

			} else {
				JOptionPane.showMessageDialog(null, "Complete todo los campos vacios", "** AVISO **",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} else {
			JOptionPane.showMessageDialog(null, "No ha ingresado Informacion en la tabla", "** AVISO **",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void addProductosALaTabla() {

		if (!vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtCantidadAdd.getText()
				.equals("")
				&& !vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtPrecioAdd.getText()
						.equals("")
				&& !vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtProductoAdd.getText()
						.equals("")) {

			int cantidad = 0;
			double precioUnitario = 0;
			int idProducto = 0;
			String nombre = "";

			nombre = vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtProductoAdd
					.getText();

			cantidad = Integer
					.parseInt(vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtCantidadAdd
							.getText());

			idProducto = Integer.parseInt(vista.internalFrameIngresarFacturaCompra.getPanel()
					.getPanelAddProductos().tableProductos.getValueAt(
							vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos
									.getSelectedRow(),
							0).toString());

			precioUnitario = Double.parseDouble(
					vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().txtPrecioAdd.getText());

			NotaentregaBodega dealleNotaEntrega = new NotaentregaBodega(cantidad, precioUnitario, idProducto);

			VistaDetalleNotaentrega detalleMostrar = new VistaDetalleNotaentrega(cantidad, precioUnitario, null,
					idProducto, nombre);

			TablasTemporales.agregarANotaEntrega(dealleNotaEntrega, detalleMostrar);

			vista.internalFrameIngresarFacturaCompra.getPanel().table_1
					.setModel(mostrarTablaNotaEntrega(TablasTemporales.detalleNotaentregaTemporal));

		} else {

			JOptionPane.showMessageDialog(null, "Faltan completar campos (productos-precio-cantidad)", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void openBodegaNuevoProducto() {
		Iniciador.controladorPrincipal.controladorVentanaBodegaNuevoProducto = new ControladorVentanaBodegaNuevoProducto(
				vista);
		vista.frameBodegaNuevoProducto = new JFrameBodegaNuevoProducto(
				Iniciador.controladorPrincipal.controladorVentanaBodegaNuevoProducto, socio);
		vista.frameBodegaNuevoProducto.comboBoxCategoria.setModel(
				Iniciador.controladorPrincipal.controladorVentanaBodegaNuevoProducto.actualizarItemsCategoria());
		vista.frameBodegaNuevoProducto.setVisible(true);
	}

	public DefaultTableModel mostrarTablaBodega(ArbolAVL<VistaBodega> arbol, Socio socio) {

		String[] titulos = { "Codigo", "Producto", "Costo", "Stock" };
		String[] registro = new String[4];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaBodega oe : arbol.inOrdenList()) {

			if (oe.getIdsocio() == socio.getIdsocio()) {

				registro[0] = String.valueOf(oe.getIdproducto());
				registro[1] = oe.getNombre();
				registro[2] = String.valueOf(oe.getCosto());
				registro[3] = String.valueOf(oe.getStock());

				modelo.addRow(registro);
			}

		}
		return modelo;

	}

	public DefaultTableModel mostrarTablaNotaEntrega(ArbolAVL<VistaDetalleNotaentrega> arbol) {
		String[] titulos = { "Cantidad", "Producto", "Costo" };
		String[] registro = new String[4];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);

		for (VistaDetalleNotaentrega oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getCantidad());
			registro[1] = oe.getNombre();
			registro[2] = String.valueOf(oe.getCosto());

			modelo.addRow(registro);

		}
		return modelo;
	}

	public DefaultComboBoxModel<Socio> actualizarItemsSocios() {

		DefaultComboBoxModel<Socio> model = new DefaultComboBoxModel<Socio>();

		for (int i = 0; i < ControladorListasYArboles.arbolSocios.size(); i++) {
			if (i == 0) {
				model.addElement(new Socio("<<Seleccione>>", "", ""));
			}
			model.addElement(ControladorListasYArboles.arbolSocios.getPreOrden(i));

		}
		return model;

	}

}
