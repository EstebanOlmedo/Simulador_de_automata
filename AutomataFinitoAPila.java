import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;
//import Simulador_de_automata.Delta;
public class AutomataFinitoAPila extends AutomataFinito implements IPila
{
	private ArrayList < ArrayList < ArrayList <Delta> > > tablaDeTransiciones;
	private Stack <Character> pila;

	public AutomataFinitoAPila()
	{
		this(0, null, null, null, null,null);
	}
	public AutomataFinitoAPila(int numeroDeEstados, char[] alfabeto, int[] estadosAceptacion, 
			ArrayList<ArrayList<ArrayList<Delta>>> tablaDeTransiciones, 
			Stack <Character> pila,TreeMap<Character,Integer> mapa)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion,mapa);
		this.tablaDeTransiciones = tablaDeTransiciones;
		this.pila = pila;
	}
	public AutomataFinitoAPila(AutomataFinitoAPila automata)
	{
		super(automata);
		this.tablaDeTransiciones = automata.tablaDeTransiciones;
		this.pila = automata.pila;
	}
	public void destruir()
	{
		super.destruir();
		if(tablaDeTransiciones != null)
			tablaDeTransiciones = null;
		if(pila != null)
			pila = null;
		System.gc();
	}
        public boolean evaluarCadena(String cadena,Stack <Character> pila,int estado,int indice)
        {
            Delta delta;
            Stack <Character> pilaCopia = pila;
            if(indice == cadena.length())
            {
                if(isAceptacion(estado))
                {
                    return true;
                }
                for(int x=0;x<tablaDeTransiciones.get(estado).size();x++)
                {
                    if(!tablaDeTransiciones.get(estado).get(x).isEmpty())
                    {
                        for(int y=0;y<tablaDeTransiciones.get(estado).get(x).size();y++)
                        {
                            delta = tablaDeTransiciones.get(estado).get(x).get(y);
                            if(delta.getPrimero() == '~' && delta.getSegundo() == peek())
                            {
                                push(delta.getTercero(),pilaCopia);
                                if(evaluarCadena(cadena, pilaCopia, estado, indice))
                                {
                                    return true;
                                }
                                
                            }
                        }
                    }
                }
            }
            for(int x=0;x<tablaDeTransiciones.get(estado).size();x++)
                {
                    if(!tablaDeTransiciones.get(estado).get(x).isEmpty())
                    {
                        for(int y=0;y<tablaDeTransiciones.get(estado).get(x).size();y++)
                        {
                            delta = tablaDeTransiciones.get(estado).get(x).get(y);
                            if(indice < cadena.length() && delta.getPrimero() == cadena.charAt(indice) && delta.getSegundo() == peek())
                            {
                                pop(delta.getTercero(),pilaCopia);
                                if(evaluarCadena(cadena, pilaCopia, x, indice+1))
                                {
                                    return true;
                                }
                            }
                            else if(delta.getPrimero() == '~' && delta.getSegundo() == peek())
                            {
                                push(delta.getTercero(),pilaCopia);
                                if(evaluarCadena(cadena, pilaCopia, x, indice))
                                {
		                     return true;
                                }
                                
                            }
                        }
                    }
                }
                            
            return false;
        }
        
        public char pop(Stack <Character> pila)
	{
		return pila.pop().charValue();
	}

	public void push(String cadena,Stack <Character> pila)
	{
            for (int i = 0; i < cadena.length(); i++)
            {
                pila.push(cadena.charAt(i));
            }
		
	}

	public char peek(Stack <Character> pila)
	{
		return pila.peek().charValue();
	}

	public boolean isEmpty(Stack <Character> pila)
	{
		return pila.empty();
	}

	public char pop()
	{
		return pila.pop().charValue();
	}

	public void push(String cadena)
	{
            for (int i = 0; i < cadena.length(); i++)
            {
                pila.push(cadena.charAt(i));
            }
		
	}

	public char peek()
	{
		return pila.peek().charValue();
	}

	public boolean isEmpty()
	{
		return pila.empty();
	}
}

