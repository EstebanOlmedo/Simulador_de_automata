import java.util.ArrayList;
import java.util.Stack;
public class AutomataAPila extends AutomataFinito
{
	private ArrayList<ArrayList<Integer>> tablaDeTransiciones;
	private ArrayList<ArrayList<String>> tablaDeLaPila;
	private Stack <String> pila;

	public AutomataAPila()
	{
		this(0, null, null, null, null, null);
	}
	public AutomataAPila(int numeroDeEstados, char[] alfabeto, int[] estadosAceptacion, 
			ArrayList<ArrayList<Integer>> tablaDeTransiciones, 
			ArrayList<ArrayList<String>> tablaDeLaPila, 
			Stack <String> pila)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion);
		this.tablaDeTransiciones = tablaDeTransiciones;
		this.tablaDeLaPila = tablaDeLaPila;
		this.pila = pila;
	}
	public AutomataAPila(AutomataAPila automata)
	{
		super(automata);
		this.tablaDeTransiciones = automata.tablaDeTransiciones;
		this.tablaDeLaPila = automata.tablaDeLaPila;
		this.pila = automata.pila;
	}
	public void destruir()
	{
		super.destruir();
		if(tablaDeTransiciones != null)
			tablaDeTransiciones = null;
		if(tablaDeLaPila != null)
			tablaDeLaPila = null;
		if(pila != null)
			pila = null;
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof AutomataAPila)){return false;}
		AutomataAPila automata = (AutomataAPila)obj;
		return super.equals(automata) &&
			tablaDeTransiciones.equals(automata.tablaDeTransiciones) &&
			tablaDeLaPila.equals(automata.tablaDeLaPila) &&
			pila.equals(automata.pila);
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		retorno += super.toString();
		int i = 0;
		for(ArrayList estado: tablaDeTransiciones)
		{
			i++;
			if(estado != null)
				retorno += "Estado " + i + ": " + estado + "\n"; 
		}
		i = 0;
		for(ArrayList simbolos: tablaDeLaPila)
		{
			i++;
			if(simbolos != null)
				retorno += "Transiciones del estado " + i + ": " + simbolos + "\n";
		}
		retorno += pila;
		return retorno;
	}
}
