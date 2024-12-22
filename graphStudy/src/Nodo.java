import java.util.ArrayList;

public class Nodo<T> 
{
    
    private T valor;

    private ArrayList<Arista<T>> list = new ArrayList<>();
    


    public Nodo(T valor) 
    {
        setValor(valor);
    }

    public void addArista(Arista<T> e)
    {
        list.add(e);
    }

    public int peso(int i)
    {
        return list.get(i).getPeso();
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    
    public ArrayList<Arista<T>> getList() {
        return list;
    }

}
