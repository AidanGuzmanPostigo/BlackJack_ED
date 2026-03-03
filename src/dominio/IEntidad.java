package dominio;
public interface IEntidad {
	void apostar(int cantidad);
	void apuestaGanada(int cantidad);
	void calcularPuntuacion();
	void plantarse();
	void doblarApuesta();
	String toString();
}