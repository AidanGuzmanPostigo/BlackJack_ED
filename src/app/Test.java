package app;
import java.util.Scanner;
public class Test {
	public void show() {
		Scanner sc = new Scanner(System.in);
		IEntradaSalida ci = new ConsoleInput(sc);
		IGestorPartida g = new GestorPartida(ci);
		IMenu menu = new Menu(ci);
		int option;
		do {
			option = menu.menuInicio();
			switch(option) {
			case 1 -> g.empezarPartida();
			}
		} while(option !=2);
	}
	
	public static void main(String[] args) {
		new Test().show();
	}
}