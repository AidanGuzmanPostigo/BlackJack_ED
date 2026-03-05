package dominio;
public class Cpu extends Entidad implements ICpu{
	static int count = 0;
	public Cpu(int balance) {
		super(balance,String.format("CPU%d",++count));
	}
	public Cpu (int balance, String mote) {
		super(balance, mote);
	}
	public int elegirJugada() {
		if (getPuntuacion() <= 13) {
			return 2;
		} else if (getPuntuacion()> 13 && getPuntuacion() <= 16) {
			return 3;
		} else {
			return 1;
		}
	}
}