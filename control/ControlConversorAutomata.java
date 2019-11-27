package control;
/**
 * @author Daniel Montes Guerrero
 */
import logica.ConversorDeAutomata;
import logica.AutomataFinitoDeterminista;
import logica.AutomataFinitoAPila;
import logica.AutomataFinitoNoDeterminista;
import logica.AutomataFinito;
public class ControlConversorAutomata
{
	private ConversorDeAutomata conversor;

	public ControlConversorAutomata(ConversorDeAutomata conversor)
	{
		this.conversor = conversor;
	}
	public ControlConversorAutomata()
	{
		this(new ConversorDeAutomata());
	}
	public ControlConversorAutomata(ControlConversorAutomata cca)
	{
		this(cca.conversor);
	}
	public void destruir()
	{
		if(conversor != null) conversor = null;
		System.gc();
	}
	@Override
	public String toString()
	{
		return "Control conversor de automata:\n"+conversor;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof ControlConversorAutomata)) return false;
		ControlConversorAutomata control = (ControlConversorAutomata)obj;
		return conversor.equals(control.conversor);
	}
	public AutomataFinito convertir(AutomataFinitoNoDeterminista automata)
	{
		try{
			conversor.convertirAFNaAFD(automata);
			System.out.println("Se ha convertido el automata con exito");
		}
		catch(NullPointerException npe){
			System.out.println("Aun no has generado el automata .-.");
			//npe.printStackTrace();
		}
		return conversor.getAutomataFinito();
	}
	public AutomataFinito convertir(AutomataFinitoDeterminista automata)
	{
		try{
			conversor.convertirAFDaAFP(automata);
			System.out.println("Se ha convertido el automata con exito");
		}
		catch(NullPointerException npe){
			System.out.println("Aun no has generado el automata .-.");
			//npe.printStackTrace();
		}
		return conversor.getAutomataFinito();
	}
}