package app;
/**
 * Clase encargada de mostrar menús al usuario.
 */
public class Menu implements IMenu {
	private IEntradaSalida ci;
	/**
	 * Constructor de la clase.
	 * @param ci Una instancia de clase que tenga los métodos de la interfaz para comunicarse con el usuario.
	 * @return Menu instanciado.
	 */
	public Menu(IEntradaSalida ci){
		this.ci = ci;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public int menuInicio() {
		ci.escribirLinea("        MENÚ\n   ______________\n");
		return ci.readIntInRange(1, 2,"1. Empezar partida\n2. Salir del programa.");
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public int opcionesRonda() {
		return ci.readIntInRange(1, 2, String.format("1.Plantarse\n2.Pedir carta"));
	}
}