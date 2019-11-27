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
import vista.Teclado;

public class ControlEvaluadorDeCadena
{
	private EvaluadorDeExpresion evaluador;
	private Teclado teclado;
	
	public ControlEvaluadorDeCadena(Teclado teclado)
	{
		this.teclado = teclado;
		evaluador = new EvaluadorDeExpresion();
	}
	public ControlEvaluadorDeCadena(EvaluadorDeExpresion evaluador,
		Teclado teclado)
	{
		this.evaluador = evaluador;
		this.teclado = teclado;
	}
	public ControlEvaluadorDeCadena()
	{
		teclado = new Teclado();
		evaluador = new EvaluadorDeExpresion();
	}
	public void destruir()
	{
		if(teclado != null)
		{
			teclado.destruir();
			teclado = null;
		}
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
		return teclado.equals(control.teclado) &&
			evaluador.equals(control.evaluador);
	}
	
	public void evaluarCadena(AutomataFinito automata)
	{
		try{
			System.out.println(mostrarConfiguracion((Object)automata));
			System.out.println(automata.getDescripcion());
			String cadena = teclado.dameUnString("Ingresa la cadena que vas a evaluar");
			if(evaluador.evaluarCadena(cadena, automata))
				System.out.println("La cadena pertenece al lenguaje");
			else
				System.out.println("La cadena no pertenece al lenguaje");
		}
		catch(NullPointerException npe){
			System.out.println("Aun no has generado el automata -.-");
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
			String cadena = teclado.dameUnString("Ingresa la cadena que vas a evaluar");
			if(evaluador.evaluarCadena(cadena, maquinaDeTuring))
				System.out.println("La cadena pertenece al lenguaje");
			else
				System.out.println("La cadena no pertenece al lenguaje");
		}
		catch(NullPointerException npe){
			System.out.println("Aun no has generado la maquina -.-");
		}
	}
}
