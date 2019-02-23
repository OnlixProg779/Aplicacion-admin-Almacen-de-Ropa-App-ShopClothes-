package conexion;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;

public abstract class AbstractReports {

	
	private static JasperReport report;
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;
	
	public static void crearReporte(Connection con, String path) {
		
		try {
			report = (JasperReport)JRLoader.loadObjectFromFile(path);
			reportFilled = JasperFillManager.fillReport(report, null, con);
		
		
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Map<String, Object> parameters = new HashMap();
	 * parameters.put("Nombre de parametro en jasper", valor del parametro aplicando el respectivo casteo o conversion);
	 * @param con
	 * @param path
	 * @param parameters
	 */
	public static void crearReporte(Connection con, String path, Map parameters) {
		
		try {
			report = (JasperReport)JRLoader.loadObjectFromFile(path);
			reportFilled = JasperFillManager.fillReport(report, parameters, con);
		
		
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public static void showViewer() {
		
		viewer = new JasperViewer(reportFilled, false);
		viewer.setVisible(true);
		
		
	}
	
	
	public static void exportarPDF(String destino) {
		
		try {
			JasperExportManager.exportReportToPdfFile(reportFilled, destino);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
