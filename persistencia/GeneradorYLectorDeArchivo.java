package persistencia;

/**
 * @author Gabriel Graciano Herrera
 */
public class GeneradorYLectorDeArchivo
{
	private LectorObjectInputStream lector;
	private EscritorObjectOutputStream escritor;

	public GeneradorYLectorDeArchivo(LectorObjectInputStream lector, EscritorObjectOutputStream escritor)
	{
		this.lector = lector;
		this.escritor = escritor;
	}
	public GeneradorYLectorDeArchivo()
	{
		this(null,null);
	}
	public GeneradorYLectorDeArchivo(GeneradorYLectorDeArchivo generador)
	{
		this(generador.lector,generador.escritor);
	}
	
	public String toString()
	{
		return "Generador y lector de archivo";
	}
	public void destruir()
	{
		if(lector != null)
		{
			lector.destruir();
			lector = null;
		}
		if(escritor != null)
		{
			escritor.destruir();
			escritor = null;
		}
		System.gc();
	}
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof GeneradorYLectorDeArchivo)) return false;
		GeneradorYLectorDeArchivo generador = (GeneradorYLectorDeArchivo) obj;
		return lector.equals(generador.lector) &&
			escritor.equals(generador.escritor);
	}
	public boolean escribirObjetoArchivo(Object obj, String nombre, String path)
	{
		escritor = new EscritorObjectOutputStream(path,nombre);
		return escritor.escribirObjeto(obj);
	}

	public Object leerObjetoArchivo(String nombre, String path)
	{
		lector = new LectorObjectInputStream(path,nombre);
		return lector.leerObjetoArchivo();
	}
}
