package app;
import java.util.Scanner;
import dominio.Baraja;
import dominio.IBaraja;
public class Test {
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
	}
	
	public static void main(String[] args) {
		new Test().show();
	}
}