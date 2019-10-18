/**
 * @author Daniel Montes Guerrero
 */
import java.util.ArrayList;
public class AutomataFinitoNoDeterminista extends AutomataFinito
{
	private ArrayList< ArrayList< ArrayList<Integer>>> tablaDeTransiciones;

	public AutomataFinitoNoDeterminista(
			int numeroDeEstados,
			char[] alfabeto,
			int[] estadosAceptacion,
			ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion);
		this.tablaDeTransiciones = tablaDeTransiciones;
	}

	public AutomataFinitoNoDeterminista(){
		this(0, null, null, null);
	}

	public AutomataFinitoNoDeterminista(AutomataFinitoNoDeterminista automata)
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
	public String  toString()
	{
		String cad = super.toString();
		for (int i=0; i<tablaDeTransiciones.size(); i++){
			cad += "Estado "+ (i+1) + ": ";
			for (int j=0; j<tablaDeTransiciones.get(0).size(); j++) {
				cad += "Simbolo "+(j+1)+" {";
				for (int k=0; k<tablaDeTransiciones.get(0).get(0).size(); k++) {
					cad += tablaDeTransiciones.get(i).get(j).get(k);
					if(k + 1 == tablaDeTransiciones.get(0).get(0).size())
						cad += "}";
					else
						cad += ", ";
				}
				cad += "\t";
			}
			cad += "\n";
		}
		return cad;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof AutomataFinitoNoDeterminista)) return false;
		AutomataFinitoNoDeterminista afd = (AutomataFinitoNoDeterminista)obj;
		return super.equals(afd) && tablaDeTransiciones.equals(afd.tablaDeTransiciones);
	}
	public ArrayList<ArrayList<Integer>> obtenerTransicion(int q)
	{
			if(q > tablaDeTransiciones.size()) 
				return null;
			return tablaDeTransiciones.get(q);
	}
	public ArrayList<ArrayList<Integer>> obtenerTransicion(int q, int p)
	{
		ArrayList<ArrayList<Integer>> trancisiones = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> estado = obtenerTransicion(q);
		if(estado == null) return null;
		for(int i=0; i<estado.size(); i++)
			if(estado.get(i).lastIndexOf(p) != -1)
				trancisiones.add(estado.get(i));
		return trancisiones;
	}
	public ArrayList<ArrayList<ArrayList<Integer>>> getTablaDeTransicion()
	{
		return tablaDeTransiciones;
	}
}
