package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLGimnasio {

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
	public  SQLGimnasio(PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GIMNASIO a la base de datos de Hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idGimnasio - El identificador del gimnasio
	 * @param capacidad - La capacidad del gimnasio
	 * @param costo - el costo del gimnasio
	 * @param incluido - indica si el costo esta incluido
	 * @param horaInicio - La hora a la que inicia el servicio del gimnasio
	 *  @param horaFin - La hora a la que finaliza el servicio del gimnasio
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSQLGimnasio (PersistenceManager pm, long idGimnasio, int capacidad, double costo, boolean incluido, String horaInicio, String horaFin  )
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaGimnasios()+ "(idGimnasio, capacidad, costo, incluido, horaInicio, horaFin) values (?,?,?,?,?,?)");
        q.setParameters(idGimnasio, capacidad, costo, incluido, horaInicio, horaFin);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un GIMNASIO de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Gimnasio darGimnasioPorId (PersistenceManager pm, long idGimnasio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasios() + " WHERE id= ? ");
		q.setResultClass(Gimnasio.class);
		q.setParameters(idGimnasio);
		return (Gimnasio) q.executeUnique();
	}
	
}
