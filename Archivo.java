import java.util.File;
public class Archivo
{
	private String path;
	private String nombre;

	public Archivo()
	{
		this("", "");
	}
	public Archivo(String path, String nombre)
	{
		this.path = path;
		this.nombre = nombre;
	}
	public Archivo(Archivo archivo)
	{
		this(archivo.path, archivo.nombre);
	}

	public void destruir()
	{
		if(path != null)
			path = null;
		if(nombre != null)
			nombre = null;
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Archivo)) return false;
		Archivo archivo = (Archivo)obj;
		return path.equals(archivo.path) &&
			nombre.equals(archivo.nombre);
	}
	@Override
	public String toString()
	{
		return "PATH: " + path + "\n" +
			"Nombre: " + nombre + "\n";
	}

	public boolean verificarExisteArchivo() throws IOException
	{
		File archivo = new File (path);
		if(archivo.exists() == true)
			return true;
		else
			return false;
	}

	public boolean verificarExisteArchivo(File archivo) throws IOException
	{
		if(archivo.exists() == true)
			return true;
		else
			return false;
	}
}
