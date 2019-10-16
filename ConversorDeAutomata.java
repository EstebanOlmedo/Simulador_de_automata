public class ConversorDeAutomata{
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDetermista automataFinitoNoDeterminista;
	private AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon;
	private AutomataAPila automataAPila;

	public ConversorDeAutomata(
			AutomataFinitoDeterminista automataFinitoDeterminista,
			AutomataFinitoNoDetermista automataFinitoNoDeterminista,
			AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon,
			AutomataAPila automataAPila
		)
	{
		this.automataFinitoDeterminista = automataFinitoDeterminista;
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataFinitoNoDeterministaEpsilon = automataFinitoNoDeterministaEpsilon;
		this.automataAPila = automataAPila;
	}

	public ConversorDeAutomata(){
		this(null, null, null, null);
	}

	public ConversorDeAutomata(ConversorDeAutomata conversor){
		this(
			conversor.automataFinitoDeterminista,
			conversor.automataFinitoNoDetermista,
			conversor.automataFinitoNoDeterministaEpsilon,
			conversor.automataAPila
			);
	}

	public void destruir(){
		if(automataFinitoDeterminista != null) automataFinitoDeterminista = null;
		if(automataFinitoNoDeterminista != null) automataFinitoNoDeterminista = null;
		if(automataFinitoNoDeterministaEpsilon != null) automataFinitoNoDeterministaEpsilon = null;
		if(automataAPila != null) automataAPila = null;
		System.gc();
	}

	@Override
	public String toString(){
		String cad = "Automatas convertidos:\n";
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
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof ConversorDeAutomata)) return false;
		ConversorDeAutomata conversor = (ConversorDeAutomata)obj;
		return automataFinitoDeterminista.equals(conversor.automataFinitoDeterminista) &&
		automataFinitoNoDeterminista.equals(conversor.automataFinitoNoDeterminista) &&
		automataFinitoNoDeterministaEpsilon.equals(conversor.automataFinitoNoDeterministaEpsilon) &&
		automataAPila.equals(conversor.automataAPila);
	}

}