package uniandes.isis2304.parranderos.negocio;
/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOHotel 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	
	/**
	 * @return el nombre del hotel
	 */
	public String getNombre();
	
	/**
	 * @return la clasificacion del hotel
	 */
	public long getClasificacion();
	
	/**
	 * @return El nombre de la cadena hotelera del hotel
	 */
	public String getCadenaHotelera();
	
	/**
	 * @return el numero de habitaciones disponibles
	 */
	public long getNumeroHabitacionesDisponibles();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
