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
	private static final int RADIO = 20;
	public Nodo()
	{
		posX = 0;
		posY = 0;
		nombre = "";
	}
	public Nodo(int posX, int posY, String nombre)
	{
		this.posX = posX;
		this.posY = posY;
		this.nombre = nombre;
	}
	public Nodo(Nodo nodo)
	{
		this(nodo.posX, nodo.posY, nodo.nombre);
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
	}
}

