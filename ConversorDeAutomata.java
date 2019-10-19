/**
 * @author Gabriel Graciano Herrera
 */
public class ConversorDeAutomata{
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon;
	private AutomataFinitoAPila automataAPila;

	public ConversorDeAutomata(
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

	public ConversorDeAutomata(){
		this(null, null, null, null);
	}

	public ConversorDeAutomata(ConversorDeAutomata conversor){
		this(
			conversor.automataFinitoDeterminista,
			conversor.automataFinitoNoDeterminista,
			conversor.automataFinitoNoDeterministaEpsilon,
			conversor.automataAPila
			);
	}

	public void destruir(){
		if(automataFinitoDeterminista != null){
			automataFinitoNoDeterminista.destruir();
			automataFinitoDeterminista = null;
		}
		if(automataFinitoNoDeterminista != null){
			automataFinitoNoDeterminista.destruir();
			automataFinitoNoDeterminista = null;
		}
		if(automataFinitoNoDeterministaEpsilon != null){
			automataFinitoNoDeterministaEpsilon.destruir();
			automataFinitoNoDeterministaEpsilon = null;
		}
		if(automataAPila != null){
			automataAPila.destruir();
			automataAPila = null;
		}
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
	public boolean sePuede(boolean sePuede)
	{
		if(sePuede)
			return true;
		else
		{
			System.out.println("No se puede convertir los automátas propuestos");
			return false;
		}
	}
	public boolean sePuede(AutomataFinitoDeterminista afd, AutomataFinitoAPila automataPila)
	{
		return sePuede(true);
	}
	public boolean sePuede(AutomataFinitoAPila automataPila, AutomataFinitoDeterminista adf)
	{
		return sePuede(false);
	}
}
