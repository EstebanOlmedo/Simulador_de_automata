package logica;
public class Delta {
    
   char primero;
   char segundo;
   String tercero;

    public Delta(char primero, char segundo, String tercero) {
        this.primero = primero;
        this.segundo = segundo;
        this.tercero = tercero;
    }
    public Delta(){
	    primero = ' ';
	    segundo = ' ';
	    tercero = null;
    }
    
    @Override
    public String toString()
    {
	    return "(" + primero + "," + segundo + "," + tercero + ")\n";
    }
    @Override
    public boolean equals(Object obj)
    {
	    if(obj == null){return true;}
	    if(!(obj instanceof Delta)){return false;}
	    Delta delta = (Delta)obj;
	    return primero == delta.primero &&
		    segundo == delta.segundo &&
		    tercero.equals(delta.tercero);
    }
    public char getPrimero() {
        return primero;
    }

    public void setPrimero(char primero) {
        this.primero = primero;
    }

    public char getSegundo() {
        return segundo;
    }

    public void setSegundo(char segundo) {
        this.segundo = segundo;
    }

    public String getTercero() {
        return tercero;
    }

    public void setTercero(String tercero) {
        this.tercero = tercero;
    }
    
}
