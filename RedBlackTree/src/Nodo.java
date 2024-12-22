public class Nodo<T extends Comparable<T>>
{
    public enum Color
    {
        RED,
        BLACK;
    }
    private Color color;
    
    private T valor;
    private Nodo<T> padre;
    
    public Color getColor() {
        return color;
    }

    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    public Nodo(T valor)
    {
        setValor(valor);
        color = Color.RED;
    }

    public Nodo(T valor, Nodo<T> padre)
    {
        this(valor);
        setPadre(padre);
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
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
    public void setDerecho(Nodo<T> derecho) 
    {
        this.derecho = derecho;
    }

    public boolean isIzquierdo() {
        return this == padre.izquierdo;
    }

    public void cambioColor()
    {
        if(color == Color.BLACK)
            color = Color.RED;
        else
        color = Color.BLACK;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }


}