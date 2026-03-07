package app;
import java.util.Scanner;
import dominio.Baraja;
import dominio.IBaraja;
/**
 * Clase en la que se ejecutará el flujo del programa.
 */
public class Main {
	/**
	 * En este método ocurre el flujo del programa.
	 */
	public void show() {
		Scanner sc = new Scanner(System.in);
		IEntradaSalida ci = new ConsoleInput(sc);
		IBaraja baraja = new Baraja();
		IMenu menu = new Menu(ci);
		IPartida partida;
		int option;
		do {
			option = menu.menuInicio();
			switch(option) {
			case 1 -> {
				partida = new Partida(ci, baraja,menu);
				partida.empezarPartida();
			}
			}
		} while(option !=2);
		ci.cerrar();
	}
	
	public static void main(String[] args) {
		new Main().show();
	}
}