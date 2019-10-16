import java.util.ArrayList;
public class AutomataFinitonNoDeterminista extends AutomataFinito
{
	private ArrayList< ArrayList< ArrayList< Integer > > > tablaDeTransiciones;

	public AutomataFinitonNoDeterminista(
		int numeroDeEstados,
		char[] alfabeto,
		int[] estadosAceptacion,
		ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion);
		this.tablaDeTransiciones = tablaDeTransiciones;
	}

	public AutomataFinitonNoDeterminista(){
		this(0, null, null, null);
	}

	public AutomataFinitonNoDeterminista(AutomataFinitonNoDeterminista automata)
	{
		super(automata);
		tablaDeTransiciones = automata.tablaDeTransiciones;
	}

	public void destruir()
	{
		if(tablaDeTransiciones != null) tablaDeTransiciones = null;
		super.destruir();
		System.gc();
	}

	@Override
	public String  toString(){
		String cad = super.toString();
		for (int i=0; i<tablaDeTransiciones.size(); i++){
			cad += "Estado "+ (i+1) + ": ";
			for (int j=0; j<tablaDeTransiciones.get(0).size(); j++) {
				cad += "Simbolo "+(j+1)+" {";
				for (int k=0; k<tablaDeTransiciones.get(0).get(0).size(); k++) {
					cad += tablaDeTransiciones.get(i).get(j).get(k);
					k+1==tablaDeTransiciones.get(0).get(0).size()? cad += "}": cad += ",";
				}
				cad += "\t";
			}
			cad += "\n";
		}
		return cad;
	}

	@Override
	public boolean equals(Objecto obj){
		if(obj == null) return false;
		if(!(obj instanceof AutomataFinitonNoDeterminista)) return false;
		AutomataFinitonNoDeterminista afd = (AutomataFinitonNoDeterminista)obj;
		return super.equals(afd) && tablaDeTransiciones.equals(afd.tablaDeTransiciones);
	}

}