public class ManejadorDeAutomataYMaquinaDeTuring
{
	private GeneradorDeTodoTipoDeAutomataFinito generadorDeAutomata;
	private GeneradorDeMaquinaDeTuring generadorDeMaquina;
	private ConversorDeAutomata conversor;
	private GeneradorDeCodigoLaTex generadorLaTex;
	private EvaluadorDeExpresion evaluador;

	public ManejadorDeAutomataYMaquinaDeTuring()
	{
		this(
				new GeneradorDeTodoTipoDeAutomataFinito(),
				new GeneradorDeMaquinaDeTuring(),
				new ConversorDeAutomata(),
				new GeneradorDeCodigoLaTex(),
				new EvaluadorDeExpresion()
				);
	}
	public ManejadorDeAutomataYMaquinaDeTuring(
		 GeneradorDeTodoTipoDeAutomataFinito generadorDeAutomata,
		 GeneradorDeMaquinaDeTuring generadorDeMaquina,
		 ConversorDeAutomata conversor,
		 GeneradorDeCodigoLaTex generadorLaTex,
		 EvaluadorDeExpresion evaluador
		 )
	{
		this.generadorDeAutomata = generadorDeAutomata;
		this.generadorDeMaquina = generadorDeMaquina;
		this.conversor = conversor;
		this.generadorLaTex = generadorLaTex;
		this.evaluador = evaluador;
	}
	public ManejadorDeAutomataYMaquinaDeTuring(ManejadorDeAutomataYMaquinaDeTuring simulador)
	{
		this(
				simulador.generadorDeAutomata,
				simulador.generadorDeMaquina,
				simulador.conversor,
				simulador.generadorLaTex,
				simulador.evaluador
				);
	}
	public void destruir()
	{
		if(generadorDeAutomata != null){
			generadorDeAutomata.destruir();
			generadorDeAutomata = null;
		}
		if(generadorDeMaquina != null){
			generadorDeMaquina.destruir();
			generadorDeMaquina = null;
		}
		if(conversor != null){
			conversor.destruir();
			conversor = null;
		}
		if(generadorLaTex != null){
			generadorLaTex.destruir();
			generadorLaTex = null;
		}
		if(evaluador != null){
			evaluador.destruir();
			evaluador = null;
		}
		System.gc();
	}
	@Override
	public String toString()
	{
		return "Manejador de Automata y MaquinaDeturing:\n" + 
			generadorDeAutomata.toString() +
			generadorDeMaquina.toString() +
			conversor.toString() +
			generadorLaTex.toString() +
			evaluador.toString();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof ManejadorDeAutomataYMaquinaDeTuring)){return false;}
		ManejadorDeAutomataYMaquinaDeTuring simulador = (ManejadorDeAutomataYMaquinaDeTuring)obj;
		return generadorDeAutomata.equals(simulador.generadorDeAutomata) &&
			generadorDeMaquina.equals(simulador.generadorDeMaquina) &&
			conversor.equals(simulador.conversor) &&
			generadorLaTex.equals(simulador.generadorLaTex) &&
			evaluador.equals(simulador.evaluador);
	}
	public void manejar()
	{
		char[] cinta = {'x', 'x', 'x'};
		generadorDeMaquina.generar(cinta);
		MaquinaDeTuring maquinaDeTuring = generadorDeMaquina.getMaquinaDeTuring();
		System.out.println(maquinaDeTuring);
	}
}
