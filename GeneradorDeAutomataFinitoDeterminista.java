/**
 * @author Gabriel Graciano Herrera
 */
public class GeneradorDeAutomataFinitoDeterminista extends GeneradorDeAutomataFinito
{
	private AutomataFinitoDeterminista afd;

	public GeneradorDeAutomataFinitoDeterminista(AutomataFinito automata, AutomataFinitoDeterminista afd)
	{
		super(automata);
		this.afd = afd;
	}
	public GeneradorDeAutomataFinitoDeterminista()
	{
		this(null, null);
	}
	public GeneradorDeAutomataFinitoDeterminista(GeneradorDeAutomataFinitoDeterminista generador){
		super(generador);
		this.afd = generador.afd;
	}
	public void destruir()
	{
		if(afd != null)
		{
			afd.destruir();
			afd = null;
		}
		System.gc();
	}
	@Override
	public String toString(){
		String retorno = "Generador de Automata Finito Determinista\nAutomata generado:\n";
		if(afd != null)
			retorno += afd;
		else
			retorno += "Aun no se ha generado";
		return retorno;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof GeneradorDeAutomataFinitoDeterminista)) return false;
		GeneradorDeAutomataFinitoDeterminista generador = (GeneradorDeAutomataFinitoDeterminista)obj;
		return super.equals(generador) && afd.equals(generador.afd);
	}
	public AutomataFinitoDeterminista crearAutomataFinitoDeterminista()
	{
		return new AutomataFinitoDeterminista();
	}
}
