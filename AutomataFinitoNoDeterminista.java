/**
 * @author Gabriel Graciano Herrera
 */
import java.util.ArrayList;
import java.util.TreeMap;
public class AutomataFinitoNoDeterminista extends AutomataFinito
{
	private ArrayList< ArrayList< ArrayList<Integer>>> tablaDeTransiciones;

	public AutomataFinitoNoDeterminista(
			int numeroDeEstados,
			char[] alfabeto,
			int[] estadosAceptacion,
			TreeMap<Character,Integer> mapa,
			ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones,
			String descripcion)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion, mapa, descripcion);
		this.tablaDeTransiciones = tablaDeTransiciones;
	}

	public AutomataFinitoNoDeterminista(){
		this(0, null, null, null, null,null);
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
		throws IndexOutOfBoundsException
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
	public ArrayList<ArrayList<ArrayList<Integer>>> getTablaDeTransiciones()
	{
		return tablaDeTransiciones;
	}
	public ArrayList<Integer> getAdyacencia(int estado, int simbolo)
	{
		try
		{
			if(obtenerTransicion(estado) == null)
				return null;
			else
				return obtenerTransicion(estado).get(simbolo);
		}catch(IndexOutOfBoundsException ioobe)
		{
			System.out.println("Ocurrió un error");
			ioobe.printStackTrace();
		}
		return null;
	}
	public boolean evaluar(String cadena, int indice, int estado)
	{
		if(indice >= cadena.length()){
			return isAceptacion(estado);
		}
		try
		{
			if(super.exist(cadena.charAt(indice))){
				boolean respuesta = false;
				ArrayList<Integer> adyacencia = getAdyacencia(estado, super.getNumeroSimbolo(cadena.charAt(indice)));
				for(int i=0; i<adyacencia.size(); i++){
					respuesta |= evaluar(cadena, indice+1, adyacencia.get(i));
					if(respuesta == true) return true;
				}
				return respuesta;
			}
			else
				return false;
		}
		catch(NullPointerException npe)
		{
			System.out.println("Ha ocurrido un error al evaluar");
			npe.printStackTrace();
		}
		catch(IndexOutOfBoundsException ioobe)
		{
			System.out.println("Ha ocurrido un error al evaluar");
			ioobe.printStackTrace();
		}
		return false;
	}
}
