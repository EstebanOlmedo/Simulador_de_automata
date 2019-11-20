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

	public void crearAutomataFinitoDeterminista()
	{
		crearAutomataFinito();
		AutomataFinito automata = getAutomataFinito();
		ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>>();
		System.out.println("Ingresando transiciones");
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<Integer> estado = new ArrayList<Integer>();
			for(int j=0; j<automata.getMapa().size(); j++){
				int estadoDestino;
				try{
					estadoDestino = getTeclado().dameUnInt("Ingresa el valor de S(q"+i+","+automata.getSimbolo(j)+")");
					if(estadoDestino >= automata.getNumeroDeEstados())
						throw new NoExisteElEstadoException(estadoDestino);
					estado.add(estadoDestino);
				}
				catch(NoExisteElEstadoException neeee){
					neeee.printStackTrace();
					System.out.println("Ingresa un estado valido");
					j--;
				}
			}
			tabla.add(estado);
		}
		System.out.println("El automata fue creado con exito :)");
		automataFinitoDeterminista = new AutomataFinitoDeterminista(
			automata.getNumeroDeEstados(),
			automata.getAlfabeto(),
			automata.getEstadosDeAceptacion(),
			automata.getMapa(),
			tabla,
			automata.getDescripcion()
		);
	}

	public AutomataFinitoDeterminista getAutomataFinitoDeterminista()
	{
		return automataFinitoDeterminista;
	}

	public void setAutomataFinitoDeterminista(AutomataFinitoDeterminista automataFinitoDeterminista)
	{
		this.automataFinitoDeterminista = automataFinitoDeterminista;
	}
}
