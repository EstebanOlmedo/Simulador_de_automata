//package Simulador_de_automata;
public class Delta {
    
   char primero;
   char segundo;
   String tercero;

    public Delta(char primero, char segundo, String tercero) {
        this.primero = primero;
        this.segundo = segundo;
        this.tercero = tercero;
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
