import java.util.ArrayList;
public class AutomataFinitoDeterminista extends AutomataFinito
{
	private ArrayList <ArrayList <Integer> > tablaDeTransiciones;

	public AutomataFinitoDeterminista()
	{
		this(0, null, null, null);
	}
	public AutomataFinitoDeterminista(
			int numeroEstados, char[] alfabeto, int[] estadosAceptacion, 
			ArrayList <ArrayList <Integer> > tablaDeTransiciones)
	{
		super(numeroEstados, alfabeto, estadosAceptacion);
		this.tablaDeTransiciones = tablaDeTransiciones;
	}
	public AutomataFinitoDeterminista(AutomataFinitoDeterminista automata)
	{
		super(automata);
		this.tablaDeTransiciones = automata.tablaDeTransiciones;
	}
	public void destruir()
	{
		super.destruir();
		if(tablaDeTransiciones != null)
			tablaDeTransiciones = null;
		System.gc();
	}
	@Override 
	public String toString()
	{
		String retorno = new String();
		retorno += super.toString();
		int i = 0;
		/*
		tablaDeTransiciones.forEach((estado) ->	
				retorno += "Estado: " + (++i) + estado);
		*/
		for(ArrayList <Integer> adyacenciaEstado: tablaDeTransiciones)
		{
			retorno += "Estado " + (++i) + ": " + adyacenciaEstado + "\n";
		}
		return retorno;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof AutomataFinitoDeterminista)) {return false;}
		AutomataFinitoDeterminista automata = (AutomataFinitoDeterminista)obj;
		return super.equals(automata) && 
			tablaDeTransiciones.equals(automata.tablaDeTransiciones);
	}
}
