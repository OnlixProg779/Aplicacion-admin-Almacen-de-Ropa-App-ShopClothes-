package controladorVistaBodega;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controladorBodega.ControladorBodegaDAO;
import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Bodega;
import modeloEntidades.Categoria;
import modeloEntidades.Socio;
import modeloEntidades.VistaBodega;
import vista.Menu_1;

public class ControladorVentanaBodegaRevisar implements ActionListener {

	private Menu_1 vista;

	public ControladorVentanaBodegaRevisar(Menu_1 vista) {
		super();
		this.vista = vista;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("actualizar estado de botones por default")) {

			limpiar();
			actualizarTabla();

		}else
		if (e.getActionCommand().equals("Aumentar Stock")) {
			System.out.println("Aumentar Stock");

		}else
		if (e.getActionCommand().equals("buscar en busqueda avanzada")) {
			System.out.println("buscar en busqueda avanzada");

		}else
		if (e.getActionCommand().equals("activar busqueda avanzada")) {

			int socio=0;
			String catego="";
			int ca=0;
			
			//Busca solo por socio
			socio= vista.inernalFrameBodega.getPanel().comboBoxSocio.getSelectedIndex();
			catego= vista.inernalFrameBodega.getPanel().comboBoxCategoria.getSelectedItem().toString();
			ca=vista.inernalFrameBodega.getPanel().comboBoxCategoria.getSelectedIndex();

			if (socio>0 && ca<=0) {
			
				VistaBodega bodega = getProductoSocio(socio);
				if (bodega != null) {
					mostrarDatosDeFiltradoPorSocio(bodega.getIdsocio());

				}

			//Busca por socio y categoria
				}else if (socio>0 && ca>0 ) {
				//int socio = 0;
			
					VistaBodega bodega = getProductoSocio(socio);
				if (bodega != null) {
					mostrarDatosDeFiltradoPorSocioyCategoria(bodega.getIdsocio(), catego);
				
				} 	

				//Busca solo por categoria
				}else if (socio<=0 && ca>0) {

					mostrarDatosDeFiltradoPorCategoria(catego);

					//null socio y categoria
			} else if(socio<=0 && ca<=0){
				JOptionPane.showMessageDialog(null, "Ingrese campos de busqueda", "*Aviso*", JOptionPane.INFORMATION_MESSAGE);
			 
		}

		

		}else
		if (e.getActionCommand().equals("Editar Categoria")) {

			int posicion = 0;
			JPanel PanelCat = new JPanel();
			PanelCat.setLayout(new BorderLayout(0, 0));

			JComboBox comboBoxTipo = new JComboBox();
			comboBoxTipo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			comboBoxTipo.setModel(actualizarItemsCategoria());
			PanelCat.add(comboBoxTipo, BorderLayout.CENTER);

			posicion = vista.inernalFrameBodega.getPanel().tableProductos.getSelectedRow() + 1;

			if (vista.inernalFrameBodega.getPanel().tableProductos.getSelectedRow() >= 0) {

				while (true) {

					int resultado = JOptionPane.showConfirmDialog(null, PanelCat, "Modificar Categoria",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (resultado == JOptionPane.OK_OPTION && comboBoxTipo.getSelectedIndex()>0) {

						String nombre = vista.inernalFrameBodega.getPanel().tableProductos.getValueAt(posicion, 2)
								.toString();
						int categoria = comboBoxTipo.getSelectedIndex();
						String categori = comboBoxTipo.getSelectedItem().toString();

						System.out.println(posicion);
						System.out.println(categoria);

						int resultado2 = JOptionPane.showConfirmDialog(null,
								"Una vez ingresado esto, no se puede modificar. ¿Desean continuar?" + "\n\n"
										+ "Producto: " + getProducto(posicion).getNombre() + "\n" + "Categoria: "
										+ categori,
								"AVISO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

						

						if (resultado2 == JOptionPane.OK_OPTION) {
							Bodega bodega = new Bodega(posicion, getProducto(posicion).getCosto(),
									getProducto(posicion).getDetalle(), categoria, getProducto(posicion).getIdsocio(),
									getProducto(posicion).getNombre(), getProducto(posicion).getStock());

							ControladorCRUD_DAO con = new ControladorCRUD_DAO();
							con.modificarSGBD(bodega);
							
							ControladorListasYArboles.actualizarArbolBodega(vista);
							actualizarTabla();

						} else if (resultado2 == JOptionPane.CANCEL_OPTION || resultado2 == JOptionPane.CLOSED_OPTION) {
							return;
						}

					} else if(resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION){
						return;
						
					}else {
						JOptionPane.showMessageDialog(null, "Seleccione una categoria", "Aviso",
								JOptionPane.PLAIN_MESSAGE);
					}
					

				}
			} else {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ningun campo de la tabla de productos");
			}

		}else 
		if (e.getActionCommand().equals("Eliminar Producto")) {
			System.out.println("Eliminar Producto");

		}else
		if (e.getActionCommand().equals("buscar por codigo")) {
System.out.println("MMMMMMMMM");
			if (!vista.inernalFrameBodega.getPanel().txtCodigo.getText().equals("")) {
				int codigo = 0;

				codigo = Integer.parseInt(vista.inernalFrameBodega.getPanel().txtCodigo.getText());
				System.out.println(codigo);

				VistaBodega bodega = getProducto(codigo);
				if (bodega != null) {

					vista.inernalFrameBodega.getPanel().txtNombre.setText(bodega.getNombre());
					vista.inernalFrameBodega.getPanel().comboBoxCategoria
							.setModel(mostrarCategoria(bodega.getCategoria()));
					vista.inernalFrameBodega.getPanel().comboBoxSocio
							.setModel(mostrarSocio(getSocio(bodega.getIdsocio()).getNombre()));
					mostrarDatosDeFiltrado(bodega.getNombre());

				} else {
					JOptionPane.showMessageDialog(null, "Producto no encontrado", "*Aviso*",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto", "*Aviso*",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}else 
		if (e.getActionCommand().equals("buscar por nombre")) {
			System.out.println("buscar por nombre");

		}else 
		if (e.getActionCommand().equals("modificar nombre")) {

			int posicion = 0;

			posicion = vista.inernalFrameBodega.getPanel().tableProductos.getSelectedRow() + 1;

			if (vista.inernalFrameBodega.getPanel().tableProductos.getSelectedRow() >= 0) {

				while (true) {
					String nombre=null;
					try{
						nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Modificar Nombre",JOptionPane.PLAIN_MESSAGE);
					
							

					if (!nombre.equals("")) {

						String nom = vista.inernalFrameBodega.getPanel().tableProductos.getValueAt(posicion - 1, 2)
								.toString();

						

						int resultado2 = JOptionPane.showConfirmDialog(null,
								"Una vez ingresado esto, no se puede modificar. ¿Desean continuar?" + "\n\n"
										+ "Producto: " + nom + "\n" + "Por el nombre : " + nombre,
								"AVISO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


						if (resultado2 == JOptionPane.OK_OPTION) {
							
						Bodega bodega = new Bodega(posicion, nombre);

						ControladorCRUD_DAO con = new ControladorCRUD_DAO();
						con.modificarSGBD(bodega);
						ControladorListasYArboles.actualizarArbolBodega(vista);
						actualizarTabla();

						} else if (resultado2 == JOptionPane.CANCEL_OPTION || resultado2 == JOptionPane.CLOSED_OPTION) {
							return;
						}
				

					} else {
						JOptionPane.showMessageDialog(null, "No puede enviar un nombre vacio", "Aviso",
								JOptionPane.PLAIN_MESSAGE);
					}
					}catch(NullPointerException ec) {
						return;
					}
					return;

				}
			} else {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ningun campo de la tabla de productos");
			}

		}else 
		if (e.getActionCommand().equals("crear un nuevo producto")) {
			System.out.println("crear un producto nuevo");
		}
		}
	

	public void limpiar() {
		vista.inernalFrameBodega.getPanel().txtCodigo.setText("");
		vista.inernalFrameBodega.getPanel().txtNombre.setText("");
		ControladorListasYArboles.actualizarArbolSocios(vista);
		ControladorListasYArboles.actualizarListaCategorias(vista);
		vista.inernalFrameBodega.getPanel().comboBoxCategoria.setModel(actualizarItemsCategoria());
		vista.inernalFrameBodega.getPanel().comboBoxSocio.setModel(actualizarItemsSocios());

	}

	public DefaultComboBoxModel<Socio> actualizarItemsSocios() {

		DefaultComboBoxModel<Socio> model = new DefaultComboBoxModel<Socio>();

		for (int i = 0; i < ControladorListasYArboles.arbolSocios.size(); i++) {
			if (i == 0) {
				model.addElement(new Socio("<<Seleccione>>", "", ""));
			}
			model.addElement(ControladorListasYArboles.arbolSocios.getPreOrden(i));

		}
		return model;

	}

	public DefaultComboBoxModel<Categoria> actualizarItemsCategoria() {

		DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<Categoria>();

		for (int i = 0; i < ControladorListasYArboles.listaCategorias.size(); i++) {

			if (i == 0) {
				model.addElement(new Categoria("<<Seleccione>>"));
			}
			model.addElement(ControladorListasYArboles.listaCategorias.get(i));

		}
		return model;

	}

	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = mostrarTablaBodega(ControladorListasYArboles.arboVistaBodega);
		vista.inernalFrameBodega.getPanel().tableProductos.setModel(mod);

	}

	public DefaultComboBoxModel<Categoria> mostrarCategoria(String categoria) {

		DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<Categoria>();
		model.addElement(new Categoria(categoria));
		return model;
	}

	public DefaultComboBoxModel<Socio> mostrarSocio(String socio) {
		DefaultComboBoxModel<Socio> model = new DefaultComboBoxModel<Socio>();
		model.addElement(new Socio(socio));
		return model;
	}

	public VistaBodega getProducto(int codigo) {
		ArbolAVL<VistaBodega> arbol = ControladorListasYArboles.arboVistaBodega;
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getIdproducto().equals(codigo)) {
				return arbol.getPreOrden(i);
			}

		}

		return null;
	}

	public VistaBodega getProductoSocio(int socio) {
		ArbolAVL<VistaBodega> arbol = ControladorListasYArboles.arboVistaBodega;
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getIdsocio().equals(socio)) {
				return arbol.getPreOrden(i);
			}

		}

		return null;
	}

	public Socio getSocio(int codigo) {
		ArbolAVL<Socio> arbol = ControladorListasYArboles.arbolSocios;
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getIdsocio().equals(codigo)) {
				return arbol.getPreOrden(i);
			}

		}

		return null;
	}

	private void mostrarDatosDeFiltrado(String buscar) {
		try {
			DefaultTableModel mod;
			ArbolAVL<VistaBodega> arbolBodega = ControladorListasYArboles.controladorBodegaDAO.filtrarProducto(buscar);
			mod = mostrarTablaBodega(arbolBodega);
			vista.inernalFrameBodega.getPanel().tableProductos.setModel(mod);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarDatosDeFiltradoPorSocio(int buscar) {
		try {
			DefaultTableModel mod;
			ArbolAVL<VistaBodega> arbolBodega = ControladorListasYArboles.controladorBodegaDAO
					.filtrarProductoPorSocio(buscar);
			mod = mostrarTablaBodega(arbolBodega);
			vista.inernalFrameBodega.getPanel().tableProductos.setModel(mod);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarDatosDeFiltradoPorSocioyCategoria(int buscar,String catego) {
		try {
			DefaultTableModel mod;
			ArbolAVL<VistaBodega> arbolBodega = ControladorListasYArboles.controladorBodegaDAO
					.filtrarProductoPorSocioyCategoria(buscar, catego);
			mod = mostrarTablaBodega(arbolBodega);
			vista.inernalFrameBodega.getPanel().tableProductos.setModel(mod);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarDatosDeFiltradoPorCategoria(String catego) {
		try {
			DefaultTableModel mod;
			ArbolAVL<VistaBodega> arbolBodega = ControladorListasYArboles.controladorBodegaDAO
					.filtrarProductoPorCategoria(catego);
			mod = mostrarTablaBodega(arbolBodega);
			vista.inernalFrameBodega.getPanel().tableProductos.setModel(mod);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public DefaultTableModel mostrarTablaBodega(ArbolAVL<VistaBodega> arbol) {
		String[] titulos = { "Codigo", "Categoria", "Producto", "Stock", "Costo", "Id Soc" };
		String[] registro = new String[6];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaBodega oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getIdproducto());
			registro[1] = oe.getCategoria();
			registro[2] = oe.getNombre();
			registro[3] = String.valueOf(oe.getStock());
			registro[4] = String.valueOf(oe.getCosto());
			registro[5] = String.valueOf(oe.getIdsocio());

			modelo.addRow(registro);
			// modelo.setColumnIdentifiers(titulos);

		}
		return modelo;
	}

	public void ObtenerProducto(int posicion) {

		vista.inernalFrameBodega.getPanel().txtCodigo.setText(vista.inernalFrameBodega.getPanel().tableProductos
				.getValueAt(vista.inernalFrameBodega.getPanel().tableProductos.getSelectedRow(), 2).toString());

		vista.inernalFrameBodega.getPanel().txtCodigo
				.setText((vista.inernalFrameBodega.getPanel().tableProductos.getValueAt(posicion, 0)).toString());
		vista.inernalFrameBodega.getPanel().txtNombre
				.setText((vista.inernalFrameBodega.getPanel().tableProductos.getValueAt(posicion, 2)).toString());
		vista.inernalFrameBodega.getPanel().comboBoxSocio.setModel(
				mostrarSocio(vista.inernalFrameBodega.getPanel().tableProductos.getValueAt(posicion, 5).toString()));

	}

}
