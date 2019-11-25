package control;
import vista.Teclado;
/**
 *
 * @author esteban
 */
public class ControlDePeticion
{
	private ControlDePersistencia persistencia;
	private ControlGeneradorDeMaquinaDeTuring generadorMaquinaDeTuring;
	private ControlGeneradorDeTodoTipoDeAutomataFinito generadorAutomata;
	private ControlEvaluadorDeCadena evaluadorCadena;
	private Teclado teclado;
	
	public ControlDePeticion()
	{
		persistencia = new ControlDePersistencia();
		teclado = new Teclado();
		generadorMaquinaDeTuring = 
			new ControlGeneradorDeMaquinaDeTuring(teclado);
		generadorAutomata = 
			new ControlGeneradorDeTodoTipoDeAutomataFinito(teclado);
		evaluadorCadena = 
			new ControlEvaluadorDeCadena(teclado);
	}
	public ControlDePeticion(
		ControlDePersistencia persistencia,
		ControlGeneradorDeMaquinaDeTuring generadorMaquinaDeTuring,
		ControlGeneradorDeTodoTipoDeAutomataFinito generadorAutomata,
		ControlEvaluadorDeCadena evaluadorCadena,
		Teclado teclado
	)
	{
		this.evaluadorCadena = evaluadorCadena;
		this.generadorAutomata = generadorAutomata;
		this.generadorMaquinaDeTuring = generadorMaquinaDeTuring;
		this.persistencia = persistencia;
		this.teclado = teclado;
	}
	public ControlDePeticion(ControlDePeticion control)
	{
		this(control.persistencia, control.generadorMaquinaDeTuring,
			control.generadorAutomata, control.evaluadorCadena,
			control.teclado);
	}
	
	public void manejarPeticion(String peticion)
	{
		switch(peticion)
		{
			case "GAFD": manejarPeticionDeGeneracion(peticion); break;
			case "GAFND": manejarPeticionDeGeneracion(peticion); break;
			case "GAFNDE": manejarPeticionDeGeneracion(peticion); break;
			case "GAFP": manejarPeticionDeGeneracion(peticion); break;
			case "GMT": manejarPeticionDeGeneracion(peticion); break;
			case "EAFD": manejarPeticionDeEvalucion(peticion); break;
			case "EAFND": manejarPeticionDeEvalucion(peticion); break;
			case "EAFNDE": manejarPeticionDeEvalucion(peticion); break;
			case "EAFP": manejarPeticionDeEvalucion(peticion); break;
			case "EMT": manejarPeticionDeEvalucion(peticion); break;
			case "PAFD": manejarPeticionDePersistencia(peticion); break;
			case "PAFND": manejarPeticionDePersistencia(peticion); break;
			case "PAFNDE": manejarPeticionDePersistencia(peticion); break;
			case "PAFP": manejarPeticionDePersistencia(peticion); break;
			case "PMT": manejarPeticionDePersistencia(peticion); break;
			case "CAFD": manejarPeticionDeCargado(peticion); break;
			case "CAFND": manejarPeticionDeCargado(peticion); break;
			case "CAFNDE": manejarPeticionDeCargado(peticion); break;
			case "CAFP": manejarPeticionDeCargado(peticion); break;
			case "CMT": manejarPeticionDeCargado(peticion); break;
		}
	}
	public void manejarPeticionDeGeneracion(String peticion)
	{
		switch(peticion)
		{
			case "GAFD": 
				generadorAutomata.generarAutomataFinito();
				break;	
			case "GAFND": 
				generadorAutomata.generarAutomataFinitoNoDeterminista();
				break;
			case "GAFNDE": 
				generadorAutomata.generarAutomataFinitoNoDeterministaEpsilon();
				break;
			case "GAFP":
				generadorAutomata.generarAutomataFinitoAPila();
				break;
			case "GMT": 
				generadorMaquinaDeTuring.generarMaquinaDeTuring();
				break;
		}
	}
	public void manejarPeticionDeEvalucion(String peticion)
	{
		switch(peticion)
		{
			case "EAFD": 
				evaluadorCadena.evaluarCadena(
					generadorAutomata.getGenerador().
						getAutomataFinitoDeterminista());
				break;
			case "EAFND": 
				evaluadorCadena.evaluarCadena(
				generadorAutomata.getGenerador().
					getAutomataFinitoNoDeterminista());
				break;
			case "EAFNDE": 
				evaluadorCadena.evaluarCadena(
				generadorAutomata.getGenerador().
					getAutomataFinitoNoDeterminista());
				break;
			case "EAFP":
				evaluadorCadena.evaluarCadena(
				generadorAutomata.getGenerador().
					getAutomataFinitoAPila());
				break;
			case "EMT": 
				evaluadorCadena.evaluarCadena(
					generadorMaquinaDeTuring.
						getGenerador().
						getMaquinaDeTuring());
				break;
			default:
				System.out.println("La petición no se "
					+ "ha encontrado");
		}
	}
	public void manejarPeticionDePersistencia(String peticion)
	{
		switch(peticion)
		{
			case "PAFD": 
				persistencia.guardarUnObjeto(
				generadorAutomata.
					getGenerador().
					getAutomataFinitoDeterminista());
			break;
			case "PAFND":
				persistencia.guardarUnObjeto(
				generadorAutomata.
					getGenerador().
					getAutomataFinitoNoDeterminista());
				break;
			case "PAFNDE":
				persistencia.guardarUnObjeto(
				generadorAutomata.
					getGenerador().
					getAutomataFinitoNoDeterministaEpsilon());
				break;
			case "PAFP":
				persistencia.guardarUnObjeto(
				generadorAutomata.
					getGenerador().
					getAutomataFinitoAPila());
				break;
			case "PMT": 
				persistencia.guardarUnObjeto(
				generadorMaquinaDeTuring.getGenerador().
					getMaquinaDeTuring());
				break;
		}
	}

	private void manejarPeticionDeCargado(String peticion)
	{
		Object automata = persistencia.recuperarObjeto();
		switch(peticion)
		{
			case "CAFD": 
				generadorAutomata.setAutomata(automata);
				break;
			case "CAFND":
				generadorAutomata.setAutomata(automata);
				break;
			case "CAFNDE":
				generadorAutomata.setAutomata(automata);
				break;
			case "CAFP":
				generadorAutomata.setAutomata(automata);
				break;
			case "CMT": 
				break;
		}
	}
}