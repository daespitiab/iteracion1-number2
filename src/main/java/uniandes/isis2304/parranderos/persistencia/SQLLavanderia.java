package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLLavanderia {
	
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
	 * Crea y ejecuta la sentencia SQL para adicionar un LAVANDERIA a la base de datos de Hotelandes
	 * @param pm - El manejador de persistencia
	 * @param idLavanderia - El identificador del gimnasio
	 * @param capacidad - La capacidad del gimnasio
	 * @param costo - el costo del gimnasio
	 * @param incluido - indica si el costo esta incluido
	 * @param horaInicio - La hora a la que inicia el servicio del gimnasio
	 *  @param horaFin - La hora a la que finaliza el servicio del gimnasio
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSQLLavanderia (PersistenceManager pm, long idLavanderia, int capacidad, double costo, boolean incluido, String horaInicio, String horaFin  )
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLavanderia()+ "(idGimnasio, capacidad, costo, incluido, horaInicio, horaFin) values (?,?,?,?,?,?)");
        q.setParameters(idGimnasio, capacidad, costo, incluido, horaInicio, horaFin);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un servicio de LAVANDERIA de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idLavanderia - El identificador del bar
	 * @return El objeto LAVANDERIA que tiene el identificador dado
	 */
	public Lavanderia darLavanderiaPorId (PersistenceManager pm, long idLavanderia) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavanderia() + " WHERE id= ? ");
		q.setResultClass(Lavanderia.class);
		q.setParameters(idLavanderia);
		return (Lavanderia) q.executeUnique();
	}

}
