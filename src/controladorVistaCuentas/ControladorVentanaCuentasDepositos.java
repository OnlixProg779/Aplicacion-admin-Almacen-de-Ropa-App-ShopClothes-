package controladorVistaCuentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import controladorVistaSocios.ControladorVentanaSociosNuevoSocio;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Banco;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCuenta;
import modeloEntidades.VistaDeposito;
import vista.Menu_1;
import vistaCuentas.JFrameNuevoDeposito;
import vistaSocios.JFrameSociosNuevoSocio;

public class ControladorVentanaCuentasDepositos  implements ActionListener{

private Menu_1 vista;
	
	public ControladorVentanaCuentasDepositos(Menu_1 vista) {
		super();
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Nuevo Deposito")) {
			ControladorVentanaIngresarDeposito controlador = new ControladorVentanaIngresarDeposito(vista,"Deposito");
			vista.frameDeposito = new JFrameNuevoDeposito(controlador);
			vista.frameDeposito.setVisible(true);
			vista.frameDeposito.setControlador(controlador);
			vista.frameDeposito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ControladorListasYArboles.actualizarArbolCuentasBancarias(vista);
			vista.frameDeposito.comboBoxCuenta.setModel(actualizarItemsCuentas());
			

		}else if(e.getActionCommand().equals("Buscar en Rango")) {
			try {
				if (!vista.internalFrameCuentasDepositos.getPanel().dateChooserFechaDesde.getDate().toString().equals("")
						&& !vista.internalFrameCuentasDepositos.getPanel().dateChooserFechaHasta.getDate().toString()
								.equals("")) {
	
					ArbolAVL<VistaDeposito> lista = buscarRangoFechas(
							java.sql.Date.valueOf(TablasTemporales.asLocalDate(
									vista.internalFrameCuentasDepositos.getPanel().dateChooserFechaDesde.getDate())),
							java.sql.Date.valueOf(TablasTemporales.asLocalDate(
									vista.internalFrameCuentasDepositos.getPanel().dateChooserFechaHasta.getDate())));
					
					
					DefaultTableModel mod = ControladorListasYArboles.controladorCuentasDepositosDAO.mostrarTablaVistaDepositos(lista);
					vista.internalFrameCuentasDepositos.getPanel().table.setModel(mod);
	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese las fechas 'Rango de busqueda'");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}else if (e.getActionCommand().equals("Actualizar")) {
			actualizarTabla();
		}else if(e.getActionCommand().equals("")) {
			
			System.out.println();
		}else if(e.getActionCommand().equals("")) {
			
			System.out.println();
		}
		
	}
	
	public DefaultComboBoxModel<Banco> actualizarItemsBancos() {
		
		ControladorListasYArboles.actualizarListaBancos(vista);
		
		DefaultComboBoxModel<Banco> model = new DefaultComboBoxModel<Banco>();
		
		
		for(int i =0; i<ControladorListasYArboles.listadoBancos.size();i++) {
			
			model.addElement(ControladorListasYArboles.listadoBancos.get(i));
			
		}
		return model;
		
	}
	
	public DefaultComboBoxModel<VistaCuenta> actualizarItemsCuentas() {
		
		DefaultComboBoxModel<VistaCuenta> model = new DefaultComboBoxModel<VistaCuenta>();
		
		
		for(int i =0; i<ControladorListasYArboles.arbolCuentasBancarias.size();i++) {
			
			model.addElement(ControladorListasYArboles.arbolCuentasBancarias.getPreOrden(i));
			
		}
		return model;
		
	}
	
	private ArbolAVL<VistaDeposito> buscarFecha(java.util.Date fecha) {

		ArbolAVL<VistaDeposito> arbol = ControladorListasYArboles.arbolVistaDeposito;
		ArbolAVL<VistaDeposito> listaResultado = new ArbolAVL<>();
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String aux = (dt.format(fecha));

		try {
			fecha = dt.parse(aux);
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getFecha().compareTo(fecha) == 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}

	private ArbolAVL<VistaDeposito> buscarRangoFechas(java.util.Date fecha1, java.util.Date fecha2) {

		ArbolAVL<VistaDeposito> arbol = ControladorListasYArboles.arbolVistaDeposito;
		ArbolAVL<VistaDeposito> listaResultado = new ArbolAVL<>();
		
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
		mod = ControladorListasYArboles.controladorCuentasDepositosDAO.mostrarTablaVistaDepositos(ControladorListasYArboles.arbolVistaDeposito);
		vista.internalFrameCuentasDepositos.getPanel().table.setModel(mod);
	}
	

}
