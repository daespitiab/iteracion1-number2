package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPiscina {
	

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
	public  SQLPiscina(PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PISCINA a la base de datos de Hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idPiscina - El identificador de la piscina
	 * @param capacidad - La capacidad de la piscina
	 * @param profundidad - La profundidad de la piscina
	 * @param costo - el costo de la piscina
	 * @param incluido - indica si el costo esta incluido
	 * @param horaInicio - La hora a la que inicia el servicio de la piscina
	 *  @param horaFin - La hora a la que finaliza el servicio de la piscina
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSQLPiscina (PersistenceManager pm, long idPiscina, int capacidad, double profundidad, double costo, boolean incluido, String horaInicio, String horaFin  )
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTiendas()+ "(idGimnasio, capacidad, profundidad, costo, incluido, horaInicio, horaFin) values (?,?,?,?,?,?,?)");
        q.setParameters(idPiscina, capacidad, profundidad, costo, incluido, horaInicio, horaFin);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un GIMNASIO de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Piscina darPiscinaPorId (PersistenceManager pm, long idPiscina) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscinas() + " WHERE id= ? ");
		q.setResultClass(Piscina.class);
		q.setParameters(idPiscina);
		return (Piscina) q.executeUnique();
	}

}
