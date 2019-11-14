/**
 * @author Gabriel Graciano Herrera
 */
import java.util.ArrayList;
public class ConversorDeAutomata{
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoAPila automataAPila;

	public ConversorDeAutomata(
			AutomataFinitoDeterminista automataFinitoDeterminista,
			AutomataFinitoNoDeterminista automataFinitoNoDeterminista,
			AutomataFinitoAPila automataAPila
		)
	{
		this.automataFinitoDeterminista = automataFinitoDeterminista;
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataAPila = automataAPila;
	}

	public ConversorDeAutomata(){
		this(null, null, null);
	}

	public ConversorDeAutomata(ConversorDeAutomata conversor){
		this(
			conversor.automataFinitoDeterminista,
			conversor.automataFinitoNoDeterminista,
			conversor.automataAPila
			);
	}

	public void destruir(){
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
		if(automataAPila != null)
		{
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
		automataAPila.equals(conversor.automataAPila);
	}
	
	public void convertirAFDaAFP(AutomataFinitoDeterminista automata)
	{
		

	}

	public void convertirAFNaAFD(AutomataFinitoNoDeterminista automata)
	{

	}

	public AutomataFinitoDeterminista getAutomataFinitoDeterminista()
	{
		return automataFinitoDeterminista;
	}

	public AutomataFinitoAPila getAutomataFinitoAPila()
	{
		return automataAPila;
	}
}
