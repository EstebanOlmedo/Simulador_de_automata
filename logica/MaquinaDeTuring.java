package logica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Gabriel Graciano Herrera
 */
public class MaquinaDeTuring implements Serializable
{
	private char[] cinta;
	private int cabezal;
	private char[] alfabeto;
	private String descripcion;
	private TablaMaquinaDeTuring tabla;
	public MaquinaDeTuring()
	{
		this(null, 0, null, null, null);
	}
	public MaquinaDeTuring(char[] cinta, int cabezal,
		       	char[] alfabeto, TablaMaquinaDeTuring tabla,
			String descripcion)
	{
		this.cinta = cinta;
		this.cabezal = cabezal;
		this.alfabeto = alfabeto;
		this.tabla = tabla;
		this.descripcion = descripcion;
	}
	public MaquinaDeTuring(MaquinaDeTuring maquinaDeTuring)
	{
		this(maquinaDeTuring.cinta, maquinaDeTuring.cabezal,
				maquinaDeTuring.alfabeto, maquinaDeTuring.tabla,
				maquinaDeTuring.descripcion);
	}
	public void destruir()
	{
		if(cinta != null)
			cinta = null;
		if(alfabeto != null)
			alfabeto = null;
		if(tabla != null)
		{
			tabla.destruir();
			tabla = null;
		}
		if(descripcion != null)
			descripcion = null;
		System.gc();
	}
	@Override
	public String toString()
	{
		String retorno = new String();
		retorno += "Descripcion:\n" + descripcion + "\n";
		retorno += "Cinta: {...,";
		for(int i = 0; i < cinta.length; i++)
			retorno += cinta[i] + ", ";
		retorno += "...}\n";
		retorno += "Cabezal: " + cabezal + "\n";
		return retorno;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof MaquinaDeTuring)) {return false;}
		MaquinaDeTuring maquinaDeTuring = (MaquinaDeTuring)obj;
		if(cinta.length != maquinaDeTuring.cinta.length)
			return false;
		for(int i = 0; i < cinta.length; i++)
		{
			if(cinta[i] != maquinaDeTuring.cinta[i])
				return false;
		}
		return cabezal == maquinaDeTuring.cabezal &&
			descripcion.equals(maquinaDeTuring.descripcion);
	}
        
	public void setCinta(char[] cinta)
	{
		this.cinta = cinta;
	}

	public TablaMaquinaDeTuring getTabla()
	{
		return tabla;
	}
	public void setTabla(TablaMaquinaDeTuring tabla)
	{
		this.tabla = tabla;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public char[] getAlfabeto()
	{
		return alfabeto;
	}
	public String getDescripcion()
	{
		return descripcion;
	}
	public int getIndiceSimboloEnAlfabeto(char simbolo) throws NoExisteEnAlfabetoException
	{
		int retorno = 0;
		int i = 0;
		try
		{
			if(estaEnAlfabeto(simbolo))
			{
				while(alfabeto[i] != simbolo)
				{
					i++;
					retorno++;
				}
				return retorno;
			}
			else
				return -1;
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			System.out.println("Hubo un error");
		}
		return -1;
	}
	private void setCinta(String cadena)
	{
		String nuevaCadena = "BB" + cadena + "BB";
		cinta = nuevaCadena.toCharArray();
		System.out.println(cinta);
	}
	public void setAlfabeto(String alfabeto)
	{
		this.alfabeto = alfabeto.toCharArray();
	}
	public void setAlfabeto(char[] alfabeto)
	{
		this.alfabeto = alfabeto;
	}

	public boolean modificarCinta(FuncionDeltaMaquinaDeTuring funcion)
	{
		try
		{
			if(funcion.hayCamino())
			{
				cinta[cabezal] = funcion.getLoQueDeja();
				if(funcion.getMovimiento() == 'D')
					cabezal++;
				else
					cabezal--;
				return true;
			}
			else
				return false;
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			System.out.println("Ocurrió un error al intentar modificar la cinta");
		}
		catch(NullPointerException npe)
		{
			System.out.println("Hubo un error");
		}
		return false;
	}
	public boolean modificarCinta(int estado, char loQueDeja, char movimiento)
	{
		return modificarCinta(new FuncionDeltaMaquinaDeTuring(estado, loQueDeja, movimiento));
	}
	public boolean estaEnAlfabeto(char simbolo) throws NoExisteEnAlfabetoException
	{
		boolean respuesta = false;
		for(int i = 0; i < alfabeto.length; i++)
		{
			if(alfabeto[i] == simbolo)
			return true;
		}
		throw new NoExisteEnAlfabetoException("El alfabeto de la máquina "
				+ "de Turing no contiene el simbolo: " + simbolo);
	}
	public boolean estaEnAlfabeto(String cadena) throws NoExisteEnAlfabetoException
	{
		boolean respuesta = true;
		try
		{
			for(int i = 0; i < cadena.length(); i++)
				respuesta = respuesta && estaEnAlfabeto(cadena.charAt(i));
		}catch(NoExisteEnAlfabetoException neeae){
			respuesta = false;
			throw new NoExisteEnAlfabetoException("La cadena: " + cadena +
					" no se encuentra dentro del alfabeto");
		}
		finally{
			return respuesta;
		}
	}
	public boolean estaEnAlfabeto(char[] cadena) throws NoExisteEnAlfabetoException
	{
		boolean respuesta = false;
		try
		{
			respuesta = estaEnAlfabeto(new String(cadena));
		}
		catch(NoExisteEnAlfabetoException neeae){
			respuesta = false;
			throw neeae;
		}
		finally{
			return respuesta;
		}
	}
	public void prepararMaquina(String cadena)
	{
		setCinta(cadena);
		cabezal = 2;
	}
	public boolean accionar()
	{
		boolean laMaquinaAcepta = false;
		try
		{
			if(estaEnAlfabeto(cinta))
			{
				int indice = getIndiceSimboloEnAlfabeto(cinta[cabezal]);
				int estado = 0;
				int estadoFinal;
				FuncionDeltaMaquinaDeTuring funcion = tabla.getFuncion(estado, indice);
				while(funcion.hayCamino())
				{
					mostrarEstado();
					estado = funcion.getEstado();
					modificarCinta(funcion);
					indice = getIndiceSimboloEnAlfabeto(cinta[cabezal]);
					funcion = tabla.getFuncion(estado, indice);
				}
				mostrarEstado();
				System.out.println("Estado final: " + estado);
				if(tabla.isEstadoAceptacion(estado))
					laMaquinaAcepta = true;
			}
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			System.out.println("Hubo un error");
		}
		catch(NullPointerException npe)
		{
			System.out.println("Hubo un error");
		}
		catch(NoExisteEnAlfabetoException neeae)
		{
			System.out.println(neeae);
		}
		finally
		{
			return laMaquinaAcepta;
		}
	}
	public void mostrarEstado()
	{
		System.out.print("|");
		for(int i = 0; i < cinta.length; i++)
		{
			System.out.print(cinta[i] + "|");
		}
		System.out.println();
		for(int i = 1; i <= cabezal*2 + 1; i++)
			System.out.print(" ");
		System.out.println("^");
		for(int i = 1; i <= cabezal*2 + 1; i++)
			System.out.print(" ");
		System.out.println("|");

	}
	public void aniadirTransicionATabla(int estado, int indiceSimbolo,
		int estadoTransicion, char loQueDeja, char movimiento)
	{
		tabla.setEstado(estado, indiceSimbolo, estadoTransicion, loQueDeja, movimiento);
	}
        
        public String getLenguaje()
        {
            StringBuffer cadena = new StringBuffer("ALFABETO ACEPTADO POR EL AUTOMATA: ");
            for(int x = 0; x < alfabeto.length;x++)
            {
                cadena.append(alfabeto[x]);
                if(x != alfabeto.length-1)
                    cadena.append(',');
            }
            return cadena.toString();
        }
        
        public String getTransiciones()
        {
		String retorno = new String();
		int numEstados = 0;
		for(int i = 0; i < tabla.getTamanio(); i++)
		{
			for(int j = 0; j < alfabeto.length; j++)
			{
				FuncionDeltaMaquinaDeTuring funcion = tabla.getFuncion(cabezal, i);
				if(funcion.getEstado() != -1)
				{
					if(numEstados % 5 == 0)
						retorno += "\n";
					retorno += "S(" + i + ", " + alfabeto[j] + ")" + funcion.toString() + "\t ";
					numEstados++;
				}
			}
		}
		return retorno;
        }
}
