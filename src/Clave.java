
public class Clave {

	private int a;
	private int b;
	private static int mod;
	
	public Clave(int a, int b, int mod) {
		this.a = a;
		this.b = b;
		this.mod = mod;
	}
	
	public int getA() {
		return this.a;
	}
	
	public int getB() {
		return this.b;
	}
	
	public int getA(int i) {
		
		int resultado = a;
		
		for(int j=1; j<i; j++) {
			resultado = resultado*a;
			resultado = mod(resultado,mod);
		}
		
		return resultado;
	}
	
	public int getB(int i) {
		return mod(b*i,mod);
	}
	
	public Clave getInversa() {
		int nuevoA = inversoMod(a, mod);
		return new Clave(nuevoA, mod(-nuevoA*b, mod), mod);
	}
	
	public static int inversoMod(int i, int mod) {
		int respuesta = (int) euclidesExtendido(Long.valueOf(String.valueOf(i)), Long.valueOf(String.valueOf(mod)))[1];
		if(respuesta<0)
			return respuesta+mod;
        else
        	return respuesta;
	}
	
	private static int mod(int a, int mod) {
		while(a<0) a += mod;
		return a%mod;
	}
	
	public static int mod(int a) {
		return mod(a,mod);
	}
	
	public static long[] euclidesExtendido(long a, long b) {
         long[] resp = new long[3];
         long x=0,y=0,d=0;
    		
         if(b==0) {
        	 resp[0] = a;
        	 resp[1] = 1;
        	 resp[2] = 0;
         }else{
	    	long x2 = 1, x1 = 0, y2 = 0, y1 = 1;
	    	long q = 0, r = 0;
	    			
	    	while(b>0) {	
		    	q = (a/b);
		    	r = a - q*b;
		    	x = x2-q*x1;
		    	y = y2 - q*y1;
		    	a = b;
		    	b = r;
		    	x2 = x1;
		    	x1 = x;
		    	y2 = y1;
		    	y1 = y;
	    	}
    			
	    	resp[0] = a;
	    	resp[1] = x2;
	    	resp[2] = y2;
        }
        return resp;		
    } 
}
