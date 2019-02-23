package controladorVistaReportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.CuentasPorPagar;
import vista.Menu_1;

public class ControladorVentanaReportesCuentasPagadas   implements ActionListener{


	
private Menu_1 vista;
	
	public ControladorVentanaReportesCuentasPagadas(Menu_1 vista) {
		super();
		this.vista = vista;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	if(e.getActionCommand().equals("abonar a la cuenta")) {
			
			System.out.println("abonar a la cuenta");
			
		}else if(e.getActionCommand().equals("buscar en rango")) {
			
			try {
				if (!vista.internalFrameReportesCuentasPagadas.getPanel().dateChooserFechaDesde.getDate().toString().equals("")
						&& !vista.internalFrameReportesCuentasPagadas.getPanel().dateChooserFechaHasta.getDate().toString()
								.equals("")) {
	
					ArbolAVL<CuentasPorPagar> lista = buscarRangoFechas(
							java.sql.Date.valueOf(TablasTemporales.asLocalDate(
									vista.internalFrameReportesCuentasPagadas.getPanel().dateChooserFechaDesde.getDate())),
							java.sql.Date.valueOf(TablasTemporales.asLocalDate(
									vista.internalFrameReportesCuentasPagadas.getPanel().dateChooserFechaHasta.getDate())));
					
					DefaultTableModel mod = ControladorListasYArboles.controladorReportesCuentasPagadasDAO.mostrarTablaVistaPagados(lista);
					vista.internalFrameReportesCuentasPagadas.getPanel().table.setModel(mod);
	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese las fechas 'Rango de busqueda'");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}

			
			
		}else if(e.getActionCommand().equals("Pagar Cuenta")) {
			
			System.out.println("Pagar Cuenta");
			
		}else if(e.getActionCommand().equals("Nueva Cuenta")) {
			
			System.out.println();
		}else if(e.getActionCommand().equals("Actualizar")) {
			
			actualizarTabla();
		}
	}
	
	private ArbolAVL<CuentasPorPagar> buscarRangoFechas(java.util.Date fecha1, java.util.Date fecha2) {

		ArbolAVL<CuentasPorPagar> arbol = ControladorListasYArboles.arboVistaCuentasPagadas;
		ArbolAVL<CuentasPorPagar> listaResultado = new ArbolAVL<>();
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String aux = (dt.format(fecha1));
		String aux2 = (dt.format(fecha2));
		try {
			fecha1 = dt.parse(aux);
			fecha2 = dt.parse(aux2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getFecha().compareTo(fecha1) >= 0
					&& arbol.getPreOrden(i).getFecha().compareTo(fecha2) <= 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}
	
	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = ControladorListasYArboles.controladorReportesCuentasPagadasDAO
				.mostrarTablaVistaPagados(ControladorListasYArboles.arboVistaCuentasPagadas);
		vista.internalFrameReportesCuentasPagadas.getPanel().table.setModel(mod);
	}
	

}
