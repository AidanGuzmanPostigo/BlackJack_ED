package dominio;
/**
 * Interfaz encargada de las entidades Cpu.
 */
public interface ICpu extends IEntidad {
	/**
	 * Se encarga de leer la puntuación de la Cpu y tomar una decisión respecto a eso.
	 * @return Devuelve la elección tomada en forma de valores 0 (Se ha pasado), 1 (Se planta) o 2 (roba).
	 */
	int elegirJugada();
}