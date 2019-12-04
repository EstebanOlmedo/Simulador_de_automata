package control;

import java.io.File;
import persistencia.GeneradorYLectorDeArchivo;
import vista.Teclado;
/**
 *
 * @author Esteban Olmedo
 */
public class ControlDePersistencia 
{
	private GeneradorYLectorDeArchivo generadorYLector;
	private Teclado teclado;
	public ControlDePersistencia(Teclado teclado)
        {
		this.teclado = teclado;
	}
	public ControlDePersistencia(Teclado teclado, 
                GeneradorYLectorDeArchivo generadorYLector)
	{
                this.teclado = teclado;
                this.generadorYLector = generadorYLector;
	}
        public ControlDePersistencia(ControlDePersistencia control)
	{
		this(control.teclado, control.generadorYLector);
	}
        public ControlDePersistencia()
        {
		this.generadorYLector = new GeneradorYLectorDeArchivo();
		this.teclado = new Teclado();
	}
	public void destruir()
	{
		if(generadorYLector != null){
			generadorYLector.destruir();
			generadorYLector = null;
		}
		if(teclado != null){
			teclado.destruir();
			teclado = null;
		}
		System.gc();
	}
	@Override
	public String toString()
	{
		return "Control de persistencia\n";
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof ControlDePersistencia))
			return false;
		ControlDePersistencia control = (ControlDePersistencia)obj;
		return teclado.equals(control.teclado) &&
			generadorYLector.equals(control.generadorYLector);
	}
	
	public void guardarUnObjeto(Object obj)
	{
		String nombre = teclado.dameUnString("Ingresa el nombre "
			+ "del archivo en el que deseas guardar");
		String path = teclado.dameUnString("Ingresa la ruta del archivo");
		if(generadorYLector.escribirObjetoArchivo(obj, nombre, path))
		{
			System.out.println("Se ha guardado correctamente");
		}
		else
		{
			System.out.println("Hubo un error");
		}
	}
	public void guardarUnObjeto(Object obj, File archivo)
	{
		generadorYLector.escribirObjetoArchivo(obj, archivo.getName(), 
			archivo.getAbsolutePath());
	}
	public Object recuperarObjeto(File file)
	{
            return generadorYLector.leerObjetoArchivo(file.getAbsolutePath());
	}
        
}
