package vistaReportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import controladorVistaReportes.ControladorVentanaReportesCuentasPagadas;
import vistaCuentas.PanelCxP;

public class JInternalFrameReportesCuentasPagadas extends JInternalFrame {
	private PanelCxP panel;
	
	public PanelCxP getPanel() {
		return panel;
	}
	
	public JInternalFrameReportesCuentasPagadas(ControladorVentanaReportesCuentasPagadas controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("REPORTE DE CUENTAS PAGADAS");
		
		panel = new PanelCxP();
		panel.getLblTitulo().setText("Cuentas Pagadas");
		panel.getPanel().setVisible(false);
		panel.getPanel_4().setVisible(false);
		
		panel.getPanel_1().setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cuentas Pagadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		
		
		panel.setControlador(controlador);
	}

}
