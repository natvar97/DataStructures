public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(8);
        tree.insert(12);
        tree.insert(6);
        tree.insert(9);
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(0);
        System.out.println(tree.min());
    }

    public static int factorial(int value) {
        if (value <= 1) return 1;
        return value * factorial(value - 1);
    }

}