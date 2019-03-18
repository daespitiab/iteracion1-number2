package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLBar {

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
	public  SQLBar(PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @param nombre - El nombre del bar
	 * @param capacidad - La capacidad del bar
	 * @param estilo - El estilo del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSQLBar (PersistenceManager pm, long idBar,String nombre, int capacidad, String estilo)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBares()+ "(idBar, nombre, capacidad, estilo) values (?,?,?,?)");
        q.setParameters(idBar, nombre);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Bar darBarPorId (PersistenceManager pm, long idBar) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBares() + " WHERE id= ? ");
		q.setResultClass(Bar.class);
		q.setParameters(idBar);
		return (Bar) q.executeUnique();
	}
	
}
