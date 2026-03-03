package dominio;
public class Jugador extends Entidad implements IJugador {
	public Jugador(int balance, String mote) {
		super(balance, mote);
	}
	public void retirarse() {
		setBalance(-1);
	}
}
