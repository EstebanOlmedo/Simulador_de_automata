/**
 * @author Gabriel Graciano Herrera
 */
public class MaquinaDeTuring
{
	private char[] cinta;
	private int cabezal;

	public MaquinaDeTuring()
	{
		this(null, 0);
	}
	public MaquinaDeTuring(char[] cinta, int cabezal)
	{
		this.cinta = cinta;
		this.cabezal = cabezal;
	}
	public MaquinaDeTuring(MaquinaDeTuring maquinaDeTuring)
	{
		this(maquinaDeTuring.cinta, maquinaDeTuring.cabezal);
	}
	public void destruir()
	{
		if(cinta != null)
			cinta = null;
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
}
