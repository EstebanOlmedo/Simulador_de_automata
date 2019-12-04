package vista;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author esteban
 */
public class DibujadorDeDiagrama extends JLabel
{
	public DibujadorDeDiagrama()
	{
		super();
	}
	public void dibujarEstado(int x, int y, int radio)
	{
		Graphics gra = this.getGraphics();
		gra.drawOval(x, y, radio, radio);
	}
	public void dibujarEnlace(int inicioX, int inicioY, int finX, int finY)
	{
		Graphics gra = this.getGraphics();
		gra.drawRect(inicioX, inicioY, finX, finY);
	}
	public void dibujarCadena(String cadena, int x, int y)
	{
		Graphics gra = this.getGraphics();
		gra.drawString(cadena, x, y);
	}
}
