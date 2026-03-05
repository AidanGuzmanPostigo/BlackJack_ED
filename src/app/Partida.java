package app;
import java.util.ArrayList;
import java.util.List;
import dominio.IBaraja;
import dominio.Baraja;
import dominio.Cpu;
import dominio.Croupier;
import dominio.IEntidad;
import dominio.Jugador;
public class Partida implements IPartida{
	private List<String> registro;
	private List<IEntidad> entidades;
	private IBaraja baraja;
	public Partida() {
		registro = new ArrayList<>();
		entidades = new ArrayList<>();
		baraja = new Baraja();
	}
	@Override
	public IBaraja getBaraja() {
		return baraja;
	}
	public void setBaraja(IBaraja baraja) {
		this.baraja = baraja;
	}
	public List<String> getRegistro() {
		return registro;
	}
	@Override
	public List<IEntidad> getEntidades() {
		return entidades;
	}
	@Override
	public void activarCroupier() {
		agregarEntidad(new Croupier());
	}
	@Override
	public void preparacionBarajas(int veces) {
		getBaraja().iniciar(veces);
	}
	@Override
	public void prepararJugadores(int balanceInicial, int veces, String ... motes) {
		for (int i = 0; i<veces;i++) {
			agregarEntidad(new Jugador(balanceInicial,motes[i]));
		}
	}
	@Override
	public void prepararCpus(int balanceInicial, int veces) {
		for (int i = 0; i<veces;i++) {
			agregarEntidad(new Cpu(balanceInicial));
		}
	}
	private void agregarEntidad(IEntidad entidad) {
		entidades.add(entidad);
	}
	@Override
	public void abandonarPartida() {
		for (int i = 0; i< entidades.size()-1;i++) {
			eliminarEntidad(entidades.get(i));
		}
	}
	@Override
	public void eliminarEntidad (IEntidad entidad) {
		entidades.remove(entidad);
	}
}