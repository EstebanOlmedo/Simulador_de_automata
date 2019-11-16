/**
 * @author Esteban Olmedo Ramírez
 */
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class ArchivoTex extends Archivo
{

	private String contenido;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;

	public ArchivoTex()
	{
		this("", "", null, "", null, null);
	}

	public ArchivoTex(String path,String nombre)
	{
		super(path,nombre,null);
		fileWriter = null;
		bufferedWriter = null;
	}

	public ArchivoTex(String path, String nombre, File file,
			String contenido,
			FileWriter fileWriter, 
			BufferedWriter bufferedWriter
			)
	{
		super(path, nombre,file);
		this.contenido = contenido;
		this.fileWriter = fileWriter;
		this.bufferedWriter = bufferedWriter;
	}

	public ArchivoTex(ArchivoTex archivo)
	{
		super(archivo);
		this.contenido = archivo.contenido;
		this.fileWriter = archivo.fileWriter;
		this.bufferedWriter = archivo.bufferedWriter;
	}

	public void destruir()
	{
		super.destruir();
		if(contenido != null)
			contenido = null;
		if(fileWriter != null)
			fileWriter = null;
		if(bufferedWriter != null)
			bufferedWriter = null;
		System.gc();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof ArchivoTex)) {return false;}
		ArchivoTex archivo = (ArchivoTex)obj;
		return super.equals(archivo) && 
			contenido.equals(archivo.contenido) &&
			fileWriter.equals(archivo.fileWriter) &&
			bufferedWriter.equals(archivo.bufferedWriter);
	}

	@Override
	public String toString()
	{
		return super.toString() + "\n" +
			"Contenido:\n" + contenido + "\n"+
			fileWriter.toString()+
			bufferedWriter.toString();
	}

	public void establecerCabecera(String cabecera)
	{
		contenido += "\\documentclass[letter,12pt]{"+cabecera+"}\n";
	}

	public void establecerCabecera(String cabecera, String section)
	{
		establecerCabecera(cabecera);
		contenido += "\\section{"+section+"}\n";
	}

	public void establecerCabecera()
	{
		contenido += "\\documentclass[border = 12pt]{standalone}\n";
		contenido += "\\usepackage{tikz-graph}\n";
		contenido += "\\begin{document}\n";
		contenido += "\t\\begin{tikzpicture}";
		contenido += "\\GraphInit[vstyle = Normal]\n";
        contenido += "\\SetUpEdge[color = orange, labelcolor = gray!20,labelstyle = {draw}]\n";
		contenido += " \\SetGraphUnit{5}\n";
	}

	public void establecerFin()
	{
		contenido += "\\end{document}";
	}
	public void aumentarContenido(String contenido)
	{
		this.contenido += contenido;
	}

	public boolean abrirArchivo(Teclado teclado)
	{
		try
		{
			if(verificarExistenciaArchivo())
			{
				String op = teclado.dameUnString("El archivo ya existe ¿quieres sobreescribirlo?");
				if((op.toUpperCase()).equals("SI"))
				{
					fileWriter = new FileWriter(getFile());
					bufferedWriter = new BufferedWriter(fileWriter);

				}
				else
				{
					return false;
				}
			}
			else
			{
				fileWriter = new FileWriter(getFile());
				bufferedWriter = new BufferedWriter(fileWriter);
			}
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		return false;
	}

	public boolean cerrarArchivo()
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
