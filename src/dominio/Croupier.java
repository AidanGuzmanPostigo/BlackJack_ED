package dominio;
/**
 * Clase que gestiona al Croupier.
 */
public class Croupier extends Cpu implements ICroupier {
	/**
	 * Constructor de la clase.
	 * Llama al constructor de Cpu con el mote Croupier.
	 * @return Croupier instanciado.
	 */
	public Croupier() {
		super("Croupier");
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public String mostrarManoCroupier() {
		StringBuilder sb = new StringBuilder("");
		sb.append(getMote() + " - ");
		for (int i = 0; i<getMano().size();i++) {
			if (i != 0 && i!= getMano().size()-1) {
				sb.append(", ");
			} else if (i == getMano().size()-1) {
				sb.append(" y ");
			}
			if (i == 0) {
				sb.append("??");
			} else {
				sb.append(getMano().get(i).toString());
			}
		}
		sb.append(".");
		return sb.toString();
	}
}