/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
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
	public SQLUtil (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqHotelAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (Long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos (PersistenceManager pm)
	{
//        Query qGustan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGustan ());          
//        Query qSirven = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSirven ());
//        Query qVisitan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitan ());
//        Query qBebida = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBebida ());
//        Query qTipoBebida = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoBebida ());
//        Query qBebedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBebedor ());
//        Query qBar = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar ());
//
//        long gustanEliminados = (Long) qGustan.executeUnique ();
//        long sirvenEliminados = (Long) qSirven.executeUnique ();
//        long visitanEliminadas = (Long) qVisitan.executeUnique ();
//        long bebidasEliminadas = (Long) qBebida.executeUnique ();
//        long tiposBebidaEliminados = (Long) qTipoBebida.executeUnique ();
//        long bebedoresEliminados = (Long) qBebedor.executeUnique ();
//        long baresEliminados = (Long) qBar.executeUnique ();
//        return new long[] {gustanEliminados, sirvenEliminados, visitanEliminadas, bebidasEliminadas, 
//        		tiposBebidaEliminados, bebedoresEliminados, baresEliminados};
		return null;
	}

}
