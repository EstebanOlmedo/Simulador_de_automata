/**
 * @author Daniel Montes Guerrero
 */
public class GeneradorDeAutomataFinito
{
	private AutomataFinito automataFinito;

	public GeneradorDeAutomataFinito(AutomataFinito automataFinito)
	{
		this.automataFinito = automataFinito;
	}
	public GeneradorDeAutomataFinito(){
		this(new AutomataFinito());
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
	public AutomataFinito crearAutomataFinito(){
		return new AutomataFinito();
	}
}