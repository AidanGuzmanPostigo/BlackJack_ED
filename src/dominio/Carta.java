package dominio;
/**
 * Record que almacena una carta de la baraja francesa.
 * @param numero Representación númerica de la carta (A-K).
 * @param palo Representación del símbolo de los palos de la baraja francesa.
 * @param valor Valor númerico de la carta.
 */
public record Carta(TipoCarta numero, Palo palo, int valor) {
	@Override
	public String toString() {
		return String.format("%s%s", numero.getSimbol(), palo.getSimbol());
	}
}