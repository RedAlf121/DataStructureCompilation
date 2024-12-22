
public class Arista<T> 
{
    private T nodo;
    private int peso;
    public Arista(T nodo, int peso) {
        setNodo(nodo);
        setPeso(peso);
    }
    public T getNodo() {
        return nodo;
    }
    public void setNodo(T nodo) {
        this.nodo = nodo;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        if(peso>=0)
            this.peso = peso;
    }    

}
