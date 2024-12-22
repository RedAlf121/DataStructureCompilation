public class App {
    public static void main(String[] args) throws Exception {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.add(1);
        rbt.add(2);
        rbt.add(3);
        rbt.add(4);
        rbt.add(5);
        rbt.add(6);
        rbt.add(7);
        rbt.preorder();
        System.out.println();
        System.out.println();
        rbt.posorder();
    }
}
