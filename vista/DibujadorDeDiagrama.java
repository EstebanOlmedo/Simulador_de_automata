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
		setVisible(true);
	}
	
	public void dibujarEstado(int x, int y, int numero)
	{
		String estado = "q" + numero;
		Nodo nodo = new Nodo(x, y, estado);
		estados.add(nodo);
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
            ArrayList<Flecha> dibujados = new ArrayList<Flecha>();
            for (Nodo estado : estados)
                    estado.pintar(gra);
            for(Flecha flecha: flechas){
                int i=0;
                for(Flecha f:dibujados){
                    if(flecha.mismasCoordenadas(f)) i++;
                }
                if(flecha.getOrigenX() == flecha.getDestinoX() && flecha.getOrigenY() == flecha.getDestinoY())
                        flecha.pintarArco(gra, 10*i);
                else
                        flecha.pintarFlecha(gra, 10*i);
                dibujados.add(flecha);
                //System.out.println("B");
            }
            super.paintComponent(gra);
	}
}
