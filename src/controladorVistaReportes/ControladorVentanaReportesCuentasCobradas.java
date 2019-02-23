package controladorVistaReportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controladorEstructuraDeDatos.ControladorListasYArboles;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.VistaCuentasPorCobrar;
import vista.Menu_1;

public class ControladorVentanaReportesCuentasCobradas  implements ActionListener {

	
private Menu_1 vista;
	
	public ControladorVentanaReportesCuentasCobradas(Menu_1 vista) {
		super();
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("buscar cedula")) {
			
			try {
				if (!vista.internalFrameReportesCuentasCobradas.getPanel().txtCedula.getText().toString().equals("")) {
					
					ArbolAVL<VistaCuentasPorCobrar> lista = buscarCedula(vista.internalFrameReportesCuentasCobradas.getPanel().txtCedula.getText());
					
					DefaultTableModel mod = ControladorListasYArboles.controladorReportesCuentasCobradasDAO.mostrarTablaVistaCuentasCobradas(lista);
					vista.internalFrameReportesCuentasCobradas.getPanel().table.setModel(mod);
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un NUMERO DE CEDULA");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			
		}else if(e.getActionCommand().equals("Pagar cuenta")) {
			
			System.out.println("Pagar cuenta");
		}else if(e.getActionCommand().equals("Buscar en rango")) {
			System.out.println("Buscar en rango");
			
		}else if(e.getActionCommand().equals("Abonar a la cuenta")) {
			
			System.out.println("Abonar a la cuenta");
		}else if(e.getActionCommand().equals("Actualizar")) {
			
			actualizarTabla();
		}
	}

	private ArbolAVL<VistaCuentasPorCobrar> buscarCedula(String cedula) {

		ArbolAVL<VistaCuentasPorCobrar> arbol = ControladorListasYArboles.arboVistaCuentasCobradas;
		ArbolAVL<VistaCuentasPorCobrar> listaResultado = new ArbolAVL<>();
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getClienteCedula().compareTo(cedula) == 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}
	
	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = ControladorListasYArboles.controladorReportesCuentasCobradasDAO
				.mostrarTablaVistaCuentasCobradas(ControladorListasYArboles.arboVistaCuentasCobradas);
		vista.internalFrameReportesCuentasCobradas.getPanel().table.setModel(mod);
	}
	
	
	
}
