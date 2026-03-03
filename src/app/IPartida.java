package app;
import java.util.List;
import dominio.Baraja;
import dominio.IEntidad;
public interface IPartida {
	boolean activarCroupier(IEntradaSalida ci);
	void agregarEntidad(IEntidad entidad);
	void abandonarPartida();
	List<IEntidad> getEntidades();
	Baraja getBaraja();
}