package controladorVistaClientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controladorCRUD.ControladorCRUD_DAO;
import modeloEntidades.Cliente;
import pruebas.Iniciador;
import vista.Menu_1;

public class ControladorVentanaClientesNuevoCliente implements ActionListener {

	private Menu_1 vista;

	public ControladorVentanaClientesNuevoCliente(Menu_1 vista) {
		super();
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Guardar Cliente")) {

			guardarCliente();

			//controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolClientes(vista);
			//Iniciador.controladorPrincipal.controladorVentanaClientesVer.actualizarTabla();
			
			vista.frameClientesNuevoCliente.dispose();

		} else if (e.getActionCommand().equals("Cancelar")) {

			vista.frameClientesNuevoCliente.dispose();

		} else if (e.getActionCommand().equals("")) {
			System.out.println();

		} else if (e.getActionCommand().equals("")) {

			System.out.println();
		} else if (e.getActionCommand().equals("")) {

			System.out.println();
		}
	}

	public void guardarCliente() {
		String cedula = vista.frameClientesNuevoCliente.txtRuc.getText();
		String nombre = vista.frameClientesNuevoCliente.txtNombre.getText();
		String apellido = vista.frameClientesNuevoCliente.txtApellido.getText();
		String direccion = vista.frameClientesNuevoCliente.txtDireccion.getText();
		String telefono = vista.frameClientesNuevoCliente.txtTelefono.getText();

		if (!cedula.equals("") && !nombre.equals("") && !apellido.equals("") && !direccion.equals("")
				&& !telefono.equals("")) {

			Cliente cliente = new Cliente(cedula, nombre, apellido, direccion, telefono);
			ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
			cont.insertarSGBD(cliente);
			JOptionPane.showMessageDialog(null, "Se ha registrado con éxito!", "* CONFIRMACION *",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, "Llenar todos los campos", "* AVISO *", JOptionPane.WARNING_MESSAGE);
		}
	}

}
