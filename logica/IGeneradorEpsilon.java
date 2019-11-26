package logica;
/**
 * @author Daniel Montes Guerrero
 */
import java.util.ArrayList;
public interface IGeneradorEpsilon
{
	public void crearAutomataFinitoNoDeterminista(
		ArrayList<ArrayList<ArrayList<Integer>>> tablaDeTransiciones,
		int numeroDeEstados, 
		char[] alfabeto, 
		int[] aceptacion, 
		String descripcion
		);
	public void crearAutomataFinitoNoDeterministaEpsilon(AutomataFinitoNoDeterminista automata,ArrayList<ArrayList<Integer>> adyacencia);
} 
