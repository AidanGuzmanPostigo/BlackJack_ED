package dominio;
/**
 * Enum para el valor de la carta.<br>
 * Contiene la representación del A a la K.
 */
public enum TipoCarta {
	A('A'),DOS('2'),TRES('3'),CUATRO('4'),CINCO('5'),SEIS('6'),SIETE('7'),OCHO('8'),NUEVE('9'),DIEZ('X'),JACK('J'),QUEEN('Q'),KING('K'),ERROR('?');
	private char simbol;
	TipoCarta(char simbol) {
		this.simbol = simbol;
	}
	public char getSimbol() {
		return simbol;
	}
}