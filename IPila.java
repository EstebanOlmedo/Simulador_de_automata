/**
 * @author Esteban Olmedo Ramírez
 */
import java.util.Stack;
public interface IPila
{
	public char pop();
	public void push(String caracter);
	public boolean isEmpty();
	public char peek();
        public char pop(Stack <Character> pila);
        public void push(String caracter,Stack <Character> pila);
        public boolean isEmpty(Stack <Character> pila);
        public char peek(Stack <Character> pila);
        
}

