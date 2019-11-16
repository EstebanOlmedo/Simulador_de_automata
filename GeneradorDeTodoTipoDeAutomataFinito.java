/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.ArrayList;
import java.util.Stack;
public class GeneradorDeTodoTipoDeAutomataFinito
	extends GeneradorDeAutomataFinitoDeterminista
	implements IGeneradorEpsilon
{
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon;
	private AutomataFinitoAPila automataAPila;

	public GeneradorDeTodoTipoDeAutomataFinito(
			AutomataFinitoDeterminista automataFinitoDeterminista,
			AutomataFinitoNoDeterminista automataFinitoNoDeterminista,
			AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon,
			AutomataFinitoAPila automataAPila
		)
	{
		super(automataFinitoDeterminista, automataFinitoDeterminista);
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataFinitoNoDeterministaEpsilon = automataFinitoNoDeterministaEpsilon;
		this.automataAPila = automataAPila;
	}

	public GeneradorDeTodoTipoDeAutomataFinito()
	{
		this(
				null, 
				null,
			       	null,
			       	null
				);
	}

	public GeneradorDeTodoTipoDeAutomataFinito(GeneradorDeTodoTipoDeAutomataFinito generador)
	{
		super(generador);
		this.automataFinitoNoDeterminista = generador.automataFinitoNoDeterminista;
		this.automataFinitoNoDeterministaEpsilon = generador.automataFinitoNoDeterministaEpsilon;
		this.automataAPila = generador.automataAPila;
	}

	public void destruir()
	{
		super.destruir();
		if(automataFinitoNoDeterminista != null)
		{
			automataFinitoNoDeterminista.destruir();
			automataFinitoNoDeterminista = null;
		}
		if(automataFinitoNoDeterministaEpsilon != null)
		{
			automataFinitoNoDeterministaEpsilon.destruir();
			automataFinitoNoDeterministaEpsilon = null;
		}
		if(automataAPila != null)
		{
			automataAPila.destruir();
			automataAPila = null;
		}
		System.gc();
	}
	@Override
	public String toString()
	{
		String cad = super.toString();
		if(automataFinitoNoDeterminista != null)
			cad += "Automata Finito No Determinista: " + automataFinitoNoDeterminista + "\n";
		if(automataFinitoNoDeterministaEpsilon != null)
			cad += "Automata Finito No Determinista Epsilon: " + automataFinitoNoDeterministaEpsilon + "\n";
		if(automataAPila != null)
			cad += "Automata a Pila: " + automataAPila + "\n";
		return cad;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof GeneradorDeTodoTipoDeAutomataFinito)) return false;
		GeneradorDeTodoTipoDeAutomataFinito conversor = (GeneradorDeTodoTipoDeAutomataFinito)obj;
		return super.equals(obj) &&
		automataFinitoNoDeterminista.equals(conversor.automataFinitoNoDeterminista) &&
		automataFinitoNoDeterministaEpsilon.equals(conversor.automataFinitoNoDeterministaEpsilon) &&
		automataAPila.equals(conversor.automataAPila);
	}
	
	public void crearAutomataFinitoNoDeterminista()
	{
		crearAutomataFinito();
		AutomataFinito automata = getAutomataFinito();
		System.out.println("Generando autómata");
		ArrayList<ArrayList<ArrayList<Integer>>> tabla = new ArrayList<ArrayList<ArrayList<Integer>>>();
		System.out.println("Ingresando transiciones");
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<ArrayList<Integer>> estado = new ArrayList<ArrayList<Integer>>();
			for(int j=0; j<automata.getMapa().size(); j++){
				System.out.println("Ingresando transiciones de S(q"+i+","+automata.getSimbolo(j)+")");
				System.out.println("Ingresa -1 para omitir/finalizar la transicion actual");
				ArrayList<Integer> transiciones = new ArrayList<Integer>();
				int transicion = -1;
				while((transicion = getTeclado().dameUnInt("Ingresa el estado destino")) >= 0){
					transiciones.add(transicion);
				}
				estado.add(transiciones);
			}
			tabla.add(estado);
		}
		System.out.println("El automata fue creado con exito :)");
		automataFinitoNoDeterminista = new AutomataFinitoNoDeterminista(
			automata.getNumeroDeEstados(),
			automata.getAlfabeto(),
			automata.getEstadosDeAceptacion(),
			automata.getMapa(),
			tabla,
			automata.getDescripcion()
		);
	}

	public void crearAutomataFinitoAPila()
	{
		crearAutomataFinito();
		AutomataFinito automata = getAutomataFinito();
		ArrayList<ArrayList<ArrayList<Delta>>> tabla = new ArrayList<ArrayList<ArrayList<Delta>>>();
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<ArrayList<Delta>> estado = new ArrayList<ArrayList<Delta>>();
			for(int j=0; j<automata.getNumeroDeEstados(); j++){
				System.out.println("Ingresando transiciones del estado q"+i+" al estado q"+j);
				System.out.println("Ingresa '#' para omitir/finalizar la transicion actual");
				ArrayList<Delta> transiciones = new ArrayList<Delta>();
				char carLeido = '#';
				char cimaPila = '\u0000';
				String dejarEnPila = "";
				while((carLeido = getTeclado().dameUnChar("Ingresa el caracter leido")) != '#'){
					cimaPila = getTeclado().dameUnChar("Ingresa lo que hay en la cima de la pila");
					dejarEnPila = getTeclado().dameUnString("Ingresa lo que se inserta en la pila");
					transiciones.add(new Delta(carLeido,cimaPila,dejarEnPila));
				}
				estado.add(transiciones);
			}
			tabla.add(estado);
		}
		automataAPila = new AutomataFinitoAPila(
			automata.getNumeroDeEstados(),
			automata.getAlfabeto(),
			automata.getEstadosDeAceptacion(),
			tabla,
			new Stack<Character>(),
			automata.getMapa(),
			automata.getDescripcion()
		);
	}
	
	public void crearAutomataFinitoNoDeterministaEpsilon()
	{
		crearAutomataFinitoNoDeterminista();
		AutomataFinitoNoDeterminista automata = getAutomataFinitoNoDeterminista();
		System.out.println("Ahora solo faltan las transiciones epsilon");
		ArrayList<ArrayList<Integer>> adyacenciaEpsilon = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<Integer> transiciones = new ArrayList<Integer>();
			System.out.println("Ingresando las transiciones-epsilon del estado "+i);
			System.out.println("Ingresa -1 para omitir/finalizar la transicion actual");
			int transicion = -1;
			while((transicion = getTeclado().dameUnInt("Ingresa el estado destino")) >= 0){
				transiciones.add(transicion);
			}
			adyacenciaEpsilon.add(transiciones);
		}
		System.out.println("El automata ha sido creado completamente ;)");
		automataFinitoNoDeterministaEpsilon =  new AutomataFinitoNoDeterministaEpsilon(
			automata.getNumeroDeEstados(),
			automata.getAlfabeto(),
			automata.getEstadosDeAceptacion(),
			automata.getMapa(),
			automata.getTablaDeTransiciones(),
			adyacenciaEpsilon,
			automata.getDescripcion()
		);
	}

	public AutomataFinitoNoDeterminista getAutomataFinitoNoDeterminista()
	{
		return automataFinitoNoDeterminista;
	}

	public AutomataFinitoNoDeterministaEpsilon getAutomataFinitoNoDeterministaEpsilon()
	{
		return automataFinitoNoDeterministaEpsilon;
	}

	public AutomataFinitoAPila getAutomataFinitoAPila()
	{
		return automataAPila;
	}

	public void setAutomataFinitoNoDeterminista(AutomataFinitoNoDeterminista afn)
	{
		automataFinitoNoDeterminista = afn;
	}

	public void setAutomataFinitoNoDeterministaEpsilon(AutomataFinitoNoDeterministaEpsilon afne)
	{
		automataFinitoNoDeterministaEpsilon = afne;
	}

	public void setAutomataFinitoAPila(AutomataFinitoAPila afp)
	{
		automataAPila = afp;
	}

}
