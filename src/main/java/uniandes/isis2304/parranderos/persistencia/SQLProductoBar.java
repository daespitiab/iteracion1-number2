package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoBar {
	
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
	public SQLProductoBar(PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idProductoBar - El identificador del bar
	 * @param nombre - El nombre del producto del bar
	 * @param costo - El costo del producto del bar
	 * @param idBar - El id del bar dueño del producto
	 * @return El número de tuplas insertadas
	 */
	public long adicionarProductoBar (PersistenceManager pm, long idProductoBar, String nombre, long costo,long idBar)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosBar()+ "(idProductoBar, nombre, costo, idBar) values (?, ?, ?, ?)");
        q.setParameters(idProductoBar, nombre, costo, idBar);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PRODUCTO_BAR de la 
	 * base de datos de Hotelandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProductoBar - El identificador del producto
	 * @return El objeto PRODUCTO_BAR que tiene el identificador dado
	 */
	public ProductoBar darProductoBarPorId(PersistenceManager pm, long idProductoBar) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductosBar() + " WHERE id = ?");
		q.setResultClass(ProductoBar.class);
		q.setParameters(idProductoBar);
		return (ProductoBar) q.executeUnique();
	}
}
