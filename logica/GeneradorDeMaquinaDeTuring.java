package logica;
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
		maquina = null;
	}
	public GeneradorDeMaquinaDeTuring(GeneradorDeMaquinaDeTuring generador)
	{
		this(generador.maquina);
	}

	public void destruir()
	{
		if(maquina != null){
			maquina.destruir();
			maquina = null;
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

	public MaquinaDeTuring getMaquinaDeTuring()
	{
		return maquina;
	}

	public void setMaquinaDeTuring(MaquinaDeTuring maquina)
	{
		this.maquina = maquina;
	}

	public void generarAlfabeto(String alfabeto)
	{
		maquina.setAlfabeto(alfabeto);
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
		}
		catch(NoExisteEnAlfabetoException neea){
			System.out.println(neea);
		}
	}

	public void crearEstructuraMaquina(
			String alfabeto, 
			int numeroEstados, 
			ArrayList<Integer> estadosAceptacion,
			String descripcion
			)
	{
		maquina = new MaquinaDeTuring();
		generarTablaMaquinaDeTuring(numeroEstados, estadosAceptacion, alfabeto);
		maquina.setDescripcion(descripcion);
	}
	public void aniadirTransicionATabla(int estado, int indiceAlfabeto,
		int estadoCambio, char loQueDeja, char movimiento)
	{
		maquina.aniadirTransicionATabla(estado, indiceAlfabeto, 
			estadoCambio, loQueDeja, movimiento);
	}
}
