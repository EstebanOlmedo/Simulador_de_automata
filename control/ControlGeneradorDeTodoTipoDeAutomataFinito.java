package control;
import logica.GeneradorDeTodoTipoDeAutomataFinito;
import logica.NoExisteEstadoException;
import logica.AutomataFinito;
import java.util.ArrayList;
import logica.AutomataFinitoAPila;
import logica.AutomataFinitoDeterminista;
import logica.AutomataFinitoNoDeterminista;
import logica.AutomataFinitoNoDeterministaEpsilon;
import logica.Delta;
import vista.Teclado;


/**
 * @author Esteban Olmedo
 */

public class ControlGeneradorDeTodoTipoDeAutomataFinito
{
	private GeneradorDeTodoTipoDeAutomataFinito generador;
	private Teclado teclado;

	public ControlGeneradorDeTodoTipoDeAutomataFinito(Teclado teclado,
			GeneradorDeTodoTipoDeAutomataFinito generador)
	{
		this.generador = generador;
		this.teclado = teclado;
	}
	public ControlGeneradorDeTodoTipoDeAutomataFinito()
	{
		this(new Teclado(), new GeneradorDeTodoTipoDeAutomataFinito());
	}
	public ControlGeneradorDeTodoTipoDeAutomataFinito(
			ControlGeneradorDeTodoTipoDeAutomataFinito control)
	{
		this(control.teclado, control.generador);
	}
	public ControlGeneradorDeTodoTipoDeAutomataFinito(Teclado teclado)
	{
		this.teclado = teclado;
		generador = new GeneradorDeTodoTipoDeAutomataFinito();
	}
	public void destruir()
	{
		if(generador != null)
		{
			generador.destruir();
			generador = null;
		}
		if(teclado != null)
		{
			teclado.destruir();
			teclado = null;
		}
		System.gc();
	}

	@Override
	public String toString()
	{
		return "Control de generación de autómatas\n";
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof ControlGeneradorDeTodoTipoDeAutomataFinito))
			return false;
		ControlGeneradorDeTodoTipoDeAutomataFinito control = (ControlGeneradorDeTodoTipoDeAutomataFinito)obj;
		return teclado.equals(control.teclado) && 
			generador.equals(control.generador);
	}

	public void generarAutomataFinito()
	{
		String descripcion = teclado.dameUnString("Ingresa la descripción del autómata");
		int numeroDeEstados = teclado.dameUnInt("Ingresa la cardinalidad del conjunto de estados");
		int numeroDeEstadosAceptacion = teclado.dameUnInt("Ingresa la cardinalidad del conjunto de estados de aceptación");
		int[] aceptacion = new int[numeroDeEstadosAceptacion];
		int i = 0;
		for(i = 0; i < numeroDeEstadosAceptacion; i++)
		{
			try{
				aceptacion[i] = teclado.dameUnInt("Ingresa el "+i+"-ésimo estado de aceptación");
				if(aceptacion[i] >= numeroDeEstados)
					throw new NoExisteEstadoException(aceptacion[i]);
			}
			catch(NoExisteEstadoException neee){
				neee.printStackTrace();
				System.out.println("Ingresa un estado válido");
				i--;
			}
		}
		int cardinalidad = teclado.dameUnInt("Ingresa la cardinalidad del alfabeto");
		char[] alfabeto = new char[cardinalidad];
		for(i = 0; i < cardinalidad; i++)
			alfabeto[i] = teclado.dameUnChar("Ingrese el símbolo " + (i+1) +" del alfabeto");
		generador.crearAutomataFinito(numeroDeEstados, alfabeto, aceptacion, descripcion);
	}
	public void generarAutomataFinitoDeterminista()
	{
		generarAutomataFinito();
		System.out.println("Ingresando transiciones");
		AutomataFinito automata = generador.getAutomataFinito();
		ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<Integer> estado = new ArrayList<Integer>();
			for(int j=0; j<automata.getMapa().size(); j++){
				int estadoDestino;
				try{
					estadoDestino = teclado.dameUnInt("Ingresa el valor de S(q"+i+","+automata.getSimbolo(j)+")");
					if(estadoDestino >= automata.getNumeroDeEstados())
						throw new NoExisteEstadoException(estadoDestino);
					estado.add(estadoDestino);
				}
				catch(NoExisteEstadoException neeee){
					neeee.printStackTrace();
					System.out.println("Ingresa un estado valido");
					j--;
				}
			}
			tabla.add(estado);
		}
		generador.crearAutomataFinitoDeterminista(automata, tabla);
	}
	public void generarAutomataFinitoNoDeterminista()
	{
		generarAutomataFinito();
		AutomataFinito automata = generador.getAutomataFinito();
		ArrayList<ArrayList<ArrayList<Integer>>> tabla = new ArrayList<ArrayList<ArrayList<Integer>>>();
		System.out.println("Ingresando transiciones");
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<ArrayList<Integer>> estado = new ArrayList<ArrayList<Integer>>();
			for(int j=0; j<automata.getMapa().size(); j++){
				System.out.println("Ingresando transiciones de S(q"+i+","+automata.getSimbolo(j)+")");
				System.out.println("Ingresa -1 para omitir/finalizar la transicion actual");
				ArrayList<Integer> transiciones = new ArrayList<Integer>();
				int transicion = -1;
				while((transicion = teclado.dameUnInt("Ingresa el estado destino")) >= 0){
					try{
						if(transicion >= automata.getNumeroDeEstados())
							throw new NoExisteEstadoException(transicion);
						else
							transiciones.add(transicion);
					}
					catch(NoExisteEstadoException neeee){
						neeee.printStackTrace();
						System.out.println("Ingresa un estado válido");
					}
				}
				estado.add(transiciones);
			}
			tabla.add(estado);
		}
		generador.crearAutomataFinitoNoDeterminista(automata, tabla);
	}
	public void generarAutomataFinitoNoDeterministaEpsilon()
	{
		generarAutomataFinitoNoDeterminista();
		AutomataFinitoNoDeterminista automata = generador.getAutomataFinitoNoDeterminista();
		System.out.println("Ahora solo faltan las transiciones epsilon");
		ArrayList<ArrayList<Integer>> adyacenciaEpsilon = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<Integer> transiciones = new ArrayList<Integer>();
			System.out.println("Ingresando las transiciones-epsilon del estado "+i);
			System.out.println("Ingresa -1 para omitir/finalizar la transicion actual");
			int transicion = -1;
			while((transicion = teclado.dameUnInt("Ingresa el estado destino")) >= 0){
				try{
					if(transicion >= automata.getNumeroDeEstados())
						throw new NoExisteEstadoException(transicion);
					else
						transiciones.add(transicion);
				}
				catch(NoExisteEstadoException neeee){
					neeee.printStackTrace();
					System.out.println("Ingresa un estado válido");
				}
			}
			adyacenciaEpsilon.add(transiciones);
		}
		generador.crearAutomataFinitoNoDeterministaEpsilon(automata, adyacenciaEpsilon);
	}
	public void generarAutomataFinitoAPila()
	{
		generarAutomataFinito();
		AutomataFinito automata = generador.getAutomataFinito();
		ArrayList<ArrayList<ArrayList<Delta>>> tabla = new ArrayList<ArrayList<ArrayList<Delta>>>();
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<ArrayList<Delta>> estado = new ArrayList<ArrayList<Delta>>();
			for(int j=0; j<automata.getNumeroDeEstados(); j++){
				System.out.println("Ingresando transiciones del estado q"+i+" al estado q"+j);
				System.out.println("Ingresa '#' para omitir/finalizar la transicion actual");
				ArrayList<Delta> transiciones = new ArrayList<Delta>();
				char carLeido = '#';
				char cimaPila = '\u0000';
				String dejarEnPila = "";
				while((carLeido = teclado.dameUnChar("Ingresa el caracter leido")) != '#'){
					cimaPila = teclado.dameUnChar("Ingresa lo que hay en la cima de la pila");
					dejarEnPila = teclado.dameUnString("Ingresa lo que se inserta en la pila");
					transiciones.add(new Delta(carLeido,cimaPila,dejarEnPila));
				}
				estado.add(transiciones);
			}
			tabla.add(estado);
		}
		generador.crearAutomataFinitoAPila(automata, tabla);
	}
	public GeneradorDeTodoTipoDeAutomataFinito getGenerador(){
		return generador;
	}

	void setAutomata(Object automata)
	{
		if(automata instanceof AutomataFinitoDeterminista)
			generador.setAutomataFinitoDeterminista(
				(AutomataFinitoDeterminista)automata
			);
		else if(automata instanceof AutomataFinitoNoDeterminista)
			generador.setAutomataFinitoNoDeterminista(
				(AutomataFinitoNoDeterminista)automata
			);
		else if(automata instanceof AutomataFinitoNoDeterministaEpsilon)
			generador.setAutomataFinitoNoDeterministaEpsilon(
				(AutomataFinitoNoDeterministaEpsilon)automata
			);
		else if(automata instanceof AutomataFinitoAPila)
			generador.setAutomataFinitoAPila(
				(AutomataFinitoAPila)automata
			);
	}
}
