/**
 * @author Esteban Olmedo
 */
package control;

import java.util.ArrayList;
import logica.NoExisteEstadoException;
import vista.Teclado;
import logica.GeneradorDeMaquinaDeTuring;
import logica.MaquinaDeTuring;

public class ControlGeneradorDeMaquinaDeTuring
{
	private Teclado teclado;
	private GeneradorDeMaquinaDeTuring generador;

	public ControlGeneradorDeMaquinaDeTuring(Teclado teclado)
	{
		this.teclado = teclado;
		generador = new GeneradorDeMaquinaDeTuring();
	}
	public ControlGeneradorDeMaquinaDeTuring(ControlGeneradorDeMaquinaDeTuring control)
	{
		this(control.teclado, control.generador);
	}
	public ControlGeneradorDeMaquinaDeTuring(
			Teclado teclado, GeneradorDeMaquinaDeTuring generador)
	{
		this.generador = generador;
		this.teclado = teclado;
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
		return teclado.equals(control.teclado) &&
			generador.equals(control.generador);
	}
	
	public void generarMaquinaDeTuring()
	{	
		String descripcion = teclado.dameUnString("¿Cuál es la descripción de la máquina? (El lenguaje que representa)");
		int tamanioAlfabeto = teclado.dameUnInt("¿Cuál es la cardinalidad del alfabeto? (Excluyendo el símbolo 'B' (blanco)");
		char[] alfabeto = new char[tamanioAlfabeto + 1];
		System.out.println("A continuación se ingresarán los carácteres del alfabeto");
		for(int i = 0; i < tamanioAlfabeto; i++)
			alfabeto[i] = teclado.dameUnChar("Ingresa el caracter del alfabeto:");
		alfabeto[tamanioAlfabeto] = 'B';
		int numeroDeEstados = teclado.dameUnInt("¿Cuál es el número de estados de la Máquina de Turing");
		System.out.println("A continuación ingrese los estados de aceptación de la máquina (indexados en cero)");
		ArrayList<Integer> estadosAceptacion = new ArrayList<>();
		while(true)
		{
			try
			{
				int estado = teclado.dameUnInt("Ingresa el número del estado de aceptación o -1 cando hayas terminado");
				if(estado > numeroDeEstados - 1) throw new NoExisteEstadoException(estado);
				if(estado == -1)
					break;
				estadosAceptacion.add(estado);
			}
			catch(NoExisteEstadoException neee)
			{
                            System.out.println("No existe el estado");
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
				System.out.println("Para el estado q" + i + " con el símbolo " +
						alfabeto[j] + ":");
				try
				{
					int estadoDeTransicion = teclado.dameUnInt("¿A qué estado cambia? Ingresa -1 si no hay transición");
					if(estadoDeTransicion > numeroDeEstados)
						throw new NoExisteEstadoException();
					if(estadoDeTransicion != -1)
					{
						char loQueDeja = teclado.dameUnChar("¿Qué es lo que deja en la cinta?");
						char movimiento = teclado.dameUnChar("¿Hacia dónde se mueve el cabezal?");
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
