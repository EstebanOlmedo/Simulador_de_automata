package vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author esteban
 */
public class Flecha 
{
	private int origenX;
	private int origenY;
	private int destinoX;
	private int destinoY;
	private String nombre;
	private final int ARR_SIZE = 4;
	
	public Flecha()
	{
		origenX = 0;
		origenY = 0;
		destinoX = 0;
		destinoY = 0;
		nombre = "";
	}
	
	public Flecha(int origenX, int origenY, 
		int destinoX, int destinoY,
		String nombre)
	{
		this.origenX = origenX;
		this.origenY = origenY;
		this.destinoX = destinoX;
		this.destinoY = destinoY;
		this.nombre = nombre;
	}

	@Override
	public String toString()
	{
		return "Flecha{" + "Origen X=" + origenX + ", Origen Y=" + 
			origenY + ", Destino X=" + destinoX + ", Destino Y=" + '}';
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null) {return false;}
		if (!(obj instanceof Flecha)){return false;}
		Flecha other = (Flecha) obj;
		if (this.origenX != other.origenX) {
			return false;
		}
		if (this.origenY != other.origenY) {
			return false;
		}
		if (this.destinoX != other.destinoX) {
			return false;
		}
		return this.destinoY == other.destinoY;
	}

	public int getOrigenX() {
		return origenX;
	}

	public int getOrigenY() {
		return origenY;
	}

	public int getDestinoX() {
		return destinoX;
	}

	public int getDestinoY() {
		return destinoY;
	}

	public int getARR_SIZE() {
		return ARR_SIZE;
	}
	
	void pintarFlecha(Graphics g1)
	{ 
		Graphics2D g = (Graphics2D) g1.create(); 
		double dx = destinoX - origenX, dy = destinoY - origenY;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx*dx + dy*dy);
		AffineTransform at = AffineTransform.getTranslateInstance(origenX, origenY);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g.transform(at); 
		g.drawLine(0, 0, len, 0);
		g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len}, new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
		g.drawString(nombre, len/2,0);
	}

	void pintarArco(Graphics g){
		g.drawArc(origenX-15,origenY-15,20,20,0,270);
		g.drawString("*",origenX+3,origenY);
		g.drawString(nombre,origenX-20,origenY-20);
	}
}
