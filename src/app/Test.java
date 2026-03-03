package app;
import java.util.Scanner;
import dominio.Baraja;
import dominio.Cpu;
import dominio.IEntidad;
import dominio.Jugador;
public class Test {
	public void show() {
		Scanner sc = new Scanner(System.in);
		IEntradaSalida ci = new ConsoleInput(sc);
		Baraja baraja = new Baraja();
		IPartida p = new Partida(baraja);
		int jugadores = 0;
		int cpus = 0;
		int balanceInicial=0;
		String mote;
		p.activarCroupier(ci);
		balanceInicial= ci.readInt("Introduce la cantidad de fichas con las que se empezará.");
		jugadores = ci.readIntInRange(1, 4, "Selecciona cuantos jugadores quieres (1-4).");
		if (!(jugadores == 4)) {
			cpus = ci.readIntInRange(jugadores==1?1:0, 4-jugadores, String.format("Selecciona cuantas CPUs quieres (%d-%d)", jugadores==1?1:0, 4-jugadores));
		}
		for (int i = 0; i<jugadores;i++) {
			mote = ci.leerTexto("Introduce el mote para el jugador " + (i+1));
			p.agregarEntidad(new Jugador(balanceInicial,mote));
		}
		for (int i = 0; i<cpus;i++) {
			p.agregarEntidad(new Cpu(balanceInicial));
		}
		for (IEntidad entidad : p.getEntidades()) {
			ci.escribirLinea(entidad.toString());
		}
		p.getBaraja().iniciar(ci.readIntInRange(1,2,"¿Cuántas barajas quieres? (1-2)."));
		ci.escribirLinea(p.getBaraja().toString());
		
		
		
	}
	public static void main(String[] args) {
		new Test().show();
	}
}