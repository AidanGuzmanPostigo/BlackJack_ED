package dominio;
public interface IEntidad {
	void apostar(int cantidad);
	void calcularPuntuacion();
	void plantarse();
	void doblarApuesta();
	String toString();
	void robar(Carta cartaRobada);
	void limpiarMano();
	void apuestaGanada();
}