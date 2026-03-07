package dominio;
/**
 * Interfaz que representa a una entidad del juego.<br>
 * Contiene comportamientos de las entidades.
 */
public interface IEntidad {
	/**
	 * Roba una carta y se le añade a la mano de la entidad.
	 * @param cartaRobada Carta a añadir a la mano de la entidad.
	 */
	void robar(Carta cartaRobada);
	/**
	 * Calcula la puntuación de la entidad, la almacena en la entidad y la devuelve.
	 * @return Puntuacion de la entidad.
	 */
	int calcularPuntuacion();
	/**
	 * Hace que la entidad se plante.
	 */
	void plantarse();
	/**
	 * Hace que la mano de la entidad se limpie.
	 */
	void limpiarMano();
	/**
	 * Muestra la mano de la entidad.
	 * @return Mano de la entidad.
	 */
	String mostrarMano();
	/**
	 * Se encarga de comprobar si una entidad sigue en pie.
	 * @return True si está en pie o false si no.
	 */
	boolean isAlive();
	/**
	 * Se encarga de comprobar si una entidad está pasada.
	 * @return True si está en pasada o false si no.
	 */
	boolean isDead();
	/**
	 * Muestra el mote de la entidad.
	 * @return Mote de la entidad.
	 */
	String getMote();
}