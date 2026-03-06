package dominio;
import java.util.ArrayList;
import java.util.List;
public class Entidad implements IEntidad{
	private EstadoEntidad estado;
	private List<Carta> mano;
	private String mote;
	public Entidad(String mote) {
		setEstado(EstadoEntidad.EN_PIE);
		this.mote = mote;
		mano = new ArrayList<>();
	}
	@Override
	public String getMote() {
		return mote;
	}
	public List<Carta> getMano() {
		return mano;
	}
	public EstadoEntidad getEstado() {
		return estado;
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
	public int calcularPuntuacion() {
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
		if (aux>21) {
			setEstado(EstadoEntidad.PASADO);
		}
		return aux;
	}
	@Override
	public void plantarse() {
		setEstado(EstadoEntidad.PLANTADO);
	}
	@Override
	public boolean isAlive() {
		return getEstado() == EstadoEntidad.EN_PIE;
	}
	@Override
	public boolean isDead() {
		return getEstado() == EstadoEntidad.PASADO;
	}
	@Override
	public String mostrarMano() {
		StringBuilder sb = new StringBuilder("");
		sb.append(getMote() + " - ");
		for (int i = 0; i<getMano().size();i++) {
			if (i != 0 && i!= getMano().size()-1) {
				sb.append(", ");
			} else if (i == getMano().size()-1) {
				sb.append(" y ");
			}
			sb.append(getMano().get(i).toString());
		}
		sb.append(".");
		return sb.toString();
	}
	@Override
	public String toString() {
		return String.format("%s, %d", getMote(), calcularPuntuacion());
	}
}