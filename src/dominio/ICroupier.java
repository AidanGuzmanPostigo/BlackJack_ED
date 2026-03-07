package dominio;
/**
 * Interfaz encargada de la entidad Croupier.
 */
public interface ICroupier extends ICpu{
	/**
	 * Se encarga de mostrar la mano de la entidad pero ocultando la primera carta.
	 * @return La mano del croupier con la primera carta "boca abajo".
	 */
	String mostrarManoCroupier();
}