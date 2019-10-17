public class AutomataFinito
{
	private int numeroDeEstados;
	private char[] alfabeto;
	private int[] estadosAceptacion;

	public AutomataFinito()
	{
		this(0, null, null);
	}
	public AutomataFinito(int numeroDeEstados, char[] alfabeto,
			int[] estadosAceptacion)
	{
		this.numeroDeEstados = numeroDeEstados;
		this.alfabeto = alfabeto;
		this.estadosAceptacion = estadosAceptacion;
	}
	public AutomataFinito(AutomataFinito automata)
	{
		this(automata.numeroDeEstados, automata.alfabeto, 
				automata.estadosAceptacion);
	}
	public void destruir()
	{
		if(alfabeto != null)
			alfabeto = null;
		if(estadosAceptacion != null)
			estadosAceptacion = null;
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof AutomataFinito)) return false;
		AutomataFinito automata = (AutomataFinito)obj;
		if(alfabeto.length != automata.alfabeto.length)
			return false;
		for(int i = 0; i < alfabeto.length; i++)
		{
			if(alfabeto[i] != automata.alfabeto[i])
					return false;
		}
		if(estadosAceptacion.length != automata.estadosAceptacion.length)
			return false;
		for(int i = 0; i < estadosAceptacion.length; i++)
		{
			if(estadosAceptacion[i] != automata.estadosAceptacion[i])
				return false;
		}
		return numeroDeEstados == automata.numeroDeEstados;
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		retorno += "Numero de estados: " + numeroDeEstados + "\n";
		retorno += "Alfabeto: {";
		for(int i = 0; i < alfabeto.length; i++)
		{
			retorno += i < (alfabeto.length - 1)? 
				alfabeto[i] + ", ": alfabeto[i] + "}";
		}
		retorno += "Estados de aceptacion: {";
		for(int i = 0; i < estadosAceptacion.length; i++)
		{
			retorno += i < (estadosAceptacion.length - 1)? 
				estadosAceptacion[i] + ", ": estadosAceptacion[i] + "}";
		}
		return retorno;
	}
	public boolean isAceptacion(int q)
	{
		for(int i = 0; i < estadosAceptacion.length; i++)
		{
			if(estadosAceptacion[i] == q)
				return true;
		}
		return false;
	}
	public int getNumeroSimbolo(char a)
	{
		for(int i = 0; i < alfabeto.length; i++)
		{
			if(alfabeto[i] == a)
				return i;
		}
		return -1;
	}
	public int getNumeroDeEstados()
	{
		return numeroDeEstados;
	}
}
