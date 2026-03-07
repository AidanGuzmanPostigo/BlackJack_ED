package app;
/**
 * Interfaz que se encarga de la comunicación con el usuario.
 */
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
	 /**
	  * Lee un caracter introducido por el usuario y lo transforma en un boolean.
	  * @param affirmativeValue Caracter que devuelve true.
	  * @param negativeValue Caracter que devuelve false.
	  * @param error Mensaje en caso de que ocurra un error.
	  * @param mensaje Mensaje indicativo al usuario para la entrada del caracter.
	  * @return True si es el primer caracter o false si es el segundo.
	  */
	 boolean readBooleanUsingChar(char affirmativeValue, char negativeValue, String error, String mensaje);
	 /**
	  * Lee un caracter introducido por el usuario.
	  * @param mensaje Mensaje indicativo al usuario para la entrada del caracter.
	  * @return Caracter introducido por el usuario.
	  */
	 char readChar(String mensaje);
	 /**
	  * Lee un número entero comprendido en un rango de valores determinado.
	  * @param lowerBound Valor mínimo.
	  * @param upperBound Valor máximo.
	  * @param mensaje Mensaje indicativo al usuario para la entrada del entero.
	  * @return Número entero introducido por el usuario.
	  */
	 int readIntInRange(int lowerBound, int upperBound, String mensaje);
	 /**
	  * Lee un número entero.
	  * @param mensaje Mensaje indicativo al usuario para la entrada del entero.
	  * @return Número entero introducido por el usuario.
	  */
	 int readInt(String mensaje);
}