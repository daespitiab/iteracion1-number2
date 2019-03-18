package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLConsumoBar {
	
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
	public SQLConsumoBar(PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CONSUMO_BAR a la base de datos de Hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador del bar
	 * @param idCuenta - El nombre del bar
	 * @param cargarConsumo - La ciudad del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSQLConsumoRestaurante (PersistenceManager pm, long idProducto, long idCuenta, boolean cargarConsumo)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaConsumoRestaurante()+ "(idProducto, idCuenta, cargarConsumo) values ( ?, ?, ?)");
        q.setParameters(idProducto, idCuenta, cargarConsumo);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public ConsumoBar darConsumoBarPorId (PersistenceManager pm, long idProducto, long idCuenta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaConsumoBar() + " WHERE idProducto = ? AND idCuenta=?");
		q.setResultClass(ConsumoBar.class);
		q.setParameters(idProducto,idCuenta);
		return (ConsumoBar) q.executeUnique();
	}

}
