public class GeneradorDeMaquinaDeTuring{
	private MaquinaDeTuring maquina;

	public GeneradorDeMaquinaDeTuring(MaquinaDeTuring maquina){
		this.maquina = maquina;
	}

	public GeneradorDeMaquinaDeTuring(){
		this(null);
	}

	public GeneradorDeMaquinaDeTuring(GeneradorDeMaquinaDeTuring generador){
		this(generador.maquina);
	}

	public void destruir(){
		if(maquina != null) maquina = null;
		System.gc();
	}

	@Override
	public String toString(){
		return "Maquina de Turing: " + maquina;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof GeneradorDeMaquinaDeTuring)) return false;
		GeneradorDeMaquinaDeTuring generador = (GeneradorDeMaquinaDeTuring)obj;
		return maquina.equals(generador.maquina);
	}

}