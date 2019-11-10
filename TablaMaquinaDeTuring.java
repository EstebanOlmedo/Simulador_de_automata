/**
 * @author Esteban Olmedo
 */
import java.util.ArrayList;
public class TablaMaquinaDeTuring
{
	private FuncionDeltaMaquinaDeTuring[][] tabla;
	private ArrayList<Integer> estadosAceptacion;

	public TablaMaquinaDeTuring()
	{
		tabla = null;
	}
	public TablaMaquinaDeTuring(FuncionDeltaMaquinaDeTuring[][] tabla)
	{
		this.tabla = tabla;
	}
	public TablaMaquinaDeTuring(TablaMaquinaDeTuring tablaMaquinaDeTuring)
	{
		this(tablaMaquinaDeTuring.tabla);
	}
	public TablaMaquinaDeTuring(int tamanioAlfabeto, int numeroEstados)
	{
		tabla = new FuncionDeltaMaquinaDeTuring[numeroEstados][tamanioAlfabeto];
	}
	public void destruir()
	{
		if(tabla != null)
			tabla = null;
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if((obj instanceof TablaMaquinaDeTuring)){return false;}
		TablaMaquinaDeTuring tablaMT = (TablaMaquinaDeTuring)obj;
		for(int i = 0; i < tabla.length; i++)
		{
			for(int j = 0; j < tabla[0].length; i++)
			{
				if(!tabla.equals(tablaMT.tabla[i][j]))
					return false;
			}
		}
		return true;
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		for(FuncionDeltaMaquinaDeTuring[] renglon: tabla)
		{
			for(FuncionDeltaMaquinaDeTuring funcion: renglon)
			{
				if(renglon != null)
				{
					retorno += funcion.toString();
				}
				else
					retorno += "(-,-,-)\n";
			}
		}
		return retorno;
	}
	public void setEstado(int numeroEstado, int simbolo,
			FuncionDeltaMaquinaDeTuring transicion)
	{
		tabla[numeroEstado][simbolo] = transicion;
	}
	public FuncionDeltaMaquinaDeTuring getFuncion(int numeroEstado, int simbolo) //Arrojar error si se pasa del tamaño del arreglo
	{
		return tabla[numeroEstado][simbolo];
	}
	public void setEstadosAceptacion(ArrayList<Integer> estadosAceptacion)
	{
		this.estadosAceptacion = estadosAceptacion;
	}
	public void aniadirEstadoAEstadosAceptacion(int numeroEstado)
	{
		estadosAceptacion.add(numeroEstado);
	}
	public boolean isEstadoAceptacion(int estado)
	{
		if(estadosAceptacion.contains(estado))
			return true;
		else
			return false;
	}
}
