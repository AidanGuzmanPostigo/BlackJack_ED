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
/**
 * Clase encargada de la gestión de la partida.
 */
public class Partida implements IPartida{
	private List<IEntidad> entidades;
	private IEntradaSalida ci;
	private IBaraja baraja;
	private int numBarajas = 0;
	private IMenu menu;
	/**
	 * Constructor de la clase.
	 * @param ci Interfaz para la comunicación con el usuario.
	 * @param baraja Interfaz para el uso de una baraja.
	 * @param menu Interfaz para el uso del menú.
	 * @return Partida instanciada.
	 */
	public Partida(IEntradaSalida ci, IBaraja baraja, IMenu menu) {
		entidades = new ArrayList<>();
		this.baraja = baraja;
		this.ci = ci;
		this.menu = menu;
	}
	/**
	 * Se encarga de agregar una entidad a la lista de las entidades de la partida.
	 * @param entidad Interfaz de entidad a añadir a la partida.
	 */
	private void agregarEntidad(IEntidad entidad) {
		getEntidades().add(entidad);
	}
	/**
	 * Se encarga de agregar los jugadores a la lista de las entidades de la partida.
	 * @param veces Número de jugadores a añadir.
	 * @param motes Cantidad x de motes para agregar a los jugadores.
	 */
	private void prepararJugadores(int veces, String ... motes) {
		for (int i = 0; i<veces;i++) {
			agregarEntidad(new Entidad(motes[i]));
		}
	}
	/**
	 * Se encarga de agregar a las Cpus a la lista de las entidades de la partida.
	 * @param veces Número de Cpus a añadir.
	 */
	private void prepararCpus(int veces) {
		for (int i = 0; i<veces;i++) {
			agregarEntidad(new Cpu());
		}
	}
	/**
	 * Se encarga de agregar un Croupier a la lista de entidades.
	 */
	private void activarCroupier() {
		agregarEntidad(new Croupier());
	}
	/**
	 * Inicia la baraja de la partida un número x de veces.
	 * @param veces Número de barajas a añadir.
	 */
	private void preparacionBarajas(int veces) {
		getBaraja().iniciar(veces);
	}
	/**
	 * @inheritDoc
	 */
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
	/**
	 * Método que llama en orden a los métodos necesarios para empezar la ronda y gestiona los turnos.
	 */
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
	/**
	 * Se encarga de decirle a las entidades que muestren sus manos y las imprime.
	 * @param isLast Dice si es o no el turno del croupier.
	 */
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
	/**
	 * Se encarga de decirle a las entidades que calculen su puntuación y las imprime.
	 * @param isLast Dice si es o no el turno del croupier.
	 */
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
	/**
	 * Se encarga del loop jugable, gestiona los turnos entidad por entidad salvo si es el croupier, si es Cpu esta elegirá su opción y si es un jugador mostrará las opciones.
	 */
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
	/**
	 * Lee la opción introducida por el usuario.
	 * @param opcion Puede ser o plantarse (1) o robar carta (2)
	 * @param aux Posición de la entidad en la lista de entidades.
	 */
	private void leerOpcion(int opcion, int aux) {
		switch(opcion){
			case 1 -> getEntidades().get(aux).plantarse();
			case 2 -> {
				getEntidades().get(aux).robar(getBaraja().draw());
				getEntidades().get(aux).calcularPuntuacion();
			}
		}
	}
	/**
	 * Hace que todas las entidades roben una carta.
	 */
	private void roboGlobal() {
		for (IEntidad entidad: getEntidades()) {
			entidad.robar(getBaraja().draw());
		}
	}
	/**
	 * Limpia las manos de las entidades.
	 */
	private void limpiarManos(){
		for (IEntidad entidad: getEntidades()) {
			entidad.limpiarMano();
		}
	}
	/**
	 * Se encarga de comprobar si todas las entidades que no son el croupier están plantadas o pasadas.
	 * @return True si están todas plantadas o pasadas y false si no.
	 */
	private boolean isFinished() {
		for (IEntidad entidad : getEntidades()) {
			if (entidad.isAlive() && !(entidad instanceof ICroupier)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Se encarga de la gestión del turno del croupier, mostrando su mano y su puntuación.
	 */
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
	/**
	 * Calcula los posibles resultados e imprime un mensaje acorde a este.
	 */
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