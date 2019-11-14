/**
 * @author Gabriel Graciano Herrera
 */
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class Archivo
{
	private String path;
	private String nombre;
	private File file;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	public Archivo()
	{
		this("", "", null, null, null);
	}
	public Archivo(String path, String nombre,
			File file, FileWriter fileWriter,
			BufferedWriter bufferedWriter)
	{
		this.path = path;
		this.nombre = nombre;
		this.file = file;
		this.fileWriter = fileWriter;
		this.bufferedWriter = bufferedWriter;
	}
	public Archivo(Archivo archivo)
	{
		this(archivo.path, archivo.nombre, archivo.file,
				archivo.fileWriter, archivo.bufferedWriter);
	}
	public void destruir()
	{
		if(path != null)
			path = null;
		if(nombre != null)
			nombre = null;
		if(file != null)
			file = null;
		if(fileWriter != null)
			fileWriter = null;
		if(bufferedWriter != null)
			bufferedWriter = null;
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
			fileWriter.equals(archivo.fileWriter) &&
			bufferedWriter.equals(archivo.bufferedWriter) &&
			file.equals(archivo.file);
	}
	@Override
	public String toString()
	{
		return "PATH: " + path + "\n" +
			"Nombre: " + nombre + "\n";
	}

	private boolean verificarExisteArchivo() throws IOException
	{
		file = new File(path + nombre);
		if(file.exists() == true)
			return true;
		else
			return false;
	}

	public boolean abrirArchivo()
	{
		boolean abierto = false;;
		try
		{
			if(verificarExisteArchivo())
			{
				System.out.println("El archivo ya existe y será sobreescrito\n");
				fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
			}
			else
			{
				fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
			}
			abierto = true;
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return abierto;
	}
	public boolean cerrar()
	{
		boolean cerradoBufferedWriter = false;
		boolean cerradoFileWriter = false;
		
		if(bufferedWriter != null)
		{
			try
			{
				bufferedWriter.close();
				cerradoBufferedWriter = true;
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		if(fileWriter != null)
		{
			try
			{
				fileWriter.close();
				cerradoFileWriter = true;
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		return cerradoFileWriter && cerradoBufferedWriter;
	}
}
