package controladorVistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import conexion.Conexion;
//import controlador.ControladorProveedores;

import vista.Menu_1;


public class ControladorVentanaProveedores implements ActionListener {

	private Conexion conexion;
	private Menu_1 vista;
	//private ControladorProveedores controladorProveedores;
/*
	private ControladorTelefonos controladorTelefonos;

	public ControladorVentanaProveedores(Menu_1 vista) {
		super();
		this.vista = vista;
		//controladorProveedores = new ControladorProveedores();
		controladorTelefonos = new ControladorTelefonos();
	}
*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("Nuevo Proveedor")) {
			System.out.println("Nuevo");

		}
		if (e.getActionCommand().equals("Agregar Proveedor")) {
			System.out.println("Agregar");
			// controladorProveedores.insertar(prov, dir, telefonos, contactos)

		}
		if (e.getActionCommand().equals("Buscar Proveedor")) {
			System.out.println("Buscar");
		

		}
		
		if (e.getActionCommand().equals("Eliminar Proveedor")) {
			System.out.println("Eliminar");

		}
		if (e.getActionCommand().equals("Modificar Proveedor")) {
			System.out.println("Modificar");

		}
		if (e.getActionCommand().equals("Telefono Nuevo")) {
			System.out.println("nuevo telefono de proveedor");
			//extraerTelefonos("Proveedor");

		}

		if (e.getActionCommand().equals("Nuevo Telefono contacto")) {
			System.out.println("nuevo telefono de contacto");
		//	extraerTelefonos("Contacto");

		}
		if (e.getActionCommand().equals("Nuevo Contacto")) {
			System.out.println("Nuevo Contacto");

		}
		if (e.getActionCommand().equals("Eliminar Numero")) {
			System.out.println("Eliminar Numero");

		}
		if (e.getActionCommand().equals("Eliminar Numero De Contacto")) {
			System.out.println("eliminar num Contacto");

		}
		if (e.getActionCommand().equals("Modificar Numero De Contacto")) {
			System.out.println("modificar Numero contacto");

		}

	}
/*
 * 

	public Socios_V extraerProveedor() {
		Panel_DatosProveedor panelProv = vista.internalFrameProv.panel.panel_datosProveedor.panelDatosProveedor;
		panelProv.txtNombre.getText();
		String ruc = panelProv.txtRuc.getText();
		String nombre = panelProv.txtNombre.getText();
		LocalDate fechaRegistro = LocalDate.now();
		String web = panelProv.txtweb.getText();
		Socios_V prov = new Socios_V(ruc, nombre, fechaRegistro, web);
		return prov;
	}

	public Direcciones extraerDireccion() {
		Panel_DireccionTelefono panelDirec = vista.internalFrameProv.panel.panel_direccionTelefono;
		String cedula_ruc = vista.internalFrameProv.panel.panel_datosProveedor.panelDatosProveedor.txtRuc.getText();
		String ciudad = panelDirec.txtCiudad.getText();
		String callePrincipal = panelDirec.txtCallePrincipal.getText();
		String numeroDeCasa = panelDirec.txtNumCasa.getText();
		String calleSecundaria = panelDirec.txtCalleSecundaria.getText();
		String referencia = panelDirec.txtReferencia.getText();
		Direcciones direcciones = new Direcciones(cedula_ruc, ciudad, callePrincipal, numeroDeCasa, calleSecundaria,
				referencia);

		return direcciones;
	}

	public void addTelefonos() {

	}

	public void extraerTelefonos(String funcion) {

		JTextField txtTelefono = new JTextField(10);
		JTextField txtReferencia = new JTextField(10);
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Teléfono"));
		myPanel.add(txtTelefono);
		myPanel.add(Box.createHorizontalStrut(15)); // indica el espacio
		myPanel.add(new JLabel("Referencia"));
		myPanel.add(txtReferencia);

		String cedula_Ruc;
		String numero;
		String referencia;

		Telefonos telef;

		while (true) {
			int resultado = JOptionPane.showConfirmDialog(null, myPanel, "Ingrese Teléfono Nuevo",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION && !txtTelefono.getText().equals("")
					&& !txtReferencia.getText().equals("")) {
				cedula_Ruc = vista.internalFrameProv.panel.panel_datosProveedor.panelDatosProveedor.txtRuc.getText();
				numero = txtTelefono.getText();
				referencia = txtReferencia.getText();

				telef = new Telefonos(cedula_Ruc, numero, referencia);

				controladorTelefonos.addTelefonoTemporal(telef, funcion);
				return;
				//
				// Cuando SELECCIONE ACEPTAR Y GUARDAR CONTACTO ESTA LISTA SE DEBE VACIAR;
				//

			} else if(resultado == JOptionPane.CANCEL_OPTION ||resultado == JOptionPane.CLOSED_OPTION){
				return;
			}else {
				JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Falta Infotmacion",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}
	
	*/

}
