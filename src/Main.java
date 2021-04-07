import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

	private static Alfabeto alfabeto;
	private static String mensaje;
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println(inicioPrograma());
		String alf;
		int a, b,m;
		try {
			alf = introducirArchivo("Alfabeto");
			alf = alf.substring(alf.indexOf("\"") + 1, alf.lastIndexOf("\""));
			a = escogerSplit("Parámetro a, de la clave");
			b = escogerSplit("parámetro b, de la clave");
			m = escogerSplit("modulo");
			mensaje = introducirArchivo("Mensaje");
			mensaje = mensaje.substring(mensaje.indexOf("\"") + 1, mensaje.lastIndexOf("\""));
			alfabeto = new Alfabeto(alf, a, b, m);
			int n = 1;
			alfabeto.run(n);
			opciones();
			System.out.println(finPrograma());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Metodo para recoger la ruta absoluta de un archivo con el que se trabaja
	 * @param asunto
	 * @return
	 * @throws IOException
	 */
	private static String introducirArchivo(String asunto) throws IOException {
		System.out.println("\nINTRODUCE LA RUTA ABSOLUTA DEL ARCHIVO (<"+asunto+">): \n");
		String ruta = in.nextLine();
		String linea;
		StringBuffer contenido = new StringBuffer("");

		try {
			FileReader f = new FileReader(ruta, StandardCharsets.UTF_8);
			BufferedReader b = new BufferedReader(f);
			while ((linea = b.readLine()) != null) {

				contenido.append(linea);
				contenido.append("\n");

			}
			b.close();
		} catch (IOException e) {
			throw e;
		}

		return contenido.toString();
	}
	
	/**
	 * Nos permite recoger un numero por pantalla, asegurandonos de que es un
	 * numero lo que se introduce
	 * @return
	 */
	private static int escogerSplit(String cad) {
		System.out.println("Introduce el número: ("+ cad +")\n");
		String l = in.nextLine();
		boolean b = false;
		while (!b) {
			try {
				Integer.parseInt(l);
				b = true;
			} catch (NumberFormatException excepcion) {
				System.out.println("INTRODUCE UN NUMERO, POR FAVOR: \n");
				l = in.nextLine();
			}
		}

		return Integer.valueOf(l);
	}
	
	/**
	 * Nos permite escoger entre las diferentes opciones del programa
	 */
	public static void opciones() {
		imprimirMenu();
		String menu = in.nextLine();
		int opc = Integer.valueOf(menu);

		if (opc != 0) {
			if (opc == 1) {
				//System.out.println(lineal.code(fuente.code(mensaje)));
				System.out.println("La codificación no está implementada todavía");
			}
			else if (opc == 2) {
				System.out.println(alfabeto.descifrar(mensaje));
			}
			else {
				System.out.println(" - OPCIÓN INVÁLIDA!! ");
				opciones();
			}
		} else {
			System.exit(0);
		}
	}
	
	/**
	 * Imprime el menu del programa por consola
	 */
	private static void imprimirMenu() {
		System.out.println("------------");
		System.out.println("MENU GENERAL");
		System.out.println("------------");
		System.out.println("1) CODIFICAR");
		System.out.println("2) DECODIFICAR");
		System.out.println("0) SALIR");
		System.out.println("-------------");
	}
	
	/**
	 * Cadena de bienvenida
	 * @return
	 */
	private static String inicioPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("SUSTITUCIÓN MONOALFABÉTICA");
		cad.append("\n");
		return cad.toString();
	}
	
	/**
	 * Mensaje de despedida del programa
	 * @return
	 */
	private static String finPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("- FIN EJECUCIÓN PROGRAMA -");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("\n");
		return cad.toString();
	}
	
}
