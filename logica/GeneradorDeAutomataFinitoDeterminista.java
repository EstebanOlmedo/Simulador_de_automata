package logica;
/**
 * @author Gabriel Graciano Herrera
 */
import java.util.ArrayList;
public class GeneradorDeAutomataFinitoDeterminista extends GeneradorDeAutomataFinito
{
	private AutomataFinitoDeterminista automataFinitoDeterminista;

	public GeneradorDeAutomataFinitoDeterminista(AutomataFinito automata, AutomataFinitoDeterminista afd)
	{
		super(automata);
		this.automataFinitoDeterminista = afd;
	}

	public GeneradorDeAutomataFinitoDeterminista()
	{
		this(null, null);
	}

	public GeneradorDeAutomataFinitoDeterminista(GeneradorDeAutomataFinitoDeterminista generador){
		super(generador);
		this.automataFinitoDeterminista = generador.automataFinitoDeterminista;
	}

	public void destruir()
	{
		if(automataFinitoDeterminista != null)
		{
			automataFinitoDeterminista.destruir();
			automataFinitoDeterminista = null;
		}
		System.gc();
	}

	@Override
	public String toString(){
		String retorno = "Generador de Automata Finito Determinista\nAutomata generado:\n";
		if(automataFinitoDeterminista != null)
			retorno += automataFinitoDeterminista;
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
		return super.equals(generador) && automataFinitoDeterminista.equals(generador.automataFinitoDeterminista);
	}

	public void crearAutomataFinitoDeterminista(
		ArrayList<ArrayList<Integer>> tabla,
		int numeroDeEstados, 
		char[] alfabeto, 
		int[] aceptacion, 
		String descripcion
	)
	{
		System.out.println("Creando autómata finito determinista");
		automataFinitoDeterminista = new AutomataFinitoDeterminista();
		crearAutomataFinito(numeroDeEstados, alfabeto, aceptacion,
			descripcion, automataFinitoDeterminista);
		automataFinitoDeterminista.setTablaDeTransiciones(tabla);
		System.out.println("El autómata ha sido creado con exito");
	}

	public AutomataFinitoDeterminista getAutomataFinitoDeterminista()
	{
		return automataFinitoDeterminista;
	}

	public void setAutomataFinitoDeterminista(AutomataFinitoDeterminista automataFinitoDeterminista)
	{
		this.automataFinitoDeterminista = automataFinitoDeterminista;
	}

	@Override
	public void crearAutomataFinito(
		int numeroDeEstados, 
		char[] alfabeto, 
		int[] aceptacion, 
		String descripcion,
		AutomataFinito automata
	)
	{
		automata.setNumeroDeEstados(numeroDeEstados);
		automata.setAlfabeto(alfabeto);
		automata.setEstadosAceptacion(aceptacion);
		automata.setDescripcion(descripcion);
		automata.setMapa();
	}
}
