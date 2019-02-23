package controladorVistaCuentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.EventListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Banco;
import modeloEntidades.Caja;
import modeloEntidades.Cuenta;
import modeloEntidades.MovimientosBancario;
import modeloEntidades.Retiro;
import modeloEntidades.Socio;
import modeloEntidades.Telefono;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCuenta;
import pruebas.Iniciador;
import serp.bytecode.visitor.VisitAcceptor;
import vista.Menu_1;
import vistaCuentas.FrameNuevaCuenta;
import vistaCuentas.JFrameNuevoDeposito;
import vistaCuentas.PanelIngresoCaja_Abono;

public class ControladorVentanaCuentasBancos extends MouseAdapter implements ActionListener, ItemListener {

	private Menu_1 vista;
	private VistaCuenta vistaCuenta;
	private String rol;

	public ControladorVentanaCuentasBancos(Menu_1 vista, String rol) {
		super();
		this.vista = vista;
		ControladorListasYArboles.actualizarArbolMovimientosBancarios(vista);
		this.rol = rol;
	}

	public void mouseClicked(MouseEvent evt) {
		JList<VistaCuenta> list = (JList) evt.getSource();

		if (evt.getClickCount() == 2) {

			// Double-click detected
			int index = list.locationToIndex(evt.getPoint());
			vistaCuenta = list.getSelectedValue();
			actualizarTabla(vistaCuenta.getIdcuenta());

		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		Banco banco = (Banco) e.getItem();
		ControladorListasYArboles.actualizarArbolCuentasBancarias(vista);
		vista.internalFrameCuentasBancos.getPanel().listNCuentas.setModel(jListarCuenta(banco.getNombre()));
		if (vista.internalFrameCuentasBancos.getPanel().table.getRowCount() > 0)
			vista.internalFrameCuentasBancos.getPanel().table.setModel(new DefaultTableModel(
					new String[] { "Cod", "Fecha", "     Descripcion    ", "Deposito", "Retiro", "Saldo" }, 0));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Buscar Fecha")) {

			try {
				if (!vista.internalFrameCuentasBancos.getPanel().dateChooserFechaBusqueda.getDate().toString().equals("")) {
					ArbolAVL<MovimientosBancario> lista = buscarFecha(
							vista.internalFrameCuentasBancos.getPanel().dateChooserFechaBusqueda.getDate(),
							vistaCuenta.getIdcuenta());

					DefaultTableModel mod = mostrarTablaMovimientosBancarios(lista, vistaCuenta.getIdcuenta());
					vista.internalFrameCuentasBancos.getPanel().table.setModel(mod);
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese una Fecha");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
			

			
		}else if (e.getActionCommand().equals("Buscar Fecha Avanzada")) {
			try {
				if (!vista.internalFrameCuentasBancos.getPanel().dateChooserDesde.getDate().toString().equals("")
						&& !vista.internalFrameCuentasBancos.getPanel().dateChooserHasta.getDate().toString().equals("")) {
	
					ArbolAVL<MovimientosBancario> lista = buscarRangoFechas(
							vista.internalFrameCuentasBancos.getPanel().dateChooserDesde.getDate(),
							vista.internalFrameCuentasBancos.getPanel().dateChooserHasta.getDate(),
							vistaCuenta.getIdcuenta());
	
					DefaultTableModel mod = mostrarTablaMovimientosBancarios(lista, vistaCuenta.getIdcuenta());
					vista.internalFrameCuentasBancos.getPanel().table.setModel(mod);
	
				} 
				else{
					JOptionPane.showMessageDialog(null, "Ingrese las fechas ","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}
			

		} else if (e.getActionCommand().equals("Nuevo deposito")) {

			ControladorVentanaIngresarDeposito controlador = new ControladorVentanaIngresarDeposito(vista, "Banco");
			vista.frameDeposito = new JFrameNuevoDeposito(controlador);
			vista.frameDeposito.setVisible(true);
			vista.frameDeposito.setControlador(controlador);
			vista.frameDeposito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
			ControladorListasYArboles.actualizarArbolCuentasBancarias(vista);
			
			vista.frameDeposito.comboBoxCuenta.setModel(actualizarItemsCuentas());

		} else if (e.getActionCommand().equals("Nuevo retiro")&& rol.equals("ADMINISTRADOR")) {

			retiro();

		} else if (e.getActionCommand().equals("nueva cuenta") && rol.equals("ADMINISTRADOR")) {

			vista.cuentaNueva = new FrameNuevaCuenta();
			vista.cuentaNueva.setControlador(Iniciador.controladorPrincipal.controladorVentanaCuentasBancos);
			vista.cuentaNueva.comboBoxBanco.setModel(actualizarItemsBancos());
			vista.cuentaNueva.setVisible(true);

		} else if (e.getActionCommand().equals("Guardar Cuenta")&& rol.equals("ADMINISTRADOR")) {

			if (!vista.cuentaNueva.txtNcuenta.getText().equals("") && !vista.cuentaNueva.txtTitular.getText().equals("")
					&& vista.cuentaNueva.comboBoxTipo.getSelectedIndex() > 0) {
				int idbanco = ((Banco) vista.cuentaNueva.comboBoxBanco.getSelectedItem()).getIdbanco();

				String ncuenta = vista.cuentaNueva.txtNcuenta.getText();
				String nombre = vista.cuentaNueva.txtTitular.getText();
				String tipo = vista.cuentaNueva.comboBoxTipo.getSelectedItem().toString();

				Cuenta cuenta = new Cuenta(idbanco, ncuenta, nombre, tipo);
				ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
				cont.insertarSGBD(cuenta);
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese todos los campos solicitados", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			ControladorListasYArboles.actualizarArbolCuentasBancarias(vista);
			vista.cuentaNueva.dispose();

		} else if (e.getActionCommand().equals("Actualizar")) {
			actualizarTabla(vistaCuenta.getIdcuenta());
		}

	}

	private void retiro() {
		if (vistaCuenta != null) {

			PanelIngresoCaja_Abono panel = new PanelIngresoCaja_Abono();
			java.util.Date fecha;
			String descripcion;
			double valor;

			Retiro retiro;

			panel.panelSuperior.setVisible(true);
			panel.lblNew1.setText(vistaCuenta.getBanco() + ":");
			panel.lblNew2.setText(vistaCuenta.getNumeroCuenta());
			panel.lblNew3.setText(vistaCuenta.getTipo());
			panel.lblFecha.setVisible(false);
			panel.lblFechaActual.setVisible(false);
			panel.panelInferior.setVisible(true);

			while (true) {
				int resultado = JOptionPane.showConfirmDialog(null, panel, "** RETIRO BANCARIO **",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (resultado == JOptionPane.OK_OPTION && !panel.txtValor.getText().equals("")
						&& !panel.txtDescripcion.getText().equals("")
						&& !panel.dateChooser.getDate().toString().equals("")) {

					valor = Double.parseDouble(panel.txtValor.getText());
					descripcion = panel.txtDescripcion.getText();
					fecha = panel.dateChooser.getDate();
					if (valor > 0) {
						valor = valor * -1;
						int resultado2 = JOptionPane.showConfirmDialog(null, "Realizando Transaccion.... ¿Continuar?",
								"AVISO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

						if (resultado2 == JOptionPane.OK_OPTION) {
							retiro = new Retiro(descripcion, fecha, vistaCuenta.getIdcuenta(), valor);

							ControladorCRUD_DAO cont = new ControladorCRUD_DAO();

							cont.insertarSGBD(retiro);

							ControladorListasYArboles.actualizarArbolMovimientosBancarios(vista);
							actualizarTabla(vistaCuenta.getIdcuenta());

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
		} else {
			JOptionPane.showMessageDialog(null, "Aun no selecciona ninguna cuenta", "Mensaje",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	public DefaultComboBoxModel<Banco> actualizarItemsBancos() {

		DefaultComboBoxModel<Banco> model = new DefaultComboBoxModel<Banco>();

		for (int i = 0; i < ControladorListasYArboles.listadoBancos.size(); i++) {
			if (i == 0) {
				model.addElement(new Banco("<<Seleccione>>"));
			}
			model.addElement(ControladorListasYArboles.listadoBancos.get(i));

		}
		return model;

	}

	public DefaultComboBoxModel<VistaCuenta> actualizarItemsCuentas() {

		DefaultComboBoxModel<VistaCuenta> model = new DefaultComboBoxModel<VistaCuenta>();

		for (int i = 0; i < ControladorListasYArboles.arbolCuentasBancarias.size(); i++) {
			if (i == 0) {
				model.addElement(new VistaCuenta("<<-Seleccione", ">>"));
			}
			model.addElement(ControladorListasYArboles.arbolCuentasBancarias.getPreOrden(i));

		}
		return model;

	}

	public DefaultListModel<VistaCuenta> jListarCuenta(String banco) {

		DefaultListModel<VistaCuenta> listModel = new DefaultListModel<>();

		List<VistaCuenta> listaCuenta = ControladorListasYArboles.arbolCuentasBancarias.inOrdenList();

		for (int i = 0; i < listaCuenta.size(); i++) {
			if (listaCuenta.get(i).getBanco().equals(banco)) {
				listModel.addElement(listaCuenta.get(i));
			}
		}

		return listModel;
	}

	public void actualizarTabla(int idCuenta) {

		DefaultTableModel mod;
		mod = mostrarTablaMovimientosBancarios(ControladorListasYArboles.arbolMovimientosBancarios, idCuenta);

		vista.internalFrameCuentasBancos.getPanel().table.setModel(mod);

	}

	private ArbolAVL<MovimientosBancario> buscarFecha(java.util.Date fecha, int idCuenta) {

		ArbolAVL<MovimientosBancario> arbol = ControladorListasYArboles.arbolMovimientosBancarios;
		ArbolAVL<MovimientosBancario> listaResultado = new ArbolAVL<>();
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

	private ArbolAVL<MovimientosBancario> buscarRangoFechas(java.util.Date fecha1, java.util.Date fecha2,
			int idCuenta) {

		ArbolAVL<MovimientosBancario> arbol = ControladorListasYArboles.arbolMovimientosBancarios;
		ArbolAVL<MovimientosBancario> listaResultado = new ArbolAVL<>();

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

	public DefaultTableModel mostrarTablaMovimientosBancarios(ArbolAVL<MovimientosBancario> arbolMovimientosBancarios,
			int idCuenta) {
		String[] titulos = { "Cod", "Fecha", "     Descripcion    ", "Deposito", "Retiro", "Saldo" };
		String[] registro = new String[6];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (MovimientosBancario oe : arbolMovimientosBancarios.inOrdenList()) {
			if (oe.getIdcuenta() == idCuenta) {

				registro[0] = oe.getCodigo();
				registro[1] = oe.getFecha().toString();
				registro[2] = oe.getDescripcion();
				registro[3] = String.valueOf(oe.getDeposito());
				registro[4] = String.valueOf(oe.getRetiro());
				registro[5] = String.valueOf(oe.getSaldo());

				modelo.addRow(registro);

			}
		}
		return modelo;
	}

}
