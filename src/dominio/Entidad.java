package dominio;
import java.util.ArrayList;
import java.util.List;
public class Entidad implements IEntidad{
	private EstadoEntidad estado;
	private int balance;
	private int puntuacion;
	private List<Carta> mano;
	private String mote;
	private int apuestaActual=0;
	public int getApuestaActual() {
		return apuestaActual;
	}
	public void setApuestaActual(int apuestaActual) {
		this.apuestaActual = apuestaActual;
	}
	public Entidad(int balance, String mote) {
		setEstado(EstadoEntidad.EN_PIE);
		setBalance(balance);
		this.mote = mote;
		mano = new ArrayList<>();
	}
	public String getMote() {
		return mote;
	}
	public List<Carta> getMano() {
		return mano;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public EstadoEntidad getEstado() {
		return estado;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setEstado(EstadoEntidad estado) {
		this.estado = estado;
	}
	@Override
	public void robar(Carta cartaRobada) {
		mano.add(cartaRobada);
	}
	@Override
	public void limpiarMano() {
		mano.clear();
	}
	@Override
	public void apostar(int cantidad) {
		setBalance(balance - cantidad);
		setApuestaActual(getApuestaActual()+cantidad);
	}
	@Override
	public void apuestaGanada() {
		setBalance(balance + (apuestaActual*2));
		setApuestaActual(0);
	}
	@Override
	public void calcularPuntuacion() {
		int aux=0;
		int asCount = 0;
		for (int i = 0; i< mano.size();i++) {
			if (mano.get(i).numero() == TipoCarta.A) {
				asCount++;
			}
			aux+= mano.get(i).valor();
			if (aux > 21 && asCount>0) {
				aux-=10;
				asCount--;
			}
		}
		setPuntuacion(aux);
		if (getPuntuacion()>21) {
			setEstado(EstadoEntidad.PASADO);
		}
	}
	@Override
	public void plantarse() {
		setEstado(EstadoEntidad.PLANTADO);
	}
	@Override
	public void doblarApuesta() {
		apostar(getApuestaActual());
		setEstado(EstadoEntidad.HA_DOBLADO);
	}
	@Override
	public String toString() {
		return String.format("%s, %d", getMote(), balance);
	}
}