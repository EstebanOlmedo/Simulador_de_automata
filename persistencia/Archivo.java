package persistencia;
/**
 * @author Gabriel Graciano Herrera
 */
import java.io.File;
public class Archivo
{
	private String path;
	private File file;

	public Archivo()
	{
		this("",null);
	}

	public Archivo(String path,File file)
	{
		this.path = path;
		this.file = file;
	}
	public Archivo(String path)
	{
		this.path = path;
	}
	public Archivo(Archivo archivo)
	{
		this(archivo.path,archivo.file);
	}
	public void destruir()
	{
		if(path != null)
			path = null;
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
			file.equals(archivo.file);
	}

	@Override
	public String toString()
	{
		return "PATH: " + path + "\n" +
			file;
	}

	public boolean verificarExistenciaArchivo()
	{
		file = new File(path);
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
		if(archivos == null  || archivos.length == 0)
		{
			System.out.println("No hay elementos en el directorio");
		}
		else
		{
			for(int x = 0;x < archivos.length; x++)
			{
				File archivo = new File(cadena + archivos[x]);
				if(archivo.isFile())
					System.out.println(archivos[x]);
			}
		}
	}

}
