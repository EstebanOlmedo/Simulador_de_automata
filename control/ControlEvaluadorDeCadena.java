package control;
/**
 *
 * @author esteban
 */
import logica.EvaluadorDeExpresion;
import logica.AutomataFinito;
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
			String cadena = teclado.dameUnString("Ingresa la cadena que vas a evaluar");
			if(evaluador.evaluarCadena(cadena, automata))
				System.out.println("La cadena pertenece al lenguaje");
			else
				System.out.println("La cadena no pertenece al lenguaje");
		}
		catch(NullPointerException npe){
			System.out.println("Aun no has generado el automata -.-");
			npe.printStackTrace();
		}
	}
	public void evaluarCadena(MaquinaDeTuring maquinaDeTuring)
	{
		try{
			String cadena = teclado.dameUnString("Ingresa la cadena que vas a evaluar");
			if(evaluador.evaluarCadena(cadena, maquinaDeTuring))
				System.out.println("La cadena pertenece al lenguaje");
			else
				System.out.println("La cadena no pertenece al lenguaje");
		}
		catch(NullPointerException npe){
			System.out.println("Aun no has generado la maquina -.-");
			npe.printStackTrace();
		}
	}
}
