package app;
/**
 * Interfaz encargada de mostrar menús al usuario.
 */
public interface IMenu {
	/**
	 * Muestra un menú al principio del programa.
	 * @return Opción elegida por el usuario.
	 */
	int menuInicio();
	/**
	 * Muestra un menú durante el transcurso de las rondas.
	 * @return Opción elegida por el usuario.
	 */
	int opcionesRonda();
}