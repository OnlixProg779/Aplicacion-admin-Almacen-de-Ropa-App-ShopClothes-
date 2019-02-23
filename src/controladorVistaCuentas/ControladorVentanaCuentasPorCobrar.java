package controladorVistaCuentas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Abono;
import modeloEntidades.Cheque;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCuentasPorCobrar;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaCuentas.PanelCheques;
import vistaCuentas.PanelIngresoCaja_Abono;
import vistaCuentas.PanelNuevoCheque;

public class ControladorVentanaCuentasPorCobrar implements ActionListener {

	private Menu_1 vista;
	//private int idCuenta;

	public ControladorVentanaCuentasPorCobrar(Menu_1 vista) {
		super();
		this.vista = vista;// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("buscar cedula")) {
			
			try {
				if (!vista.internalFrameCuentasPorCobrar.getPanel().txtCedula.getText().toString().equals("")) {
					
					ArbolAVL<VistaCuentasPorCobrar> lista = buscarCedula(vista.internalFrameCuentasPorCobrar.getPanel().txtCedula.getText());
					
					DefaultTableModel mod = ControladorListasYArboles.controladorCuentasCuentasPorCobrarDAO.mostrarTablaVistaCheques(lista);
					vista.internalFrameCuentasPorCobrar.getPanel().table.setModel(mod);
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getActionCommand().equals("Pagar cuenta")) {

			int idCuenta = 0; 
			
			if (vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow() != -1) {
				idCuenta = Integer.parseInt((vista.internalFrameCuentasPorCobrar.getPanel().table
						.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 0)
						.toString()));
				
				try {
					
					pagarCuentaPorCobrar(idCuenta);

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla", "** ERROR **",
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getActionCommand().equals("Buscar en rango")) {
			System.out.println("Buscar en rango");

		} else if (e.getActionCommand().equals("Abonar a la cuenta")) {
		
			int idCuenta =0;
			if (vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow() != -1) {
				idCuenta = Integer.parseInt((vista.internalFrameCuentasPorCobrar.getPanel().table
						.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 0)
						.toString()));
				abonar(idCuenta);
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla", "** ERROR **",
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getActionCommand().equals("Actualizar")) {

			actualizarTabla();
		}

	}

	public void abonar(int idCuenta) {

		JPanel PanelTipoDeAbono = new JPanel();
		PanelTipoDeAbono.setLayout(new BorderLayout(0, 0));

		JLabel lblAbonarCon = new JLabel("Abonar Con:");
		lblAbonarCon.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		PanelTipoDeAbono.add(lblAbonarCon, BorderLayout.NORTH);

		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "<<Seleccione>>", "CHEQUE", "EFECTIVO" }));
		PanelTipoDeAbono.add(comboBoxTipo, BorderLayout.CENTER);

		double valor;
		String descripcion;

		int resul = JOptionPane.showConfirmDialog(null, PanelTipoDeAbono, "**ABONO**", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resul == JOptionPane.OK_OPTION && comboBoxTipo.getSelectedItem().toString().equals("CHEQUE")) {

			PanelNuevoCheque pnc = new PanelNuevoCheque();

			int chek = JOptionPane.showConfirmDialog(null, pnc, "**Ingreso de Cheque**", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (chek == JOptionPane.OK_OPTION) {

				int nCheque = Integer.parseInt(pnc.txtNumCheque.getText());
				int PKCUENTA = (idCuenta);
				String chequeBanco = pnc.txtChequeBanco.getText();
				String titularCuenta = pnc.txtTitularCuenta.getText();
				java.util.Date fechaIngreso = pnc.dateChooserIngreso.getDate();
				java.util.Date fechaDeposito = pnc.dateChooserDeposito.getDate();
				double val = Double.parseDouble(pnc.txtValor.getText());
				Cheque cheque = new Cheque(chequeBanco, "PENDIENTE", fechaDeposito, fechaIngreso, nCheque, PKCUENTA,
						titularCuenta, val);
				ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
				cont.insertarSGBD(cheque);

				controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolCXC(vista);
				actualizarTabla();

				JOptionPane.showMessageDialog(null, "Registrado.!", "** NEW **", JOptionPane.PLAIN_MESSAGE);

			} else if (chek == JOptionPane.CANCEL_OPTION || chek == JOptionPane.CLOSED_OPTION) {

				JOptionPane.showMessageDialog(null, "Transacción Cancelada");

			}

		} else if (resul == JOptionPane.OK_OPTION && comboBoxTipo.getSelectedItem().toString().equals("EFECTIVO")) {

			PanelIngresoCaja_Abono panel = new PanelIngresoCaja_Abono();
			int resultado = JOptionPane.showConfirmDialog(null, panel, "** Ingresar Abono **",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION && !panel.txtValor.getText().equals("")
					&& !panel.txtDescripcion.getText().equals("")) {

				valor = Double.parseDouble(panel.txtValor.getText());
				descripcion = panel.txtDescripcion.getText();

				Abono abono = new Abono(descripcion, idCuenta, valor);
				ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
				cont.insertarSGBD(abono);

				controladorEstructuraDeDatos.ControladorListasYArboles.actualizarArbolCXC(vista);
				actualizarTabla();

				JOptionPane.showMessageDialog(null, "Registrado.!", "** NEW **", JOptionPane.PLAIN_MESSAGE);

			} else if (resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION) {

				JOptionPane.showMessageDialog(null, "Transacción Cancelada");

			}

		} else if (resul == JOptionPane.CANCEL_OPTION || resul == JOptionPane.CLOSED_OPTION) {

			JOptionPane.showMessageDialog(null, "Transacción Cancelada");
		}

	}

	public void pagarCuentaPorCobrar(int idcuenta) throws SQLException {

		JPanel PanelTipoDeAbono = new JPanel();
		PanelTipoDeAbono.setLayout(new BorderLayout(0, 0));

		JLabel lblAbonarCon = new JLabel("Abonar Con:");
		lblAbonarCon.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		PanelTipoDeAbono.add(lblAbonarCon, BorderLayout.NORTH);

		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "<<Seleccione>>", "CHEQUE", "EFECTIVO" }));
		PanelTipoDeAbono.add(comboBoxTipo, BorderLayout.CENTER);

		try {

				int res = JOptionPane.showConfirmDialog(null, PanelTipoDeAbono, "Transacción",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (res == JOptionPane.OK_OPTION && comboBoxTipo.getSelectedItem().toString().equals("CHEQUE")) {

					PanelNuevoCheque pnc = new PanelNuevoCheque();
					double valor = Double.parseDouble(vista.internalFrameCuentasPorCobrar.getPanel().table.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 0).toString());

					pnc.txtValor.setText(String.valueOf(valor));
					pnc.txtValor.setEditable(false);
					
					int nCheque = Integer.parseInt(pnc.txtNumCheque.getText());
					int pkCuentaxcobrarfk = (idcuenta);
					String chequeBanco = pnc.txtChequeBanco.getText();
					String titularCuenta = pnc.txtTitularCuenta.getText();
					Date fechaIngreso = (Date) pnc.dateChooserIngreso.getDate();
					Date fechaDeposito = (Date) pnc.dateChooserDeposito.getDate();
							
							
					
					Cheque cheque = new Cheque(chequeBanco, "PENDIENTE", fechaDeposito, fechaIngreso, nCheque,
							pkCuentaxcobrarfk, titularCuenta, valor);
					ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
					cont.insertarSGBD(cheque);
					
					

				} else if (res == JOptionPane.OK_OPTION && comboBoxTipo.getSelectedItem().toString().equals("EFECTIVO")) {
					
					String descripcion = "Por cancelacion total de la factura N°: "+ vista.internalFrameCuentasPorCobrar.getPanel().table
							.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 1)
							.toString()+"\n , CXP N° "+ vista.internalFrameCuentasPorCobrar.getPanel().table
							.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 0)
							.toString();
					
					int idCuenta  = Integer.parseInt(vista.internalFrameCuentasPorCobrar.getPanel().table
							.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 0)
							.toString());
					
					double valor = Double.parseDouble(vista.internalFrameCuentasPorCobrar.getPanel().table
							.getValueAt(vista.internalFrameCuentasPorCobrar.getPanel().table.getSelectedRow(), 3)
							.toString());
					
					Abono abono = new Abono(descripcion, idCuenta, valor);
					ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
					cont.insertarSGBD(abono);
					
					JOptionPane.showMessageDialog(null, "Cuenta Pagada");
					
				
				
				}
				Iniciador.controladorPrincipal.openCuentasPorCobrar();
				
		} catch (Exception ez) {
			ez.printStackTrace();
		}

	}
	
	private ArbolAVL<VistaCuentasPorCobrar> buscarCedula(String cedula) {

		ArbolAVL<VistaCuentasPorCobrar> arbol = ControladorListasYArboles.arbolVistaCXC;
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
		mod = ControladorListasYArboles.controladorCuentasCuentasPorCobrarDAO
				.mostrarTablaVistaCheques(ControladorListasYArboles.arbolVistaCXC);
		vista.internalFrameCuentasPorCobrar.getPanel().table.setModel(mod);
	}

}
