package controladorVistaBodega;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import modeloEntidades.Bodega;
import modeloEntidades.Categoria;
import modeloEntidades.VistaDetalleNotaentrega;
import pruebas.Iniciador;
import vista.Menu_1;

public class ControladorVentanaBodegaNuevoProducto implements ActionListener, ItemListener {

	private Menu_1 vista;

	public ControladorVentanaBodegaNuevoProducto(Menu_1 vista) {
		super();
		this.vista = vista;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Guardar Producto")) {

			String detalle = vista.frameBodegaNuevoProducto.txtDescripcion.getText();
			Integer idcategoria = ((Categoria) vista.frameBodegaNuevoProducto.comboBoxCategoria.getSelectedItem())
					.getIdcategoria();
			Integer idsocio = vista.frameBodegaNuevoProducto.socio.getIdsocio();
			String nombre = vista.frameBodegaNuevoProducto.txtNombre.getText();

			Bodega bodega = new Bodega(0, detalle, idcategoria, idsocio, nombre, 0);

			ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
					cont.insertarSGBD(bodega);

			ControladorListasYArboles.actualizarArbolBodega(vista);

			vista.internalFrameIngresarFacturaCompra.getPanel().getPanelAddProductos().tableProductos
			.setModel(Iniciador.controladorPrincipal.controladorVentanaNotaDeEntrega.mostrarTablaBodega(
					ControladorListasYArboles.arboVistaBodega, vista.frameBodegaNuevoProducto.socio));
			
			vista.frameBodegaNuevoProducto.dispose();

		} else if (e.getActionCommand().equals("Cancelar")) {

			vista.frameBodegaNuevoProducto.dispose();

		} else if (e.getActionCommand().equals("")) {
			System.out.println();

		} else if (e.getActionCommand().equals("")) {

			System.out.println();
		} else if (e.getActionCommand().equals("")) {

			System.out.println();
		}
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

}
