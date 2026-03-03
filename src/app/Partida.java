package app;
import java.util.ArrayList;
import java.util.List;

import dominio.Baraja;
import dominio.Croupier;
import dominio.IEntidad;
public class Partida implements IPartida{
	private List<String> registro;
	private List<IEntidad> entidades;
	private Baraja baraja;
	private int apuestaActual=50;
	public int getApuestaActual() {
		return apuestaActual;
	}
	public void setApuestaActual(int apuestaActual) {
		this.apuestaActual = apuestaActual;
	}
	public Partida(Baraja baraja) {
		registro = new ArrayList<>();
		entidades = new ArrayList<>();
		this.baraja = baraja;
	}
	public Baraja getBaraja() {
		return baraja;
	}
	public void setBaraja(Baraja baraja) {
		this.baraja = baraja;
	}
	public List<String> getRegistro() {
		return registro;
	}
	public List<IEntidad> getEntidades() {
		return entidades;
	}
	public boolean activarCroupier(IEntradaSalida ci) {
		if(ci.readBooleanUsingChar('s', 'n', "Tienes que introducir s/n.", "¿Quieres tener un croupier?")) {
			agregarEntidad(new Croupier());
			return true;
		} else {
			return false;
		}
	}
	public void agregarEntidad(IEntidad entidad) {
		entidades.add(entidad);
	}
	public void abandonarPartida() {
		for (int i = 0; i< entidades.size()-1;i++) {
			eliminarEntidad(entidades.get(i));
		}
	}
	private void eliminarEntidad (IEntidad entidad) {
		entidades.remove(entidad);
	}
}