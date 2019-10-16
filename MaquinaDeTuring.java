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
		retorno += "Cinta: {";
		for(int i = 0; i < cinta.length; i++)
			retorno += cinta[i] + ", ";
		retorno += "}\n";
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
}
