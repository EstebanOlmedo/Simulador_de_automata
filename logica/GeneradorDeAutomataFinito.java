package logica;
/**
 * @author Daniel Montes Guerrero
 */
public abstract class GeneradorDeAutomataFinito
{
	private AutomataFinito automataFinito;

	public GeneradorDeAutomataFinito(AutomataFinito automataFinito)
	{
		this.automataFinito = automataFinito;
	}
	public GeneradorDeAutomataFinito(){
		automataFinito = null;
	}
	public GeneradorDeAutomataFinito(GeneradorDeAutomataFinito generador)
	{
		this(generador.automataFinito);
	}
	public void destruir()
	{
		if(automataFinito != null){
			automataFinito.destruir();
			automataFinito = null;
		}
		System.gc();
	}
	@Override
	public String toString(){
		String retorno = "Generador de automata finito\nAutomata generado:\n";
		if(automataFinito != null)
			retorno += automataFinito;
		else
			retorno += "Aun no se ha generado";
		return retorno;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof GeneradorDeAutomataFinito)) return false;
		GeneradorDeAutomataFinito generador = (GeneradorDeAutomataFinito)obj;
		return automataFinito.equals(generador.automataFinito);
	}
	public abstract void crearAutomataFinito(
		int numeroDeEstados, 
		char[] alfabeto, 
		int[] aceptacion, 
		String descripcion,
		AutomataFinito automata
	);
	public AutomataFinito getAutomataFinito(){
		return automataFinito;
	}
}
