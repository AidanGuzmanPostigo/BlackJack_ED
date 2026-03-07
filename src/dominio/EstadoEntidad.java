package dominio;
/**
 * Interfaz encargada de almacenar los posibles valores de una entidad.
 * PLANTADO: La entidad se ha plantado sin robar carta.
 * PASADO: La entidad ha superado 21.
 * EN PIE: La entidad tiene que elegir si plantarse o robar carta.
 */
public enum EstadoEntidad {
	PLANTADO,PASADO,EN_PIE;
}