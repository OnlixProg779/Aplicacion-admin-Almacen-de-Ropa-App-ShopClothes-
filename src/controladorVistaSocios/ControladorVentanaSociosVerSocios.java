package controladorVistaSocios;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorSocios.ControladorSociosVerSociosDAO;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Cliente;
import modeloEntidades.Socio;
import modeloEntidades.Telefono;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaSocios.JFrameSociosNuevoSocio;

public class ControladorVentanaSociosVerSocios extends MouseAdapter implements ActionListener, KeyListener {

	private Menu_1 vista;
	private String rol;

	public ControladorVentanaSociosVerSocios(Menu_1 vista, String rol) {
		super();
		this.vista = vista;
		this.rol = rol;
	}

	public void mousePressed(MouseEvent mouse) {
		// this.controlador =ControladorListasYArboles.controladorSociosVerSociosDAO;

		vista.internalFrameSociosVerSocios.getPanel().JlistTelefonos
				.setModel(ControladorListasYArboles.controladorSociosVerSociosDAO
						.jListarNumeros(Integer.parseInt(vista.internalFrameSociosVerSocios.getPanel().table
								.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 0)
								.toString())));

		if (mouse.getClickCount() == 2) {
			vista.internalFrameSociosVerSocios.getPanel().txtNombre
					.setText(vista.internalFrameSociosVerSocios.getPanel().table
							.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 2)
							.toString());

			vista.internalFrameSociosVerSocios.getPanel().txtRuc
					.setText(vista.internalFrameSociosVerSocios.getPanel().table
							.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 1)
							.toString());

			vista.internalFrameSociosVerSocios.getPanel().txtReferencia
					.setText(vista.internalFrameSociosVerSocios.getPanel().table
							.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 3)
							.toString());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Eliminar Socio") && rol.equals("ADMINISTRADOR")) {

			eliminarSocio();
			controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolSocios(vista);
			Iniciador.controladorPrincipal.controladorVentanaSociosVerSocios.actualizarTabla();

		} else if (e.getActionCommand().equals("Modificar Socio") && rol.equals("ADMINISTRADOR")) {

			if (!vista.internalFrameSociosVerSocios.getPanel().txtNombre.getText().equals("")
					&& !vista.internalFrameSociosVerSocios.getPanel().txtReferencia.getText().equals("")
					&& !vista.internalFrameSociosVerSocios.getPanel().txtRuc.getText().equals("")) {

				modificarSocio();
				controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolSocios(vista);
				Iniciador.controladorPrincipal.controladorVentanaSociosVerSocios.actualizarTabla();

				limpiar();
			}
		} else if (e.getActionCommand().equals("Nuevo Socio") && rol.equals("ADMINISTRADOR")) {
			Iniciador.controladorPrincipal.controladorVentanaSociosNuevoSocio = new ControladorVentanaSociosNuevoSocio(vista);
			vista.frameSociosNuevoSocio = new JFrameSociosNuevoSocio(Iniciador.controladorPrincipal.controladorVentanaSociosNuevoSocio);
			vista.frameSociosNuevoSocio.setVisible(true);


		} else if (e.getActionCommand().equals("nuevo telefono") && rol.equals("ADMINISTRADOR")) {

			
			System.out.println("nuevo telefono");
			if(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow()>=0) {
				
			
			JPanel panel = new JPanel();
			JTextField txtTelefono = new JTextField();
			JLabel tel = new JLabel("Telefono: ");
			JTextField txtReferencia = new JTextField();
			JLabel ref = new JLabel("Referencia: ");
			panel.setLayout(new GridLayout(5,5));
			panel.add(tel);
			panel.add(txtTelefono);
			panel.add(ref);
			panel.add(txtReferencia);
			
			int x = JOptionPane.showConfirmDialog(null, panel,"Ingrese Telefono",JOptionPane.OK_CANCEL_OPTION);
			if(x== JOptionPane.OK_OPTION) {
				if(!txtTelefono.getText().equals("")) {
					ControladorCRUD_DAO con = new ControladorCRUD_DAO();
					Telefono telef =new Telefono(Integer.parseInt(vista.internalFrameSociosVerSocios.getPanel().table.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 0).toString()), txtReferencia.getText(), txtTelefono.getText()); 
					if(con.insetarTelefonoDAO(telef)) {
						JOptionPane.showMessageDialog(null, "Agregado");
						try {

							DefaultTableModel mod;
							ArbolAVL<Socio> arbol = ControladorListasYArboles.controladorSociosVerSociosDAO.listarSociosEnArbol();
							mod = mostrarTablaDeSocios(arbol);
							vista.internalFrameSociosVerSocios.getPanel().table.setModel(mod);

							vista.internalFrameSociosVerSocios.getPanel().JlistTelefonos
									.setModel(ControladorListasYArboles.controladorSociosVerSociosDAO.jListarNumeros(1));

						} catch (SQLException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
				}
			}else if (x == JOptionPane.CLOSED_OPTION || x==JOptionPane.CANCEL_OPTION) {
				
			}else {
				
			}
			}else {
				JOptionPane.showMessageDialog(null, "Seleccione Un item de la tabla");
			}
			
		} else if (e.getActionCommand().equals("buscar ruc")) {

			if(vista.internalFrameSociosVerSocios.getPanel().txtRuc.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Ingrese una cedula a buscar");
			}else	
			{
			String cedula = vista.internalFrameSociosVerSocios.getPanel().txtRuc.getText();
			ArbolAVL<Socio> c = new ArbolAVL<>();
			c.add(buscado(cedula));
			DefaultTableModel mod;
			mod = mostrarTablaDeSocios(c);
			vista.internalFrameSociosVerSocios.getPanel().table.setModel(mod);
			}
			

		} else if (e.getActionCommand().equals("actualizar paguina")) {

			try {

				DefaultTableModel mod;
				ArbolAVL<Socio> arbol = ControladorListasYArboles.controladorSociosVerSociosDAO.listarSociosEnArbol();
				mod = mostrarTablaDeSocios(arbol);
				vista.internalFrameSociosVerSocios.getPanel().table.setModel(mod);

				vista.internalFrameSociosVerSocios.getPanel().JlistTelefonos
						.setModel(ControladorListasYArboles.controladorSociosVerSociosDAO.jListarNumeros(1));

			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		} else if (e.getActionCommand().equals("")) {

			System.out.println();
		}
	}

	private Socio buscado(String cedula) {

		for(int i =0; i<ControladorListasYArboles.arbolSocios.size(); i++) {
			
			if(ControladorListasYArboles.arbolSocios.getPreOrden(i).getRuc().equals(cedula)) {
				return ControladorListasYArboles.arbolSocios.getPreOrden(i);
			}else {
				return null;
			}
			
		}
		return null;
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		mostrarDatosDeFiltrado(vista.internalFrameSociosVerSocios.getPanel().txtNombre.getText());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void modificarSocio() {

		int id = Integer.parseInt(vista.internalFrameSociosVerSocios.getPanel().table
				.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 0).toString());
		String ruc = vista.internalFrameSociosVerSocios.getPanel().txtRuc.getText();
		String nombre = vista.internalFrameSociosVerSocios.getPanel().txtNombre.getText();
		String referencia = vista.internalFrameSociosVerSocios.getPanel().txtReferencia.getText();

		if (!ruc.equals("") && !nombre.equals("") && !referencia.equals("")) {

			Socio socio = new Socio(id, nombre, referencia, ruc);

			ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
			cont.modificarSGBD(socio);
			JOptionPane.showMessageDialog(null, "Datos de Socio modificados!", "* CONFIRMACION *",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, "Llenar todos los campos", "* AVISO *", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void limpiar() {
		vista.internalFrameSociosVerSocios.getPanel().txtRuc.setText("");
		vista.internalFrameSociosVerSocios.getPanel().txtNombre.setText("");
		vista.internalFrameSociosVerSocios.getPanel().txtReferencia.setText("");

	}

	public void eliminarSocio() {
		int id = Integer.parseInt(vista.internalFrameSociosVerSocios.getPanel().table
				.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 0).toString());
		String ruc = String.valueOf(vista.internalFrameSociosVerSocios.getPanel().table
				.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 1));
		String socion = String.valueOf(vista.internalFrameSociosVerSocios.getPanel().table
				.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 2));
		String referencia = String.valueOf(vista.internalFrameSociosVerSocios.getPanel().table
				.getValueAt(vista.internalFrameSociosVerSocios.getPanel().table.getSelectedRow(), 3));

		Socio socio = new Socio(id, socion, referencia, ruc);
		int i = JOptionPane.showConfirmDialog(null,
				"Eliminar:" + "\n" + "Ruc: " + ruc + "Nombre: " + socion + "Referencia: " + referencia);
		if (i == JOptionPane.OK_OPTION) {

			ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
			cont.eliminarSGBD(socio);
			JOptionPane.showMessageDialog(null, "El Socio " + socio + "Ha sido eliminado con éxito!", "* MENSAJE *",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void mostrarDatosDeFiltrado(String buscar) {
		try {
			DefaultTableModel mod;
			// List<Estudiante> Lista = Oest.ListarEstudiante();
			ArbolAVL<Socio> arbolSocios = ControladorListasYArboles.controladorSociosVerSociosDAO
					.filtrarSocioNombre(buscar);
			mod = mostrarTablaDeSocios(arbolSocios);
			vista.internalFrameSociosVerSocios.getPanel().table.setModel(mod);

			vista.internalFrameSociosVerSocios.getPanel().JlistTelefonos.setModel(new DefaultListModel<String>());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultTableModel mostrarTablaDeSocios(ArbolAVL<Socio> arbolSocios) {
		String[] titulos = { "Id", "Ruc/CI", "Socio", "Referencia" };
		String[] registro = new String[4];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (Socio oe : arbolSocios.inOrdenList()) {
			registro[0] = String.valueOf(oe.getIdsocio());
			registro[1] = oe.getRuc();
			registro[2] = oe.getNombre();
			registro[3] = oe.getReferencia();

			modelo.addRow(registro);

		}
		return modelo;
	}

	
	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = mostrarTablaDeSocios(ControladorListasYArboles.arbolSocios);
		vista.internalFrameSociosVerSocios.getPanel().table.setModel(mod);

		vista.internalFrameSociosVerSocios.getPanel().JlistTelefonos
				.setModel(ControladorListasYArboles.controladorSociosVerSociosDAO.jListarNumeros(1));
	}

}
