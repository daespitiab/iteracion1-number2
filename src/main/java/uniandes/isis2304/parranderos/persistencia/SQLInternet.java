package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLInternet {

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
	public  SQLInternet(PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un INTERNET a la base de datos de Hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idGimnasio - El identificador del internet
	 * @param capacidad - La capacidad del internet
	 * @param costo - el costo del internet
	 * @param incluido - indica si el costo esta incluido
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSQLInternet (PersistenceManager pm, long idInternet, int capacidad, double costo, boolean incluido)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTiendas()+ "(idInternet, capacidad, costo, incluido, horaInicio, horaFin) values (?,?,?,?,?,?)");
        q.setParameters(idInternet, capacidad, costo, incluido);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un servicio de INTERNET de la 
	 * base de datos de hotelandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idInternet - El identificador del internet
	 * @return El objeto INTERNET que tiene el identificador dado
	 */
	public Internet darInternetPorId (PersistenceManager pm, long idInternet) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInternet() + " WHERE id= ? ");
		q.setResultClass(Internet.class);
		q.setParameters(idInternet);
		return (Internet) q.executeUnique();
	}
}
