package logica;
/**
 * @author Daniel Montes Guerrero
 */
public class NoExisteEnAlfabetoException extends Exception
{
	private String mensaje;

	public NoExisteEnAlfabetoException()
	{
		super("La cadena no pertenece al alfabeto");
		mensaje = "La cadena no pertenece al alfabeto";
	}
	public NoExisteEnAlfabetoException(char simbolo)
	{
		super("El simbolo: " + simbolo + "no existe en el alfabeto");
		mensaje = "El simbolo: " + simbolo + "no existe en el alfabeto";
	}
	public NoExisteEnAlfabetoException(String mensaje)
	{
		super(mensaje);
		this.mensaje = mensaje;
	}
	@Override
	public String toString()
	{
		return mensaje;
	}
}
