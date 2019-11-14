/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.ArrayList;
public class GeneradorDeCodigoLaTex
{
	private Archivo archivo;
	private AutomataFinitoAPila automataAPila;
	private AutomataFinitoDeterminista afd;
	private AutomataFinitoNoDeterminista afn;
	private AutomataFinitoNoDeterministaEpsilon afnEpsilon;

	public GeneradorDeCodigoLaTex()
	{
		this(null, null, null, null, null);
	}
	public GeneradorDeCodigoLaTex(Archivo archivo,
			AutomataFinitoAPila automataAPila,
			AutomataFinitoDeterminista afd, 
			AutomataFinitoNoDeterminista afn,
			AutomataFinitoNoDeterministaEpsilon afnEpsilon)
	{
		this.archivo = archivo;
		this.automataAPila = automataAPila;
		this.afd = afd;
		this.afn = afn;
		this.afnEpsilon = afnEpsilon;
	}
	public GeneradorDeCodigoLaTex(GeneradorDeCodigoLaTex generador)
	{
		this(generador.archivo, generador.automataAPila, 
				generador.afd, generador.afn, generador.afnEpsilon);
	}
	public void destruir()
	{
		if(archivo != null)
		{
			archivo.destruir();
			archivo = null;
		}
		if(automataAPila != null)
		{
			automataAPila.destruir();
			automataAPila = null;
		}
		if(afd != null)
		{
			afd.destruir();
			afd = null;
		}
		if(afn != null)
		{
			afn.destruir();
			afn = null;
		}
		if(afnEpsilon != null)
		{
			afnEpsilon.destruir();
			afnEpsilon = null;
		}
		System.gc();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) {return false;}
		if(!(obj instanceof GeneradorDeCodigoLaTex)) {return false;}
		GeneradorDeCodigoLaTex generador = (GeneradorDeCodigoLaTex)obj;
		return archivo.equals(generador.archivo) && 
			automataAPila.equals(generador.automataAPila) &&
			afd.equals(generador.afd) &&
			afn.equals(generador.afn) &&
			afnEpsilon.equals(generador.afnEpsilon);
	}
	@Override
	public String toString()
	{
		return "Archivo:\n" + archivo.toString() + "\n" +
			"Automata a pila:\n" + automataAPila.toString() + "\n" +
			"Automata finito determinista:\n" + afd.toString() + "\n" +
			"Automata finito no determinista:\n" + afn.toString() + "\n" +
			"Automata finito no determinista epsilon:\n" + afnEpsilon.toString() + "\n";
	}
	public boolean verificar(AutomataFinito automata)
	{
		if(automata != null && automata.getNumeroDeEstados() > 0)
			return true;
		return false;
	}
	public boolean verificar(AutomataFinitoDeterminista afd)
	{
		return verificar((AutomataFinito)afd) &&
			afd.getTablaDeTransiciones().size()==afd.getNumeroDeEstados();
	}
	public boolean verificar(AutomataFinitoNoDeterminista afn)
	{
		return verificar((AutomataFinito)afn) &&
			afn.getTablaDeTransiciones().size()==afn.getNumeroDeEstados();
	}
	public boolean verificar(AutomataFinitoNoDeterministaEpsilon afnE)
	{
		return verificar((AutomataFinitoNoDeterminista)afnE);
	}
	public boolean verificar(AutomataFinitoAPila ap)
	{
		return verificar((AutomataFinito)ap);
	}
	public boolean generarCodigo(AutomataFinitoDeterminista automata)
	{
		String contenido = new String();
		String estilo1 = "style={bend right, ->}";
		String estilo2 = "style={bend left, ->}";
		String estiloLoop = "dist = 4cm, dir = NO, color = orange, labelstyle = {draw, color = black, fill = gray!20}";
		String[] dire = {"NOEA", "EA","SOEA", "SO"};
		boolean[] estanDisponibles = new boolean[4];
		for(int i = 0; i < 4; i++)
			estanDisponibles[i] = true;
		((ArchivoTex)archivo).establecerCabecera();
		ArrayList<ArrayList<Integer> > tablaDeTransiciones = automata.getTablaDeTransiciones();
		boolean[] usados = new boolean[tablaDeTransiciones.size()];
		for(int i = 0; i < tablaDeTransiciones.size(); i++)
		{
			ArrayList<Integer> lista = tablaDeTransiciones.get(i);
			if(lista.size() > 4)
				return false;
			if(!usados[i])
				contenido += "\t\t\\Vertex{"+i +"}\n";
			for(int j = 0; j < lista.size(); j++)
			{
				int k = 0;
				while(!estanDisponibles[k])k++;
				estanDisponibles[k] = true;
				if(lista.get(j) != i)
				{
					contenido += "\\" + dire[k] + "("+i+")"+ "{"+
						lista.get(j)+"}\n";
					usados[lista.get(j)] = true;
					contenido += "\\Edge[label = " + "," + estilo1 + "](" + 
						i + ")(" +lista.get(j) + ")\n";
				}
				else
				{
					contenido += "\\Loop[" + estiloLoop + ",label = " + i + "]" + 
						"(" + i + ".north)\n";
				}
			}
		}
		((ArchivoTex)archivo).aumentarContenido(contenido);
		((ArchivoTex)archivo).establecerFin();
		return true;
	}
}
