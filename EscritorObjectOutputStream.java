/**
 * @author Esteban Olmedo Ramírez
 */
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
public class EscritorObjectOutputStream extends Archivo
{
	private FileOutputStream fos;
	private ObjectOutputStream  oos;

	public EscritorObjectOutputStream(String path,String nombre)
	{
		super(path,nombre,null);
		fos = null;
		oos = null;
	}

	public EscritorObjectOutputStream(String path,String nombre,File file,FileOutputStream fos,ObjectOutputStream oos)
	{
		super(path,nombre,file);
		this.fos = fos;
		this.oos = oos;
	}

	public EscritorObjectOutputStream()
	{
		this("","",null,null,null);
	}

	public EscritorObjectOutputStream(EscritorObjectOutputStream escritor)
	{
		super(escritor);
		this.fos = escritor.fos;
		this.oos = escritor.oos;
	}

	private boolean abrirFlujo(Teclado teclado)
	{
		try
		{
			if(verificarExistenciaArchivo())
			{
				String op = teclado.dameUnString("El archivo ya existe,¿quieres sobreescribirlo?");
				if((op.toUpperCase()).equals("SI"))
				{
					fos = new FileOutputStream(getFile());
					oos = new ObjectOutputStream(fos);
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				fos = new FileOutputStream(getFile());
				oos = new ObjectOutputStream(fos);
				return true;
			}
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
			return false;
		}catch(IOException ioe){
			ioe.printStackTrace();
			return false;
		}
	}

	private boolean cerrarFlujo()
	{
		boolean cerradoOos = false;
		boolean cerradoFos = false;
		try{
			if(oos != null)
			{
				oos.close();
				cerradoOos = true;
			}
			if(fos != null)
			{
				fos.close();
				cerradoFos = true;
			}
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}finally
		{
			return cerradoFos && cerradoOos;
		}
	}

	public boolean escribirObjeto(Object objeto,Teclado teclado)
	{
		if(abrirFlujo(teclado))
		{
			try
			{
				oos.writeObject(objeto);
				return true;
			}catch(IOException ioe){
				ioe.printStackTrace();
			}finally
			{
				if(cerrarFlujo())
					System.out.println("Flujo cerrado correctamente");
				else
					System.out.println("Ocurrio un error al cerrar el flujo");
			}
		}
		return false;
	}
}
	