package vista;
import control.ControlDePeticion;
public class Menu
{
	private Teclado teclado;
	private ControlDePeticion control;
	public Menu()
	{
		teclado = new Teclado();
		control = new ControlDePeticion();
	}
	public Menu(Teclado teclado, ControlDePeticion control)
	{
		this.teclado = teclado;
		this.control = control;
	}
	public Menu(Menu menu)
	{
		this(menu.teclado, menu.control);
	}

	@Override
	public String toString()
	{
		return "MENU";
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof Menu)){return false;}
		Menu menu = (Menu)obj;
		return teclado.equals(menu.teclado);
	}

	public void mostrarMenu()
	{
		System.out.println("Menu");
		System.out.println("\t1.-Automata Finito Determinista");
		System.out.println("\t2.-Automata Finito No Determinista");
		System.out.println("\t3.-Automata Finito No Determinista Epsilon");
		System.out.println("\t4.-Automata Finito A Pila");
		System.out.println("\t5.-Maquina de Turing");
          	System.out.println("\t6.-Salir");
	}
	public void mostrarSubMenuAFD()
	{
		System.out.println("Menu AFD");
		System.out.println("\t1.-Generar Automata Finito Determinista");
		System.out.println("\t2.-Evaluar cadena del automata");
		System.out.println("\t3.-Convertir AFD a AFP");
		System.out.println("\t4.-Guardar el Automata Finito Determinista en un archivo");
		System.out.println("\t5.-Cargar AFD de un archivo");
		System.out.println("\t6.-Salir");
	}
	public void mostrarSubMenuAFND()
	{
		System.out.println("Menu AFND");
		System.out.println("\t1.-Generar Automata Finito No Determinista");
		System.out.println("\t2.-Evaluar cadena del automata");
		System.out.println("\t3.-Convertir AFND a AFD");
		System.out.println("\t4.-Guardar el Automata Finito No Determinista en un archivo");
		System.out.println("\t5.-Cargar AFND de un archivo");
		System.out.println("\t6.-Salir");
	}
	public void mostrarSubMenuAFNDE()
	{
		System.out.println("Menu AFND");
		System.out.println("\t1.-Generar Automata Finito No Determinista");
		System.out.println("\t2.-Evaluar cadena del automata");
		System.out.println("\t3.-Guardar el Automata Finito No Determinista Epsilon en un archivo");
		System.out.println("\t4.-Cargar AFNDE de un archivo");
		System.out.println("\t5.-Salir");
	}
	public void mostrarSubMenuAFP()
	{
		System.out.println("Menu AFP");
		System.out.println("\t1.-Generar Automata Finito a Pila");
		System.out.println("\t2.-Evaluar cadena del automata");
		System.out.println("\t3.-Guardar el Automata Finito a Pila en un archivo");
		System.out.println("\t4.-Cargar AFP de un archivo");
		System.out.println("\t5.-Salir");
	}
	public void elegirOpcion()
	{
		int opcion;
		do
		{
			mostrarMenu();
			opcion = teclado.dameUnInt("Ingresa la opción que deseas");
			switch(opcion)
			{
				case 1: elegirOpcionAFD(); break;
				case 2: elegirOpcionAFND(); break;
				case 3: elegirOpcionAFNDE(); break;
				case 4: elegirOpcionAFP(); break;
				case 5: elegirOpcionMT(); break;
				case 6: System.out.println("Hasta la próxima :)"); break;
				default: System.out.println("Ingresa una correcta"); break;
			}
		}while(opcion != 6);
	}
	public void elegirOpcionAFD()
	{
		int opcion;
		do
		{
			mostrarSubMenuAFD();
			opcion = teclado.dameUnInt("Ingresa la opción que deseas");
			switch(opcion)
			{
				case 1: control.manejarPeticion("GAFD"); break;
				case 2: control.manejarPeticion("EAFD"); break;
				case 3: control.manejarPeticion("EAFD"); break;
				case 4: control.manejarPeticion("PAFD"); break;
				case 5: control.manejarPeticion("CAFD"); break;
				case 6: break;
				default: System.out.println("Ingresa una correcta"); break;
			}
		}while(opcion != 6);
	}
	public void elegirOpcionAFND()
	{
		int opcion;
		do
		{
			mostrarSubMenuAFND();
			opcion = teclado.dameUnInt("Ingresa la opción que deseas");
			switch(opcion)
			{
				case 1: control.manejarPeticion("GAFND"); break;
				case 2: control.manejarPeticion("EAFND"); break;
				case 3: control.manejarPeticion("EAFND"); break;
				case 4: control.manejarPeticion("PAFND"); break;
				case 5: control.manejarPeticion("CAFND"); break;
				case 6: break;
				default: System.out.println("Ingresa una correcta"); break;
			}
		}while(opcion != 6);
	}
	public void elegirOpcionAFNDE()
	{
		int opcion;
		do
		{
			mostrarSubMenuAFNDE();
			opcion = teclado.dameUnInt("Ingresa la opción que deseas");
			switch(opcion)
			{
				case 1: control.manejarPeticion("GAFNDE"); break;
				case 2: control.manejarPeticion("EAFNDE"); break;
				case 3: control.manejarPeticion("PAFNDE"); break;
				case 4: control.manejarPeticion("CAFNDE"); break;
				case 5: break;
				default: System.out.println("Ingresa una correcta"); break;
			}
		}while(opcion != 5);
	}
	public void elegirOpcionAFP()
	{
		int opcion;
		do
		{
			mostrarSubMenuAFP();
			opcion = teclado.dameUnInt("Ingresa la opción que deseas");
			switch(opcion)
			{
				case 1: control.manejarPeticion("GAFP"); break;
				case 2: control.manejarPeticion("EAFP"); break;
				case 3: control.manejarPeticion("PAFP"); break;
				case 4: control.manejarPeticion("CAFP"); break;
				case 5: break;
				default: System.out.println("Ingresa una correcta"); break;
			}
		}while(opcion != 5);
	}
	public void elegirOpcionMT()
	{
		int opcion;
		do
		{
			mostrarSubMenuAFP();
			opcion = teclado.dameUnInt("Ingresa la opción que deseas");
			switch(opcion)
			{
				case 1: control.manejarPeticion("GAFD"); break;
				case 2: control.manejarPeticion("GAFD"); break;
				case 3: control.manejarPeticion("GAFD"); break;
				case 4: control.manejarPeticion("GAFD"); break;
				case 5: break;
				default: System.out.println("Ingresa una correcta"); break;
			}
		}while(opcion != 5);
	}
}
