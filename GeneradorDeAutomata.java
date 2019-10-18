public class GeneradorDeAutomata implements IGeneradorDeAutomataFinitoAPila, IGeneradorDeAutomataFinitoNoDeterminista
{
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon;
	private AutomataFinitoAPila automataAPila;

	public GeneradorDeAutomata(
			AutomataFinitoDeterminista automataFinitoDeterminista,
			AutomataFinitoNoDeterminista automataFinitoNoDeterminista,
			AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon,
			AutomataFinitoAPila automataAPila
		)
	{
		this.automataFinitoDeterminista = automataFinitoDeterminista;
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataFinitoNoDeterministaEpsilon = automataFinitoNoDeterministaEpsilon;
		this.automataAPila = automataAPila;
	}

	public GeneradorDeAutomata()
	{
		this(null, null, null, null);
	}

	public GeneradorDeAutomata(GeneradorDeAutomata conversor)
	{
		this(
			conversor.automataFinitoDeterminista,
			conversor.automataFinitoNoDeterminista,
			conversor.automataFinitoNoDeterministaEpsilon,
			conversor.automataAPila
			);
	}

	public void destruir()
	{
		if(automataFinitoDeterminista != null)
		{
			automataFinitoNoDeterminista.destruir();
			automataFinitoDeterminista = null;
		}
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
		String cad = "Automatas generados:\n";
		if(automataFinitoDeterminista != null)
			cad += "Automata Finito Determinista: " + automataFinitoDeterminista + "\n";
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
		if(!(obj instanceof GeneradorDeAutomata)) return false;
		GeneradorDeAutomata conversor = (GeneradorDeAutomata)obj;
		return automataFinitoDeterminista.equals(conversor.automataFinitoDeterminista) &&
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
}
