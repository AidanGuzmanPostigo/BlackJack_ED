package app;
import java.util.List;
import dominio.IBaraja;
import dominio.IEntidad;
public interface IPartida {
	void abandonarPartida();
	void activarCroupier();
	void preparacionBarajas(int veces);
	void prepararJugadores(int balanceInicial, int veces, String ... motes);
	void prepararCpus(int balanceInicial, int veces);
	void eliminarEntidad (IEntidad entidad);
	List<IEntidad> getEntidades();
	IBaraja getBaraja();
}