/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.ArrayList;
import java.util.TreeMap;
public class AutomataFinitoDeterminista extends AutomataFinito
{
	private ArrayList <ArrayList <Integer> > tablaDeTransiciones;

	public AutomataFinitoDeterminista()
	{
		this(0, null, null, null, null);
	}
	public AutomataFinitoDeterminista(
			int numeroEstados,
			char[] alfabeto, int[] estadosAceptacion,
			TreeMap<Character,Integer> mapa,
			ArrayList <ArrayList <Integer> > tablaDeTransiciones)
	{
		super(numeroEstados, alfabeto, estadosAceptacion, mapa);
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
	public ArrayList<Integer> obtenerTransicion(int q)
	{
		if(q > tablaDeTransiciones.size())
			return null;
		ArrayList<Integer> estado = tablaDeTransiciones.get(q);
		return estado;
	}
	public ArrayList<Integer> obtenerTransicion(int q, int p)
	{
		ArrayList<Integer> estado = obtenerTransicion(q);
		ArrayList<Integer> transiciones = new ArrayList<Integer>();
		if(estado == null){return null;}
		for (int i=0; i<estado.size(); i++)
		{
			if(estado.get(i) == p)
				transiciones.add(i);
		}
		return transiciones;
	}
	public int getAdyacencia(int q, char a)
	{
		int simbolo = super.getNumeroSimbolo(a);
		if(simbolo == -1)
			return -1;
		else
			return getAdyacencia(q, simbolo);
	}
	public int getAdyacencia(int q, int simbolo)
	{
		if(simbolo > tablaDeTransiciones.get(0).size())
			return -1;
		else
			return tablaDeTransiciones.get(q).get(simbolo);
	}
	public ArrayList<ArrayList<Integer>> getTablaDeTransiciones()
	{
		return tablaDeTransiciones;
	}
	public boolean evaluar(String cadena)
	{
		int estado = 0;
		for(int i=0; i<cadena.length(); i++)
		{
			char simbolo = cadena.charAt(i);
			if(super.exist(simbolo))
				estado = getAdyacencia(estado, super.getNumeroSimbolo(simbolo));
			else
				return false;
			if(estado == -1) return false;
		}
		return isAceptacion(estado);
	}
}
