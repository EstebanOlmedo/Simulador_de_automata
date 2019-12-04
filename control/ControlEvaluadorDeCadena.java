package control;
/**
 * @author esteban
 */
import logica.EvaluadorDeExpresion;
import logica.AutomataFinito;
import logica.AutomataFinitoAPila;
import logica.AutomataFinitoDeterminista;
import logica.AutomataFinitoNoDeterminista;
import logica.AutomataFinitoNoDeterministaEpsilon;
import logica.MaquinaDeTuring;
import javax.swing.JOptionPane;

public class ControlEvaluadorDeCadena
{
	private EvaluadorDeExpresion evaluador;
	
	public ControlEvaluadorDeCadena()
	{
		evaluador = new EvaluadorDeExpresion();
	}
	public ControlEvaluadorDeCadena(EvaluadorDeExpresion evaluador)
	{
		this.evaluador = evaluador;
	}
	public void destruir()
	{
		if(evaluador != null)
		{
			evaluador.destruir();
			evaluador = null;
		}
		System.gc();
	}
	@Override
	public String toString()
	{
		return "Control evaluador de cadena";
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof ControlEvaluadorDeCadena))
			return false;
		ControlEvaluadorDeCadena control = (ControlEvaluadorDeCadena)obj;
		return evaluador.equals(control.evaluador);
	}
	
	public void evaluarCadena(AutomataFinito automata)
	{
		try{
			System.out.println(mostrarConfiguracion((Object)automata));
			System.out.println(automata.getDescripcion());
			String cadena = JOptionPane.showInputDialog("Ingresa la cadena a evaluar");
			if(evaluador.evaluarCadena(cadena, automata))
				JOptionPane.showMessageDialog(null, "La cadena pertenece al lenguaje", "Resultado", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "La cadena no pertenece al lenguaje", "Resultado", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException npe){
			JOptionPane.showMessageDialog(null, "Aun no has generado el automata", "Error", JOptionPane.ERROR_MESSAGE);
			//System.out.println("Aun no has generado el automata -.-");
		}
	}
	private String mostrarConfiguracion(Object obj)
	{
		if(obj instanceof AutomataFinitoDeterminista)
			return ((AutomataFinitoDeterminista)obj).getTransiciones();
		else if(obj instanceof AutomataFinitoNoDeterminista)
			return ((AutomataFinitoNoDeterminista)obj).getTransiciones();
		else if(obj instanceof AutomataFinitoNoDeterministaEpsilon)
			return ((AutomataFinitoNoDeterministaEpsilon)obj).getTransiciones();
		else if(obj instanceof AutomataFinitoAPila)
			return ((AutomataFinitoAPila)obj).getTransiciones();
		else return "No es autómata";
	}
	public void evaluarCadena(MaquinaDeTuring maquinaDeTuring)
	{
		try{
			System.out.println(maquinaDeTuring.getDescripcion());
			String cadena = JOptionPane.showInputDialog("Ingresa la cadena a evaluar");
			if(evaluador.evaluarCadena(cadena, maquinaDeTuring))
				JOptionPane.showMessageDialog(null, "La cadena pertenece al lenguaje", "Resultado", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "La cadena no pertenece al lenguaje", "Resultado", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException npe){
			//System.out.println("Aun no has generado la maquina -.-");
			JOptionPane.showMessageDialog(null, "Aun no has generado la maquina", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
