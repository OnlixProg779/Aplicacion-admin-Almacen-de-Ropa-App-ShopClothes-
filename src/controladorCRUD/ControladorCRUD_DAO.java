package controladorCRUD;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.xml.stream.events.NotationDeclaration;

import controladorFacturacion.TablasTemporales;
import modeloEntidades.Bodega;
import modeloEntidades.Categoria;
import modeloEntidades.Detallefactura;
import modeloEntidades.DevolucionDeCliente;
import modeloEntidades.NotaentregaBodega;
import modeloEntidades.Socio;
import modeloEntidades.Telefono;

public class ControladorCRUD_DAO {

	private  EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlmacenDeRopaJPA");
	private  EntityManager em = emf.createEntityManager();
	private  EntityTransaction et = em.getTransaction();

	public  <T> void insertarSGBD(T entidad) {

		try {

			
			et.begin(); // ABRIR LA TRANSACCION EN LA BASE DE DATOS
			
			em.persist((T) entidad); // PARA PODER PERSISTIR (GUARDAR) EN LA BD
			et.commit(); // HACER UN COMMIT

			System.out.println("Registro creado correctamente");
		}

		catch (Exception ex) {
			// System.out.println("printStack");

			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
			System.out.println("roll Back");
			et.rollback(); // HACER UN ROLLBACK

		} finally {
			em.close();
			
		}
	}

	public  <T> void insertarDetalleSGBD(List<T> lista) {

		try {

			et.begin(); // ABRIR LA TRANSACCION EN LA BASE DE DATOS

			for (T t : lista) {
				em.persist((T) t); // PARA PODER PERSISTIR (GUARDAR) EN LA BD
			}

			et.commit(); // HACER UN COMMIT

			System.out.println("Registro creado correctamente");
		}

		catch (Exception ex) {
			// System.out.println("printStack");

			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
			System.out.println("roll Back");
			et.rollback(); // HACER UN ROLLBACK

		} finally {
			em.close();
		}
	}

	public  <T> void modificarSGBD(T entidad) {

		try {

			et.begin();

			em.merge((T) entidad);

			et.commit();

			System.out.println("Registro actualizado correctamente");

		} catch (Exception ex) {
			System.out.println("printStack");

			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
			System.out.println("roll Back");
			et.rollback(); // HACER UN ROLLBACK

		} finally {
			em.close();
		}

	}

	public  <T> void eliminarSGBD(T entidadCompleta) {

		try {

			et.begin();

			em.remove((T) entidadCompleta);

			et.commit();

			System.out.println("Registro eliminado correctamente");

		} catch (Exception ex) {

			System.out.println("printStack");

			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
			System.out.println("roll Back");
			et.rollback(); // HACER UN ROLLBACK

		} finally {
			em.close();
		}

	}

	public  <T> void queryConsulta(String nombre, Class<T> ent) {

		try {

			et.begin();
			// TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findAll",
			// Categoria.class);
			TypedQuery<T> query = em.createNamedQuery(nombre, ent);
			List<T> lista = query.getResultList();

			et.commit();
			// recorrer y mostrar en pantalla o enviar a una tabla, etc.

			for (T objeto : lista) {

				System.out.println(objeto.toString());

			}

		} catch (Exception ex) {
			System.out.println("printStack");

			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
			System.out.println("roll Back");
			// et.rollback(); // HACER UN ROLLBACK

		} finally {
			em.close();
		}

	}

	private static final String INSERT = "INSERT into detallefactura values (?,?,?,?,?)";


	public static boolean insetarDetalleFactura(List<Detallefactura> lista) {

		conexion.Conexion con = new conexion.Conexion();
		boolean estado = false;

		try (Connection connection = con.ObtenerConexion()) {
			PreparedStatement pst = connection.prepareStatement(INSERT);
			for (Detallefactura detallefactura : lista) {
				try {
					pst.setString(1, detallefactura.getNumerofactura());
					pst.setInt(2, detallefactura.getIdproducto());
					pst.setInt(3, detallefactura.getCantidad());
					pst.setDouble(4, detallefactura.getPreciounitario());
					pst.setDouble(5, detallefactura.getPreciototal());

					int n = pst.executeUpdate();
					if (n != 0) {
						estado = true;
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage() + " Fallo en el ingreso a detalles en la base de datos" + "\n"
							+ ex.getStackTrace() + "\n" + ex.getSQLState());
					ex.getMessage();
					continue;
				}

			}

			connection.close();
			pst.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage() + " Fallo en el ingreso a la base de datos" + "\n"
					+ e.getStackTrace() + "\n" + e.getSQLState());
			e.getMessage();

		}

		return estado;
	}
	
	private static final String INSERT_DEVOLUCION = "INSERT into devolucion_de_cliente values (?,?,?,?,?)";

	public static boolean insetarDevolucionDeFacura(List<DevolucionDeCliente> lista) {

		conexion.Conexion con = new conexion.Conexion();
		boolean estado = false;

		try (Connection connection = con.ObtenerConexion()) {
			PreparedStatement pst = connection.prepareStatement(INSERT_DEVOLUCION);
			for (DevolucionDeCliente detallefactura : lista) {
				try {
					pst.setString(1, detallefactura.getNumerofactura());
					pst.setInt(2, detallefactura.getIdproducto());
					pst.setInt(3, detallefactura.getCantidad());
					pst.setString(4, detallefactura.getDescripcion());
					pst.setDate(5, java.sql.Date.valueOf(TablasTemporales.asLocalDate(detallefactura.getFecha())));

					int n = pst.executeUpdate();
					if (n != 0) {
						estado = true;
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
					System.out.println(ex.getMessage() + " Fallo en el ingreso a detalles en la base de datos" + "\n"
							+ ex.getStackTrace() + "\n" + ex.getSQLState());
					ex.getMessage();
					continue;
				}

			}

			connection.close();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();

		}

		return estado;
	}
	
	private final String INSERT_TELEFONO = "INSERT into telefonos values (?,?,?)";


	public boolean insetarTelefonoDAO(Telefono telefono) {

		conexion.Conexion con = new conexion.Conexion();
		boolean estado = false;

		try (Connection connection = con.ObtenerConexion()) {
			PreparedStatement pst = connection.prepareStatement(INSERT_TELEFONO);
			
			
			pst.setInt(1, telefono.getIdsocio());
					pst.setString(2, telefono.getTelefono());
					pst.setString(3, telefono.getReferencia());
					

					int n = pst.executeUpdate();
					if (n != 0) {
						estado = true;
					}


			connection.close();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + " Fallo en el telefono a la base de datos" + "\n"
					+ e.getStackTrace() + "\n" + e.getSQLState());
			e.getMessage();

		}

		return estado;
	}
	
	
}
