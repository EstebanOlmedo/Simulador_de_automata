/**
 * @author Daniel Montes Guerrero
 */

package control;

import java.util.ArrayList;
import java.util.TreeMap;
import logica.Delta;
import logica.AutomataFinito;
import logica.AutomataFinitoDeterminista;
import logica.AutomataFinitoAPila;
import logica.AutomataFinitoNoDeterminista;
import logica.AutomataFinitoNoDeterministaEpsilon;
import vista.DibujadorDeDiagrama;
import javafx.util.Pair;
import logica.MaquinaDeTuring;
import logica.TablaMaquinaDeTuring;

public class ControlDibujarDiagrama{
	private MaquinaDeTuring maquina;
	private AutomataFinito automata;
	private DibujadorDeDiagrama dibujador;
	private int alto;
	private int ancho;
	TreeMap<Integer,Pair<Integer,Integer>> posUsados;

	public ControlDibujarDiagrama(){
		this(null,null,null);
	}

	public ControlDibujarDiagrama(AutomataFinito automata, MaquinaDeTuring maquina, DibujadorDeDiagrama dibujador){
		this.automata = automata;
		this.maquina = maquina;
		this.dibujador = dibujador;
		posUsados = new TreeMap<Integer,Pair<Integer,Integer>>();
		alto = 350;
		ancho = 570;
	}

	public ControlDibujarDiagrama(ControlDibujarDiagrama control){
		this(control.automata,control.maquina,control.dibujador);
	}

	public void destruir(){
		if(automata != null) automata = null;
		if(maquina != null) maquina = null;
		if(dibujador != null) dibujador = null;
		if(posUsados != null) posUsados = null;
		System.gc();
	}

	@Override
	public String toString(){
		return "Automata: "+ automata + "\nDibujador: " + dibujador;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof ControlDibujarDiagrama)) return false;
		ControlDibujarDiagrama control = (ControlDibujarDiagrama)obj;
		return automata.equals(control.automata) && dibujador.equals(control.dibujador);
	}

	public void dibujarEstados(int noEstados){
		int noDiv = (int)Math.ceil(Math.sqrt(noEstados));
		int espacioX = 0, espacioY = 0;
		espacioX = ancho/(noDiv+1);
		espacioY = alto/(noDiv+1);

		int contEstados = 0;
		for(int i=1; i<=noDiv; i++){
			for(int j=1; j<=noDiv; j++){
				if((contEstados) == noEstados){
					break;
				}
                                boolean aceptacion = automata.isAceptacion(contEstados);
				dibujador.dibujarEstado(j*espacioX,i*espacioY,contEstados, aceptacion);
				posUsados.put(contEstados,new Pair<Integer,Integer>(j*espacioX,i*espacioY));
				contEstados++;
			}
		}
		dibujarTransiciones();
	}

	public void dibujarAutomata(){
		dibujarEstados(automata.getNumeroDeEstados());
	}

	public void dibujarMaquina(){
		dibujarEstados(maquina.getTabla().getTabla().length);
	}

	public void dibujarTransicionesMT(){
		TablaMaquinaDeTuring tabla = maquina.getTabla();
		int noEstados = tabla.getTabla().length;
		int noAlfabeto = maquina.getAlfabeto().length;
		for(int i=0; i<noEstados; i++){
			int x1 = posUsados.get(i).getKey();
			int y1 = posUsados.get(i).getValue();
			for(int j=0; j<noAlfabeto; j++){
				try{
					if(tabla.getFuncion(i,j) != null){
						int x2 = posUsados.get(tabla.getFuncion(i,j).getEstado()).getKey();
						int y2 = posUsados.get(tabla.getFuncion(i,j).getEstado()).getValue();
						String cad = tabla.getFuncion(i,j).toString();
						dibujador.dibujarEnlace(x1,y1,x2,y2,"");
					}
				}
				catch(NullPointerException npe){
					
				}
			}
		}
	}

	public void dibujarTransiciones(){
		if(maquina != null)
			dibujarTransicionesMT();
		else if(automata instanceof AutomataFinitoDeterminista)
			dibujarTransicionesAFD();
		else if(automata instanceof AutomataFinitoNoDeterministaEpsilon)
			dibujarTransicionesAFNE();
		else if(automata instanceof AutomataFinitoNoDeterminista)
			dibujarTransicionesAFN();
		else if(automata instanceof AutomataFinitoAPila)
			dibujarTransicionesAFP();
	}

	public void dibujarTransicionesAFD(){
		ArrayList<ArrayList<Integer>> tabla = ((AutomataFinitoDeterminista)automata).getTablaDeTransiciones();
		int noAlfabeto = automata.getAlfabeto().length;
		int noEstados = tabla.size();
		for(int i=0; i<noEstados; i++){
			int x1 = posUsados.get(i).getKey();
			int y1 = posUsados.get(i).getValue();
			for(int j=0; j<noAlfabeto; j++){
				int x2 = posUsados.get(tabla.get(i).get(j)).getKey();
				int y2 = posUsados.get(tabla.get(i).get(j)).getValue();
				String cad = Character.toString(automata.getSimbolo(j));
				dibujador.dibujarEnlace(x1,y1,x2,y2,"");
				//System.out.println("A");
			}
		}
	}

	public void dibujarTransicionesAFN(){
		ArrayList<ArrayList<ArrayList<Integer>>> tabla = ((AutomataFinitoNoDeterminista)automata).getTablaDeTransiciones();
		int noEstados = tabla.size();
		int noAlfabeto = automata.getAlfabeto().length;
		for(int i=0; i<noEstados; i++){
			int x1 = posUsados.get(i).getKey();
			int y1 = posUsados.get(i).getValue();
			for(int j=0; j<noAlfabeto; j++){
				for(int k=0; k<tabla.get(i).get(j).size(); k++){
					int x2 = posUsados.get(tabla.get(i).get(j).get(k)).getKey();
					int y2 = posUsados.get(tabla.get(i).get(j).get(k)).getValue();
					String cad = Character.toString(automata.getSimbolo(j));
					dibujador.dibujarEnlace(x1,y1,x2,y2,"");
				}
			}
		}
	}

	public void dibujarTransicionesAFNE(){
		dibujarTransicionesAFN();
		ArrayList<ArrayList<Integer>> tabla = ((AutomataFinitoNoDeterministaEpsilon)automata).getAdyacenciaEpsilon();
		int noEstados = tabla.size();
		System.out.println(noEstados);
		for(int i=0; i<noEstados; i++){
			int x1 = posUsados.get(i).getKey();
			int y1 = posUsados.get(i).getValue();
			for(int j=0; j<tabla.get(i).size(); j++){
				int x2 = posUsados.get(tabla.get(i).get(j)).getKey();
				int y2 = posUsados.get(tabla.get(i).get(j)).getValue();
				dibujador.dibujarEnlace(x1,y1,x2,y2,"");
			}
		}
	}

	public void dibujarTransicionesAFP(){
		ArrayList<ArrayList<ArrayList<Delta>>> tabla = ((AutomataFinitoAPila)automata).getTablaTransiciones();
		int noEstados = tabla.size();
		int df = 0;
		for(int i=0; i<noEstados; i++){
			int x1 = posUsados.get(i).getKey();
			int y1 = posUsados.get(i).getValue();
			for(int j=0; j<noEstados; j++){
				int x2 = posUsados.get(j).getKey();
				int y2 = posUsados.get(j).getValue();
				for(int k=0; k<tabla.get(i).get(j).size(); k++){
					x2 += k*df;
					y2 += k*df;
					x1 += k*df;
					y1 += k*df;
					String cad = tabla.get(i).get(j).get(k).toString();
					dibujador.dibujarEnlace(x1,y1,x2,y2,"");
				}
			}
		}
	}

	public DibujadorDeDiagrama getDibujador(){
		return dibujador;	
	}

}