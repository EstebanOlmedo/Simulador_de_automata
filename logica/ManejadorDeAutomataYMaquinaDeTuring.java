package logica;
import persistencia.GeneradorYLectorDeArchivo;
import vista.Teclado;
import control.ControlGeneradorDeTodoTipoDeAutomataFinito;
/**
 * @author Gabriel Graciano Herrera
 */
public class ManejadorDeAutomataYMaquinaDeTuring
{
    private ControlGeneradorDeTodoTipoDeAutomataFinito controlGeneradorDeAutomata;
    private GeneradorDeMaquinaDeTuring generadorDeMaquina;
    private ConversorDeAutomata conversor;
    private EvaluadorDeExpresion evaluador;
    private GeneradorYLectorDeArchivo generador;
    private Teclado teclado;

    public ManejadorDeAutomataYMaquinaDeTuring()
    {
        this(
                new ControlGeneradorDeTodoTipoDeAutomataFinito(),
                new GeneradorDeMaquinaDeTuring(),
                new ConversorDeAutomata(),
                new EvaluadorDeExpresion(),
                new GeneradorYLectorDeArchivo(),
                new Teclado()
            );
    }
    public ManejadorDeAutomataYMaquinaDeTuring(
         ControlGeneradorDeTodoTipoDeAutomataFinito generadorDeAutomata,
         GeneradorDeMaquinaDeTuring generadorDeMaquina,
         ConversorDeAutomata conversor,
         EvaluadorDeExpresion evaluador,
         GeneradorYLectorDeArchivo generador,
         Teclado teclado
         )
    {
        this.controlGeneradorDeAutomata = controlGeneradorDeAutomata;
        this.generadorDeMaquina = generadorDeMaquina;
        this.conversor = conversor;
        this.generador = generador;
        this.evaluador = evaluador;
        this.teclado = teclado;
    }
    public ManejadorDeAutomataYMaquinaDeTuring(ManejadorDeAutomataYMaquinaDeTuring simulador)
    {
        this(
                simulador.controlGeneradorDeAutomata,
                simulador.generadorDeMaquina,
                simulador.conversor,
                simulador.evaluador,
                simulador.generador,
                simulador.teclado
                );
    }
    public void destruir()
    {
        if(controlGeneradorDeAutomata != null){
            controlGeneradorDeAutomata.destruir();
            controlGeneradorDeAutomata = null;
        }
        if(generadorDeMaquina != null){
            generadorDeMaquina.destruir();
            generadorDeMaquina = null;
        }
        if(conversor != null){
            conversor.destruir();
            conversor = null;
        }
        if(generador != null){
            generador.destruir();
            generador = null;
        }
        if(evaluador != null){
            evaluador.destruir();
            evaluador = null;
        }
        if(teclado != null)
        {
            teclado.destruir();
            teclado = null;
        }
        System.gc();
    }
    @Override
    public String toString()
    {
        return "Manejador de Automata y MaquinaDeturing:\n" + 
            controlGeneradorDeAutomata.toString() +
            generadorDeMaquina.toString() +
            conversor.toString() +
            generador.toString() +
            evaluador.toString()+teclado.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null){return false;}
        if(!(obj instanceof ManejadorDeAutomataYMaquinaDeTuring)){return false;}
        ManejadorDeAutomataYMaquinaDeTuring simulador = (ManejadorDeAutomataYMaquinaDeTuring)obj;
        return controlGeneradorDeAutomata.equals(simulador.controlGeneradorDeAutomata) &&
            generadorDeMaquina.equals(simulador.generadorDeMaquina) &&
            conversor.equals(simulador.conversor) &&
            generador.equals(simulador.generador) &&
            evaluador.equals(simulador.evaluador) &&
            teclado.equals(simulador.teclado);
    }

    public void menu()
    {
        int op;
        do
        {
            System.out.println("Menu");
            System.out.println("\t1.-Automata Finito Determinista");
            System.out.println("\t2.-Automata Finito No Determinista");
            System.out.println("\t3.-Automata Finito No Determinista Epsilon");
            System.out.println("\t4.-Automata Finito A Pila");
            System.out.println("\t5.-Maquina de Turing");
            System.out.println("\t6.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    subMenuAFD();
                    break;
                case 2:
                    subMenuAFN();
                    break;
                case 3:
                    subMenuAFNE();
                    break;
                case 4:
                    subMenuAFP();
                    break;
                case 5:
                    subMenuMT();                    
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
        }while(op != 6);
    }
    
    private void subMenuAFD()
    {
        int op;
        do 
        {
            System.out.println("Menu AFD");
            System.out.println("\t1.-Generar Automata Finito Determinista");
            System.out.println("\t2.-Evaluar cadena del automata");
            System.out.println("\t3.-Convertir AFD a AFP");
            System.out.println("\t4.-Guardar el Automata Finito Determinista en un archivo");
            System.out.println("\t5.-Cargar AFD de un archivo");
            System.out.println("\t6.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    controlGeneradorDeAutomata.generarAutomataFinitoDeterminista();
                    break;
                case 2:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista() != null)
                        {
            			   System.out.println(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista().getDescripcion());
                           String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                           if( evaluador.evaluarCadena(cadena,controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista()) )
			               {
            				   System.out.println("La cadena pertenece al lenguaje del automata");
            			   }
            			   else
            			   {
            				   System.out.println("La cadena no pertenece al lenguaje del automata");
            			   }
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 3:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista() != null)
                        {
                            conversor.convertirAFDaAFP(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista());
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 4:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista() != null)
                        {
                            if(generador.escribirObjetoArchivo(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoDeterminista(),teclado))
                                System.out.println("Se guardo el automata con exito");
                            else
                                System.out.println("Hubo un error al guardar el automata");
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                       npe.printStackTrace();
                    }            
                case 5:
                    try
                    {
                        AutomataFinitoDeterminista aux = (AutomataFinitoDeterminista) generador.leerObjetoArchivo(teclado);
                        controlGeneradorDeAutomata.getGenerador().setAutomataFinitoDeterminista(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFD");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFD");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
                
        }while (op != 6);
    }
    
    private void subMenuAFN()
    {
        int op;
        do 
        {
            System.out.println("Menu AFN");
            System.out.println("\t1.-Generar Automata Finito No Determinista");
            System.out.println("\t2.-Evaluar cadena del automata");
            System.out.println("\t3.-Convertir AFN a AFD");
            System.out.println("\t4.-Guardar el AFN en un archivo");
            System.out.println("\t5.-Cargar AFN de un archivo");
            System.out.println("\t6.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    controlGeneradorDeAutomata.generarAutomataFinitoNoDeterminista();
                    break;
                case 2:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista() != null)
                        {
			                 System.out.println(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista().getDescripcion());
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista()))
                            {
                				    System.out.println("La cadena pertenece al lenguaje del automata");
            			    }
            			    else
            			    {
                				    System.out.println("La cadena no pertenece al lenguaje del automata");
            			    }
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    { 
                        npe.printStackTrace();
                    }
                case 3:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista() != null)
                        {
                            conversor.convertirAFNaAFD(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista());
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 4:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista() != null)
                        {
                            if(generador.escribirObjetoArchivo(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterminista(),teclado))
                                System.out.println("Se guardo el automata con exito");
                            else
                                System.out.println("Hubo un error al guardar el automata");
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 5:
                    try
                    {
                        AutomataFinitoNoDeterminista aux = (AutomataFinitoNoDeterminista) generador.leerObjetoArchivo(teclado);
                        controlGeneradorDeAutomata.getGenerador().setAutomataFinitoNoDeterminista(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFN");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFN");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
                
        }while (op != 6);
    }

    private void subMenuAFNE()
    {
        int op;
        do 
        {
            System.out.println("Menu Automata Finito No Determinista Epsilon");
            System.out.println("\t1.-Generar AFNE");
            System.out.println("\t2.-Evaluar cadena del automata");
            System.out.println("\t3.-Guardar el AFNE en un archivo");
            System.out.println("\t4.-Cargar AFNE de un archivo");
            System.out.println("\t5.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {  
                case 1:
                    controlGeneradorDeAutomata.generarAutomataFinitoNoDeterministaEpsilon();
                    break;
                case 2:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterministaEpsilon() != null)
                        {
            			    System.out.println(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterministaEpsilon().getDescripcion());
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterministaEpsilon()) )
                            {
				                System.out.println("La cadena pertenece al lenguaje del automata");
                            }
                            else
                            {
            				    System.out.println("La cadena no pertenece al lenguaje del automata");
                            }       
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 3:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterministaEpsilon() != null)
                        {
                            if(generador.escribirObjetoArchivo(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoNoDeterministaEpsilon(),teclado))
                                System.out.println("El automata se guardo con exito");
                            else
                                System.out.println("Hubo un error al guardar el automata");
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 4:
                    try
                    {
                        AutomataFinitoNoDeterministaEpsilon aux = (AutomataFinitoNoDeterministaEpsilon) generador.leerObjetoArchivo(teclado);
                        controlGeneradorDeAutomata.getGenerador().setAutomataFinitoNoDeterministaEpsilon(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFNE");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFNE");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
                
        }while (op != 5);
    }
    
    private void subMenuAFP()
    {
        int op;
        do
        {
            System.out.println("Menu Automata Finito a Pila");
            System.out.println("\t1.-Generar Automata Finito a Pila");
            System.out.println("\t2.-Evaluar cadena del automata");
            System.out.println("\t3.-Guardar el AFP en un archivo");
            System.out.println("\t4.-Cargar AFP de un archivo");
            System.out.println("\t5.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    controlGeneradorDeAutomata.generarAutomataFinitoAPila();
                    break;
                case 2:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoAPila() != null)
                        {
                            System.out.println(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoAPila().getDescripcion());
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,controlGeneradorDeAutomata.getGenerador().getAutomataFinitoAPila()))
                            {
				                System.out.println("La cadena pertenece al lenguaje del automata");
                            }
                            else
                            {
				                System.out.println("La cadena no pertenece al lenguaje del automata");
                            }
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 3:
                    try
                    {
                        if(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoAPila() != null)
                        {
                            if(generador.escribirObjetoArchivo(controlGeneradorDeAutomata.getGenerador().getAutomataFinitoAPila(),teclado))
                                System.out.println("El automata se guardo con exito");
                            else
                                System.out.println("Hubo un error al guardar el automata");
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 4:
                    try
                    {
                        AutomataFinitoNoDeterminista aux = (AutomataFinitoNoDeterminista) generador.leerObjetoArchivo(teclado);
                        controlGeneradorDeAutomata.getGenerador().setAutomataFinitoNoDeterminista(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFN");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFN");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
        }while(op != 5);
    }
    
    private void subMenuMT()
    {
        int op;
        do
        {
            System.out.println("Menu Maquina de Turing");
            System.out.println("\t1.-Generar Maquina de Turing");
            System.out.println("\t2.-Evaluar cadena de la maquina");
            System.out.println("\t3.-Guardar la MT en un archivo");
            System.out.println("\t4.-Cargar MT de un archivo");
            System.out.println("\t5.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    generadorDeMaquina.crearMaquinaDeTuring();
                    break;
                case 2:
                    try
                    {
                        if(generadorDeMaquina.getMaquinaDeTuring() != null)
                        {
                            System.out.println(generadorDeMaquina.getMaquinaDeTuring().getDescripcion()); 
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,generadorDeMaquina.getMaquinaDeTuring()))
                            {
                                System.out.println("La cadena pertenece al lenguaje de la MT");
                            }
                            else
                            {
				                System.out.println("La cadena no pertenece al lenguaje de la MT");
                            }
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 3:
                    try
                    {
                        if(generadorDeMaquina.getMaquinaDeTuring() != null)
                        {
                            if(generador.escribirObjetoArchivo(generadorDeMaquina.getMaquinaDeTuring(),teclado))
                                System.out.println("La maquina se guardo con exito");
                            else
                                System.out.println("Hubo un error al guardar la maquina");
                        }
                        else
                        {
                            System.out.println("Aun no has generado el automata");
                        }
                        break;
                    }catch(NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                case 4:
                    try
                    {
                        MaquinaDeTuring aux = (MaquinaDeTuring) generador.leerObjetoArchivo(teclado);
                        generadorDeMaquina.setMaquinaDeTuring(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia una MT");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia una MT");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
        }while(op != 5);
    }

}
