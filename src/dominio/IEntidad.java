package dominio;
public interface IEntidad {
	void robar(Carta cartaRobada);
	int calcularPuntuacion();
	void plantarse();
	void limpiarMano();
	String mostrarMano();
	boolean isAlive();
	boolean isDead();
	String getMote();
}