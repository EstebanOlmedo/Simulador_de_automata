package vista;

import java.awt.Graphics;

/**
 *
 * @author esteban
 */
public class Nodo 
{
	private int posX;
	private int posY;
	private String nombre;
	private static final int RADIO = 40;
        private boolean aceptacion;
        
	public Nodo()
	{
		posX = 0;
		posY = 0;
		nombre = "";
	}
	public Nodo(int posX, int posY, String nombre, boolean aceptacion)
	{
		this.posX = posX;
		this.posY = posY;
		this.nombre = nombre;
                this.aceptacion = aceptacion;
	}
	public Nodo(Nodo nodo)
	{
		this(nodo.posX, nodo.posY, nodo.nombre, nodo.aceptacion);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof Nodo)){return false;}
		Nodo nodo = (Nodo)obj;
		return posX == nodo.posX &&
			posY == nodo.posY &&
			nombre.equals(nodo.nombre);
	}

	@Override
	public String toString() {
		return "Nodo{" + "posX=" + posX + ", posY=" + posY + ", nombre=" + nombre + '}';
	}

	public void setPosX(int posX) 
	{
		this.posX = posX;
	}

	public void setPosY(int posY) 
	{
		this.posY = posY;
	}
	
	public void pintar(Graphics graphics)
	{
		graphics.drawOval(posX, posY, RADIO, RADIO);
		graphics.drawString(nombre,posX+RADIO/2-4,posY+RADIO/2+3);
                if(aceptacion)
                    graphics.drawOval(posX-5, posY-5, RADIO+10, RADIO+10);
	}
}

