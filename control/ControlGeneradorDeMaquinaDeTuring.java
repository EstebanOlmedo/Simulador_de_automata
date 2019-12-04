/**
 * @author Esteban Olmedo
 */
package control;

import java.util.ArrayList;
import logica.NoExisteEstadoException;
import logica.GeneradorDeMaquinaDeTuring;
import logica.MaquinaDeTuring;
import javax.swing.JOptionPane;

public class ControlGeneradorDeMaquinaDeTuring
{
	private GeneradorDeMaquinaDeTuring generador;

	public ControlGeneradorDeMaquinaDeTuring()
	{
		this(new GeneradorDeMaquinaDeTuring());
	}
	public ControlGeneradorDeMaquinaDeTuring(ControlGeneradorDeMaquinaDeTuring control)
	{
		this(control.generador);
	}
	public ControlGeneradorDeMaquinaDeTuring(GeneradorDeMaquinaDeTuring generador)
	{
		this.generador = generador;
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
		return "Control del generador de maquina de Turing";
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof ControlGeneradorDeMaquinaDeTuring))
			return false;
		ControlGeneradorDeMaquinaDeTuring control = (ControlGeneradorDeMaquinaDeTuring)obj;
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
	public void generarMaquinaDeTuring()
	{	
		String descripcion = obtenerString("¿Cuál es la descripción de la máquina? (El lenguaje que representa)","Descripción");
		int tamanioAlfabeto = obtenerInt("¿Cuál es la cardinalidad del alfabeto? (Excluyendo el símbolo 'B' (blanco)", "Alfabeto");
		char[] alfabeto = new char[tamanioAlfabeto + 1];
		//System.out.println("A continuación se ingresarán los carácteres del alfabeto");
		for(int i = 0; i < tamanioAlfabeto; i++)
			alfabeto[i] = obtenerChar("Ingresa el caracter del alfabeto:","Alfabeto");
		alfabeto[tamanioAlfabeto] = 'B';
		int numeroDeEstados = obtenerInt("¿Cuál es el número de estados de la Máquina de Turing","Estados");
		//System.out.println("A continuación ingrese los estados de aceptación de la máquina (indexados en cero)");
		ArrayList<Integer> estadosAceptacion = new ArrayList<>();
		while(true)
		{
			try
			{
				int estado = obtenerInt("Ingresa el número del estado de aceptación o -1 cando hayas terminado","Estados (Indexados en cero)");
				if(estado > numeroDeEstados - 1) throw new NoExisteEstadoException(estado);
				if(estado == -1)
					break;
				estadosAceptacion.add(estado);
			}
			catch(NoExisteEstadoException neee)
			{
                //System.out.println("No existe el estado");
				//System.out.println(neee);
			}
		}
		generador.crearEstructuraMaquina(new String(alfabeto), numeroDeEstados, estadosAceptacion, descripcion);
		generarEstadosMaquinaDeTuring(numeroDeEstados, tamanioAlfabeto, alfabeto);
	}
	private void generarEstadosMaquinaDeTuring(int numeroDeEstados, int tamanioAlfabeto,
			char[] alfabeto)
	{
		for(int i = 0; i < numeroDeEstados; i++)
		{
			for(int j = 0; j < tamanioAlfabeto + 1; j++)
			{
				String mensaje = "Para el estado q" + i + " con el símbolo " + alfabeto[j] + ":\n";
				try
				{
					int estadoDeTransicion = obtenerInt(mensaje + "¿A qué estado cambia? Ingresa -1 si no hay transición", "Transiciones");
					if(estadoDeTransicion > numeroDeEstados)
						throw new NoExisteEstadoException();
					if(estadoDeTransicion != -1)
					{
						char loQueDeja = obtenerChar("¿Qué es lo que deja en la cinta?","Transicion");
						char movimiento = obtenerChar("¿Hacia dónde se mueve el cabezal?", "Transicion");
						generador.aniadirTransicionATabla(i, j, estadoDeTransicion, loQueDeja, movimiento);
					}
					else
						generador.aniadirTransicionATabla(i, j, -1, '-', '-');
				}catch(NoExisteEstadoException neee){
					j--;
					//neee.printStackTrace();
				}
			}
		}
	}
	public GeneradorDeMaquinaDeTuring getGenerador()
	{
		return generador;
	}

	void setMaquinaDeTuring(MaquinaDeTuring maquinaDeTuring){
		generador.setMaquinaDeTuring(maquinaDeTuring);
	}
}
