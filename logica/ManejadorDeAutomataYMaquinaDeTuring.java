package logica;
import persistencia.GeneradorYLectorDeArchivo;
/**
 * @author Gabriel Graciano Herrera
 */
public class ManejadorDeAutomataYMaquinaDeTuring
{
    private GeneradorDeTodoTipoDeAutomataFinito generadorDeAutomata;
    private GeneradorDeMaquinaDeTuring generadorDeMaquina;
    private ConversorDeAutomata conversor;
    private EvaluadorDeExpresion evaluador;
    private GeneradorYLectorDeArchivo generador;
    private Teclado teclado;

    public ManejadorDeAutomataYMaquinaDeTuring()
    {
        this(
                new GeneradorDeTodoTipoDeAutomataFinito(),
                new GeneradorDeMaquinaDeTuring(),
                new ConversorDeAutomata(),
                new EvaluadorDeExpresion(),
                new GeneradorYLectorDeArchivo(),
                new Teclado()
            );
    }
    public ManejadorDeAutomataYMaquinaDeTuring(
         GeneradorDeTodoTipoDeAutomataFinito generadorDeAutomata,
         GeneradorDeMaquinaDeTuring generadorDeMaquina,
         ConversorDeAutomata conversor,
         EvaluadorDeExpresion evaluador,
         GeneradorYLectorDeArchivo generador,
         Teclado teclado
         )
    {
        this.generadorDeAutomata = generadorDeAutomata;
        this.generadorDeMaquina = generadorDeMaquina;
        this.conversor = conversor;
        this.generador = generador;
        this.evaluador = evaluador;
        this.teclado = teclado;
    }
    public ManejadorDeAutomataYMaquinaDeTuring(ManejadorDeAutomataYMaquinaDeTuring simulador)
    {
        this(
                simulador.generadorDeAutomata,
                simulador.generadorDeMaquina,
                simulador.conversor,
                simulador.evaluador,
                simulador.generador,
                simulador.teclado
                );
    }
    public void destruir()
    {
        if(generadorDeAutomata != null){
            generadorDeAutomata.destruir();
            generadorDeAutomata = null;
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
            generadorDeAutomata.toString() +
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
        return generadorDeAutomata.equals(simulador.generadorDeAutomata) &&
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
            System.out.println("\t5.-Generar un archivo Latex con el Automata Finito Determinista");
            System.out.println("\t6.-Cargar AFD de un archivo");
            System.out.println("\t7.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    generadorDeAutomata.crearAutomataFinitoDeterminista();
                    break;
                case 2:
                    try
                    {
                        if(generadorDeAutomata.getAutomataFinitoDeterminista() != null)
                        {
            			   System.out.println(generadorDeAutomata.getAutomataFinitoDeterminista().getDescripcion());
                           String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                           if( evaluador.evaluarCadena(cadena,generadorDeAutomata.getAutomataFinitoDeterminista()) )
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
                        if(generadorDeAutomata.getAutomataFinitoDeterminista() != null)
                        {
                            conversor.convertirAFDaAFP(generadorDeAutomata.getAutomataFinitoDeterminista());
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
                        if(generadorDeAutomata.getAutomataFinitoDeterminista() != null)
                        {
                            if(generador.escribirObjetoArchivo(generadorDeAutomata.getAutomataFinitoDeterminista(),teclado))
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
                        if(generadorDeAutomata.getAutomataFinitoDeterminista() != null)
                        {
                            if(generador.crearArchivoLatex(generadorDeAutomata.getAutomataFinitoDeterminista(),teclado))
                                System.out.println("Se genero correctamente el archivo latex");
                            else
                                System.out.println("Hubo un error al crear el archivo latex");    
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
                case 6:
                    try
                    {
                        AutomataFinitoDeterminista aux = (AutomataFinitoDeterminista) generador.leerObjetoArchivo(teclado);
                        generadorDeAutomata.setAutomataFinitoDeterminista(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFD");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFD");
                    }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
                
        }while (op != 7);
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
            System.out.println("\t5.-Generar un archivo Latex con el AFN");
            System.out.println("\t6.-Cargar AFN de un archivo");
            System.out.println("\t7.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    generadorDeAutomata.crearAutomataFinitoNoDeterminista();
                    break;
                case 2:
                    try
                    {
                        if(generadorDeAutomata.getAutomataFinitoNoDeterminista() != null)
                        {
			                 System.out.println(generadorDeAutomata.getAutomataFinitoNoDeterminista().getDescripcion());
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,generadorDeAutomata.getAutomataFinitoNoDeterminista()))
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
                        if(generadorDeAutomata.getAutomataFinitoNoDeterminista() != null)
                        {
                            conversor.convertirAFNaAFD(generadorDeAutomata.getAutomataFinitoNoDeterminista());
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
                        if(generadorDeAutomata.getAutomataFinitoNoDeterminista() != null)
                        {
                            if(generador.escribirObjetoArchivo(generadorDeAutomata.getAutomataFinitoNoDeterminista(),teclado))
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
                        if(generadorDeAutomata.getAutomataFinitoNoDeterminista() != null)
                        {
                            if(generador.crearArchivoLatex(generadorDeAutomata.getAutomataFinitoNoDeterminista(),teclado))
                                System.out.println("Se creo el archivo latex con exito");
                            else
                                System.out.println("Hubo un error al crear el archivo latex");
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
                case 6:
                    try
                    {
                        AutomataFinitoNoDeterminista aux = (AutomataFinitoNoDeterminista) generador.leerObjetoArchivo(teclado);
                        generadorDeAutomata.setAutomataFinitoNoDeterminista(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFN");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFN");
                    }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Ingresa una opcion correcta");
                    break;
            }
                
        }while (op != 7);
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
            System.out.println("\t4.-Generar un archivo Latex con el AFNE");
            System.out.println("\t5.-Cargar AFNE de un archivo");
            System.out.println("\t6.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {  
                case 1:
                    generadorDeAutomata.crearAutomataFinitoNoDeterministaEpsilon();
                    break;
                case 2:
                    try
                    {
                        if(generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon() != null)
                        {
            			    System.out.println(generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon().getDescripcion());
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon()) )
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
                        if(generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon() != null)
                        {
                            if(generador.escribirObjetoArchivo(generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon(),teclado))
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
                        if(generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon() != null)
                        {
                            if(generador.crearArchivoLatex(generadorDeAutomata.getAutomataFinitoNoDeterministaEpsilon(),teclado))
                                System.out.println("Se creo el archivo latex con exito");
                            else
                                System.out.println("Hubo un error al crear el archivo latex");
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
                        AutomataFinitoNoDeterministaEpsilon aux = (AutomataFinitoNoDeterministaEpsilon) generador.leerObjetoArchivo(teclado);
                        generadorDeAutomata.setAutomataFinitoNoDeterministaEpsilon(aux);
                    }catch(NullPointerException npe)
                    {
                        System.out.println("El archivo no contenia un AFNE");
                    }catch(ClassCastException cce)
                    {
                        System.out.println("El archivo no contenia un AFNE");
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
    
    private void subMenuAFP()
    {
        int op;
        do
        {
            System.out.println("Menu Automata Finito a Pila");
            System.out.println("\t1.-Generar Automata Finito a Pila");
            System.out.println("\t2.-Evaluar cadena del automata");
            System.out.println("\t3.-Guardar el AFP en un archivo");
            System.out.println("\t4.-Generar un archivo Latex con el AFP");
            System.out.println("\t5.-Cargar AFP de un archivo");
            System.out.println("\t6.-Salir");
            op = teclado.dameUnInt("Ingresa una opcion");
            switch(op)
            {
                case 1:
                    generadorDeAutomata.crearAutomataFinitoAPila();
                    break;
                case 2:
                    try
                    {
                        if(generadorDeAutomata.getAutomataFinitoAPila() != null)
                        {
                            System.out.println(generadorDeAutomata.getAutomataFinitoAPila().getDescripcion());
                            String cadena = teclado.dameUnString("Ingresa la cadena a evaluar");
                            if(evaluador.evaluarCadena(cadena,generadorDeAutomata.getAutomataFinitoAPila()))
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
                        if(generadorDeAutomata.getAutomataFinitoAPila() != null)
                        {
                            if(generador.escribirObjetoArchivo(generadorDeAutomata.getAutomataFinitoAPila(),teclado))
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
                        if(generadorDeAutomata.getAutomataFinitoAPila() != null)
                        {
                            if(generador.crearArchivoLatex(generadorDeAutomata.getAutomataFinitoAPila(),teclado))
                                System.out.println("El archivo latex fue creado con exito");
                            else
                                System.out.println("Hubo un error al crear el archivo latex");
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
                        generadorDeAutomata.setAutomataFinitoNoDeterminista(aux);
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
        }while(op != 6);
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
