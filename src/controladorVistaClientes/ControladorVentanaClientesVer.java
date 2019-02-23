package controladorVistaClientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexion.AbstractReports;
import conexion.Conexion;
import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Cliente;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaClientes.JFrameClientesNuevoCliente;

public class ControladorVentanaClientesVer extends MouseAdapter implements ActionListener {

	private Conexion conexion;
	private Menu_1 vista;

	public ControladorVentanaClientesVer(Menu_1 vista) {
		super();
		this.vista = vista;

		// TODO Auto-generated constructor stub
	}

	public void mousePressed(MouseEvent mouse) {

		if (mouse.getClickCount() == 2) {
			int seleccion = vista.internalFrameCli.getPanelClientes().table.rowAtPoint(mouse.getPoint());

			vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtCedula
					.setText(String.valueOf(vista.internalFrameCli.getPanelClientes().table.getValueAt(seleccion, 0)));

			vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtNombre
					.setText(String.valueOf(vista.internalFrameCli.getPanelClientes().table.getValueAt(seleccion, 1)));

			vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtApellido
					.setText(String.valueOf(vista.internalFrameCli.getPanelClientes().table.getValueAt(seleccion, 2)));

			vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtDireccion
					.setText(String.valueOf(vista.internalFrameCli.getPanelClientes().table.getValueAt(seleccion, 3)));

			vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtTelefono
					.setText(String.valueOf(vista.internalFrameCli.getPanelClientes().table.getValueAt(seleccion, 4)));

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("Nuevo Cliente")) {

			vista.frameClientesNuevoCliente = new JFrameClientesNuevoCliente(
					new ControladorVentanaClientesNuevoCliente(vista));
			vista.frameClientesNuevoCliente.setVisible(true);

		} else if (e.getActionCommand().equals("Limpiar")) {

			limpiar();
		} else if (e.getActionCommand().equals("Modificar cliente")) {

			modificarCliente();
			controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolClientes(vista);
			Iniciador.controladorPrincipal.controladorVentanaClientesVer.actualizarTabla();

		} else if (e.getActionCommand().equals("Eliminar cliente")) {

			eliminarCliente();
			controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolClientes(vista);
			Iniciador.controladorPrincipal.controladorVentanaClientesVer.actualizarTabla();

		} else if (e.getActionCommand().equals("buscar Cliente")) {
			if(vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtCedula.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Ingrese una cedula a buscar");
			}else	
			{
			String cedula = vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtCedula.getText();
			ArbolAVL<Cliente> c = new ArbolAVL<>();
			c.add(buscado(cedula));
			DefaultTableModel mod;
			mod = mostrarTablaDeClientes(c);
			vista.internalFrameCli.getPanelClientes().table.setModel(mod);
			}

		} else if (e.getActionCommand().equals("agregar email al cliente")) {
			System.out.println("Agregar Email cliente");

		} else if (e.getActionCommand().equals("eliminar email del cliente")) {
			System.out.println("eliminar email cliente");

		} else if (e.getActionCommand().equals("Telefono Nuevo")) {
			System.out.println("Telefono nuevo del cliente");

		} else if (e.getActionCommand().equals("Generar Reporte")) {

			generarReporte();

		} else if (e.getActionCommand().equals("Actualizar Paguina")) {
			actualizarTabla();

		}
	}

	public void generarReporte() {
		conexion.Conexion con = new conexion.Conexion();

		try (Connection connection = con.ObtenerConexion()) {

			AbstractReports.crearReporte(connection, "src\\ReportesAlmacen\\Clientes.jasper");
			AbstractReports.showViewer();

		} catch (SQLException ex) {

			ex.printStackTrace();
			ex.getMessage();

		}

	}

	public void limpiar() {
		vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtCedula.setText("");
		vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtNombre.setText("");
		vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtApellido.setText("");
		vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtDireccion.setText("");
		vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtTelefono.setText("");
	}

	public void modificarCliente() {
		String cedula = vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtCedula.getText();
		String nombre = vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtNombre.getText();
		String apellido = vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtApellido.getText();
		String direccion = vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtDireccion.getText();
		String telefono = vista.internalFrameCli.getPanelClientes().getPanelDatosGenerales().txtTelefono.getText();

		if (!cedula.equals("") && !nombre.equals("") && !apellido.equals("") && !direccion.equals("")
				&& !telefono.equals("")) {

			Cliente cliente = new Cliente(cedula, nombre, apellido, direccion, telefono);
			ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
			cont.modificarSGBD(cliente);
			JOptionPane.showMessageDialog(null, "Datos de cliente modificados!", "* CONFIRMACION *",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, "Llenar todos los campos", "* AVISO *", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void eliminarCliente() {
		String cedula = String.valueOf(vista.internalFrameCli.getPanelClientes().table
				.getValueAt(vista.internalFrameCli.getPanelClientes().table.getSelectedRow(), 0));
		String nombre = String.valueOf(vista.internalFrameCli.getPanelClientes().table
				.getValueAt(vista.internalFrameCli.getPanelClientes().table.getSelectedRow(), 1));
		String apellido = String.valueOf(vista.internalFrameCli.getPanelClientes().table
				.getValueAt(vista.internalFrameCli.getPanelClientes().table.getSelectedRow(), 2));
		String direccion = String.valueOf(vista.internalFrameCli.getPanelClientes().table
				.getValueAt(vista.internalFrameCli.getPanelClientes().table.getSelectedRow(), 3));
		String telefono = String.valueOf(vista.internalFrameCli.getPanelClientes().table
				.getValueAt(vista.internalFrameCli.getPanelClientes().table.getSelectedRow(), 4));

		Cliente cliente = new Cliente(cedula, nombre, apellido, direccion, telefono);
		ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
		cont.eliminarSGBD(cliente);
		JOptionPane.showMessageDialog(null, "El cliente " + nombre + " " + apellido + "Ha sido eliminado con éxito!",
				"* MENSAJE *", JOptionPane.INFORMATION_MESSAGE);
	}

	public Cliente buscado (String cedula) {
		
		for(int i =0; i<ControladorListasYArboles.arboVistaClientes.size(); i++) {
			
			if(ControladorListasYArboles.arboVistaClientes.getPreOrden(i).getCedula().equals(cedula)) {
				return ControladorListasYArboles.arboVistaClientes.getPreOrden(i);
			}else {
				return null;
			}
			
		}
		return null;
		
	}
	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = mostrarTablaDeClientes(ControladorListasYArboles.arboVistaClientes);
		vista.internalFrameCli.getPanelClientes().table.setModel(mod);
	}

	public DefaultTableModel mostrarTablaDeClientes(ArbolAVL<Cliente> arbol) {
		String[] titulos = { "Cedula", "Nombre", "Apellido", "Direccion", "Teléfono" };
		String[] registro = new String[5];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);

		for (Cliente oe : arbol.inOrdenList()) {
			registro[0] = oe.getCedula();
			registro[1] = oe.getNombre();
			registro[2] = oe.getApellido();
			registro[3] = oe.getDireccion();
			registro[4] = oe.getTelefono();

			modelo.addRow(registro);

		}

		return modelo;
	}
	
	

}
