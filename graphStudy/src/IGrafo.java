import java.util.ArrayList;

public interface IGrafo<T> 
{
    final int oo = 99999;    
    void addNodo(T nuevoNodo);
    int findNodo(T nodoBuscar);
    void addArista(T nodoInicial, T nodoFinal, int costo);
    int pesoArista(T nodoInicial, T nodoFinal);
    ArrayList<T> adyacentes(T nodoInicial);
    String show();
}
