import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Grafo<T> implements IGrafo<T>
{
    private ArrayList<Nodo<T>> nodos = new ArrayList<>();

    @Override
    public void addNodo(T valor) 
    {
        nodos.add(new Nodo<T>(valor));
        
    }



    @Override
    public void addArista(T nodoInicial, T nodoFinal, int costo) 
    {
        int posNodoInicial = findNodo(nodoInicial);
        int posNodoFinal = findNodo(nodoFinal);

        nodos.get(posNodoInicial).addArista(new Arista<T>(nodoFinal, costo));
        nodos.get(posNodoFinal).addArista(new Arista<T>(nodoInicial, costo));        
    }

    @Override
    public int pesoArista(T nodoInicial, T nodoFinal) 
    {
        int peso = IGrafo.oo;
        int posNodoInicial = findNodo(nodoInicial);
        int posNodoFinal = findNodo(nodoFinal);
        
        Nodo<T> nodo = nodos.get(posNodoInicial);
        ArrayList<Arista<T>> aristas = nodo.getList();
        boolean found = false;

        for(int i = 0;  i < aristas.size() && !found; i++)
        {            
            if(aristas.get(i).getNodo().equals(nodoFinal))
            {
                peso = aristas.get(i).getPeso();
                found = true;
            }
            
        }

        return peso;
    }

    @Override
    public ArrayList<T> adyacentes(T nodoInicial) 
    {
        ArrayList<T> listaAdyacentes = null;
        int posNodoInicial = findNodo(nodoInicial);
        if(posNodoInicial != -1)
        {
            listaAdyacentes = new ArrayList<>();
            Nodo<T> nodo = nodos.get(posNodoInicial);
            ArrayList<Arista<T>> aristasAdyacentes = nodo.getList();
            for(Arista<T> i : aristasAdyacentes)
            {
                listaAdyacentes.add(i.getNodo());
            }
        }
        return listaAdyacentes;
    }

    @Override
    public String show() 
    {
        String s = "";

        for(Nodo<T> i : nodos)
        {            
            for(Arista<T> j : i.getList())
            {
                s+= i.getValor() + "->" + j.getNodo() + " peso: " + j.getPeso() + "\n";
            }
        }

        return s;
    }

    @Override
    public int findNodo(T nodoBuscar) {
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < nodos.size() && !found; i++)
        {
            if(nodos.get(i).getValor().equals(nodoBuscar))
            {
                pos = i;
                found = true;
            }
        }

        return pos;
    }
    
    public ArrayList<Pair<Integer,T>> dijkstra(T a, T b)
    {
        //Peso,Nodo
        ArrayList<Pair<Integer,T>>dist = new ArrayList<Pair<Integer,T>>(nodos.size());
        
        for(int i = 0; i < nodos.size(); i++)
        {            
            dist.add(i, new Pair<Integer,T>(IGrafo.oo, null));
        }

        PriorityQueue<Pair<Integer,T>> queue = new PriorityQueue(new MyComparator());
        //Nodo inicial
        int initialPos = findNodo(a);
        dist.set(initialPos, new Pair<Integer,T>(0, a));
        
        queue.add(new Pair<Integer,T>(0, a));
        //dijkstra  
        T menorMarca = null;      
        while(!queue.isEmpty() && !b.equals(menorMarca))
        {
            //No hace falta buscar la menor marca xq ya se guarda en la cola de prioridad
            menorMarca = queue.peek().getValue();
            int posMenorMarca = findNodo(menorMarca);
            //Revisamos los adyacentes
            for(T i : adyacentes(menorMarca))
            {
                int costo = pesoArista(menorMarca, i);
                int auxPos = findNodo(i);
                if(dist.get(auxPos).getKey()>dist.get(posMenorMarca).getKey()+costo)
                {
                    dist.set(auxPos, new Pair<Integer,T>(costo+dist.get(posMenorMarca).getKey(), menorMarca));
                    queue.add(new Pair<Integer,T>(costo+dist.get(posMenorMarca).getKey(), i));
                }
            }
            queue.poll();
        }
        return dist;
    }

    private void dijkstraPath(T nodoInicial, T nodoFinal, ArrayList<Pair<Integer,T>> dist, Stack<T> path)
    {
        path.add(nodoFinal);
        if(!nodoInicial.equals(nodoFinal))
        {
            int posNodoFinal = findNodo(nodoFinal);
            dijkstraPath(nodoInicial, dist.get(posNodoFinal).getValue(), dist, path);
        }
    }

    public String dijkstraQuery(T a, T b)
    {
        String s = "";

        int posNodoFinal = findNodo(b);

        ArrayList<Pair<Integer,T>> dist = dijkstra(a, b);
        Stack<T> path = new Stack<>();
        dijkstraPath(a, b, dist, path);
        s+="Distancia: " + dist.get(posNodoFinal).getKey() + "\n";
        s+="Camino:\n[";
        
        while(!path.isEmpty())
        {            
            s+=path.pop()+"";
            if(path.size()>0)
            {
                s+=",";
            }
        }
        s+="]";
        return s;
    }



    @Override
    public String toString() {
        return show();
    }

}
