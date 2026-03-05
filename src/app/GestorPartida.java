package app;
import dominio.IEntidad;
import dominio.IJugador;
import dominio.ICpu;
public class GestorPartida implements IGestorPartida{
	private IPartida p;
	private IEntradaSalida ci;
	private int numBarajas = 0;
	private int apuestaMinima= 50;
	private int bote = 0;
	public GestorPartida(IEntradaSalida ci) {
		p = new Partida();
		this.ci = ci;
	}
	@Override
	public void empezarPartida () {
		int balanceInicial= ci.readInt("Introduce la cantidad de fichas con las que se empezará.");
		int jugadores = ci.readIntInRange(1, 4, "Selecciona cuantos jugadores quieres (1-4).");
		String [] motes = new String[4];
		for (int i = 0; i< jugadores;i++) {
			motes[i] = ci.leerTexto("Introduce el mote para el jugador " + (i+1));
		}
		if(ci.readBooleanUsingChar('s', 'n', "Tienes que introducir s/n.", "¿Quieres tener un croupier?")) {
			p.activarCroupier();
		}
		p.prepararJugadores(balanceInicial, jugadores,motes);
		if (jugadores !=4) {
			p.prepararCpus(balanceInicial,ci.readIntInRange(jugadores==1?1:0,4-jugadores, String.format("Selecciona cuantas CPUs quieres (%d-%d)", jugadores==1?1:0,4-jugadores)));
		}
		numBarajas = ci.readIntInRange(1,2,"¿Cuántas barajas quieres? (1-2).");
		p.preparacionBarajas(numBarajas);
	}
	@Override
	public void empezarRonda() {
		p.preparacionBarajas(numBarajas);
		for (int i = 0; i<p.getEntidades().size();i++) {
			p.getEntidades().get(i).robar(p.getBaraja().draw());
			p.getEntidades().get(i).robar(p.getBaraja().draw());
		}
	}
	@Override
	public void calculoBote() {
		for (IEntidad entidad: p.getEntidades()) {
			switch (entidad) {
			case ICpu cpu-> cpu.apostar(apuestaMinima);
			case IJugador jugador -> jugador.apostar(ci.readIntGreaterOrEqualThan(apuestaMinima, "Elige la cantidad para la apuesta (mínimo" + apuestaMinima));
			default -> throw new IllegalArgumentException("Unexpected value: " + entidad);
			}
		}
	}
}