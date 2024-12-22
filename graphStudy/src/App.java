

public class App {
    public static void main(String[] args) throws Exception {
        Grafo<Integer> g = new Grafo<>();
        g.addNodo(1);
        g.addNodo(2);
        g.addNodo(3);
        g.addNodo(4);
        g.addNodo(5);
        g.addNodo(6);

        g.addArista(1, 6, 14);
        g.addArista(1, 3, 9);
        g.addArista(1, 2, 7);
        g.addArista(2, 3, 10);
        g.addArista(2, 4, 15);
        g.addArista(3, 6, 2);
        g.addArista(3, 4, 11);
        g.addArista(6, 5, 9);
        g.addArista(4, 5, 6);


        System.out.println(g.dijkstraQuery(3,5));
        
    }
}
