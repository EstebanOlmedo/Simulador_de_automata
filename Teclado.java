/**
 * @author Daniel Montes Guerrero
 */
import java.util.Scanner;
import java.util.NoSuchElementException;
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
		String entrada = "";
		try{
			System.out.println(mensaje);
			entrada = teclado.nextLine();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public int dameUnInt(String mensaje){
		int entrada = 0;
		try{
			System.out.println(mensaje);
			entrada = Integer.parseInt(teclado.nextLine());
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe ingresar un numero");
			nfe.printStackTrace();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public short dameUnShort(String mensaje){
		short entrada = 0;
		try{
			System.out.println(mensaje);
			entrada = Short.parseShort(teclado.nextLine());
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe ingresar un numero");
			nfe.printStackTrace();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public byte dameUnByte(String mensaje){
		byte entrada = 0;
		try{
			System.out.println(mensaje);
			entrada = Byte.parseByte(teclado.nextLine());
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe ingresar un numero");
			nfe.printStackTrace();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public long dameUnLong(String mensaje){
		long entrada = 0;
		try{
			System.out.println(mensaje);
			entrada = Long.parseLong(teclado.nextLine());
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe ingresar un numero");
			nfe.printStackTrace();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public float dameUnFloat(String mensaje){
		float entrada = 0.0f;
		try{
			System.out.println(mensaje);
			entrada = Float.parseFloat(teclado.nextLine());
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe ingresar un numero");
			nfe.printStackTrace();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public double dameUnDouble(String mensaje){
		Double entrada = 0.0d;
		try{
			System.out.println(mensaje);
			entrada = Double.parseDouble(teclado.nextLine());
		}
		catch(NumberFormatException nfe){
			System.out.println("Debe ingresar un numero");
			nfe.printStackTrace();
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public boolean dameUnBoolean(String mensaje){
		Boolean entrada = false;
		try{
			System.out.println(mensaje);
			entrada = Boolean.parseBoolean(teclado.nextLine());
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
	}

	public char dameUnChar(String mensaje){
		Character entrada = '\u0000';
		try{
			System.out.println(mensaje);
			entrada = teclado.nextLine().charAt(0);
		}
		catch(NoSuchElementException nsee){
			nsee.printStackTrace();
		}
		return entrada;
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
