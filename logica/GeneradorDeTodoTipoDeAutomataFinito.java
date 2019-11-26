package logica;
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
	
	@Override
	public void crearAutomataFinitoNoDeterminista(
		ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones,
		int numeroDeEstados, 
		char[] alfabeto, 
		int[] aceptacion, 
		String descripcion)
	{
		System.out.println("Generando autómata");
		automataFinitoNoDeterminista = new AutomataFinitoNoDeterminista();
		crearAutomataFinito(numeroDeEstados, alfabeto, 
			aceptacion, descripcion, automataFinitoNoDeterminista);
		automataFinitoNoDeterminista.setTablaDeTransiciones(tablaDeTransiciones);
		System.out.println("El automata fue creado con exito :)");
	}
	public void crearAutomataFinitoAPila(
		ArrayList<ArrayList<ArrayList<Delta>>> tablaDeTransiciones,
		int numeroDeEstados, 
		char[] alfabeto, 
		int[] aceptacion, 
		String descripcion)
	{
		System.out.println("Creando un automáta finito a pila");
		automataAPila = new AutomataFinitoAPila();
		crearAutomataFinito(numeroDeEstados, alfabeto, aceptacion, 
			descripcion, automataAPila);
		automataAPila.setTablaDeTransiciones(tablaDeTransiciones);
		System.out.println("El autómata ha sido creado con éxito :)");
	}
	
	@Override
	public void crearAutomataFinitoNoDeterministaEpsilon(AutomataFinitoNoDeterminista automata,
			ArrayList<ArrayList<Integer>> adyacenciaEpsilon)
	{
		System.out.println("Creando automata finito no determinista Epsilon");
		automataFinitoNoDeterministaEpsilon = new AutomataFinitoNoDeterministaEpsilon(automata);
		automataFinitoNoDeterministaEpsilon.setAdyacenciaEpsilon(adyacenciaEpsilon);
		System.out.println("El automata ha sido creado completamente ;)");
	}
	
	public AutomataFinito getAutomataFinitoNoDeterminista()
	{
		return automataFinitoNoDeterminista;
	}

	public AutomataFinito getAutomataFinitoNoDeterministaEpsilon()
	{
		return automataFinitoNoDeterministaEpsilon;
	}

	public AutomataFinito getAutomataFinitoAPila()
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
