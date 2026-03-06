package app;
public interface IEntradaSalida {
	/**
     * Cierra el recurso del escáner.
     */
	 void cerrar();
	 /**
	  * Imprime un mensaje y un salto de línea en consola.
	  * @param texto Texto a mostrar.
	  */
	 void escribirLinea(String texto);
	 /**
	  * Lee un mensaje introducido por el usuario.
	  * @param mensaje Mensaje indicativo al usuario para la entrada de la cadena.
	  * @return Cadena introducida por el usuario.
	  */
	 String leerTexto(String mensaje);
	 boolean readBooleanUsingChar(char affirmativeValue, char negativeValue, String error, String mensaje);
	 char readChar(String mensaje);
	 int readIntInRange(int lowerBound, int upperBound, String mensaje);
	 int readInt(String mensaje);
}