package dominio;
public class Cpu extends Entidad implements ICpu{
	static int count = 0;
	public Cpu() {
		super(String.format("CPU%d",++count));
	}
	public Cpu (String mote) {
		super(mote);
	}
	@Override
	public int elegirJugada() {
		if (calcularPuntuacion() <= 13) {
			return 2;
		} else if (calcularPuntuacion()>21) {
			return 0;
		} else {
			return 1;
		}
	}
}