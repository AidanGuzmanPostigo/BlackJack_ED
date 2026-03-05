package dominio;
public class Croupier extends Cpu implements ICroupier{
	public Croupier() {
		super(1000000,"Croupier");
	}
	public void recargarFondos() {
		if (getBalance() <= 0) {
			setBalance(1000000);
		}
	}
	@Override
	public String toString() {
		return String.format("%s∞", super.toString().substring(0,10));
	}
}
