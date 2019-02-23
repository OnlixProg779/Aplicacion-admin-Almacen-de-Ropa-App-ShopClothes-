package controladorVistaBodega;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Menu_1;

public class ControladorVentanaSociosDevoluciones  implements ActionListener {
private Menu_1 vista;
	
	public ControladorVentanaSociosDevoluciones(Menu_1 vista) {
		super();
		this.vista = vista;
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("add a devolucion")) {
			
			System.out.println("add a devolucion");
			
		}else if(e.getActionCommand().equals("buscar nota entrega")) {
			
			System.out.println("buscar");
			
		}else if(e.getActionCommand().equals("eliminar item")) {
			
			System.out.println("eliminar item");
			
		}else if(e.getActionCommand().equals("guardar devolucion")) {
			
			System.out.println("guardar devolucion");
			
		}else if(e.getActionCommand().equals("")) {
			
			System.out.println();
		}
	}

}
