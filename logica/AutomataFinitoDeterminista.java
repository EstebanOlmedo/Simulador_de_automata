package logica;
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
		this(0, null, null, new TreeMap<Character,Integer>(), null,null);
	}
	public AutomataFinitoDeterminista(
			int numeroEstados,
			char[] alfabeto, int[] estadosAceptacion,
			TreeMap<Character,Integer> mapa,
			ArrayList <ArrayList <Integer> > tablaDeTransiciones,
			String descripcion)
	{
		super(numeroEstados, alfabeto, estadosAceptacion, mapa, descripcion);
		this.tablaDeTransiciones = tablaDeTransiciones;
	}
	public AutomataFinitoDeterminista(AutomataFinitoDeterminista automata)
	{
		super(automata);
		this.tablaDeTransiciones = automata.tablaDeTransiciones;
	}
	public AutomataFinitoDeterminista(AutomataFinito automata)
	{
		super(automata);
		tablaDeTransiciones = null;
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
		throws IndexOutOfBoundsException
	{
		if(q > tablaDeTransiciones.size())
			return -1;
		else
			return tablaDeTransiciones.get(q).get(simbolo);
	}
	public ArrayList<ArrayList<Integer>> getTablaDeTransiciones()
	{
		return tablaDeTransiciones;
	}
	@Override
	public boolean evaluar(String cadena)
	{
		int estado = 0;
		try
		{
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
		}catch(IndexOutOfBoundsException ioobe){
			System.out.println("Ocurrió un error en el proceso de evaluación");
			ioobe.printStackTrace();
		}
		return false;
	}
	public void setTablaDeTransiciones(ArrayList<ArrayList<Integer>> tablaDeTransiciones)
	{
		this.tablaDeTransiciones = tablaDeTransiciones;
	}
	@Override
	public String getTransiciones()
	{
		String transiciones = "";
		for(int i=0; i<getNumeroDeEstados(); i++){
			for(int j=0; j<getAlfabeto().length; j++){
				transiciones += "S(q"+i+","+getSimbolo(j)+") = "+getAdyacencia(i,j)+"\n";
			}
		}
		return transiciones;
	}
}
