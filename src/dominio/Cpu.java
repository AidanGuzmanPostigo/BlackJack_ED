package dominio;
/**
 * Clase que se encarga de gestionar las Cpu.
 */
public class Cpu extends Entidad implements ICpu{
	static int count = 0;
	/**
	 * Constructor de la clase por defecto, el mote se "crea solo" utilizando un contador estático.
	 * @return Cpu instanciado.
	 */
	public Cpu() {
		super(String.format("CPU%d",++count));
	}
	/**
	 * Constructor de la clase con parámetro.
	 * @return Cpu instanciado.
	 */
	public Cpu (String mote) {
		super(mote);
	}
	/**
	 * @inheritDoc
	 */
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