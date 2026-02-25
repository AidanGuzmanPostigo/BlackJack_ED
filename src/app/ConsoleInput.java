package app;
import java.util.Scanner;
import java.util.InputMismatchException;
public class ConsoleInput {
	private Scanner keyboard;
	public ConsoleInput(Scanner keyboard) {
		this.keyboard = keyboard;
	}
	private void cleanInput() {
		keyboard.nextLine();
	}
	public int readInt() {
		int value=0;
		boolean error;
		do {
			try {
				value = keyboard.nextInt();
				error = false;
			} catch (InputMismatchException e) {
				System.out.printf("%sEl valor del integer debe ser de tipo númerico entero y comprendido entre el rango %d - %d.%s\n", "\u001B[31m", Integer.MIN_VALUE, Integer.MAX_VALUE, "\u001B[0m");
				error = true;
			} finally {
				cleanInput();
			}
		} while (error);
		return value;
	}
	public int readIntLessThan(int upperBound){
		int value = 0;
		do {
			value = readInt();
			if (value >= upperBound) {
				System.out.printf("%sEl valor del integer debe ser menor que %d.%s\n", "\u001B[31m",upperBound, "\u001B[0m");
			}
		} while (value >= upperBound);
		return value;
	}
	public int readIntLessOrEqualThan(int upperBound) {
		int value = 0;
		do {
			value = readInt();
			if (value > upperBound) {
				System.out.printf("%sEl valor del integer debe ser menor o igual que %d.%s\n", "\u001B[31m",upperBound, "\u001B[0m");
			}
		} while (value > upperBound);
		return value;
	}
	public int readIntGreaterThan(int lowerBound) {
		int value = 0;
		do {
			value = readInt();
			if (value <= lowerBound) {
				System.out.printf("%sEl valor del integer debe ser mayor que %d.%s\n", "\u001B[31m",lowerBound, "\u001B[0m");
			}
		} while (value <= lowerBound);
		return value;
	}
	public int readIntGreaterOrEqualThan(int lowerBound) {
		int value = 0;
		do {
			value = readInt();
			if (value < lowerBound) {
				System.out.printf("%sEl valor del integer debe ser mayor o igual que %d.%s\n", "\u001B[31m",lowerBound, "\u001B[0m");
			}
		} while (value < lowerBound);
		return value;
	}
	public int readIntInRange(int lowerBound, int upperBound) {
		int value = 0;
		do {
			value = readInt();
			if (value < lowerBound || value > upperBound) {
				System.out.printf("%sEl valor del integer debe estar comprendido entre %d y %d (ambos incluidos).%s\n", "\u001B[31m",lowerBound, upperBound, "\u001B[0m");
			}
		} while (value < lowerBound || value > upperBound);
		return value;
	}
	public double readDouble() {
		double value=0;
		boolean error;
		do {
			try {
				value = keyboard.nextDouble();
				error = false;
			} catch (InputMismatchException e) {
				System.out.printf("%sEl valor del double debe ser de tipo númerico decimal y comprendido entre el rango %f - %f.%s\n", "\u001B[31m", Double.MIN_VALUE, Double.MAX_VALUE, "\u001B[0m");
				error = true;
			} finally {
				cleanInput();
			}
		} while (error);
		return value;
	}
	public double readDoubleLessThan(double upperBound) {
		double value = 0;
		do {
			value = readDouble();
			if (value >= upperBound) {
				System.out.printf("%sEl valor del double debe ser menor que %f.%s\n", "\u001B[31m",upperBound, "\u001B[0m");
			}
		} while (value >= upperBound);
		return value;
	}
	public double readDoubleLessOrEqualThan(double upperBound) {
		double value = 0;
		
		do {
			value = readDouble();
			if (value > upperBound) {
				System.out.printf("%sEl valor del double debe ser menor o igual que %f.%s\n", "\u001B[31m",upperBound, "\u001B[0m");
			}
		} while (value > upperBound);
		return value;
	}
	public double readDoubleGreaterThan(double lowerBound) {
		double value = 0;
		do {
			value = readDouble();
			if (value <= lowerBound) {
				System.out.printf("%sEl valor del double debe ser mayor que %f.%s\n", "\u001B[31m",lowerBound, "\u001B[0m");
			}
		} while (value <= lowerBound);
		return value;
	}
	public double readDoubleGreaterOrEqualThan(double lowerBound) {
		double value = 0;
		do {
			value = readDouble();
			if (value < lowerBound) {
				System.out.printf("%sEl valor del double debe ser mayor o igual que %f.%s\n", "\u001B[31m",lowerBound, "\u001B[0m");
			}
		} while (value < lowerBound);
		return value;
	}
	public double readDoubleInRange(double lowerBound, double upperBound) {
		double value= 0;
		do {
			value = readDouble();
			if (value < lowerBound || value > upperBound) {
				System.out.printf("%sEl valor del double debe estar comprendido entre %f y %f (ambos incluidos).%s\n", "\u001B[31m",lowerBound, upperBound, "\u001B[0m");
			}
		} while (value < lowerBound || value > upperBound);
		return value;
	}
	public char readChar() {
		String value = "";
		do {
			value = readString();
			if (value.trim().length() != 1) {
				System.out.printf("%sEl valor del char debe ser de un único caracter.%s\n", "\u001B[31m", "\u001B[0m");
			}
		} while (value.trim().length() != 1);
		return value.trim().charAt(0);
	}
	public String readString() {
		String value = "";
		do {
			value = keyboard.nextLine();
			if (value.trim().isEmpty()) {
				System.out.printf("%sNo puedes introducir una cadena vacía, debe contener al menos un caracter.%s\n", "\u001B[31m", "\u001B[0m");
			}
		} while (value.trim().isEmpty());
		return value;
	}
	public String readString(int maxLength) {
		String value = "";
		do {
			value = readString();
			if (value.length() > maxLength) {
				System.out.printf("%sEl valor del String no puede superar los %d caracteres.%s\n", "\u001B[31m", maxLength, "\u001B[0m");
			}
		} while (value.length() > maxLength);
		return value;
	}
	public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue, String error) {
		char value = '¬';
		do {
			value = readChar();
			if (value != affirmativeValue && value != negativeValue) {
				System.out.printf("%s%s%s\n", "\u001B[31m",error, "\u001B[0m");
			}
		} while (value != affirmativeValue && value != negativeValue);
		if (value == affirmativeValue) {
			return true;
		} else {
			return false;
		}
	}
	public String readEmptyString() {
		String value = "";
		value = keyboard.nextLine();
		return value;
	}
}