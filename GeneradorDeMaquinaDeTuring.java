/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.ArrayList;
public class GeneradorDeMaquinaDeTuring
{
	private MaquinaDeTuring maquina;
	private Teclado teclado;
	public GeneradorDeMaquinaDeTuring(MaquinaDeTuring maquina,
			Teclado teclado)
	{
		this.maquina = maquina;
		this.teclado = teclado;
	}

	public GeneradorDeMaquinaDeTuring()
	{
		this(new MaquinaDeTuring(), new Teclado());
	}

	public GeneradorDeMaquinaDeTuring(GeneradorDeMaquinaDeTuring generador)
	{
		this(generador.maquina, generador.teclado);
	}

	public void destruir()
	{
		if(maquina != null){
			maquina.destruir();
			maquina = null;
		}
		if(teclado != null){
			teclado.destruir();
			teclado = null;
		}
		System.gc();
	}

	@Override
	public String toString()
	{
		return "Maquina de Turing: " + maquina;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof GeneradorDeMaquinaDeTuring)) return false;
		GeneradorDeMaquinaDeTuring generador = (GeneradorDeMaquinaDeTuring)obj;
		return maquina.equals(generador.maquina);
	}
	public String getInforme()
	{
		return "Maquina de turing generada\nInformacion de la maquina:\n";
	}
	public String getInforme(MaquinaDeTuring maquina)
	{
		return getInforme() + maquina;
	}
	public MaquinaDeTuring getMaquinaDeTuring()
	{
		return maquina;
	}
	public void generar(char[] cinta)
	{
		System.out.println("Generando máquina de turing...");
		maquina.setCinta(cinta);
	}
	public void generarAlfabeto(String alfabeto)
	{
		maquina.setAlfabeto(alfabeto.toCharArray());
	}
	public void generarTablaMaquinaDeTuring(int numeroEstados, 
			ArrayList<Integer> estadosAceptacion, String alfabeto)
	{
		generarAlfabeto(alfabeto);
		TablaMaquinaDeTuring tabla = maquina.getTabla();
		if(tabla == null)
			tabla = new TablaMaquinaDeTuring(numeroEstados, alfabeto.length());
		tabla.setEstadosAceptacion(estadosAceptacion);
		maquina.setTabla(tabla);
	}
	public void crearFuncionEnTabla(
			int estado, 
			int estadoCambio,
			char simbolo, 
			char movimiento)
	{
		try
		{
			int indiceSimbolo = maquina.getIndiceSimboloEnAlfabeto(simbolo);
			FuncionDeltaMaquinaDeTuring funcion = new FuncionDeltaMaquinaDeTuring(estadoCambio, simbolo, movimiento);
			TablaMaquinaDeTuring tabla = maquina.getTabla();
			tabla.setEstado(estado, indiceSimbolo, funcion);
			maquina.setTabla(tabla);
		}
		catch(IndexOutOfBoundsException aoobe)
		{
			System.out.println("Ha ocurrido un error");
			aoobe.printStackTrace();
		}
	}
	public void crearEstructuraMaquina(
			String alfabeto, 
			int numeroEstados, 
			ArrayList<Integer> estadosAceptacion,
			String descripcion
			)
	{
		generarTablaMaquinaDeTuring(numeroEstados, estadosAceptacion, alfabeto);
		maquina.setDescripcion(descripcion);
	}
	public MaquinaDeTuring getMaquina()
	{
		return maquina;
	}
	public void crearMaquinaDeTuring()
	{
		String descripcion = new String();
		descripcion = teclado.dameUnString("¿Cuál es la descripción de la máquina?");
		int tamanio = teclado.dameUnInt("¿Cuál es el tamaño del alfabeto?");
		char[] alfabeto = new char[tamanio + 1];
		for(int i = 0; i < tamanio; i++)
			alfabeto[i] = teclado.dameUnChar("Ingresa el caracter del alfabeto:");
		alfabeto[tamanio] = 'B';
		int numeroDeEstados = teclado.dameUnInt("¿Cuál es el número de estados de la Máquina de Turing");
		System.out.println("A continuación ingrese los estados de aceptación de la máquina");
		ArrayList<Integer> estadosAceptacion = new ArrayList<>();
		while(true)
		{
			int estado = teclado.dameUnInt("Ingresa el número del estado de aceptación o -1 cando hayas terminado");
			if(estado == -1)
				break;
			estadosAceptacion.add(estado);
		}
		crearEstructuraMaquina(new String(alfabeto), numeroDeEstados, estadosAceptacion, descripcion);
		crearEstados(numeroDeEstados, tamanio);
	}
	private void crearEstados(int numeroDeEstados, int tamanioAlfabeto)
	{
		TablaMaquinaDeTuring tabla = maquina.getTabla();
		char[] alfabeto = maquina.getAlfabeto();
		if(tabla == null)
			tabla = new TablaMaquinaDeTuring(tamanioAlfabeto, numeroDeEstados);
		int respuesta;
		for(int i = 0; i < numeroDeEstados; i++)
		{
			for(int j = 0; j < tamanioAlfabeto + 1; j++)
			{
				System.out.println("Para el estado " + i + " con el símbolo " +
						alfabeto[j] + ":");
				int estadoDeTransicion = teclado.dameUnInt("¿A qué estado cambia? Ingresa -1 si no hay transición");
				if(estadoDeTransicion != -1)
				{
					char loQueDeja = teclado.dameUnChar("¿Qué es lo que deja en la cinta?");
					char movimiento = teclado.dameUnChar("¿Hacia dónde se mueve el cabezal?");
					tabla.setEstado(i, j, estadoDeTransicion, loQueDeja, movimiento);
				}
				else
					tabla.setEstado(i, j, estadoDeTransicion, '-', '-');
			}
		}
		maquina.setTabla(tabla);
	}
}
