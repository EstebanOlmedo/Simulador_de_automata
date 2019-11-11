/**
 * @author Gabriel Graciano Herrera
 */
public class EvaluadorDeExpresion{
	private MaquinaDeTuring maquina;
	private AutomataFinitoAPila automataPila;
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon;

	public EvaluadorDeExpresion(
		MaquinaDeTuring maquina,
		AutomataFinitoAPila automataPila,
		AutomataFinitoDeterminista automataFinitoDeterminista,
		AutomataFinitoNoDeterminista automataFinitoNoDeterminista,
		AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon)
	{
		this.maquina = maquina;
		this.automataPila = automataPila;
		this.automataFinitoDeterminista = automataFinitoDeterminista;
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataFinitoNoDeterministaEpsilon = automataFinitoNoDeterministaEpsilon;
	}

	public EvaluadorDeExpresion(){
		this(null, null, null, null, null);
	}

	public EvaluadorDeExpresion(EvaluadorDeExpresion evaluador){
		this(evaluador.maquina,
			evaluador.automataPila,
			evaluador.automataFinitoDeterminista,
			evaluador.automataFinitoNoDeterminista,
			evaluador.automataFinitoNoDeterministaEpsilon);
	}

	public void destruir(){
		if(maquina != null){
			maquina.destruir();
			maquina = null;
		}
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
		if(automataPila != null){
			automataPila.destruir();
			automataPila = null;
		}
		System.gc(); 
	}

	@Override
	public String toString(){
		String cad = "";
		cad += "Maquina de turing: "+maquina+"\n";
		cad += "Automata a pila: "+automataPila+"\n";
		cad += "Automata Finito Determinista: "+automataFinitoDeterminista+"\n";
		cad += "Automata Finito No Determinista: "+automataFinitoNoDeterminista+"\n";
		cad += "Automata Finito No Determinista Epsilon: "+automataFinitoNoDeterministaEpsilon+"\n";
		return cad;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof EvaluadorDeExpresion)) return false;
		EvaluadorDeExpresion evaluador = (EvaluadorDeExpresion)obj;
		return maquina.equals(evaluador.maquina) &&
		automataPila.equals(evaluador.automataPila) &&
		automataFinitoDeterminista.equals(evaluador.automataFinitoDeterminista) &&
		automataFinitoNoDeterminista.equals(evaluador.automataFinitoNoDeterminista) &&
		automataFinitoNoDeterministaEpsilon.equals(evaluador.automataFinitoNoDeterministaEpsilon);
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
		if(!perteneceAlAlfabeto(cadena, automata)){return false;}
		int tam = cadena.length();
		if(automata instanceof AutomataFinitoDeterminista)
		{
			return ((AutomataFinitoDeterminista)automata).evaluar(cadena);
		}
		//TODO implementar las siguientes partes para los distintos autómatas
		else
			return false;
	}
	public boolean evaluarCadena(String cadena, MaquinaDeTuring maquinaDeTuring)
	{
		this.maquina = maquinaDeTuring;
		maquina.prepararMaquina(cadena);
		if(maquina.accionar())
			return true;
		else
			return false;
	}
}
