package vistaClientes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import controladorVistaClientes.ControladorVentanaClientesVer;

public class JInternalFrameClientesVer extends JInternalFrame {
	private Panel_Clientes panelClientes;

	

	public Panel_Clientes getPanelClientes() {
		return panelClientes;
	}



	public JInternalFrameClientesVer(ControladorVentanaClientesVer controlador) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("CLIENTES");
		panelClientes = new Panel_Clientes();
		getContentPane().add(panelClientes, BorderLayout.CENTER);
		panelClientes.setControlador(controlador);

	}

}
