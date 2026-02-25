package dominio;
public enum Palo {
	CORAZON('♥'), DIAMANTE('♦'), TREBOL('♣'), PICA('♠'), ERROR('?');
	private char simbol;
	Palo(char simbol) {
		this.simbol = simbol;
	}
	public char getSimbol(){
		return simbol;
	}
}