package vistaReportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import controladorVistaReportes.ControladorVentanaReportesCuentasCobradas;
import vistaCuentas.PanelCxC;

public class JInternalFrameReportesCuentasCobradas extends JInternalFrame {
	private PanelCxC panel;

	
	
	public PanelCxC getPanel() {
		return panel;
	}


	
	public JInternalFrameReportesCuentasCobradas(ControladorVentanaReportesCuentasCobradas controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("REPORTE DE CUENTAS COBRADAS");
		
		panel = new PanelCxC();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.getPanel_1().setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cuentas Cobradas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel.getLblTitulo().setText("Cuentas Cobradas");
		panel.getPanel().setVisible(false);
		panel.getPanel_4().setVisible(false);
		
		panel.setControlador(controlador);
	}

}
