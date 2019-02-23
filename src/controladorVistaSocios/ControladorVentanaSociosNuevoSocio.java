package controladorVistaSocios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controladorCRUD.ControladorCRUD_DAO;
import modeloEntidades.Socio;
import vista.Menu_1;

public class ControladorVentanaSociosNuevoSocio  implements ActionListener {

private Menu_1 vista;
	
	public ControladorVentanaSociosNuevoSocio(Menu_1 vista) {
		super();
		this.vista = vista;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Guardar Socio")) {
			if(!vista.frameSociosNuevoSocio.txtNombre.getText().equals("") && 
					!vista.frameSociosNuevoSocio.txtReferencia.getText().equals("") && 
					!vista.frameSociosNuevoSocio.txtRuc.getText().equals("") ) {
			System.out.println("Agregado socio a Tabla Socios");
		String nombre = vista.frameSociosNuevoSocio.txtNombre.getText();
		String referencia = vista.frameSociosNuevoSocio.txtReferencia.getText();
		String ruc = vista.frameSociosNuevoSocio.txtRuc.getText();
			Socio socio = new Socio(nombre, referencia, ruc);
		ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
		cont.insertarSGBD(socio);
JOptionPane.showMessageDialog(null, "Agregado.!");
		vista.frameSociosNuevoSocio.dispose();
			}
		}else if(e.getActionCommand().equals("Cancelar")) {
			
			System.out.println("Cancelado");
			vista.frameSociosNuevoSocio.dispose();
			
		}	else if(e.getActionCommand().equals("")) {
			System.out.println();
			
		}else if(e.getActionCommand().equals("")) {
			
			System.out.println();
		}else if(e.getActionCommand().equals("")) {
			
			System.out.println();
		}
	}

}
