package logica;
/**
 * @author Gabriel Graciano Herrera
 */
public class EvaluadorDeExpresion{
	private MaquinaDeTuring maquina;
	private AutomataFinito automata;

	public EvaluadorDeExpresion(
		MaquinaDeTuring maquina,
		AutomataFinito automata)
	{
		this.maquina = maquina;
		this.automata = automata;
	}

	public EvaluadorDeExpresion(){
		this(null, null);
	}

	public EvaluadorDeExpresion(EvaluadorDeExpresion evaluador){
		this(evaluador.maquina,
			evaluador.automata
		);
	}

	public void destruir(){
		if(maquina != null){
			maquina.destruir();
			maquina = null;
		}
		if(automata != null){
			automata.destruir();
			automata = null;
		}
		System.gc(); 
	}

	@Override
	public String toString(){
		String cad = "";
		cad += "Maquina de turing: "+maquina+"\n";
		cad += "Automata Finito: "+automata+"\n";
		return cad;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof EvaluadorDeExpresion)) return false;
		EvaluadorDeExpresion evaluador = (EvaluadorDeExpresion)obj;
		return maquina.equals(evaluador.maquina) &&
		automata.equals(evaluador.automata);
	}
	public boolean perteneceAlAlfabeto(char caracter, AutomataFinito automata)
	{
		if(automata.getNumeroSimbolo(caracter) == -1)
			return false;
		else
			return true;
	}
	public boolean perteneceAlAlfabeto(String cadena, AutomataFinito automata)
	{
		for(int i = 0; i < cadena.length(); i++)
		{
			if(!perteneceAlAlfabeto(cadena.charAt(i), automata))
				return false;
		}
		return true;
	}
	public boolean evaluarCadena(String cadena, AutomataFinito automata)
	{
		try
		{
			if(!perteneceAlAlfabeto(cadena, automata))
			{
					return false;
			}
			return automata.evaluar(cadena);
		}catch(NullPointerException npe)
		{
			System.out.println("No se ha podido evaluar, " + 
					"existe un error con el autómata");
		}
		return false;
	}

	public boolean evaluarCadena(String cadena, MaquinaDeTuring maquinaDeTuring)
	{
		try
		{
			this.maquina = maquinaDeTuring;
			maquina.prepararMaquina(cadena);
			if(maquina.accionar())
				return true;
			else
				return false;
		}catch(NullPointerException npe)
		{
			System.out.println("No se ha podido evaluar correctamente, " + 
					"hay un error con la máquina de Turing");
		}
		return false;
	}
}
