/**
 * @author Esteban Olmedo Ramírez
 */
public class ArchivoTex extends Archivo
{
	private String contenido;

	public ArchivoTex()
	{
		this("", "", "");
	}
	public ArchivoTex(String path, String nombre, 
			String contenido)
	{
		super(path, nombre);
		this.contenido = contenido;
	}
	public ArchivoTex(ArchivoTex archivo)
	{
		super(archivo);
		this.contenido = archivo.contenido;
	}
	public void destruir()
	{
		super.destruir();
		if(contenido != null)
			contenido = null;
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof ArchivoTex)) {return false;}
		ArchivoTex archivo = (ArchivoTex)obj;
		return super.equals(archivo) && 
			contenido.equals(archivo.contenido);
	}
	@Override
	public String toString()
	{
		return super.toString() + "\n" +
			"Contenido:\n" + contenido + "\n";
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
}
