package tree;

public class Main {
    public static void main(String [] args) {
        AVLTree t = new AVLTree();
        t.add(23);
        t.add(3242);
        t.add(32);
        t.add(23);
        SomeLogger.log(t.toString());
        t.delete(24);
        SomeLogger.log(t.toString());
    }
}
