package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Bar;
import uniandes.isis2304.parranderos.negocio.Bebedor;
import uniandes.isis2304.parranderos.negocio.Bebida;
import uniandes.isis2304.parranderos.negocio.Gustan;
import uniandes.isis2304.parranderos.negocio.Sirven;
import uniandes.isis2304.parranderos.negocio.TipoBebida;
import uniandes.isis2304.parranderos.negocio.Visitan;

/**
 * Clase para el manejador de persistencia del proyecto HotelAndes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Daniel Espitia
 */
public class PersistenciaHotelAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaHotelAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaHotelAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;	
	private SQLAdministradorDatos sqlAdministradorDatos;
	private SQLAdministradorSistema sqlAdministradorSistema;
	private SQLAnaquel sqlAnaquel;
	private SQLCliente sqlCliente;
	private SQLConsumoRestaurante sqlConsumoRestaurante;
	private SQLConsumoSupermercado sqlConsumoSupermercado;
	private SQLConsumoTienda sqlConsumoTienda;
	private SQLCuenta sqlCuenta;
	private SQLEmpleado sqlEmpleado;
	private SQLGerente sqlGerente;
	private SQLHotel sqlHotel;
	private SQLIncluidoEnPlan sqlIncluidoEnPlan;
	private SQLPlanConsumo sqlPlanConsumo;
	private SQLPlanConsumoReserva sqlPlanConsumoReserva;
	private SQLProductoRestaurante sqlProductoRestaurante;
	private SQLProductoSupermercado sqlProductoSupermercado;
	private SQLProductoTienda sqlProductoTienda;
	private SQLRecepcionista sqlRecepcionista;
	private SQLReservaCliente sqlReservaCliente;
	private SQLReservaHabitacion sqlReservaHabitacion;
	private SQLRestaurante sqlRestaurante;
	private SQLRolUsuario sqlRolUsuario;
	private SQLSupermercado sqlSupermercado;
	private SQLTienda sqlTienda;
	private SQLUsuario sqlUsuario;

	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaHotelAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("HotelAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("HotelAndes_sequence");
		tablas.add ("HOTEL");
		tablas.add ("HABITACION");
		tablas.add ("TIPOHABITACION");
		tablas.add ("ESDETIPO");
		tablas.add ("PISCINA");
		tablas.add ("GIMNASIO");
		tablas.add ("MAQUINA");
		tablas.add ("MAQUINAGIMNASIO");
		tablas.add ("INTERNET");
		tablas.add ("SERVICIOINTERNET");
		tablas.add ("LAVANDERIA");
		tablas.add ("SPA");
		tablas.add ("SERVICIOSPA");
		tablas.add ("RESERVASERVICIOSPA");		
		tablas.add ("UTENCILIO");
		tablas.add ("ESTADOUTENSILIO");
		tablas.add ("PRESTAMOUTENSILIO");
		tablas.add ("SALONES");
		tablas.add ("TIPOSALON");
		tablas.add ("RESERVASALON");
		tablas.add ("SERVICIOSALON");
		tablas.add ("CONSUMOSERVICIOSALON");
		tablas.add ("BAR");
		tablas.add ("PRODUCTOBAR");
		tablas.add ("CONSUMOBAR");
		tablas.add ("INCLUIDOENPLAN");
		tablas.add ("RESTAURANTE");
		tablas.add ("PRODUCTORESTAURANTE");
		tablas.add ("CONSUMORESTAURANTE");
		tablas.add ("SUPERMERCADO");
		tablas.add ("ANAQUEL");
		tablas.add ("PRODUCTOSUPERMERCADO");
		tablas.add ("CONSUMOSUPERMERCADO");
		tablas.add ("TIENDA");
		tablas.add ("PRODUCTOTIENDA");
		tablas.add ("CONSUMOTIENDA");
		tablas.add ("RESERVACLIENTE");
		tablas.add ("RESERVAHABITACION");
		tablas.add ("PLANCONSUMO");
		tablas.add ("RECEPCIONISTA");
		tablas.add ("ADMINISTRADORDEDATOS");
		tablas.add ("CLIENTE");
		tablas.add ("CUENTA");
		tablas.add ("PLANCONSUMORESERVA");
		tablas.add ("ADMINISTRADORSISTEMA");
		tablas.add ("EMPLEADO");
		tablas.add ("GERENTE");
		tablas.add ("USUARIO");
		tablas.add ("ROLUSUARIO");
		
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaHotelAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaHotelAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaHotelAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaHotelAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaHotelAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaHotelAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaHotelAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlAdministradorDatos= new SQLAdministradorDatos(this);
		sqlAdministradorSistema= new SQLAdministradorSistema(this);
		sqlAnaquel= new SQLAnaquel(this);
		sqlCliente= new SQLCliente(this);
		sqlConsumoRestaurante=new SQLConsumoRestaurante(this);
		sqlConsumoSupermercado= new SQLConsumoSupermercado(this);
		sqlConsumoTienda=new SQLConsumoTienda(this);
		sqlCuenta= new SQLCuenta(this);
		sqlEmpleado= new SQLEmpleado(this);
		sqlGerente= new SQLGerente(this);
		sqlHotel=new SQLHotel(this);
		sqlIncluidoEnPlan= new SQLIncluidoEnPlan(this);
		sqlPlanConsumo= new SQLPlanConsumo(this);
		sqlPlanConsumoReserva= new SQLPlanConsumoReserva(this);
		sqlProductoRestaurante= new SQLProductoRestaurante(this);
		sqlProductoSupermercado= new SQLProductoSupermercado(this);
		sqlProductoTienda= new SQLProductoTienda(this);
		sqlRecepcionista= new SQLRecepcionista(this);
		sqlReservaCliente= new SQLReservaCliente(this);
		sqlReservaHabitacion= new SQLReservaHabitacion(this);
		sqlRestaurante= new SQLRestaurante(this);
		sqlRolUsuario= new SQLRolUsuario(this);
		sqlSupermercado= new SQLSupermercado(this);
		sqlTienda= new SQLTienda(this);
		sqlUsuario= new SQLUsuario(this);
		sqlUtil= new SQLUtil(this);

	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de hotel andes
	 */
	public String darSeqHotelAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaHoteles()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaHabitaciones ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaTipoHabitaciones ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaEsDeTipo ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaPiscinas ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaGimnasios ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaMaquinas ()
	{
		return tablas.get (7);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaMaquinasGimnasio ()
	{
		return tablas.get (8);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaInternet ()
	{
		return tablas.get (9);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServicioInternet ()
	{
		return tablas.get (10);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaLavanderia ()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaCostosLavanderia ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServicioLavanderia ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaSpas ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServiciosSpa ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaUtensilios ()
	{
		return tablas.get (16);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaEstadoUtensilios ()
	{
		return tablas.get (17);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPrestamoUtensilios()
	{
		return tablas.get (18);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaSalones()
	{
		return tablas.get (19);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaTiposSalon ()
	{
		return tablas.get (20);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaReservasSalon ()
	{
		return tablas.get (21);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServicioSalon()
	{
		return tablas.get (22);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaConsumoServicioSalon ()
	{
		return tablas.get (23);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaBares ()
	{
		return tablas.get (24);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaProductosBar ()
	{
		return tablas.get (25);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaConsumoBar ()
	{
		return tablas.get (26);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaIncluidoEnPlan ()
	{
		return tablas.get (27);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaRestaurantes ()
	{
		return tablas.get (28);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaProductoRestaurante ()
	{
		return tablas.get (29);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaConsumoRestaurante ()
	{
		return tablas.get (30);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaSupermercado ()
	{
		return tablas.get (32);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaAnaqueles ()
	{
		return tablas.get (33);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaProductosSupermercados ()
	{
		return tablas.get (34);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaConsumoSupermercado ()
	{
		return tablas.get (35);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaTiendas ()
	{
		return tablas.get (36);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaProductoTienda ()
	{
		return tablas.get (37);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaConsumoTienda ()
	{
		return tablas.get (38);
	}	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaReservaCliente()
	{
		return tablas.get (39);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaReservaHabitacion ()
	{
		return tablas.get (40);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPlanesConsumo ()
	{
		return tablas.get (41);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaRecepcionistas ()
	{
		return tablas.get (42);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaAdministradoresDatos ()
	{
		return tablas.get (43);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaClientes ()
	{
		return tablas.get (44);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaCuentas ()
	{
		return tablas.get (45);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPlanesConsumoReserva ()
	{
		return tablas.get (46);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaAdministradoresSistema ()
	{
		return tablas.get (47);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaEmpleados ()
	{
		return tablas.get (48);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaGerentes ()
	{
		return tablas.get (49);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaUsuarios ()
	{
		return tablas.get (50);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaRolesUsuario ()
	{
		return tablas.get (51);
	}
	
	
	
	
	/**
	 * Transacción para el generador de secuencia de HotelAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	//ADICIONAR METODOS

	public IncluidoEnPlan adicionarIncluidoEnPlan(long idProducto, long idPlan) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlIncluidoEnPlan.adicionarIncluidoEnPlan(pm,idProducto, idPlan);
            tx.commit();
            
            log.trace ("Inserción IncluidoEnPlan: " + idProducto+","+idPlan+ ": " + tuplasInsertadas + " tuplas insertadas");
            return new IncluidoEnPlan (idProducto,idPlan);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Restaurante adicionarRestaurante(String nombre, long capacidad, String estilo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlRestaurante.adicionarRestaurante(pm, idBebida, nombre, capacidad, estilo);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Restaurante (idBebida,nombre, capacidad, estilo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ProductoRestaurante adicionarProductoRestaurante(String nombre, long costo, long idRes) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlProductoRestaurante.adicionarProductoRestaurante(pm, idBebida, nombre, costo, idRes);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ProductoRestaurante (idBebida, nombre, costo, idRes);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ConsumoRestaurante adicionarConsumoRestaurante(long idPro, long idCuenta, boolean cargarConsumo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlConsumoRestaurante.adicionarSQLConsumoRestaurante(pm, idPro, idCuenta, cargarConsumo);
            tx.commit();
            
            log.trace ("Inserción bebida: " + idPro + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ConsumoRestaurante (idPro,idCuenta,cargarConsumo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Supermercado adicionarSupermercado(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlSupermercado.adicionarSQLSupermercado(pm, idBebida, nombre);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Supermercado (idBebida,nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Anaquel adicionarAnaquel(String nombre, long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlAnaquel.adicionarSQLAnaquel(pm, idBebida, nombre, id);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Anaquel (idBebida,nombre, id);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ProductoSupermercado adicionarProductoSupermercado(String nombre, long costo, long idAnaq) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlProductoSupermercado.adicionarSQLProductoSupermercado(pm, idBebida, nombre, costo, idAnaq);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ProductoSupermercado (idBebida,nombre, costo, idAnaq);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ConsumoSupermercado adicionarConsumoSupermercado(long idPro, long idCue, boolean cargar) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlConsumoSupermercado.adicionarSQLConsumoSupermercado(pm, idPro, idCue, cargar);
            tx.commit();
            
            log.trace ("Inserción bebida: " + idPro + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ConsumoSupermercado (idPro,idCue, cargar);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Tienda adicionarTienda(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlTienda.adicionarSQLTienda(pm, idBebida, nombre);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Tienda (idBebida,nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ProductoTienda adicionarProductoTienda(String nombre, long costo, long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlProductoTienda.adicionarSQLProductoTienda(pm, idBebida, nombre, costo, id);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ProductoTienda (idBebida,nombre, costo, id);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ConsumoTienda adicionarConsumoTienda(long idPro, long idCue,boolean cargarConsumo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlConsumoTienda.adicionarSQLConsumoTienda(pm, idPro, idCue, cargarConsumo);
            		tx.commit();
            
            log.trace ("Inserción bebida: " + idPro + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ConsumoTienda (idPro,idCue, cargarConsumo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ReservaCliente adicionarReservaCliente(long numPer, Date inicio, Date fin,long idPlan,long idClie) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlReservaCliente.adicionarSQLReservaCliente(pm, idBebida, numPer,inicio , fin, idPlan, idClie);
            tx.commit();
            
            log.trace ("Inserción bebida: " + idBebida + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ReservaCliente (idBebida,numPer, inicio, fin,idPlan,idClie);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Cliente adicionarCliente(long numDocu, String tipoDocu, String nombre,String correo,String contra) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarSQLCliente(pm, numDocu, tipoDocu, nombre, correo, contra);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Cliente (numDocu, tipoDocu, nombre, correo, contra);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ReservaHabitacion adicionarReservaHabitacion(long idReser, long idHab, boolean checkIn,boolean checkOut)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlReservaHabitacion.adicionarSQLReservaHabitacion(pm, idReser, idHab, checkIn, checkOut);
            tx.commit();
            
            log.trace ("Inserción bebida: " + idReser + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ReservaHabitacion (idReser, idHab, checkIn, checkOut);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Cuenta adicionarCuenta(long costo, boolean paz,long idReser) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlCuenta.adicionarSQLCuenta(pm, idBebida, costo, paz, idReser);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Cuenta (idBebida, costo, paz, idReser);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public  PlanConsumo adicionarPlanConsumo(String nombre, long porce, long cost,Date inicio,Date fin) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlPlanConsumo.adicionarSQLPlanConsumo(pm, idBebida, nombre, porce, cost, inicio, fin);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new PlanConsumo (idBebida, nombre, porce, cost, inicio, fin);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public  PlanConsumoReserva adicionarPlanConsumoReserva(long idReser, long idPlan)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idBebida = nextval ();
            long tuplasInsertadas = sqlPlanConsumoReserva.adicionarSQLPlanConsumoReserva(pm, idReser, idPlan);
            tx.commit();
            
            log.trace ("Inserción bebida: " + idReser + ": " + tuplasInsertadas + " tuplas insertadas");
            return new PlanConsumoReserva (idReser, idPlan);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
//	public  Recepcionista adicionarRecepcionista(long numDocu, String tipoDocu, String nombre,String correo,String contra)  
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long idBebida = nextval ();
//            long tuplasInsertadas = sqlRecepcionista.adicionarSQLRecepcionista(pm, numDocu, tipoDocu, nombre, correo, contra);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new Recepcionista (numDocu, tipoDocu, nombre, correo, contra);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//	public  AdministradorSistema adicionarAdministradorSistema(long numDocu, String tipoDocu, String nombre,String correo,String contra)  
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long idBebida = nextval ();
//            long tuplasInsertadas = sqlAdministradorSistema.adicionarSQLAdministradorSistema(pm, numDocu, tipoDocu, nombre, correo, contra);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new AdministradorSistema (numDocu, tipoDocu, nombre, correo, contra);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//	public  Empleado adicionarEmpleado(long numDocu, String tipoDocu, String nombre,String correo,String contra)  
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long idBebida = nextval ();
//            long tuplasInsertadas = sqlEmpleado.adicionarSQLEmpleado(pm, numDocu, tipoDocu, nombre, correo, contra);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new Empleado (numDocu, tipoDocu, nombre, correo, contra);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//	public  Gerente adicionarGerente(String nombre, long idTipoBebida, int gradoAlcohol) 
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long idBebida = nextval ();
//            long tuplasInsertadas = sqlBebida.adicionarBebida(pm, idBebida, nombre, idTipoBebida, gradoAlcohol);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new Bebida (idBebida,nombre, idTipoBebida, gradoAlcohol);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//	public  Usuario adicionarUsuario(String nombre, long idTipoBebida, int gradoAlcohol) 
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long idBebida = nextval ();
//            long tuplasInsertadas = sqlBebida.adicionarBebida(pm, idBebida, nombre, idTipoBebida, gradoAlcohol);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new Bebida (idBebida,nombre, idTipoBebida, gradoAlcohol);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//	public  RolUsuario adicionarRolUsuario(String nombre, long idTipoBebida, int gradoAlcohol) 
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long idBebida = nextval ();
//            long tuplasInsertadas = sqlBebida.adicionarBebida(pm, idBebida, nombre, idTipoBebida, gradoAlcohol);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new Bebida (idBebida,nombre, idTipoBebida, gradoAlcohol);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//	/**
//	 * Método que consulta todas las tuplas en la tabla Bebida que tienen el nombre dado
//	 * @param nombreBebida - El nombre de la bebida
//	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
//	 */
//	public IncluidoEnPlan darIncluidoEnPlan (long id1,long id2)
//	{
//		return sqlIncluidoEnPlan.darIncluidosEnPlan(pmf);
//	}
//	
 
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	

 }