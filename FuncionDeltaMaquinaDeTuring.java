public class FuncionDeltaMaquinaDeTuring
{
	private int estado;
	private char loQueDeja;
	private char movimiento;

	public FuncionDeltaMaquinaDeTuring(
			int estado,
			char loQueDeja,
			char movimiento)
	{
		this.estado = estado;
		this.loQueDeja = loQueDeja;
		this.movimiento = movimiento;
	}
	public FuncionDeltaMaquinaDeTuring()
	{
		estado = -1;
		loQueDeja = '-';
		movimiento = '-';
	}
	public FuncionDeltaMaquinaDeTuring(
			FuncionDeltaMaquinaDeTuring funcion)
	{
		this(funcion.estado, funcion.loQueDeja, funcion.movimiento);
	}
	public void destruir()
	{
		System.out.println("Destruyendo función delta");
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null){return false;}
		if(!(obj instanceof FuncionDeltaMaquinaDeTuring))
			return false;
		FuncionDeltaMaquinaDeTuring funcion = (FuncionDeltaMaquinaDeTuring)obj;
		return estado == funcion.estado && loQueDeja == funcion.loQueDeja &&
			movimiento==funcion.movimiento;
	}
	@Override
	public String toString()
	{
		return "(" + estado + "," + loQueDeja + "," + movimiento + ")";
	}
	public int getEstado()
	{
		return estado;
	}
	public char getMovimiento()
	{
		return movimiento;
	}
	public char getLoQueDeja()
	{
		return loQueDeja;
	}
	public boolean hayCamino()
	{
		if(estado == -1)
			return false;
		else
			return true;
	}
}
