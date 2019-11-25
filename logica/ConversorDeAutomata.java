package logica;
/**
 * @author Gabriel Graciano Herrera
 */
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
public class ConversorDeAutomata{
	private AutomataFinitoDeterminista automataFinitoDeterminista;
	private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
	private AutomataFinitoAPila automataAPila;
	private TreeMap<Integer,ArrayList<Integer>> mapa;

	public ConversorDeAutomata(
			AutomataFinitoDeterminista automataFinitoDeterminista,
			AutomataFinitoNoDeterminista automataFinitoNoDeterminista,
			AutomataFinitoAPila automataAPila
		)
	{
		this.automataFinitoDeterminista = automataFinitoDeterminista;
		this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
		this.automataAPila = automataAPila;
		mapa = new TreeMap<Integer,ArrayList<Integer>>();
	}

	public ConversorDeAutomata(){
		this(null, null, null);
	}

	public ConversorDeAutomata(ConversorDeAutomata conversor){
		this(
			conversor.automataFinitoDeterminista,
			conversor.automataFinitoNoDeterminista,
			conversor.automataAPila
			);
	}

	public void destruir(){
		if(automataFinitoDeterminista != null)
		{
			automataFinitoNoDeterminista.destruir();
			automataFinitoDeterminista = null;
		}
		if(automataFinitoNoDeterminista != null)
		{
			automataFinitoNoDeterminista.destruir();
			automataFinitoNoDeterminista = null;
		}
		if(automataAPila != null)
		{
			automataAPila.destruir();
			automataAPila = null;
		}
		System.gc();
	}

	@Override
	public String toString(){
		String cad = "Automatas convertidos:\n";
		if(automataFinitoDeterminista != null)
			cad += "Automata Finito Determinista: " + automataFinitoDeterminista + "\n";
		if(automataFinitoNoDeterminista != null)
			cad += "Automata Finito No Determinista: " + automataFinitoNoDeterminista + "\n";
		if(automataAPila != null)
			cad += "Automata a Pila: " + automataAPila + "\n";
		return cad;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof ConversorDeAutomata)) return false;
		ConversorDeAutomata conversor = (ConversorDeAutomata)obj;
		return automataFinitoDeterminista.equals(conversor.automataFinitoDeterminista) &&
		automataFinitoNoDeterminista.equals(conversor.automataFinitoNoDeterminista) &&
		automataAPila.equals(conversor.automataAPila);
	}
	
	public void convertirAFDaAFP(AutomataFinitoDeterminista automata)
	{
		try{
			ArrayList<ArrayList<ArrayList<Delta>>> tabla = crearTabla(automata);
			automataAPila = new AutomataFinitoAPila(
				automata.getNumeroDeEstados(),
				automata.getAlfabeto(),
				automata.getEstadosDeAceptacion(),
				tabla,
				new Stack<Character>(),
				automata.getMapa(),
				automata.getDescripcion()
			);
		}
		catch(NullPointerException npe){
			System.out.println("Ocurrió un problema al convertir el automata");
			npe.printStackTrace();
		}
		catch(IndexOutOfBoundsException ioobe){
			System.out.println("Ocurrió un problema al convertir el automata");
			ioobe.printStackTrace();
		}
	}
	public ArrayList<ArrayList<ArrayList<Delta>>> crearTabla(AutomataFinitoDeterminista automata){
		ArrayList<ArrayList<Integer>> tabla = automata.getTablaDeTransiciones();
		ArrayList<ArrayList<ArrayList<Delta>>> nuevaTabla = new ArrayList<ArrayList<ArrayList<Delta>>> ();
		for(int i=0; i<tabla.size(); i++){
			nuevaTabla.add(new ArrayList<ArrayList<Delta>> (nuevaTabla.size()));
			for(int j=0; j<tabla.size(); j++)
				nuevaTabla.get(i).add(new ArrayList<Delta> ());
		}
		for(int i=0; i<tabla.size(); i++){
			for(int j=0; j<automata.getAlfabeto().length; j++){
				nuevaTabla.get(i).get(tabla.get(i).get(j)).add(new Delta(automata.getSimbolo(j), '#', "#"	));
			}
		}
		return nuevaTabla;
	}
	public void convertirAFNaAFD(AutomataFinitoNoDeterminista automata)
	{
		try{
			ArrayList<ArrayList<Integer>> tabla = crearTabla(automata);
			automataFinitoDeterminista = new AutomataFinitoDeterminista(
				tabla.size(),
				automata.getAlfabeto(),
				crearEstadosAceptacion(automata),
				null,
				tabla,
				automata.getDescripcion()
			);
			automataFinitoDeterminista.setMapa();
		}
		catch(NullPointerException npe){
			System.out.println("Ocurrió un problema al convertir el automata");
			npe.printStackTrace();
		}
		catch(IndexOutOfBoundsException ioobe){
			System.out.println("Ocurrió un problema al convertir el automata");
			ioobe.printStackTrace();
		}
 	}
 	public ArrayList<ArrayList<Integer>> crearTabla(AutomataFinitoNoDeterminista automata){
 		ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>> ();
		ArrayList<Integer> trancision = new ArrayList<Integer> (automata.getAlfabeto().length);
		LinkedList<ArrayList<Integer>> cola = new LinkedList<ArrayList<Integer>> ();
		ArrayList<Integer> auxiliar = new ArrayList<Integer> ();
		auxiliar.add(0);
		obtenerID(auxiliar,cola);
		for(int i=0; i<automata.getAlfabeto().length; i++){
			ArrayList<Integer> nuevoEstado = generarEstado(auxiliar,i,automata);
			int idNuevoEstado = obtenerID(nuevoEstado, cola);
			trancision.add(idNuevoEstado);
		}
		tabla.add(trancision);
		while(cola.size() > 0){
			auxiliar = cola.peek();
			trancision.clear();
			for(int i=0; i<automata.getAlfabeto().length; i++){
				ArrayList<Integer> nuevoEstado = generarEstado(auxiliar,i,automata);
				int idNuevoEstado = obtenerID(nuevoEstado, cola);
				trancision.add(idNuevoEstado);	
			}
			tabla.add(trancision);
			cola.removeFirst();
		}
		return tabla;
 	}
 	public int[] crearEstadosAceptacion(AutomataFinitoNoDeterminista automata){
 		ArrayList<Integer> aceptacion = new ArrayList<Integer>();
 		for(Map.Entry<Integer,ArrayList<Integer>> entry:mapa.entrySet()){
 			for(Integer i:entry.getValue()){
 				if(automata.isAceptacion(i)){
 					if(aceptacion.lastIndexOf(i) == -1){
 						aceptacion.add(i);
 					}
 				}
 			}
 		}
 		int[] estadosAceptacion = new int[aceptacion.size()];
 		for(int i=0; i<aceptacion.size(); i++){
 			estadosAceptacion[i] = aceptacion.get(i);
 		}
 		return estadosAceptacion;
 	}
	public AutomataFinitoDeterminista getAutomataFinitoDeterminista()
	{
		return automataFinitoDeterminista;
	}

	public AutomataFinitoAPila getAutomataFinitoAPila()
	{
		return automataAPila;
	}
	public void insertarEnMapa(ArrayList<Integer> estado){
		int nuevoEstado = mapa.size();
		mapa.put(nuevoEstado, estado);
	}
	public ArrayList<Integer> generarEstado(ArrayList<Integer> estado, int simbolo,AutomataFinitoNoDeterminista automata){
		ArrayList<Integer> nuevoEstado = new ArrayList<Integer> ();
		for(Integer i:estado){
			for(Integer e:automata.getAdyacencia(i,simbolo)){
				nuevoEstado.add(e);
			}
		}
		return nuevoEstado;
	}
	public int obtenerID(ArrayList<Integer> estado, LinkedList<ArrayList<Integer>> cola){
		if(mapa.containsValue(estado)){
			ArrayList<ArrayList<Integer>> valores = new ArrayList<ArrayList<Integer>>(mapa.values());
			return valores.lastIndexOf(estado);
		}
		else{
			insertarEnMapa(estado);
			cola.add(estado);
			return mapa.size();
		}
	}
}
