package dominio;
public record Carta(TipoCarta numero, Palo palo, int valor) {
	@Override
	public String toString() {
		return String.format("%s%s", numero.getSimbol(), palo.getSimbol());
	}
}