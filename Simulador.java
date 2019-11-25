/**
 * @author Esteban Olmedo Ramírez
 */
public class Simulador
{
	public static void main(String[] args)
	{
		Menu menu = new Menu();
		menu.elegirOpcion();
		menu.destruir();
		menu = null;
	}
}
