package app;
public class Menu implements IMenu {
	private IEntradaSalida ci;
	public Menu(IEntradaSalida ci){
		this.ci = ci;
	}
	@Override
	public int menuInicio() {
		ci.escribirLinea("        MENÚ\n   ______________\n");
		return ci.readIntInRange(1, 2,"1. Empezar partida\n2. Salir del programa.");
	}
	@Override
	public int opcionesRonda() {
		return ci.readIntInRange(1, 2, String.format("1.Plantarse\n2.Pedir carta"));
	}
}