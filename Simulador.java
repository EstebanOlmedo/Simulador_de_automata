/**
 * @author Esteban Olmedo Ramírez
 */
public class Simulador
{
	public static void main(String[] args)
	{
		ManejadorDeAutomataYMaquinaDeTuring manejador = new ManejadorDeAutomataYMaquinaDeTuring();
		manejador.manejar();
		manejador.destruir();
	}
}
