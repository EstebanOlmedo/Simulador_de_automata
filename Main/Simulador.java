package proyecto;
import vista.Menu;
public class Simulador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
	menu.elegirOpcion();
	menu.destruir();
	menu = null;
    }
    
}
