package persistencia;
/**
 * @author Gabriel Graciano Herrera
 */
import java.io.File;
public class Archivo
{
	private String path;
	private String nombre;
	private File file;

	public Archivo()
	{
		this("", "",null);
	}

	public Archivo(String path, String nombre,File file)
	{
		this.path = path;
		this.nombre = nombre;
		this.file = file;
	}

	public Archivo(Archivo archivo)
	{
		this(archivo.path, archivo.nombre,archivo.file);
	}
	public void destruir()
	{
		if(path != null)
			path = null;
		if(nombre != null)
			nombre = null;
		if(file != null)
			file = null;
		System.gc();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Archivo)) return false;
		Archivo archivo = (Archivo)obj;
		return path.equals(archivo.path) &&
			nombre.equals(archivo.nombre) &&
			file.equals(archivo.file);
	}

	@Override
	public String toString()
	{
		return "PATH: " + path + "\n" +
			"Nombre: " + nombre + "\n"+
			file;
	}

	public boolean verificarExistenciaArchivo()
	{
		file = new File(path + nombre);
		if(file.exists() == true)
			return true;
		else
			return false;
	}

	public File getFile()
	{
		return file;
	}

	public void mostrarArchivosEnCarpeta(String cadena)
	{
		File carpeta = new File(cadena);
		String [] archivos = carpeta.list();
		if(listado == null  || listado.length == 0)
		{
			System.out.println("No hay elementos en el directorio");
		}
		else
		{
			for(int x = 0;x < archivos.length; x++)
			{
				System.out.println(archivos[i]);
			}
		}
	}

}
