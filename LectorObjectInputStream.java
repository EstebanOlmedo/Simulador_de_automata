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

public class LectorObjectInputStream
{
	private String archivo;
	private FileInputStream fis;
	private ObjectInputStream ois;

	public LectorObjectInputStream(String archivo)
	{
		this.archivo = archivo;
		fis = null;
		ois = null;
	}

	private boolean abrirFlujo()
	{
		boolean abierto = false;
		try
		{
			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			abierto = true;
		}catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		return abierto;
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
	public List<Object> leerObjetos()
	{
		ArrayList<Object> lista = new ArrayList<>();
		if(abrirFlujo())
		{
			try
			{
				if(fis.available() != 0)
				{
					Object aux = ois.readObject();
					lista = (ArrayList<Object>)aux;
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
		return lista;
	}
}
