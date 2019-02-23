package vista;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

public class JpanelFondo extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagen;

	public JpanelFondo(String nombre) {
		super();
		initialize();
		imagen = new ImageIcon(getClass().getResource(nombre));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());

	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	protected void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(imagen.getImage(), 0, 0, d.width, d.height, null);
		this.setOpaque(false);
		super.paintComponent(g);

	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));
	}

}
