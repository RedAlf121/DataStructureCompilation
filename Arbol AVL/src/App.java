public class App {
    public static void main(String[] args) throws Exception {
        AVL<Integer> avl = new AVL<>();
        avl.add(9);
        avl.add(5);
        avl.add(10);
        avl.add(0);
        avl.add(6);
        avl.add(11);
        avl.add(-1);
        avl.add(1);
        avl.add(2);

        System.out.println("Antes de borrar:");
        avl.preorder();

        avl.eliminar(10);
        System.out.println("Luego de borrar a 10:");
        avl.preorder();
    }
}
