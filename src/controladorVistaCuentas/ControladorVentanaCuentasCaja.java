package controladorVistaCuentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Caja;
import modeloEntidades.VistaCaja;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaCuentas.PanelIngresoCaja_Abono;

public class ControladorVentanaCuentasCaja implements ActionListener, KeyListener {

	private Menu_1 vista;
	private String rol;

	public ControladorVentanaCuentasCaja(Menu_1 vista, String rol) {
		super();
		this.vista = vista;
		this.rol = rol;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("buscar por fecha")) {
			try {
				if (!vista.internalFrameCuentasCaja.getPanel().dateChooserFechaBusqueda.getDate().toString().equals("")) {
					ArbolAVL<VistaCaja> lista = buscarFecha(
							vista.internalFrameCuentasCaja.getPanel().dateChooserFechaBusqueda.getDate());
					DefaultTableModel mod = mostrarTablaDeCaja(lista);
					vista.internalFrameCuentasCaja.getPanel().table.setModel(mod);
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese una Fecha");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getActionCommand().equals("buscar por fecha avanzado")) {

			try {
				if (!vista.internalFrameCuentasCaja.getPanel().dateChooserFechaDesde.getDate().toString().equals("")
						&& !vista.internalFrameCuentasCaja.getPanel().dateChooserFechaHasta.getDate().toString()
								.equals("")) {
	
					ArbolAVL<VistaCaja> lista = buscarRangoFechas(
							vista.internalFrameCuentasCaja.getPanel().dateChooserFechaDesde.getDate(),
	
							vista.internalFrameCuentasCaja.getPanel().dateChooserFechaHasta.getDate());
	
					DefaultTableModel mod = mostrarTablaDeCaja(lista);
					vista.internalFrameCuentasCaja.getPanel().table.setModel(mod);
	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese las fechas 'Rango de busqueda'");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getActionCommand().equals("Nuevo Registro")) {

			nuevoIngresoCaja();

		} else if (e.getActionCommand().equals("Retiro") && rol.equals("ADMINISTRADOR")) {

			nuevoRetiroCaja();

		}else if (e.getActionCommand().equals("Actualizar")) {
			actualizarTabla();
		}


	}

	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = mostrarTablaDeCaja(ControladorListasYArboles.arbolVistaCaja);
		vista.internalFrameCuentasCaja.getPanel().table.setModel(mod);
	}
	public DefaultTableModel mostrarTablaDeCaja(ArbolAVL<VistaCaja> arbol)
    {
        String [] titulos = {"cod","Fecha","Referencia","N° Fact Ref","Valor $$"};
        String[] registro = new String[5]; 
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        for(VistaCaja oe : arbol.inOrdenList())
        {
            registro[0]= String.valueOf(oe.getIdcaja());
            registro[1]= oe.getFecha().toString();
            registro[2]= oe.getReferencia();
            registro[3]= oe.getNumerofactura();
            registro[4]= String.valueOf(oe.getValor());
            
           
            
            
            modelo.addRow(registro);
            
        }
     return modelo;
}
    
	private void nuevoIngresoCaja() {
		String fecha;
		String hora;
		double valor;
		String referencia;

		SimpleDateFormat sdfh = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		fecha = sdf.format(new Date());
		hora = sdfh.format(new Date());

		vistaCuentas.PanelIngresoCaja_Abono panel = new PanelIngresoCaja_Abono();
		panel.lblFechaActual.setText(LocalDate.now().toString());
		while (true) {
			int resultado = JOptionPane.showConfirmDialog(null, panel, "Nuevo Ingreso", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION && !panel.txtValor.getText().equals("")
					&& !panel.txtDescripcion.getText().equals("")) {

				valor = Double.parseDouble(panel.txtValor.getText());
				referencia = panel.txtDescripcion.getText();
				if (valor > 0) {
					int resultado2 = JOptionPane.showConfirmDialog(null,
							"Una vez ingresado esto, no se puede modificar. ¿Desean continuar?", "AVISO",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (resultado2 == JOptionPane.OK_OPTION) {
						Caja caja = new Caja(java.sql.Date.valueOf(fecha), Time.valueOf(hora), null, referencia, valor);
						ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
						cont.insertarSGBD(caja);
						
						
						Iniciador.controladorPrincipal.openCuentasCaja();

					} else if (resultado2 == JOptionPane.CANCEL_OPTION || resultado2 == JOptionPane.CLOSED_OPTION) {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros positivos", "Aviso",
							JOptionPane.PLAIN_MESSAGE);
				}
				return;

			} else if (resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION) {
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Falta Infotmacion",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	private void nuevoRetiroCaja() {
		String fecha;
		String hora;
		double valor;
		String referencia;

		SimpleDateFormat sdfh = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		fecha = sdf.format(new Date());
		hora = sdfh.format(new Date());

		vistaCuentas.PanelIngresoCaja_Abono panel = new PanelIngresoCaja_Abono();

		while (true) {
			int resultado = JOptionPane.showConfirmDialog(null, panel, "** Retiro **", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION && !panel.txtValor.getText().equals("")
					&& !panel.txtDescripcion.getText().equals("")) {

				valor = Double.parseDouble(panel.txtValor.getText());
				referencia = panel.txtDescripcion.getText();
				if (valor > 0) {
					valor = valor * -1;
					int resultado2 = JOptionPane.showConfirmDialog(null, "Realizando Transaccion.... ¿Continuar?",
							"AVISO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (resultado2 == JOptionPane.OK_OPTION) {
						Caja caja = new Caja(java.sql.Date.valueOf(fecha), Time.valueOf(hora), null, referencia, valor);
						ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
						cont.insertarSGBD(caja);

						Iniciador.controladorPrincipal.openCuentasCaja();

					} else if (resultado2 == JOptionPane.CANCEL_OPTION || resultado2 == JOptionPane.CLOSED_OPTION) {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros positivos", "Aviso",
							JOptionPane.PLAIN_MESSAGE);
				}
				return;

			} else if (resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION) {
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Falta Infotmacion",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	private ArbolAVL<VistaCaja> buscarFecha(java.util.Date fecha) {

		ArbolAVL<VistaCaja> arbol = ControladorListasYArboles.arbolVistaCaja;
		ArbolAVL<VistaCaja> listaResultado = new ArbolAVL<>();

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

	private ArbolAVL<VistaCaja> buscarRangoFechas(java.util.Date fecha1, java.util.Date fecha2) {

		ArbolAVL<VistaCaja> arbol = ControladorListasYArboles.arbolVistaCaja;
		ArbolAVL<VistaCaja> listaResultado = new ArbolAVL<>();

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

	public void actualizarSaldoDeCaja() {
		double saldo;
		try {
			saldo = ControladorListasYArboles.controladorCuentasCajaDAO.getSaldoDeCaja();

			vista.internalFrameCuentasCaja.getPanel().lblCajaActual.setText(String.valueOf(saldo));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
