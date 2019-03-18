package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUsuario {
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
	public SQLUsuario (PersistenciaHotelAndes pp)
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
	public long adicionarSQLUsuario(PersistenceManager pm, long numDocu,String tipoDocu,String nombre,String correo,String contrase�a,long idRol)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuarios()+ "(numDocu,tipoDocu,nombre,correo,contrase�a,idRol) values (?,?,?,?,?,?)");
        q.setParameters(numDocu,tipoDocu,nombre,correo,contrase�a,idRol);
        return (Long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Usuario darUsuarioPorId (PersistenceManager pm, long idAnaquel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarios () + " WHERE id= ? ");
		q.setResultClass(Usuario.class);
		q.setParameters(idAnaquel);
		return (Usuario) q.executeUnique();
	}
	
}
