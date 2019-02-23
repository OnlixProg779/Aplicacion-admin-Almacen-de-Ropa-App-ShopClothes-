package vistaReportes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaReportes.ControladorVentanaReportesVerVentas;

public class JInternalFrameReportesVerVentas extends JInternalFrame {

	
	public JInternalFrameReportesVerVentas(ControladorVentanaReportesVerVentas controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("REPORTE DE VENTAS");
	}

}
