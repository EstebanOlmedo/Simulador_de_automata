package control;
/**
 * @author Daniel Montes Guerrero
 */
import logica.ConversorDeAutomata;
import logica.AutomataFinitoDeterminista;
import logica.AutomataFinitoAPila;
import logica.AutomataFinitoNoDeterminista;
import logica.AutomataFinito;
import javax.swing.JOptionPane;

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
			//System.out.println("Se ha convertido el automata con exito");
			JOptionPane.showMessageDialog(null, "Se ha concertido el automata con exito", "Resultado", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException npe){
			//System.out.println("Aun no has generado el automata .-.");
			JOptionPane.showMessageDialog(null, "Aun no has generado el automata", "Error", JOptionPane.ERROR_MESSAGE);
			//npe.printStackTrace();
		}
		return conversor.getAutomataFinito();
	}
	public AutomataFinito convertir(AutomataFinitoDeterminista automata)
	{
		try{
			conversor.convertirAFDaAFP(automata);
			JOptionPane.showMessageDialog(null, "Se ha concertido el automata con exito", "Resultado", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException npe){
			//System.out.println("Aun no has generado el automata .-.");
			//npe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Aun no has generado el automata", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return conversor.getAutomataFinito();
	}
}