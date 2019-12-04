package persistencia;
/**
 * @author Esteban Olmedo Ramírez
 */
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.File;

public class LectorObjectInputStream extends Archivo
{

	private FileInputStream fis;
	private ObjectInputStream ois;

	public LectorObjectInputStream(String path)
	{
		super(path,null);
		fis = null;
		ois = null;
	}

	public LectorObjectInputStream(String path,File file,FileInputStream fis,ObjectInputStream ois)
	{
		super(path,file);
		this.fis = fis;
		this.ois = ois;
	}

	public LectorObjectInputStream()
	{
		this("",null,null,null);
	}

	public LectorObjectInputStream(LectorObjectInputStream lector)
	{
		super(lector);
		this.fis = lector.fis;
		this.ois = lector.ois;
	}

	@Override
	public void destruir()
	{
		super.destruir();
		if(fis != null)
			fis = null;
		if(ois != null)
			ois = null;
		System.gc();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) 
			return false;
		if(!(obj instanceof LectorObjectInputStream))
			return false;
		LectorObjectInputStream lector = (LectorObjectInputStream) obj;
		return super.equals(lector) && 
				fis.equals(lector.fis) &&
				ois.equals(lector.ois);
	}

	@Override
	public String toString()
	{
		return super.toString() + fis.toString() + ois.toString();
	}

	private boolean abrirFlujo()
	{
		try
		{
			if(verificarExistenciaArchivo())
			{
				fis = new FileInputStream(getFile());
				ois = new ObjectInputStream(fis);
				return true;
			}
			else
			{
				System.out.println("El archivo no se existe");
			}
		}catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
			return false;
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			return false;
		}
		return false;
	}

	private boolean cerrarFlujo()
	{
		boolean cerradoOis = false;
		boolean cerradoFis = false;
		if(ois != null)
		{
			try
			{
				ois.close();
				cerradoOis = true;
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		if(fis != null)
		{
			try
			{
				fis.close();
				cerradoFis = true;
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return cerradoOis && cerradoFis;
	}

	public Object leerObjetoArchivo()
	{
		if(abrirFlujo())
		{
			try
			{
				if(fis.available() != 0)
				{
					Object aux = ois.readObject();
					return aux;
				}
			}catch(EOFException eofe)
			{
				eofe.printStackTrace();
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}catch(ClassNotFoundException cnfe)
			{
				cnfe.printStackTrace();
			}catch(ClassCastException cce)
			{
				System.out.println("Se ha producido un error");
				cce.printStackTrace();
			}
			finally
			{
				if(cerrarFlujo())
					System.out.println("Flujo cerrado correctamente");
				else
					System.out.println("Ocurrió un error al cerrar el flujo");
			}
		}
		return null;
	}
}
