package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLSupermercado {
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
	public SQLSupermercado (PersistenciaHotelAndes pp)
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
	public long adicionarSQLSupermercado (PersistenceManager pm, long idSupermercado, String nombre)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSupermercado()+ "(idSupermercado,nombre) values (?, ?)");
        q.setParameters(idSupermercado, nombre);
        return (Long) q.executeUnique();
	}



//	public Supermercado darSupermercadoPorId (PersistenceManager pm, long idSupermercado) 
//	{
//		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoRestaurante () + " WHERE id= ? ");
//		q.setResultClass(Supermercado.class);
//		q.setParameters(idSupermercado);
//		return (Supermercado) q.executeUnique();
//	}

}
