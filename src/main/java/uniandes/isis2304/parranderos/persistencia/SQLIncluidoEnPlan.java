package uniandes.isis2304.parranderos.persistencia;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;


class SQLIncluidoEnPlan 
{
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
	public SQLIncluidoEnPlan (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GUSTAN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarIncluidoEnPlan(PersistenceManager pm, long idProducto, long idPlan) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaIncluidoEnPlan() + "(idProducto, idPlan) values (?, ?)");
        q.setParameters(idProducto, idPlan);
        return (Long) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los GUSTAN de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos GUSTAN
	 */
//	public List<Gustan> darIncluidosEnPlan (PersistenceManager pm)
//	{
//		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaIncluidoEnPlan ());
//		q.setResultClass(IncluidoEnPlan.class);
//		List<IncluidoEnPlan> resp = (List<IncluidoEnPlan>) q.execute();
//		return resp;
//	}
}
