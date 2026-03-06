package app;
import java.util.ArrayList;
import java.util.List;
import dominio.Cpu;
import dominio.Croupier;
import dominio.Entidad;
import dominio.IBaraja;
import dominio.ICpu;
import dominio.ICroupier;
import dominio.IEntidad;
public class Partida implements IPartida{
	private List<IEntidad> entidades;
	private IEntradaSalida ci;
	private IBaraja baraja;
	private int numBarajas = 0;
	private IMenu menu;
	public Partida(IEntradaSalida ci, IBaraja baraja, IMenu menu) {
		entidades = new ArrayList<>();
		this.baraja = baraja;
		this.ci = ci;
		this.menu = menu;
	}
	private void prepararJugadores(int veces, String ... motes) {
		for (int i = 0; i<veces;i++) {
			agregarEntidad(new Entidad(motes[i]));
		}
	}
	private void prepararCpus(int veces) {
		for (int i = 0; i<veces;i++) {
			agregarEntidad(new Cpu());
		}
	}
	private void activarCroupier() {
		agregarEntidad(new Croupier());
	}
	private void agregarEntidad(IEntidad entidad) {
		getEntidades().add(entidad);
	}
	private void preparacionBarajas(int veces) {
		getBaraja().iniciar(veces);
	}
	@Override
	public void empezarPartida () {
		int jugadores = ci.readIntInRange(1, 4, "Selecciona cuantos jugadores quieres (1-4).");
		String [] motes = new String[4];
		for (int i = 0; i< jugadores;i++) {
			motes[i] = ci.leerTexto("Introduce el mote para el jugador " + (i+1));
		}
		if(ci.readBooleanUsingChar('s', 'n', "Tienes que introducir s/n.", "¿Quieres tener un croupier?")) {
			activarCroupier();
		}
		prepararJugadores(jugadores,motes);
		if (jugadores !=4) {
			prepararCpus(ci.readIntInRange(jugadores==1?1:0,4-jugadores, String.format("Selecciona cuantas CPUs quieres (%d-%d)", jugadores==1?1:0,4-jugadores)));
		}
		setNumBarajas(ci.readIntInRange(1,2,"¿Cuántas barajas quieres? (1-2)."));
		preparacionBarajas(getNumBarajas());
		empezarRonda();
	}
	public void setNumBarajas(int numBarajas) {
		this.numBarajas = numBarajas;
	}
	private void empezarRonda() {
		preparacionBarajas(numBarajas);
		limpiarManos();
		roboGlobal();
		roboGlobal();
		mostrarManos(false);
		puntuacionRonda(false);
		while (!isFinished()) {
			loopRonda();
			mostrarManos(false);
			puntuacionRonda(false);
		}
		if (getEntidades().get(0) instanceof ICroupier) {
			turnoCroupier();
		}
		calcularGanador();
	}
	private void mostrarManos(boolean isLast) {
		for (int i = 0; i<getEntidades().size();i++) {
			if (getEntidades().get(i) instanceof ICroupier && !isLast) {
				ci.escribirLinea(((ICroupier) getEntidades().get(i)).mostrarManoCroupier());
			} else {
				ci.escribirLinea(getEntidades().get(i).mostrarMano());
			}
		}
		ci.escribirLinea("");
	}
	private void puntuacionRonda(boolean isLast) {
		ci.escribirLinea("PUNTUACIÓN");
		for (int i = 0; i<getEntidades().size();i++) {
			getEntidades().get(i).calcularPuntuacion();
			if (getEntidades().get(i) instanceof ICroupier && !isLast) {
				ci.escribirLinea("Croupier, ??");
			} else {
				ci.escribirLinea(getEntidades().get(i).toString());
			}
		}
		ci.escribirLinea("");
	}
	private void loopRonda() {
		for (int i = 0; i< getEntidades().size();i++) {
			if (!(getEntidades().get(i) instanceof ICroupier)) {
				if (getEntidades().get(i) instanceof ICpu && getEntidades().get(i).isAlive()) {
					if(((ICpu) getEntidades().get(i)).elegirJugada() == 1) {
						getEntidades().get(i).plantarse();
					} else if (((ICpu) getEntidades().get(i)).elegirJugada() == 2) {
						getEntidades().get(i).robar(getBaraja().draw());
						getEntidades().get(i).calcularPuntuacion();
					}
				} else if (getEntidades().get(i).isAlive()) {
					ci.escribirLinea(String.format("TURNO DE: %s", getEntidades().get(i).getMote()));
					leerOpcion(menu.opcionesRonda(),i);
				}
			}
		}
	}
	private void leerOpcion(int opcion, int aux) {
		switch(opcion){
			case 1 -> getEntidades().get(aux).plantarse();
			case 2 -> {
				getEntidades().get(aux).robar(getBaraja().draw());
				getEntidades().get(aux).calcularPuntuacion();
			}
		}
	}
	private void roboGlobal() {
		for (IEntidad entidad: getEntidades()) {
			entidad.robar(getBaraja().draw());
		}
	}
	private void limpiarManos(){
		for (IEntidad entidad: getEntidades()) {
			entidad.limpiarMano();
		}
	}
	private boolean isFinished() {
		for (IEntidad entidad : getEntidades()) {
			if (entidad.isAlive() && !(entidad instanceof ICroupier)) {
				return false;
			}
		}
		return true;
	}
	private void turnoCroupier() {
		if(((ICpu) getEntidades().get(0)).elegirJugada() == 1) {
			getEntidades().get(0).plantarse();
		} else if (((ICpu) getEntidades().get(0)).elegirJugada() == 2) {
			getEntidades().get(0).robar(getBaraja().draw());
			getEntidades().get(0).calcularPuntuacion();
		}
		mostrarManos(true);
		puntuacionRonda(true);
	}
	private void calcularGanador() {
		boolean isTie=false,areAllDead=true;
		StringBuilder ganador = new StringBuilder ("");
		int puntGanador=0;
		for (int i = 0; i<getEntidades().size();i++) {
			if (!getEntidades().get(i).isDead()) {
				if (getEntidades().get(i).calcularPuntuacion() > puntGanador) {
					ganador = new StringBuilder(getEntidades().get(i).getMote());
					puntGanador = getEntidades().get(i).calcularPuntuacion();
					areAllDead=false;
				} else if (getEntidades().get(i).calcularPuntuacion() == puntGanador) {
					ganador.append(getEntidades().get(i).getMote());
					isTie = true;
					areAllDead = false;
				}
			}
		}
		if (areAllDead) {
			isTie = true;
			for (int i = 0; i<getEntidades().size();i++) {
				ganador.append(getEntidades().get(i));
			}
		}
		if (isTie && areAllDead) {
			ci.escribirLinea("Empate, todos se han pasado.");
		} else if (isTie && !areAllDead) {
			ci.escribirLinea(String.format("Empate entre los siguientes jugadores: %s", ganador));
		} else {
			ci.escribirLinea(String.format("El ganador es: %s",ganador));
		}
	}
	public IBaraja getBaraja() {
		return baraja;
	}
	public List<IEntidad> getEntidades() {
		return entidades;
	}
	public int getNumBarajas() {
		return numBarajas;
	}
}