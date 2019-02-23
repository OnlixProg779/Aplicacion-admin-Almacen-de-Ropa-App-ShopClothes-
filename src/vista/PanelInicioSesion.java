package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import controladorVistaPrincipal.ControladorVentanaPrincipalSecundario;

public class PanelInicioSesion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6712612245004094020L;
	
	private JLabel lblIngresar;
	private JLabel lblCerrar;
	public JTextField txtUsuario;
	public JPasswordField passwordField;
	public JLabel labelWindow;
	private JpanelFondo panel_Usuario;
	/**
	 * Create the panel.
	 */
	
	
	
	public PanelInicioSesion() {
		setLayout(new BorderLayout());
		
		JpanelFondo panel = new JpanelFondo("/Imagenes/modelosTienda.jpg");
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panel_Usuario = new JpanelFondo("/Imagenes/borrBlue.jpg");
		panel_Usuario.setBounds(-374, 0, 374, 572);
		panel.add(panel_Usuario);
		panel_Usuario.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Menu_1.class.getResource("/Imagenes/user.png")));
		lblNewLabel.setBounds(51, 76, 256, 277);
		panel_Usuario.add(lblNewLabel);
		
		lblCerrar = new JLabel("CERRAR");
		lblCerrar.setForeground(new Color(0, 0, 128));
		lblCerrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		lblCerrar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setBounds(188, 440, 139, 45);
		panel_Usuario.add(lblCerrar);
		
		lblIngresar = new JLabel("INGRESAR");
		lblIngresar.setForeground(new Color(0, 0, 128));
		lblIngresar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblIngresar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		lblIngresar.setBounds(39, 440, 139, 45);
		panel_Usuario.add(lblIngresar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(39, 364, 285, 27);
		panel_Usuario.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(39, 398, 285, 27);
		panel_Usuario.add(passwordField);
		
		labelWindow = new JLabel("");
		labelWindow.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(139, 0, 0), new Color(139, 0, 0), new Color(139, 0, 0), new Color(255, 0, 0)));
		
		labelWindow.setBounds(0, 0, 64, 58);
		panel.add(labelWindow);
		labelWindow.setIcon(new ImageIcon(Menu_1.class.getResource("/Imagenes/fechaD.png")));
		
		lblCerrar.setName("cerrar");
		lblIngresar.setName("ingresar");
		labelWindow.setName("window");
	
	}
	public JpanelFondo getPanel_Usuario() {
		return panel_Usuario;
	}
	public void setControlInicio(ControladorVentanaPrincipalSecundario controlador) {
		// TODO Auto-generated method stub
		labelWindow.addMouseListener(controlador); 
		lblCerrar.addMouseListener(controlador);
		lblIngresar.addMouseListener(controlador);
		
	}
}
