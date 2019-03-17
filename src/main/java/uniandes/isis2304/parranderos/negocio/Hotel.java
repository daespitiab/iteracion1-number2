package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Hotel implements VOHotel
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El id del Hotel
	 */
	private long id;
	
	/**
	 * El nombre del Hotel
	 */
	private String nombre;

	/**
	 * La clasificacion del hotel
	 */
	private long clasificacion;
	
	/**
	 * El numero de habitaciones disponibles del hotel
	 */
	private long numeroHabitacionesDisponibles;
	
	/**
	 * El nombre de la cadena hotelera del hotel
	 */
	private String cadenaHotelera;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Hotel() 
    {
    	this.id=0;
		this.nombre = "";
		this.clasificacion = 0;
		this.numeroHabitacionesDisponibles = 0;
		this.cadenaHotelera = "";
	}

	/**
	 * Constructor con valores
	 * @param nombre - El nombre del Hotel
	 * @param clasificacion - La clasificacion del Hotel
	 * @param numeroHabitacionesDisponibles - El numero de habitaciones disponibles
	 * @param cadenaHotelera - El nombre de la cadena hotelera
	 */
    public Hotel(long id,String nombre, long clasificacion, String cadenaHotelera, long numeroHabitacionesDisponibles) 
    {
    	this.id=id;
		this.nombre = nombre;
		this.clasificacion = clasificacion;
		this.cadenaHotelera = cadenaHotelera;
		this.numeroHabitacionesDisponibles = numeroHabitacionesDisponibles;
	}

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El nombre del hotel
	 */
    public String getNombre() {
		return nombre;
	}
    /**
	 * @param nombre - el nuevo nombre del hotel
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return La clasificacion del hotel
	 */
	public long getClasificacion() {
		return clasificacion;
	}
	/**
	 * @param clasificacion - la nueva clasificacion del hotel
	 */
	public void setClasificacion(long clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * @return El numero de habitaciones disponibles
	 */
	public long getNumeroHabitacionesDisponibles() {
		return numeroHabitacionesDisponibles;
	}
	/**
	 * @param numeroHabitacionesDisponibles - El nuevo numero de habitaciones disponibles
	 */
	public void setNumeroHabitacionesDisponibles(long numeroHabitacionesDisponibles) {
		this.numeroHabitacionesDisponibles = numeroHabitacionesDisponibles;
	}
	/**
	 * @return El nombre de la cadena hotelera
	 */
	public String getCadenaHotelera() {
		return cadenaHotelera;
	}
	/**
	 * @param cadenaHotelera - El nuevo nombre de la cadena hotelera
	 */
	public void setCadenaHotelera(String cadenaHotelera) {
		this.cadenaHotelera = cadenaHotelera;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Hotel
	 */
	public String toString() 
	{
		return "Bar [nombre=" + nombre + ", clasificacion=" + clasificacion + ", numero de habitaciones disponibles=" + numeroHabitacionesDisponibles + ", nombre cadena hotelera=" + cadenaHotelera
				+ "]";
	}

	
	

}
