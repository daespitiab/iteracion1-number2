package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;

public class SQLProductoRestaurante {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaHotelAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaHotelAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductoRestaurante (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarProductoRestaurante (PersistenceManager pm, long idProductoRestaurante, String nombre, long costo,long idRestaurante)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoRestaurante()+ "(id, nombre, costo, idRestaurante) values (?, ?, ?, ?)");
        q.setParameters(idProductoRestaurante, nombre, costo, idRestaurante);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
//	public ProductoRestaurante darProductoRestaurantePorId (PersistenceManager pm, long idProductoRestaurante) 
//	{
//		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoRestaurante () + " WHERE id = ?");
//		q.setResultClass(ProductoRestaurante.class);
//		q.setParameters(idProductoRestaurante);
//		return (ProductoRestaurante) q.executeUnique();
//	}

}
