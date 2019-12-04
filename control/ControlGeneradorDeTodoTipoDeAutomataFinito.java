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
import javax.swing.JOptionPane;


/**
 * @author Esteban Olmedo
 */

public class ControlGeneradorDeTodoTipoDeAutomataFinito
{
	private GeneradorDeTodoTipoDeAutomataFinito generador;

	public ControlGeneradorDeTodoTipoDeAutomataFinito(GeneradorDeTodoTipoDeAutomataFinito generador)
	{
		this.generador = generador;
	}
	public ControlGeneradorDeTodoTipoDeAutomataFinito()
	{
		this(new GeneradorDeTodoTipoDeAutomataFinito());
	}
	public ControlGeneradorDeTodoTipoDeAutomataFinito(
			ControlGeneradorDeTodoTipoDeAutomataFinito control)
	{
		this(control.generador);
	}
	public void destruir()
	{
		if(generador != null)
		{
			generador.destruir();
			generador = null;
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
		return generador.equals(control.generador);
	}
	public int obtenerInt(String mensaje, String titulo){
		int tipo = 3;
		int entrada = 0;
		String mensajeError = "";
		while(true){
			try{
				entrada = Integer.parseInt(JOptionPane.showInputDialog(null,mensajeError + mensaje, titulo, tipo));
				break;
			}
			catch(NumberFormatException nfe){
				mensajeError = "Debe ingresar un numero\n";
				tipo = 0;
				titulo = "ERROR";
			}
		}
		return entrada;
	}
	public String obtenerString(String mensaje, String titulo){
		return JOptionPane.showInputDialog(null,	mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
	}
	public char obtenerChar(String mensaje, String titulo){
		int tipo = 3;
		char entrada = '\u0000';
		String mensajeError = "";
		while(true){
			try{
				entrada = JOptionPane.showInputDialog(null,mensajeError + mensaje, titulo, tipo).charAt(0);
				break;
			}
			catch(IndexOutOfBoundsException ioob){
				mensajeError = "Debe ingresar un caracter\n";
				tipo = 0;
				titulo = "ERROR";
			}
		}
		return entrada;
	}
	public Object[] generarAutomataFinito()
	{
		int numeroDeEstados = 0;
		char[] alfabeto = null;
		int[] aceptacion = null;
		String descripcion = null;
		descripcion = JOptionPane.showInputDialog("Ingresa la descripción del autómata", "");
		numeroDeEstados = 0;
		String mensajeError = "";
		while(numeroDeEstados <= 0){
			numeroDeEstados = obtenerInt(mensajeError+"Ingresa la cadinalidad del conjunto de estados","Estados");
			if(numeroDeEstados == 0)
				mensajeError = "Debe haber al menos un estado\n";
			else
				mensajeError = "";
		}
		int numeroDeEstadosAceptacion = obtenerInt("Ingresa la cardinalidad del conjunto de estados de aceptación","Estados de aceptación");
		aceptacion = new int[numeroDeEstadosAceptacion];
		int i = 0;
		for(i = 0; i < numeroDeEstadosAceptacion; i++)
		{
			try{
				aceptacion[i] = obtenerInt(mensajeError+"Ingresa el "+i+"-ésimo estado de aceptación", "Estados de aceptación");
				if(aceptacion[i] >= numeroDeEstados)
					throw new NoExisteEstadoException(aceptacion[i]);
				else
					mensajeError = "";
			}
			catch(NoExisteEstadoException neee){
				//neee.printStackTrace();
				mensajeError = "Ingresa un estado válido\n";
				i--;
			}
		}
		int cardinalidad = 0;
		while(cardinalidad <= 0){
			cardinalidad = obtenerInt(mensajeError+"Ingresa la cadinalidad del alfabeto","Alfabeto");
			if(cardinalidad == 0)
				mensajeError = "Debe haber al menos un simbolo en el alfabeto\n";
			else
				mensajeError = "";
		}
		alfabeto = new char[cardinalidad];
		for(i = 0; i < cardinalidad; i++)
			alfabeto[i] = obtenerChar("Ingrese el símbolo " + (i+1) +" del alfabeto", "Simbolo del alfabeto");
		Object[] elementos = {
			numeroDeEstados,
			alfabeto,
			aceptacion,
			descripcion
		};
		return elementos;
		
	}
	
	public void generarAutomataFinitoDeterminista()
	{
		Object[] elementos = generarAutomataFinito();
		int numeroDeEstados = (int)elementos[0]; 
		char[] alfabeto = (char[])elementos[1];
		int[] aceptacion = (int[])elementos[2];
		String descripcion = (String)elementos[3];
		//System.out.println("Ingresando transiciones");
		ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<numeroDeEstados; i++){
			ArrayList<Integer> estado = new ArrayList<Integer>();
			String mensajeError = "";
			for(int j=0; j<alfabeto.length; j++){
				int estadoDestino;
				try{
					estadoDestino = obtenerInt(mensajeError+"Ingresa el valor de S(q"+i+"," + alfabeto[j]+")","Ingresando trancisones");
					if(estadoDestino >= numeroDeEstados)
						throw new NoExisteEstadoException(estadoDestino);
					else
						mensajeError = "";
					estado.add(estadoDestino);
				}
				catch(NoExisteEstadoException neeee){
					//neeee.printStackTrace();
					mensajeError = "Ingresa un estado valido\n";
					j--;
				}
			}
			tabla.add(estado);
		}
		generador.crearAutomataFinitoDeterminista(tabla, numeroDeEstados, alfabeto, 
			aceptacion, descripcion);
	}
	public void generarAutomataFinitoNoDeterminista()
	{
		Object[] elementos = generarAutomataFinito();
		int numeroDeEstados = (int)elementos[0]; 
		char[] alfabeto = (char[])elementos[1];
		int[] aceptacion = (int[])elementos[2];
		String descripcion = (String)elementos[3];
		ArrayList<ArrayList<ArrayList<Integer>>> tabla = new ArrayList<ArrayList<ArrayList<Integer>>>();
		//System.out.println("Ingresando transiciones");
		for(int i=0; i<numeroDeEstados; i++){
			ArrayList<ArrayList<Integer>> estado = new ArrayList<ArrayList<Integer>>();
			for(int j=0; j<alfabeto.length; j++){
				String mensaje = "Ingresando transiciones de S(q"+i+","+alfabeto[j]+")\n";
				String instruccion = "Ingresa -1 para omitir/finalizar la transicion actual\n";
				ArrayList<Integer> transiciones = new ArrayList<Integer>();
				int transicion = -1;
				while((transicion = obtenerInt(mensaje + instruccion + "Ingresa el estado destino", "Ingresando transiciones")) >= 0){
					try{
						if(transicion >= numeroDeEstados)
							throw new NoExisteEstadoException(transicion);
						else{
							transiciones.add(transicion);
							mensaje = "Ingresando transiciones de S(q"+i+","+alfabeto[j]+")\n";
						}
					}
					catch(NoExisteEstadoException neeee){
						//neeee.printStackTrace();
						mensaje = "Ingresa un estado válido\nIngresando transiciones de S(q"+i+","+alfabeto[j]+")\n";
					}
				}
				estado.add(transiciones);
			}
			tabla.add(estado);
		}
		generador.crearAutomataFinitoNoDeterminista(tabla, numeroDeEstados, 
			alfabeto, aceptacion, descripcion);
	}
	public void generarAutomataFinitoNoDeterministaEpsilon()
	{
		generarAutomataFinitoNoDeterminista();
		AutomataFinito automata = generador.getAutomataFinitoNoDeterminista();
		//System.out.println("Ahora solo faltan las transiciones epsilon");
		ArrayList<ArrayList<Integer>> adyacenciaEpsilon = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<automata.getNumeroDeEstados(); i++){
			ArrayList<Integer> transiciones = new ArrayList<Integer>();
			String mensaje = "Ingresando las transiciones-epsilon del estado "+i+"\n";
			String instruccion = "Ingresa -1 para omitir/finalizar la transicion actual\n";
			int transicion = -1;
			while((transicion = obtenerInt(mensaje+instruccion+"Ingresa el estado destino","Ingresando transiciones epsilon")) >= 0){
				try{
					if(transicion >= automata.getNumeroDeEstados())
						throw new NoExisteEstadoException(transicion);
					else{
						transiciones.add(transicion);
						mensaje = "Ingresando las transiciones-epsilon del estado "+i+"\n";
					}
				}
				catch(NoExisteEstadoException neeee){
					//neeee.printStackTrace();
					mensaje = "Ingresa un estado válido\nIngresando las transiciones-epsilon del estado "+i+"\n";
				}
			}
			adyacenciaEpsilon.add(transiciones);
		}
		try
		{
			generador.crearAutomataFinitoNoDeterministaEpsilon(
				(AutomataFinitoNoDeterminista)automata, 
				adyacenciaEpsilon);
		}catch(ClassCastException cce){
                    //System.out.println("Hubo un error");
                    //cce.printStackTrace();
		}
	}
	public void generarAutomataFinitoAPila()
	{
		Object[] elementos = generarAutomataFinito();
		int numeroDeEstados = (int)elementos[0]; 
		char[] alfabeto = (char[])elementos[1];
		int[] aceptacion = (int[])elementos[2];
		String descripcion = (String)elementos[3];
		ArrayList<ArrayList<ArrayList<Delta>>> tabla = new ArrayList<ArrayList<ArrayList<Delta>>>();
		for(int i=0; i<numeroDeEstados; i++){
			ArrayList<ArrayList<Delta>> estado = new ArrayList<ArrayList<Delta>>();
			for(int j=0; j<numeroDeEstados; j++){
				String mensaje = "Ingresando transiciones del estado q"+i+" al estado q"+j+"\n";
				String instruccion = "Ingresa '#' para omitir/finalizar la transicion actual\n";
				ArrayList<Delta> transiciones = new ArrayList<Delta>();
				char carLeido = '#';
				char cimaPila = '\u0000';
				String dejarEnPila = "";
				while((carLeido = obtenerChar(mensaje+instruccion+"Ingresa el caracter leido", "Ingresando transiciones")) != '#'){
					cimaPila = obtenerChar("Ingresa lo que hay en la cima de la pila", "Ingresando cima de la pila");
					dejarEnPila = obtenerString("Ingresa lo que se inserta en la pila", "Ingresando cadena a insertar");
					transiciones.add(new Delta(carLeido,cimaPila,dejarEnPila));
				}
				estado.add(transiciones);
			}
			tabla.add(estado);
		}
		generador.crearAutomataFinitoAPila(tabla, numeroDeEstados, alfabeto, 
			aceptacion, descripcion);
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
	void imprimirTransiciones(Object automata)
	{
		if(automata instanceof AutomataFinitoDeterminista)
			System.out.println(((AutomataFinitoDeterminista)automata).getTransiciones());
		else if(automata instanceof AutomataFinitoNoDeterminista)
			System.out.println(((AutomataFinitoNoDeterminista)automata).getTransiciones());
		else if(automata instanceof AutomataFinitoNoDeterministaEpsilon)
			System.out.println(((AutomataFinitoNoDeterministaEpsilon)automata).getTransiciones());
		else if(automata instanceof AutomataFinitoAPila)
			System.out.println(((AutomataFinitoAPila)automata).getTransiciones());
	}
        
        public AutomataFinito getAutomataFinitoDeterminista()
        {
            return generador.getAutomataFinitoDeterminista();
        }
        
        public AutomataFinito getAutomataFinitoNoDeterminista()
        {
            return generador.getAutomataFinitoNoDeterminista();
        }
        
        public AutomataFinito getAutomataFinitoNoDeterministaEpsilon()
        {
            return generador.getAutomataFinitoNoDeterministaEpsilon();
        } 
        
        public AutomataFinito getAutomataFinitoAPila()
        {
            return generador.getAutomataFinitoAPila();
        }
               
}
