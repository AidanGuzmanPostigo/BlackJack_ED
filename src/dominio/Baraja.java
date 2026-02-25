package dominio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Baraja {
	List<Carta> cartasEnMazo;
	List<Carta> cartasEnDescarte;
	public Baraja() {
		cartasEnMazo = new ArrayList<>();
		cartasEnDescarte = new ArrayList<>();
	}
	public void barajar() {
		Collections.shuffle(cartasEnMazo);
	}
	public void mazoInicial() {
		int valor=0;
		TipoCarta tipoCarta;
		Palo palo;
		for (int i = 1; i<=4;i++) {
			palo = switch(i) {
			case 1 -> Palo.CORAZON;
			case 2 -> Palo.DIAMANTE;
			case 3 -> Palo.TREBOL;
			case 4 -> Palo.PICA;
			default -> Palo.ERROR;
			};
			for (int j = 1; j<=13;j++) {
				tipoCarta = switch (j) {
				case 1 -> {
					valor = 11;
					yield TipoCarta.A;
				}
				case 2 -> {
					valor = 2;
					yield TipoCarta.DOS;
				}
				case 3 -> {
					valor = 3;
					yield TipoCarta.TRES;
				}
				case 4 -> {
					valor = 4;
					yield TipoCarta.CUATRO;
				}
				case 5 -> {
					valor = 5;
					yield TipoCarta.CINCO;
				}
				case 6 -> {
					valor = 6;
					yield TipoCarta.SEIS;
				}
				case 7 -> {
					valor = 7;
					yield TipoCarta.SIETE;
				}
				case 8 -> {
					valor = 8;
					yield TipoCarta.OCHO;
				}
				case 9 -> {
					valor = 9;
					yield TipoCarta.NUEVE;
				}
				case 10 -> {
					valor = 10;
					yield TipoCarta.DIEZ;
				}
				case 11 -> {
					valor = 10;
					yield TipoCarta.JACK;
				}
				case 12 -> {
					valor = 10;
					yield TipoCarta.QUEEN;
				}
				case 13 -> {
					valor = 10;
					yield TipoCarta.KING;
				}
				default -> TipoCarta.ERROR;
				};
				addCarta(new Carta(tipoCarta,palo,valor));
			}
		}
	}
	public void addCarta(Carta c) {
		cartasEnMazo.add(c);
	}
	public void toDiscardPile(Carta c) {
		cartasEnMazo.remove(c);
		cartasEnDescarte.add(c);
	}
}