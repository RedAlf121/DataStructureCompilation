public class AVL<T extends Comparable<T>>
{
    private Nodo<T> root;

    /** INSERTAR **/
    public void add(T valor)
    {
        if(root == null)
            root = new Nodo<T>(valor);
        else root = add(valor, root);
    }

    private Nodo<T> add(T valor, Nodo<T> root)
    {        
        if(root != null)
        {
            int compareTo = valor.compareTo(root.getValor());
            //Insercion
            if(compareTo < 0)
                root.setIzquierdo(add(valor, root.getIzquierdo()));            
            else if(compareTo > 0)
                root.setDerecho(add(valor, root.getDerecho()));
            
            //Balance
            int factorEquilibrio = factorEquilibrio(root);
            
            if(factorEquilibrio > 1)
            {
                Nodo<T> derecho = root.getDerecho();
                if(derecho != null && valor.compareTo(derecho.getValor()) < 0)
                    root.setDerecho(rotacionDerecha(derecho));
                root = rotacionIzquierda(root);
            }
            else if(factorEquilibrio < -1)
            {
                Nodo<T> izquierdo = root.getIzquierdo();
                if(izquierdo != null && valor.compareTo(izquierdo.getValor()) > 0)
                    root.setIzquierdo(rotacionIzquierda(izquierdo));
                root = rotacionDerecha(root);
            }

        }
        else
            root = new Nodo<T>(valor);

        return root;
    }

    /** RECORRIDOS **/
    public void inorder()
    {
        inorder(root);
    }

    public void inorder(Nodo<T> root)
    {
        if(root == null)
            return;
        else
        {
            inorder(root.getIzquierdo());
            System.out.println(root.getValor());
            inorder(root.getDerecho());
        }
    }

    public void preorder()
    {
        preorder(root);
    }

    private void preorder(Nodo<T> root) 
    {
        if(root == null)
            return;
        System.out.println(root.getValor());
        preorder(root.getIzquierdo());
        preorder(root.getDerecho());
    }

    public void posorder()
    {
        posorder(root);
    }

    private void posorder(Nodo<T> root) 
    {
        if(root == null)
            return;
        posorder(root.getIzquierdo());
        posorder(root.getDerecho());
        System.out.println(root.getValor());
    }
    /** ELIMINAR **/
    public Nodo<T> eliminar(T valor)
    {
        return root = eliminar(valor, root);
    }

    private Nodo<T> eliminar(T valor, Nodo<T> root) {
        boolean deleted = false;
        if(root != null)
        {            
            if(valor.compareTo(root.getValor()) == 0)
            {
                switch(root.cantHijos())
                {
                    case 0: root = null; deleted = true; break;
                    case 1: if(root.getIzquierdo() != null) root = root.getIzquierdo();
                            else root = root.getDerecho();
                            deleted = true; 
                            break;
                    case 2: Nodo<T> subArbolDerecho = root.getDerecho();
                            Nodo<T> reemplazo = buscarSustituto(subArbolDerecho);
                            reemplazo.setIzquierdo(root.getIzquierdo());
                            root = reemplazo;
                            deleted = true; 
                            break;
                }
            }
            if(!deleted)
            {
                if(valor.compareTo(root.getValor()) < 0)
                    root.setIzquierdo(eliminar(valor,root.getIzquierdo()));
                else if(valor.compareTo(root.getValor()) > 0)
                    root.setDerecho(eliminar(valor,root.getDerecho()));
                
                    int factorEquilibrio = factorEquilibrio(root);
            
                    if(factorEquilibrio > 1)
                    {
                        Nodo<T> derecho = root.getDerecho();
                        int factorEquilibrioDerecho = factorEquilibrio(derecho);
                        if(factorEquilibrioDerecho < 0)
                            root.setDerecho(rotacionDerecha(derecho));
                        root = rotacionIzquierda(root);
                    }
                    else if(factorEquilibrio < -1)
                    {
                        Nodo<T> izquierdo = root.getIzquierdo();
                        int factorEquilibrioIzquierda = factorEquilibrio(izquierdo);
                        if(factorEquilibrioIzquierda > 0)
                            root.setIzquierdo(rotacionIzquierda(izquierdo));
                        root = rotacionDerecha(root);
                    }
        
            }
        }
        
        return root;
    }

    private Nodo<T> buscarSustituto(Nodo<T> subArbolDerecho) {
        Nodo<T> sustituto = subArbolDerecho;
        Nodo<T> iter = subArbolDerecho.getIzquierdo();

        while(iter != null)
        {
            sustituto = iter;
            iter = iter.getIzquierdo();
        }

        return sustituto;
    }

    /** ROTACIONES **/
    private Nodo<T> rotacionIzquierda(Nodo<T> root)
    {
        Nodo<T> derecho = root.getDerecho();
        Nodo<T> aux = derecho.getIzquierdo();

        derecho.setIzquierdo(root);
        root.setDerecho(aux);

        return derecho;
    }

    private Nodo<T> rotacionDerecha(Nodo<T> root)
    {
        Nodo<T> izquierdo = root.getIzquierdo();
        Nodo<T> aux = izquierdo.getDerecho();

        izquierdo.setDerecho(root);
        root.setIzquierdo(aux);

        return izquierdo;
    }

    /** Calcular la Altura **/

    private int calcularAltura(Nodo<T> root)
    {
        int altura = 0;
        if(root != null)
        {
            int izquierdo = calcularAltura(root.getIzquierdo());
            int derecho = calcularAltura(root.getDerecho());
            altura = 1 + Math.max(izquierdo, derecho);
        }
        
        return altura;
    }

    private int factorEquilibrio(Nodo<T> root)
    {
        int factor = 0;

        if(root != null)
            factor = calcularAltura(root.getDerecho())-calcularAltura(root.getIzquierdo());

        return factor;
    }

    public Nodo<T> getRoot() {
        return root;
    }

    public void setRoot(Nodo<T> root) {
        this.root = root;
    }

}
