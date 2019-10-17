public class EvaluadorDeExpresion{
	private MaquinaDeTuring maquina;
	private AutomataAPila automataPila;
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoNoDeterministaEpsilon automataFinitoNoDeterministaEpsilon;

	public EvaluadorDeExpresion(
		MaquinaDeTuring maquina,
		AutomataAPila automataPila,
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

}
