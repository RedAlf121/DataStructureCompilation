public class Nodo<T extends Comparable<T>>
{
    private T valor;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;
    
    
    public Nodo(T valor, Nodo<T> izquierdo, Nodo<T> derecho)
    {
        setValor(valor);
        setIzquierdo(izquierdo);
        setDerecho(derecho);
    }
    
    public Nodo(T valor) 
    {
        this(valor,null,null);
    }
    
    public T getValor() {
        return valor;
    }
    public void setValor(T valor) {
        this.valor = valor;
    }
    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }
    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }
    public Nodo<T> getDerecho() {
        return derecho;
    }
    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }

    public int cantHijos()
    {
        int cant = 0;

        if(izquierdo != null) cant++;
        if(derecho != null) cant++;

        return cant;
    }
}
