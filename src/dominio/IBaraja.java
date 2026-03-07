package dominio;
/**
 * Interfaz encargada de la baraja.
 */
public interface IBaraja {
	/**
	 * Se encarga de iniciar las barajas y añadir todas las cartas de la baraja francesa a esta.
	 * @param barajas Numero de barajas con las que se quiere jugar.
	 */
	void iniciar(int barajas);
	/**
	 * Elimina la primera carta del mazo y la devuelve.
	 * @return La primera carta del mazo.
	 */
	Carta draw();
}