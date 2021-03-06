package logica;
/**
 * @author Gabriel Graciano Herrera
 */
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.EmptyStackException;
public class AutomataFinitoAPila extends AutomataFinito implements IPila
{
	private ArrayList < ArrayList < ArrayList <Delta> > > tablaDeTransiciones;
	private Stack <Character> pila;

	public AutomataFinitoAPila()
	{
		this(0, null, null, null, null,null,null);
	}
	public AutomataFinitoAPila(int numeroDeEstados, char[] alfabeto, int[] estadosAceptacion, 
			ArrayList<ArrayList<ArrayList<Delta>>> tablaDeTransiciones, 
			Stack <Character> pila,TreeMap<Character,Integer> mapa,String descripcion)
	{
		super(numeroDeEstados, alfabeto, estadosAceptacion,mapa,descripcion);
		this.tablaDeTransiciones = tablaDeTransiciones;
		this.pila = pila;
	}
	public AutomataFinitoAPila(AutomataFinitoAPila automata)
	{
		super(automata);
		this.tablaDeTransiciones = automata.tablaDeTransiciones;
		this.pila = automata.pila;
	}
	public AutomataFinitoAPila(AutomataFinito automata)
	{
		super(automata);
		pila = new Stack<Character>();
		tablaDeTransiciones = null;
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
	@Override
	public boolean evaluar(String cadena)
	{
		Stack<Character> pilaCopia = crearCopia(pila);
		push("#",pilaCopia);
		return evaluarCadena(cadena,pilaCopia,0,0);
	}
        public boolean evaluarCadena(String cadena,Stack <Character> pila,int estado,int indice)
        {
            Delta delta;
            if(indice == cadena.length())
            {
                if(isAceptacion(estado))
                {
                    return true;
                }
                try
                {
                    for(int x=0;x<tablaDeTransiciones.get(estado).size();x++)
                    {
                        if(!tablaDeTransiciones.get(estado).get(x).isEmpty())
                        {
                            for(int y=0;y<tablaDeTransiciones.get(estado).get(x).size();y++)
                            {
                                delta = tablaDeTransiciones.get(estado).get(x).get(y);
                                if(delta.getPrimero() == '~' && delta.getSegundo() == peek(pila))
                                {
                                    Stack <Character> pilaCopia = crearCopia(pila);
                                    pop(pilaCopia);
                                    push(delta.getTercero(),pilaCopia);
                                    if(evaluarCadena(cadena, pilaCopia, x, indice))
                                    {
                                        return true;
                                    }
                                    
                                }
                            }
                        }
                    }
                }
                catch(IndexOutOfBoundsException ioobe){
                    System.out.println("Ocurrió un problema al evaluar la cadena");
                }
                catch(EmptyStackException ese){
                    System.out.println("Ocurrio un problema con la pila");
                }
                return false;
            }
            try
            {
                for(int x=0;x<tablaDeTransiciones.get(estado).size();x++)
                    {
                        if(!tablaDeTransiciones.get(estado).get(x).isEmpty())
                        {
                            for(int y=0;y<tablaDeTransiciones.get(estado).get(x).size();y++)
                            {
                                delta = tablaDeTransiciones.get(estado).get(x).get(y);
                                if(indice < cadena.length() && delta.getPrimero() == cadena.charAt(indice) && delta.getSegundo() == peek(pila))
                                {
                                    Stack <Character> pilaCopia = crearCopia(pila);
                                    pop(pilaCopia);
                                    push(delta.getTercero(),pilaCopia);
                                    if(evaluarCadena(cadena, pilaCopia, x, indice+1))
                                    {
                                        return true;
                                    }
                                }
                                else if(delta.getPrimero() == '~' && delta.getSegundo() == peek(pila))
                                {
                                    Stack <Character> pilaCopia = crearCopia(pila);
                                    pop(pilaCopia);
                                    push(delta.getTercero(),pilaCopia);
                                    if(evaluarCadena(cadena, pilaCopia, x, indice))
                                    {
                                        return true;
                                    }
                                    
                                }
                            }
                        }
                    }
            }
            catch(IndexOutOfBoundsException ioobe){
                System.out.println("Ocurrió un problema al evaluar la cadena");
            }
            catch(EmptyStackException ese){
                System.out.println("Ha currido un problema con la pila");
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
                if(cadena.charAt(i) != '~')
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
                if(cadena.charAt(i) != '~')
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
    	public Stack<Character> crearCopia(Stack<Character> pila){
        	Stack<Character> copia = new Stack<Character>();
        	copia.addAll(pila);
        	return copia;
    	}
	public void setPila(Stack<Character> pila)
	{
		this.pila = pila;
	}
	public void setTablaDeTransiciones(ArrayList<ArrayList<ArrayList<Delta>>> tablaDeTransiciones)
	{
		this.tablaDeTransiciones = tablaDeTransiciones;
	}
    @Override
    public String getTransiciones()
    {
        String transiciones = "";
        for(int i=0; i<getNumeroDeEstados(); i++){
            for(int j=0; j<getNumeroDeEstados(); j++){
                for(Delta delta:tablaDeTransiciones.get(i).get(j)){
                    transiciones += "S(q"+i+","+delta.getPrimero()+") = (q"+j+","+delta.getSegundo()+","+delta.getTercero()+")\t";
                }
            }
        }
        return transiciones;
    }
    public ArrayList<ArrayList<ArrayList<Delta>>> getTablaTransiciones(){
        return tablaDeTransiciones;
    }
}

