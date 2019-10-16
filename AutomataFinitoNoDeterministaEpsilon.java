public class AutomataFinitoNoDeterministaEpsilon extends AutomataFinitoNoDeterminista
{
	private ArrayList<ArrayList<Integer>> adyacenciaEpsilon;

	public AutomataFinitoNoDeterministaEpsilon()
	{
		this(0, null, null, null, null);
	}
	public AutomataFinitoNoDeterministaEpsilon(
			int numeroDeEstados,
			char[] alfabeto,
			int[] estadosAceptacion,
			ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones,
			ArrayList<ArrayList<Integer>> adyacenciaEpsilon)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion, tablaDeTransiciones);
		this.adyacenciaEpsilon = adyacenciaEpsilon;
	}
	public AutomataFinitoNoDeterministaEpsilon(AutomataFinitoNoDeterministaEpsilon automata)
	{
		super(automata);
		this.adyacenciaEpsilon = automata.adyacenciaEpsilon;
	}
	public void destruir()
	{
		super.destruir();
		if(adyacenciaEpsilon != null)
			adyacenciaEpsilon = null;
		System.gc();
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		retorno += super.toString();
		int i = 0;
		for(ArrayList<Integer> estadoEpsilon: adyacenciaEpsilon)
		{
			i++;
			if(estadoEpsilon != null)
				retorno += "Adyacencia epsilon estado " + i + ": " + estadoEpsilon + "\n";
		}
		return retorno;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof AutomataFinitoNoDeterministaEpsilon)){
			return false;
		}
		AutomataFinitoNoDeterministaEpsilon automata = (AutomataFinitoNoDeterministaEpsilon)obj;
		return super.equals(automata) && 
			adyacenciaEpsilon.equals(automata.adyacenciaEpsilon);
	}
}
