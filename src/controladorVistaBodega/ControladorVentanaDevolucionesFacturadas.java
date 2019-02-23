package controladorVistaBodega;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.ControladorReportesFactura;
import controladorFacturacion.TablasTemporales;
import controladorVistaFacturacion.ControladorVentanaNuevaFactura;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Detallefactura;
import modeloEntidades.DevolucionDeCliente;
import modeloEntidades.VistaDetallefactura;
import modeloEntidades.VistaDevolucionDeCliente;
import modeloEntidades.VistaFactura;
import vista.Menu_1;
import vistaFacturacion.Panel_Factura;

public class ControladorVentanaDevolucionesFacturadas extends MouseAdapter implements ActionListener {

	private Menu_1 vista;

	public ControladorVentanaDevolucionesFacturadas(Menu_1 vista) {
		super();
		this.vista = vista;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblProducto
				.setText(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura()
						.getValueAt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura().getSelectedRow(), 2)
						.toString());

		vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblPrecio
		.setText(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura()
				.getValueAt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura().getSelectedRow(), 3)
				.toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("add a tabla devolucion")) {
			
			addProductosAdevolucion();

		} else if (e.getActionCommand().equals("buscar numero de factura")) {

			buscarFactura();
			
		} else if (e.getActionCommand().equals("disminuir cantidad")) {
			System.out.println("disminuir cantidad");

		} else if (e.getActionCommand().equals("guardar devolucion")) {
			
			String fact= vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtNumeroFactura.getText();
			
			if(!fact.equals("")) {
			ControladorCRUD_DAO.insetarDevolucionDeFacura(TablasTemporales.listaDevolucionFactura);
			TablasTemporales.listaDevolucionFactura = null;
			TablasTemporales.detalleDevolucionFactura = null;
			JOptionPane.showMessageDialog(null, "Guardado.!");
			}else {
				JOptionPane.showMessageDialog(null, "Ingrese Nº Factura", "*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		} else if (e.getActionCommand().equals("Eliminar Item")) {

			System.out.println("Eliminar Item");
		}
	}
	
	private void addProductosAdevolucion() {
		
		
		
		
		
		if (!vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblProducto
				.getText().equals("-")
				&& !vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblPrecio.getText().equals("-")
				&& !vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtCantidad.getText().equals("")
				&& !vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtRazon.getText().equals("")) {

			Integer cantidad; 
			String descripcion; 
			Date fecha; 
			Integer idproducto; 
			String nombre;
			String numerofactura;

			
			cantidad = Integer.parseInt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtCantidad.getText());
			
			if(cantidad <= Integer.parseInt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura()
						.getValueAt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura().getSelectedRow(), 1)
						.toString())) {
			
			idproducto = Integer
					.parseInt(
							vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura()
							.getValueAt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura().getSelectedRow(), 0)
							.toString());
			
			nombre = vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura()
					.getValueAt(vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura().getSelectedRow(), 2)
					.toString();

			numerofactura = vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtNumeroFactura.getText();

			descripcion = vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtRazon.getText();
			
			fecha = TablasTemporales.asUtilDate(LocalDate.now(),ZoneId.systemDefault());
			
			VistaDevolucionDeCliente vistaDevolucion = new VistaDevolucionDeCliente(cantidad, descripcion, fecha, idproducto, nombre, numerofactura);
			DevolucionDeCliente devolucion = new DevolucionDeCliente(cantidad, descripcion, fecha, idproducto, numerofactura);

			TablasTemporales.agregarADevolucionDeFactura(devolucion, vistaDevolucion);
			
			
		
			vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_Devolucion().setModel(mostrarTablaDevolucion(TablasTemporales.detalleDevolucionFactura));
			
			}else {
				JOptionPane.showMessageDialog(null, "cantidad Devolucion es menor a los productos facturados", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}

		} else {

			JOptionPane.showMessageDialog(null, "Faltan completar campos (productos-precio-cantidad-razon)", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	

	private void buscarFactura() {
		ControladorListasYArboles.actualizarArbolFacturas(vista);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 0));
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		JTable table = new JTable(mostrarFacturas(ControladorListasYArboles.arboVistaFacturas));

		scrollPane.setViewportView(table);
		while (true) {
			int x = JOptionPane.showConfirmDialog(null, panel, "Lista De Faturas", JOptionPane.OK_CANCEL_OPTION);

			if (x == JOptionPane.OK_OPTION && table.getSelectedRow() >= 0) {

				ControladorReportesFactura.buscarFactura(table.getValueAt(table.getSelectedRow(), 0).toString());
				ArbolAVL<VistaDetallefactura> detalleFactura = ControladorReportesFactura.cargarItemsFacturadosArbol();
				VistaFactura vistaFactura = ControladorReportesFactura.cargarFactura();

				vista.internalFrameBodegaDevolucionesDeClientes.getPanel().txtNumeroFactura.setText(vistaFactura.getNumerofactura());
				vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblFechaFactura.setText(vistaFactura.getFecha().toString());
				vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblRuc.setText(vistaFactura.getClienteCedula());
				vista.internalFrameBodegaDevolucionesDeClientes.getPanel().lblNombre.setText(vistaFactura.getNombre()+" "+vistaFactura.getApellido());
			
				vista.internalFrameBodegaDevolucionesDeClientes.getPanel().getTable_ProductosDeFactura().setModel(mostrarTablaFactura(detalleFactura));
				
				return;

			} else if (x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION) {
				return;
			} else {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna factura");
			}
		}
	}

	public DefaultTableModel mostrarTablaFactura(ArbolAVL<VistaDetallefactura> arbol) {
		String[] titulos = { "Id","Cantidad", "Producto", "Precio", "Total" };
		String[] registro = new String[5];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaDetallefactura oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getIdproducto());
			registro[1] = String.valueOf(oe.getCantidad());
			registro[2] = oe.getNombre();
			registro[3] = String.valueOf(oe.getPreciounitario());
			registro[4] = String.valueOf(oe.getPreciototal());

			modelo.addRow(registro);
			
		}
		return modelo;
	}
	
	public DefaultTableModel mostrarTablaDevolucion(ArbolAVL<VistaDevolucionDeCliente> arbol) {
		String[] titulos = { "Cantidad", "Producto", "Descripcion" };
		String[] registro = new String[3];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaDevolucionDeCliente oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getCantidad());
			registro[1] = oe.getNombre();
			registro[2] = oe.getDescripcion();
		

			modelo.addRow(registro);
			
		}
		return modelo;
	}
	
	public DefaultTableModel mostrarFacturas(ArbolAVL<VistaFactura> facturas) {
		String[] titulos = { "Numero Factura", "Cliente", "Fecha" };
		String[] registro = new String[3];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);

		for (VistaFactura oe : facturas.inOrdenList()) {
			registro[0] = oe.getNumerofactura();
			registro[1] = oe.getNombre() + " " + oe.getApellido();
			registro[2] = oe.getFecha().toString();

			modelo.addRow(registro);

		}
		return modelo;
	}

}
