package persistencia;
/**
 * @author Gabriel Graciano Herrera
 */
public class GeneradorYLectorDeArchivo
{
	private ArchivoTex archivoTex;
	private LectorObjectInputStream lector;
	private EscritorObjectOutputStream escritor;
	private GeneradorDeCodigoLaTex latex;

	public GeneradorYLectorDeArchivo(ArchivoTex archivoTex,LectorObjectInputStream lector,EscritorObjectOutputStream escritor)
	{
		this.archivoTex = archivoTex;
		this.lector = lector;
		this.escritor = escritor;
	}

	public GeneradorYLectorDeArchivo()
	{
		this(null,null,null);
	}

	public GeneradorYLectorDeArchivo(GeneradorYLectorDeArchivo generador)
	{
		this(generador.archivoTex,generador.lector,generador.escritor);
	}

	public boolean crearArchivoLatex(AutomataFinitoDeterminista afd,Teclado teclado)
	{
            return false;
	}

	public boolean crearArchivoLatex(AutomataFinitoNoDeterminista afn,Teclado teclado)
	{
            return false;
	}

	public boolean crearArchivoLatex(AutomataFinitoNoDeterministaEpsilon afne,Teclado teclado)
	{
            return false;
	}

	public boolean crearArchivoLatex(AutomataFinitoAPila afp,Teclado teclado)
	{
            return false;
	}

	public boolean escribirObjetoArchivo(Object obj,Teclado teclado)
	{
		String path,nombre;
		path = teclado.dameUnString("Ingresa la ruta del archivo");
		nombre = teclado.dameUnString("Ingresa el nombre del archivo");
		escritor = new EscritorObjectOutputStream(path,nombre);
		return escritor.escribirObjeto(obj,teclado);
	}

	public Object leerObjetoArchivo(Teclado teclado)
	{
		String path,nombre;
		path = teclado.dameUnString("Ingresa la ruta del archivo");
		nombre = teclado.dameUnString("Ingresa el nombre del archivo");
		lector = new LectorObjectInputStream(path,nombre);
		return lector.leerObjetoArchivo();
	}

	
	public String toString()
	{
		return archivoTex.toString()+lector.toString()+escritor.toString();
	}

	
	public void destruir()
	{
		if(archivoTex != null)
		{
			archivoTex.destruir();
			archivoTex = null;
		}
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
		return archivoTex.equals(generador.archivoTex) &&
			lector.equals(generador.lector) &&
			escritor.equals(generador.escritor);
	}
}
