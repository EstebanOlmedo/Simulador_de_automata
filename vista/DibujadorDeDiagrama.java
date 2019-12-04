package vista;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author esteban
 */
public class DibujadorDeDiagrama extends JLabel
{
	private ArrayList<Nodo> estados;
	private ArrayList<Flecha> flechas;
	
	public DibujadorDeDiagrama()
	{
		super();
		estados = new ArrayList<>();
		flechas = new ArrayList<>();
	}
	
	public void dibujarEstado(int x, int y, int numero)
	{
		String estado = "q" + numero;
		Nodo nodo = new Nodo(x, y, estado);
		repaint();
	}
	public void dibujarEnlace(int inicioX, int inicioY, int finX, int finY,
		String funcion)
	{
		Flecha flecha = new Flecha(inicioX, inicioY, finX, finY, funcion);
		flechas.add(flecha);
		repaint();
	}
	@Override
	public void paintComponent(Graphics gra)
	{
		for (Nodo estado : estados)
			estado.pintar(gra);
		for(Flecha flecha: flechas)
			flecha.pintarFlecha(gra);
	}
}
