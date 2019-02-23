package vistaReportes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaReportes.ControladorVentanaReportesVentasPorSocio;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class JInternalFrameReportesVentasPorSocio extends JInternalFrame {
	private PanelReportesVentasPorSocio panel;

	
	public PanelReportesVentasPorSocio getPanel() {
		return panel;
	}


	public JInternalFrameReportesVentasPorSocio(ControladorVentanaReportesVentasPorSocio controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("REPORTE DE VENTAS POR SOCIOS");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new PanelReportesVentasPorSocio();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.setControlador(controlador);
	}

}
