import java.util.ArrayList;

public class Alfabeto {

	private String texto;
	private Clave claveCifrado, claveDescifrado;
	private ArrayList<String> alfabeto = new ArrayList<String>();
	private int modulo;
	
	
	public Alfabeto(String alf, int a, int b, int mod) {
		this.texto = alf;
		this.modulo = mod;
		this.claveCifrado = new Clave(a,b,mod);
		this.claveDescifrado = this.claveCifrado.getInversa();
	}

	public void run(int simTam) {
		this.simbolSplit(this.texto, simTam);
		
	}

	/**
	 * Separacion de los simbolos y almacenamiento
	 * @param text
	 * @param n
	 */
	private void simbolSplit(String text, int n) {

		StringBuffer finalText = new StringBuffer("");

		for (int i = 1; i <= text.length(); i++) {

			finalText.append(texto.charAt(i - 1));
			if (i != 0 && i % n == 0 && i != texto.length()) {
				finalText.append("\t");
			}

		}

		this.texto = finalText.toString();
		finalText = new StringBuffer("");

		String[] simbDif = this.texto.split("\t");

		for (String simb : simbDif) {
			this.alfabeto.add(simb);
		}
	}

	public String descifrar(String mensaje) {

		int contador = 1;
		StringBuilder cadenafinal = new StringBuilder("");
		char letra;
		String letraOriginal;
		
		for(int i=0; i<mensaje.length(); i++) {
			
			if(cadenafinal.length()>=2) {
				if(cadenafinal.substring(cadenafinal.length()-2, cadenafinal.length()).equals("  ")) {
					contador++;
					cadenafinal = new StringBuilder(cadenafinal.substring(0, cadenafinal.length()-2) + "\n");
				}
			}
			
			letra = mensaje.charAt(i);
			letraOriginal = descifrarLetra(letra, obtenerClave(contador));
			cadenafinal.append(letraOriginal);
			
		}
		
		return cadenafinal.toString();
	}
	
	private Clave obtenerClave(int contador) {
		
		Clave c = new Clave(claveCifrado.getA(contador), claveCifrado.getB(contador), this.modulo);
		return c.getInversa();		
	}
	
	private String descifrarLetra(char letra, Clave clave) {
		
		int num1, num2;
		
		num1 = letraToNum(String.valueOf(letra));
		num2 = desencriptar(num1,clave);
		
		return this.alfabeto.get(num2);
	}
	
	private int letraToNum(String letra) {
		for(int i=0; i<this.alfabeto.size(); i++) {
			if(alfabeto.get(i).equals(letra)) {
				return i;
			}
		}
		return -1;
	}
	
	private int desencriptar(int num, Clave clave) {
		
		return Clave.mod(num*clave.getA() + clave.getB());
		
	}

}
