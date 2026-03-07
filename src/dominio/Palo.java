package dominio;
/**
 * Enum para el palo de la carta y su color.<br>
 * Contiene la representación de los 4 palos de la baraja francesa.
 */
public enum Palo {
	CORAZON("\u001B[31m"+"♥"), DIAMANTE("\u001B[33m"+"♦"), TREBOL("\u001B[34m"+"♣"), PICA("\u001B[30m"+"♠"), ERROR("?");
	private String simbol;
	Palo(String simbol) {
		this.simbol = simbol+"\u001B[0m";
	}
	public String getSimbol(){
		return simbol;
	}
}