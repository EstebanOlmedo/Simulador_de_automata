package logica;
/**
 * @author Daniel Montes Guerrero
 */
import java.util.ArrayList;
public interface IGeneradorEpsilon
{
	public void crearAutomataFinitoNoDeterminista(AutomataFinito automata,
			ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones);
	public void crearAutomataFinitoNoDeterministaEpsilon(AutomataFinitoNoDeterminista automata,ArrayList<ArrayList<Integer>> adyacencia);
} 
