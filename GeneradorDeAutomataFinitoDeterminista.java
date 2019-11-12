/**
 * @author Gabriel Graciano Herrera
 */
import java.util.ArrayList;
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
		AutomataFinito automata = crearAutomataFinito();
		ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>>();
		System.out.println("Ingresando transiciones");
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<Integer> estado = new ArrayList<Integer>();
			for(int j=0; j<automata.getMapa().size(); j++){
				int estadoDestino = getTeclado().dameUnInt("Ingresa el valor de S(q"+i+","+automata.getSimbolo(j)+")");
				estado.add(estadoDestino);
			}
			tabla.add(estado);
		}
		System.out.println("El automata fue creado con exito :)");
		return new AutomataFinitoDeterminista(
			automata.getNumeroDeEstados(),
			automata.getAlfabeto(),
			automata.getEstadosDeAceptacion(),
			automata.getMapa(),
			tabla
		);
	}
}
