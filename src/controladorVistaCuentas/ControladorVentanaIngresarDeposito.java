package controladorVistaCuentas;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import modeloEntidades.Banco;
import modeloEntidades.Deposito;
import modeloEntidades.VistaCuenta;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaCuentas.PanelCheques;

public class ControladorVentanaIngresarDeposito implements ActionListener, KeyListener {

	private Menu_1 vista;
	private String llamada;
	public ControladorVentanaIngresarDeposito(Menu_1 vista, String llamada) {
		super();
		this.vista = vista;
		this.llamada = llamada;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("buscar cheque")) {

			
			PanelCheques.setNuevo(new JFrame("Cheques"));

			PanelCheques.getNuevo().getContentPane().setLayout(new BorderLayout(5, 5));
			PanelCheques.setPanelCheques(new PanelCheques());
			PanelCheques.getPanelCheques().setName("PANEL DE CHEQUES - DEPOSITOS");
			PanelCheques.getNuevo().getContentPane().add(PanelCheques.getPanelCheques());
			PanelCheques.getNuevo().setBounds(100, 100, 818, 462);
			PanelCheques.getPanelCheques().getPanelBotones().setVisible(false);
			PanelCheques.getPanelCheques().getPanelRangoFechas().setVisible(false);
			PanelCheques.getPanelCheques().getBtnNuevoCheque().setVisible(false);

			ControladorListasYArboles.actualizarArbolCheques(vista);
			DefaultTableModel mod;
			mod = Iniciador.controladorPrincipal.controladorVentanaCuentasCheques
					.mostrarTablaVistaCheques(ControladorListasYArboles.arbolVistaCheques, "PENDIENTE");
			PanelCheques.getPanelCheques().table.setModel(mod);
			PanelCheques.getPanelCheques().setControlador(new ControladorVentanaCuentasCheques(vista, "ADMINISTRADOR"));

			PanelCheques.getNuevo().setVisible(true);

		} else if (e.getActionCommand().equals("guardar en bd")) {

			if (!vista.frameDeposito.txtDepositante.getText().equals("")
					&& !vista.frameDeposito.txtTotal.getText().equals("")
					&& !vista.frameDeposito.txtNDeposito.getText().equals("")) {

				Integer idCuenta = ((VistaCuenta) vista.frameDeposito.comboBoxCuenta.getSelectedItem()).getIdcuenta();
				int cheque = 0;
				if (!vista.frameDeposito.txtIdCheque.getText().equals("")) {
					cheque = Integer.parseInt(vista.frameDeposito.txtIdCheque.getText());
				}

				String depositante = vista.frameDeposito.txtDepositante.getText();
				String descripcion = vista.frameDeposito.txtDescripcion.getText();
				double efectivo = 0;
				if (!vista.frameDeposito.txtEfectivo.getText().equals("")) {
					efectivo = Double.parseDouble(vista.frameDeposito.txtEfectivo.getText());
				}

				LocalDate date = TablasTemporales.asLocalDate(vista.frameDeposito.dateChooserFecha.getDate());

				Date fecha = Date.valueOf(date);

				String nDeposito = vista.frameDeposito.txtNDeposito.getText();
				double total = Double.parseDouble(vista.frameDeposito.txtTotal.getText());

				Deposito deposito = new Deposito(cheque, depositante, descripcion, efectivo, fecha, idCuenta,
						nDeposito);

				try {
					ControladorListasYArboles.controladorCuentasDepositosDAO.insertarDeposito(deposito);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(llamada.equals("Deposito")) {
				 Iniciador.controladorPrincipal.openCuentasDepositos();
				}else {
					Iniciador.controladorPrincipal.openCuentasBancos();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Datos Faltantes Para Realizar Ingreso de Deposito", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getActionCommand().equals("")) {

			System.out.println();
		}

	}

	public DefaultComboBoxModel<Banco> actualizarItemsBancos() {
		ControladorListasYArboles.actualizarListaBancos(vista);

		DefaultComboBoxModel<Banco> model = new DefaultComboBoxModel<Banco>();
		model.addElement(ControladorListasYArboles.listadoBancos.get(1));
		for (int i = 0; i < ControladorListasYArboles.listadoBancos.size(); i++) {

			model.addElement(ControladorListasYArboles.listadoBancos.get(i));

		}
		return model;

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Entro al key");

		System.out.println((int) e.getKeyChar());

		if ((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 46 || e.getKeyChar() == 127
				|| e.getKeyChar() == 8 || e.getKeyChar() == 65535) {
			vista.frameDeposito.txtTotal.setText(vista.frameDeposito.txtEfectivo.getText());
			if (!vista.frameDeposito.txtCheque.getText().equals("")) {
				double valor1 = Double.parseDouble(vista.frameDeposito.txtEfectivo.getText());
				double valor2 = Double.parseDouble(vista.frameDeposito.txtCheque.getText());
				vista.frameDeposito.txtTotal.setText(String.valueOf(valor1 + valor2));

			}
		} else {
			vista.frameDeposito.txtEfectivo.setText(vista.frameDeposito.txtEfectivo.getText().substring(0,
					vista.frameDeposito.txtEfectivo.getText().length() - 1));
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
