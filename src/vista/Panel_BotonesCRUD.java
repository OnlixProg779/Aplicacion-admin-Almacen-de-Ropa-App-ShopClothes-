package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;
import vistaProveedores.Panel_DatosProveedor;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controladorVistaPrincipal.ControladorVentanaProveedores;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_BotonesCRUD extends JPanel {
	private JButton btnNuevo;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	public Panel_DatosProveedor panelDatosProveedor;
	private JButton btnBuscar;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public Panel_BotonesCRUD() {
		setLayout(new BorderLayout(20, 0));

		panelDatosProveedor = new Panel_DatosProveedor();
		add(panelDatosProveedor, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();

		add(panel_1, BorderLayout.WEST);
		panel_1.setSize(20, 500);
		panel_1.setLayout(new GridLayout(0, 1, 5, 5));

		btnNuevo = new JButton("   Nuevo  ");
		btnNuevo.setIcon(new ImageIcon(Panel_BotonesCRUD.class.getResource("/Imagenes/vendors-new 24x24.png")));
		btnNuevo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		panel_1.add(btnNuevo);
		btnAgregar = new JButton("  Agregar ");
		btnAgregar.setIcon(new ImageIcon(Panel_BotonesCRUD.class.getResource("/Imagenes/save-add2 24x24.png")));
		btnAgregar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		panel_1.add(btnAgregar);
		btnModificar = new JButton(" Modificar ");
		btnModificar.setIcon(new ImageIcon(Panel_BotonesCRUD.class.getResource("/Imagenes/EditInformationHS.png")));
		btnModificar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		panel_1.add(btnModificar);
		btnEliminar = new JButton("  Eliminar  ");
		btnEliminar.setIcon(new ImageIcon(Panel_BotonesCRUD.class.getResource("/Imagenes/vendors-remove 24x24.png")));
		btnEliminar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		panel_1.add(btnEliminar);

		btnAgregar.setActionCommand("Agregar Proveedor");
		btnEliminar.setActionCommand("Eliminar Proveedor");
		btnModificar.setActionCommand("Modificar Proveedor");
		btnNuevo.setActionCommand("Nuevo Proveedor");

		btnBuscar = new JButton("   Buscar   ");
		btnBuscar.setIcon(new ImageIcon(Panel_BotonesCRUD.class.getResource("/Imagenes/vendors-search 24x24.png")));
		btnBuscar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnBuscar.setActionCommand("Buscar Proveedor");
		panel_1.add(btnBuscar);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setVisible(false);
		btnNewButton.setEnabled(false);
		panel_1.add(btnNewButton);

	}

	public void setControlador(ControladorVentanaProveedores controlador) {
		btnAgregar.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
		btnModificar.addActionListener(controlador);
		btnNuevo.addActionListener(controlador);
		btnBuscar.addActionListener(controlador);
	}

}
