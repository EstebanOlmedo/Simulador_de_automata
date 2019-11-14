/**
 * @author Esteban Olmedo Ramírez
 */
public class Simulador
{
	public static void main(String[] args)
	{
		ManejadorDeAutomataYMaquinaDeTuring manejador = new ManejadorDeAutomataYMaquinaDeTuring();
		manejador.menu();
		if(manejador != null)
		{
			manejador.destruir();
			manejador = null;
			System.gc();
		}
		
	}
}
