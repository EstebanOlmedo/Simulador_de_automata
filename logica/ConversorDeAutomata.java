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
	private AutomataFinito automata;

	public ConversorDeAutomata(AutomataFinito automata)
	{
		this.automata = automata;
	}

	public ConversorDeAutomata(){
		this(new AutomataFinitoDeterminista());
	}

	public ConversorDeAutomata(ConversorDeAutomata conversor){
		this(conversor.automata);
	}

	public void destruir(){
		if(automata != null)
		{
			automata.destruir();
			automata = null;
		}
		System.gc();
	}

	@Override
	public String toString(){
		String cad = "Automata convertido:\n";
		if(automata != null)
			cad += automata;
		return cad;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof ConversorDeAutomata)) return false;
		ConversorDeAutomata conversor = (ConversorDeAutomata)obj;
		return automata.equals(conversor.automata);
	}
	
	public void convertirAFDaAFP(AutomataFinitoDeterminista automata)
	{
		try{
			ArrayList<ArrayList<ArrayList<Delta>>> tabla = crearTabla(automata);
			this.automata = new AutomataFinitoAPila(
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
		}
		catch(IndexOutOfBoundsException ioobe){
			System.out.println("Ocurrió un problema al convertir el automata");
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
			TreeMap<Integer,ArrayList<Integer>> mapa = new TreeMap<Integer,ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> tabla = crearTabla(automata,mapa);
			this.automata = new AutomataFinitoDeterminista(
				tabla.size(),
				automata.getAlfabeto(),
				crearEstadosAceptacion(automata,mapa),
				null,
				tabla,
				automata.getDescripcion()
			);
			this.automata.setMapa();
		}
		catch(NullPointerException npe){
			System.out.println("Ocurrió un problema al convertir el automata");
		}
		catch(IndexOutOfBoundsException ioobe){
			System.out.println("Ocurrió un problema al convertir el automata");
		}
 	}
 	public ArrayList<ArrayList<Integer>> crearTabla(AutomataFinitoNoDeterminista automata,TreeMap<Integer,ArrayList<Integer>> mapa){
 		ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>> ();
		ArrayList<Integer> trancision = new ArrayList<Integer> ();
		LinkedList<ArrayList<Integer>> cola = new LinkedList<ArrayList<Integer>> ();
		ArrayList<Integer> auxiliar = new ArrayList<Integer> ();
                ArrayList<Integer> procesados = new ArrayList<Integer>();
		auxiliar.add(0);
		procesados.add(obtenerID(auxiliar,cola,mapa));
		for(int i=0; i<automata.getAlfabeto().length; i++){
			ArrayList<Integer> nuevoEstado = generarEstado(auxiliar,i,automata);
			int idNuevoEstado = obtenerID(nuevoEstado, cola,mapa);
			trancision.add(idNuevoEstado);
		}
		tabla.add(trancision);
		while(cola.size() > 0){
			auxiliar = cola.peek();
                        if(procesados.indexOf(obtenerID(auxiliar,cola,mapa)) == -1){
                            procesados.add(obtenerID(auxiliar,cola,mapa));
                            trancision = new ArrayList<Integer>();
                            for(int i=0; i<automata.getAlfabeto().length; i++){
                                    ArrayList<Integer> nuevoEstado = generarEstado(auxiliar,i,automata);
                                    int idNuevoEstado = obtenerID(nuevoEstado, cola,mapa);
                                    trancision.add(idNuevoEstado);	
                            }
                            tabla.add(trancision);
                        }
			cola.removeFirst();
		}
		return tabla;
 	}
 	public int[] crearEstadosAceptacion(AutomataFinitoNoDeterminista automata, TreeMap<Integer,ArrayList<Integer>> mapa){
 		ArrayList<Integer> aceptacion = new ArrayList<Integer>();
 		for(Map.Entry<Integer,ArrayList<Integer>> entry:mapa.entrySet()){
 			for(Integer i:entry.getValue()){
 				if(automata.isAceptacion(i)){
 						aceptacion.add(entry.getKey());
 				}
 			}
 		}
 		int[] estadosAceptacion = new int[aceptacion.size()];
 		for(int i=0; i<aceptacion.size(); i++){
 			estadosAceptacion[i] = aceptacion.get(i);
 		}
 		return estadosAceptacion;
 	}
	public AutomataFinito getAutomataFinito()
	{
		return automata;
	}
	public void insertarEnMapa(ArrayList<Integer> estado, TreeMap<Integer,ArrayList<Integer>> mapa){
		int nuevoEstado = mapa.size();
		mapa.put(nuevoEstado, estado);
	}
	public ArrayList<Integer> generarEstado(ArrayList<Integer> estado, int simbolo,AutomataFinitoNoDeterminista automata){
		ArrayList<Integer> nuevoEstado = new ArrayList<Integer> ();
		ArrayList<Integer> set = new ArrayList<Integer> ();
                for(Integer i:estado){
			for(Integer e:automata.getAdyacencia(i,simbolo)){
				if(set.indexOf(e) == -1)
                                    nuevoEstado.add(e);
			}
		}
		return nuevoEstado;
	}
	public int obtenerID(ArrayList<Integer> estado, LinkedList<ArrayList<Integer>> cola, TreeMap<Integer,ArrayList<Integer>> mapa){
		if(mapa.containsValue(estado)){
                    for(Map.Entry<Integer,ArrayList<Integer>> entry:mapa.entrySet()){
 			if(entry.getValue().equals(estado))
                            return entry.getKey();
                    }
                    return 0;
		}
		else{
			insertarEnMapa(estado, mapa);
			cola.add(estado);
                        for(Map.Entry<Integer,ArrayList<Integer>> entry:mapa.entrySet()){
                            if(entry.getValue().equals(estado))
                                return entry.getKey();
                        }
			return 0;
		}
	}
}
