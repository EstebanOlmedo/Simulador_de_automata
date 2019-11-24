package logica;
/**
 * @author Esteban Olmedo
 * 
 * */
public class NoExisteEstadoException extends Exception
{
	private String descripcion;

	public NoExisteEstadoException(String descripcion)

	{
		super(descripcion);
		this.descripcion = descripcion;
	}
	public NoExisteEstadoException()
	{
		super("El estado ingresado se encuentra dentro del faro de posibilidad de estados");
		this.descripcion = "El estado ingresado se encuentra dentro del faro de posibilidad de estados";
	}
	public NoExisteEstadoException(int estado)
	{
		super("El estado q" + estado + " se encuentra fuera del rango del numero de estados");
		descripcion = "El estado q" + estado + " se encuentra fuera del rango del numero de estados";
	}
	@Override
	public String toString()
	{
		return "ERROR: " + descripcion;
	}
}
