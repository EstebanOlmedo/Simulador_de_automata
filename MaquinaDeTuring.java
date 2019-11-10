/**
 * @author Gabriel Graciano Herrera
 */
public class MaquinaDeTuring
{
	private char[] cinta;
	private int cabezal;
	private char[] alfabeto;
	private TablaMaquinaDeTuring tabla;
	public MaquinaDeTuring()
	{
		this(null, 0, null, null);
	}
	public MaquinaDeTuring(char[] cinta, int cabezal,
		       	char[] alfabeto, TablaMaquinaDeTuring tabla)
	{
		this.cinta = cinta;
		this.cabezal = cabezal;
		this.alfabeto = alfabeto;
		this.tabla = tabla;
	}
	public MaquinaDeTuring(MaquinaDeTuring maquinaDeTuring)
	{
		this(maquinaDeTuring.cinta, maquinaDeTuring.cabezal,
				maquinaDeTuring.alfabeto, maquinaDeTuring.tabla);
	}
	public void destruir()
	{
		if(cinta != null)
			cinta = null;
		if(alfabeto != null)
			alfabeto = null;
		if(tabla != null)
		{
			tabla.destruir();
			tabla = null;
		}
		System.gc();
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		retorno += "Cinta: {...,";
		for(int i = 0; i < cinta.length; i++)
			retorno += cinta[i] + ", ";
		retorno += "...}\n";
		retorno += "Cabezal: " + cabezal + "\n";
		return retorno;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof MaquinaDeTuring)) {return false;}
		MaquinaDeTuring maquinaDeTuring = (MaquinaDeTuring)obj;
		if(cinta.length != maquinaDeTuring.cinta.length)
			return false;
		for(int i = 0; i < cinta.length; i++)
		{
			if(cinta[i] != maquinaDeTuring.cinta[i])
				return false;
		}
		return cabezal == maquinaDeTuring.cabezal;
	}
	public void modificarCinta(char modificacion, char movimiento)
	{
		cinta[cabezal] = modificacion;
		if(movimiento == 'L')
			cabezal--;
		else
			cabezal++;
	}
	public void modificarCinta(char modificacion, char movimiento, int casillas)
	{
		for(int i = 0; i < casillas; i++)
		{
			modificarCinta(modificacion, movimiento);
		}
	}
	public void modificarCinta(char modificacion, char movimiento, char paro)
	{
		while(cinta[cabezal] != paro)
			modificarCinta(modificacion, movimiento);
	}
	public void agrandarCinta(char haciaDonde)
	{
		int actual = cinta.length;
		char[] temp = cinta;
		agrandarCinta(actual);
		if(haciaDonde == 'L')
		{
			for(int i = temp.length-1; i >= cabezal; i--)
				cinta[i] = temp[i];
		}
		else
		{
			for(int i = 0; i <= cabezal; i++)
				cinta[i] = temp[i];
		}
		temp = null;
		System.gc();
	}
	public void agrandarCinta(int tamanio)
	{
		char[] nueva = new char[cinta.length*2];
		cinta = nueva;
	}
	public void setCinta(char[] cinta)
	{
		this.cinta = cinta;
	}
	public boolean modificarCinta(FuncionDeltaMaquinaDeTuring funcion)
	{
		if(funcion.hayCamino())
		{
			cinta[cabezal] = funcion.getLoQueDeja();
			if(funcion.getMovimiento() == 'D')
				cabezal++;
			else
				cabezal--;
			return true;
		}
		else
			return false;
	}
	private void setCinta(String cadena)
	{
		String nuevaCadena = "B" + cadena + "B";
		cinta = nuevaCadena.toCharArray();
	}
	public void prepararMaquina(String cadena)
	{
		setCinta(cadena);
		cabezal = 1;
	}
	public void setAlfabeto(String alfabeto)
	{
		setAlfabeto(alfabeto.toCharArray());
	}
	public void setAlfabeto(char[] alfabeto)
	{
		this.alfabeto = alfabeto;
	}
	public int getIndiceSimboloEnAlfabeto(char simbolo)
	{
		int retorno = 0;
		int i = 0;
		while(alfabeto[i] != simbolo)
		{
			i++;
			retorno++;
		}
		return retorno;
	}
	public boolean accionar()
	{
		int indice = getIndiceSimboloEnAlfabeto(cinta[cabezal]);
		int estado = 0;
		FuncionDeltaMaquinaDeTuring funcion = tabla.getFuncion(estado, indice);
		while(funcion.hayCamino())
		{
			estado = funcion.getEstado();
			modificarCinta(funcion);
			indice = getIndiceSimboloEnAlfabeto(cinta[cabezal]);
			funcion = tabla.getFuncion(estado, indice);
		}
		if(tabla.isEstadoAceptacion(estado))
			return true;
		else
			return false;
	}
}
