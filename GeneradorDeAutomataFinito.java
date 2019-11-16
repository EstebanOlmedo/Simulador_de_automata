/**
 * @author Daniel Montes Guerrero
 */
import java.util.TreeMap;
public class GeneradorDeAutomataFinito
{
	private AutomataFinito automataFinito;
	private Teclado teclado;

	public GeneradorDeAutomataFinito(AutomataFinito automataFinito)
	{
		this.automataFinito = automataFinito;
		teclado = new Teclado();
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
	public void crearAutomataFinito()
	{
		System.out.println("Creando Automata...");
		String descripcion = teclado.dameUnString("Ingresa la descripción del autómata");
		int numeroDeEstados = teclado.dameUnInt("Ingresa la cardinalidad del conjunto de estados");
		int numeroDeEstadosAceptacion = teclado.dameUnInt("Ingresa la cardinalidad del conjunto de estados de aceptación");
		int[] aceptacion = new int[numeroDeEstadosAceptacion];
		for(int i=0; i<numeroDeEstadosAceptacion; i++){
			while(true){
				aceptacion[i] = teclado.dameUnInt("Ingresa el "+i+"-ésimo estado de aceptacion");
				if(aceptacion[i] < numeroDeEstados)
					break;
				else
					System.out.println("No existe el estado que ingresaste ._.");
			}
		}
		int cardinalidad = teclado.dameUnInt("Ingresa la cardinalidad del alfabeto");
		char[] alfabeto = new char[cardinalidad];
		for(int i=0; i<cardinalidad; i++){
			alfabeto[i] = teclado.dameUnChar("Ingresa el símbolo "+(i+1)+" del alfabeto");
		}
		AutomataFinito automata = new AutomataFinito(numeroDeEstados,alfabeto,aceptacion,new TreeMap<Character,Integer>(), descripcion);
		automata.setMapa();
		automataFinito = automata;
	}
	public Teclado getTeclado()
	{
		return teclado;
	}
	public AutomataFinito getAutomataFinito(){
		return automataFinito;
	}
}