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
}
