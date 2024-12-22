public class RedBlackTree<T extends Comparable<T>>
{
    private Nodo<T> root;

    public void add(T valor)
    {    
        Nodo<T> nodo = new Nodo<T>(valor);    
        root = add(nodo,root);
        recolorAndRotate(nodo);
    }
    
    
    private Nodo<T> add(Nodo<T> nodo, Nodo<T> root) 
    {
        if(root != null)
        {
            int compareTo = nodo.getValor().compareTo(root.getValor());
            if(compareTo < 0)
            {
                root.setIzquierdo(add(nodo, root.getIzquierdo()));
                root.getIzquierdo().setPadre(root);
            }
            else if(compareTo > 0)
            {
                root.setDerecho(add(nodo, root.getDerecho()));
                root.getDerecho().setPadre(root);       
            }
            
        }
        else
            root = nodo;

        return root;
    }
    
    private void recolorAndRotate(Nodo<T> nodo) 
    {
        Nodo<T> padre = nodo.getPadre();
        if(padre != null && padre.getColor() == Nodo.Color.RED)
        {
            Nodo<T> abuelo = padre.getPadre();
            Nodo<T> tio = nodo.isIzquierdo()? abuelo.getDerecho() : abuelo.getIzquierdo();
            if(tio != null && tio.getColor() == Nodo.Color.RED)
            {
                padre.cambioColor();
                tio.cambioColor();
                abuelo.cambioColor();
                recolorAndRotate(abuelo);
            }
            else if(padre.isIzquierdo())
            {
                if(!nodo.isIzquierdo())
                    rotarIzquierda(padre);
                padre.cambioColor();
                abuelo.cambioColor();
                rotarDerecha(abuelo);
                recolorAndRotate(nodo.isIzquierdo()? padre : abuelo);
            }
            else
            {
                if(nodo.isIzquierdo())
                    rotarDerecha(padre);
                padre.cambioColor();
                abuelo.cambioColor();
                rotarIzquierda(abuelo);
                recolorAndRotate(nodo.isIzquierdo()? abuelo : padre);
            }
        }
        this.root.setColor(Nodo.Color.BLACK);
    }

    private void rotarIzquierda(Nodo<T> nodo) 
    {
        Nodo<T> derecho = nodo.getDerecho();
        nodo.setDerecho(derecho.getIzquierdo());
        if(nodo.getDerecho() != null)
        {
            nodo.getDerecho().setPadre(nodo);
        }
        derecho.setIzquierdo(nodo);
        derecho.setPadre(nodo.getPadre());
        actualizarRaiz(nodo, derecho);
        nodo.setPadre(derecho);
    }


    private void rotarDerecha(Nodo<T> nodo) {
        Nodo<T> izquierdo = nodo.getIzquierdo();
        nodo.setIzquierdo(izquierdo.getDerecho());
        if(nodo.getIzquierdo() != null)
        {
            nodo.getIzquierdo().setPadre(nodo);            
        }
        izquierdo.setDerecho(nodo);
        izquierdo.setPadre(nodo.getPadre());
        actualizarRaiz(nodo,izquierdo);
        nodo.setPadre(izquierdo);
    }


    private void actualizarRaiz(Nodo<T> nodo, Nodo<T> auxiliarNodo) {
        if(nodo.getPadre() == null)
            this.root = auxiliarNodo;
        else if(nodo.isIzquierdo())
            nodo.getPadre().setIzquierdo(auxiliarNodo);
        else
            nodo.getPadre().setDerecho(auxiliarNodo);
    }


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
    

}