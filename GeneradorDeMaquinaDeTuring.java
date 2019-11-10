/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.ArrayList;
public class GeneradorDeMaquinaDeTuring
{
	private MaquinaDeTuring maquina;

	public GeneradorDeMaquinaDeTuring(MaquinaDeTuring maquina)
	{
		this.maquina = maquina;
	}

	public GeneradorDeMaquinaDeTuring()
	{
		this(new MaquinaDeTuring());
	}

	public GeneradorDeMaquinaDeTuring(GeneradorDeMaquinaDeTuring generador)
	{
		this(generador.maquina);
	}

	public void destruir()
	{
		if(maquina != null) maquina = null;
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
		}
		catch(ArrayOutOfBoundsException aoobe)
		{
			System.out.println("Ha ocurrido un error");
			aoobe.printStackTrace();
		}
	}
	public void crearEstructuraMaquina(
			String alfabeto, 
			int numeroEstados, 
			ArrayList<Integer> estadosAceptacion
			)
	{
		generarTablaMaquinaDeTuring(numeroEstados, estadosAceptacion, alfabeto);
	}
	public MaquinaDeTuring getMaquina()
	{
		return maquina;
	}
}
