/**
 * @author Daniel Montes Guerrero
 */
import java.util.Scanner;
public class Teclado{
	Scanner teclado;

	public Teclado(Scanner scanner){
		teclado = scanner;
	}

	public Teclado(){
		this(new Scanner(System.in));
	}

	public Teclado(Teclado miTeclado){
		this(miTeclado.teclado);
	}

	public void destruir(){
		if(teclado != null){
			teclado = null;
		}
		System.gc();
	}

	public String dameUnString(String mensaje){
		System.out.println(mensaje);
		return teclado.nextLine();
	}

	public int dameUnInt(String mensaje){
		System.out.println(mensaje);
		return Integer.parseInt(teclado.nextLine());
	}

	public short dameUnShort(String mensaje){
		System.out.println(mensaje);
		return Short.parseShort(teclado.nextLine());
	}

	public byte dameUnByte(String mensaje){
		System.out.println(mensaje);
		return Byte.parseByte(teclado.nextLine());
	}

	public long dameUnLong(String mensaje){
		System.out.println(mensaje);
		return Long.parseLong(teclado.nextLine());
	}

	public float dameUnFloat(String mensaje){
		System.out.println(mensaje);
		return Float.parseFloat(teclado.nextLine());
	}

	public double dameUnDouble(String mensaje){
		System.out.println(mensaje);
		return Double.parseDouble(teclado.nextLine());
	}

	public boolean dameUnBoolean(String mensaje){
		System.out.println(mensaje);
		return Boolean.parseBoolean(teclado.nextLine());
	}

	public char dameUnChar(String mensaje){
		System.out.println(mensaje);
		return teclado.nextLine().charAt(0);
	}

	public void esperar(String mensaje){
		System.out.println(mensaje);
		teclado.nextLine();
	}
	
	@Override
	public String toString(){
		return "Esto es un teclado";
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof Teclado)) return false;
		Teclado t = (Teclado)obj;
		return teclado==t.teclado;
	}

}
