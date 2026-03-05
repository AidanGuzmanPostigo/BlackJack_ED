package app;
import java.util.Scanner;
import java.util.InputMismatchException;
public class ConsoleInput implements IEntradaSalida{
	private Scanner keyboard;
	public ConsoleInput(Scanner keyboard) {
		this.keyboard = keyboard;
	}
	private void cleanInput() {
		keyboard.nextLine();
	}
	@Override
	public int readInt(String mensaje) {
		int value=0;
		boolean error;
		escribirLinea(mensaje);
		do {
			try {
				value = keyboard.nextInt();
				error = false;
			} catch (InputMismatchException e) {
				escribirLinea(String.format("%sEl valor del integer debe ser de tipo númerico entero y comprendido entre el rango %d - %d.%s", "\u001B[31m", Integer.MIN_VALUE, Integer.MAX_VALUE, "\u001B[0m"));
				error = true;
			} finally {
				cleanInput();
			}
		} while (error);
		return value;
	}
	@Override
	public int readIntLessOrEqualThan(int upperBound, String mensaje) {
		int value = 0;
		do {
			value = readInt(mensaje);
			if (value > upperBound) {
				escribirLinea(String.format("%sEl valor del integer debe ser menor o igual que %d.%s", "\u001B[31m",upperBound, "\u001B[0m"));
			}
		} while (value > upperBound);
		return value;
	}
	@Override
	public int readIntInRange(int lowerBound, int upperBound, String mensaje) {
		int value = 0;
		do {
			value = readInt(mensaje);
			if (value < lowerBound || value > upperBound) {
				escribirLinea(String.format("%sEl valor del integer debe estar comprendido entre %d y %d (ambos incluidos).%s", "\u001B[31m",lowerBound, upperBound, "\u001B[0m"));
			}
		} while (value < lowerBound || value > upperBound);
		return value;
	}
	@Override
	public int readIntGreaterOrEqualThan(int lowerBound, String mensaje) {
		int value = 0;
		do {
			value = readInt(mensaje);
			if (value < lowerBound) {
				escribirLinea(String.format("%sEl valor del integer debe ser menor o igual que %d.%s", "\u001B[31m",lowerBound, "\u001B[0m"));
			}
		} while (value < lowerBound);
		return value;
	}
	@Override
	public char readChar(String mensaje) {
		String value = "";
		escribirLinea(mensaje);
		do {
			value = keyboard.nextLine();
			if (value.trim().length() != 1) {
				escribirLinea(String.format("%sEl valor del char debe ser de un único caracter.%s", "\u001B[31m", "\u001B[0m"));
			}
		} while (value.trim().length() != 1);
		return value.trim().charAt(0);
	}
	@Override
	public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue, String error, String mensaje) {
		char value = '¬';
		do {
			value = readChar(mensaje);
			if (value != affirmativeValue && value != negativeValue) {
				escribirLinea(String.format("%s%s%s", "\u001B[31m",error, "\u001B[0m"));
			}
		} while (value != affirmativeValue && value != negativeValue);
		if (value == affirmativeValue) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void cerrar() {
		keyboard.close();
	}
	@Override
    public void escribirLinea(String texto) {
        System.out.println(texto);
    }
	@Override
    public String leerTexto(String mensaje) {
    	String value = "";
    	escribirLinea(mensaje);
		do {
			value = keyboard.nextLine();
			if (value.trim().isEmpty()) {
				escribirLinea(String.format("%sNo puedes introducir una cadena vacía, debe contener al menos un caracter.%s", "\u001B[31m", "\u001B[0m"));
			}
		} while (value.trim().isEmpty());
		return value;
    }
}