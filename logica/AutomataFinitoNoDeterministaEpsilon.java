package logica;
/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.ArrayList;
import java.util.TreeMap;
public class AutomataFinitoNoDeterministaEpsilon extends AutomataFinitoNoDeterminista
{
	private ArrayList<ArrayList<Integer>> adyacenciaEpsilon;

	public AutomataFinitoNoDeterministaEpsilon()
	{
		this(0, null, null, null, null, null, null);
	}
	public AutomataFinitoNoDeterministaEpsilon(
			int numeroDeEstados,
			char[] alfabeto,
			int[] estadosAceptacion,
			TreeMap<Character, Integer> mapa,
			ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones,
			ArrayList<ArrayList<Integer>> adyacenciaEpsilon,
			String descripcion)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion, mapa, tablaDeTransiciones,descripcion);
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
	public ArrayList<ArrayList<Integer>> obtenerTransicion(int q, int p, int k)
	{
		ArrayList<ArrayList<Integer>> transiciones = super.obtenerTransicion(q,p);
		if(q > adyacenciaEpsilon.size()) return transiciones;
		if(transiciones == null) transiciones = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> epsilon = adyacenciaEpsilon.get(q);
		if(epsilon.lastIndexOf(k) != -1)
			transiciones.add(epsilon);
		return transiciones;
	}
	@Override
	public boolean evaluar(String cadena)
	{
		return evaluar(cadena,0,0);
	}
	@Override
	public boolean evaluar(String cadena, int indice, int estado)
	{
		boolean respuesta = false;
		if(indice >= cadena.length()){
			if(isAceptacion(estado))
				return true;
			else
			{
				for(int i=0; i<adyacenciaEpsilon.get(estado).size(); i++){
					if( isAceptacion(adyacenciaEpsilon.get(estado).get(i)) )
						return true;
				}
				return false;
			}
		}
		if(super.exist(cadena.charAt(indice))){
			ArrayList<Integer> adyacencia = getAdyacencia(estado, super.getNumeroSimbolo(cadena.charAt(indice)));
			for(int i=0; i<adyacencia.size(); i++){
				respuesta |= evaluar(cadena, indice+1, adyacencia.get(i));
				if(respuesta == true) return true;
			}
			for(int i=0; i<adyacenciaEpsilon.get(estado).size(); i++){
				respuesta |= evaluar(cadena, indice, adyacenciaEpsilon.get(estado).get(i));
				if(respuesta == true) return true;
			}
			return respuesta;
		}
		else
			return false;
	}
}
