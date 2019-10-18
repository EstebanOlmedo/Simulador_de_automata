/**
 * @author Esteban Olmedo Ramírez
 */
public class GeneradorDeTodoTipoDeAutomataFinito
	implements IGeneradorDeTodoTipoDeAutomataFinitoFinitoNoDeterministaEpsilon,
		   IGeneradorDeTodoTipoDeAutomataFinitoFinitoAPila,
	extends GeneradorDeAutomataFinitoDeterminista;
{
	//private AutomataFinitoDeterminista automataFinitoDeterminista;
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
		//this.automataFinitoDeterminista = automataFinitoDeterminista;
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataFinitoNoDeterministaEpsilon = automataFinitoNoDeterministaEpsilon;
		this.automataAPila = automataAPila;
	}

	public GeneradorDeTodoTipoDeAutomataFinito()
	{
		this(
				new AutomataFinitoDeterminista(), 
				new AutomataFinitoNoDeterministaEpsilon(),
			       	new AutomataFinitoNoDeterministaEpsilon(),
			       	new AutomataFinitoAPila()
				);
	}

	public GeneradorDeTodoTipoDeAutomataFinito(GeneradorDeAutomata generador)
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
		GeneradorDeTodoTipoDeAutomataFinito conversor = (GeneradorDeAutomata)obj;
		return super.equals(obj) &&
		automataFinitoNoDeterminista.equals(conversor.automataFinitoNoDeterminista) &&
		automataFinitoNoDeterministaEpsilon.equals(conversor.automataFinitoNoDeterministaEpsilon) &&
		automataAPila.equals(conversor.automataAPila);
	}
	public String getInforme()
	{
		return "Automata generado\nDatos del automata generado:n";
	}
	public String getInforme(AutomataFinitoDeterminista afd)
	{
		return getInforme() + afd;
	}
	public String getInforme(AutomataFinitoNoDeterminista afn){
		return getInforme() + afn;
	}
	public String getInforme(AutomataFinitoNoDeterministaEpsilon afn_e)
	{
		return getInforme() + afn_e;
	}
	public String getInforme(AutomataFinitoAPila automataPila)
	{
		return getInforme() + automataPila;
	}
	public AutomataFinitoNoDeterminista crearAutomataFinitoNoDeterminista()
	{
		System.out.println("Generando autómata");
		return new AutomataFinitoNoDeterminista();
	}
	public AutomataFinitoAPila crearAutomataFinitoAPila()
	{
		System.out.println("Generando autómata");
		return new AutomataFinitoAPila();
	}
	/*
	public AutomataFinitoDeterminista crearAutomataFinitoDeterminista()
	{
		System.out.println("Generando automata");
		return new AutomataFinitoDeterminista();
	}
	*/
	public AutomataFinitoNoDeterministaEpsilon crearAutomataFinitoNoDeterministaEpsilon()
	{
		System.out.println("Generando automata");
		return new AutomataFinitoNoDeterministaEpsilon();
	}
}
