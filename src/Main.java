import java.sql.SQLOutput;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(8);
        tree.insert(12);
        tree.insert(6);
        tree.insert(9);
        var list = tree.printNodesAtDistance(2);
        System.out.println(tree.isBinarySearchTree());
        tree.traverseLevelOrder();

    }

    public static int factorial(int value) {
        if (value <= 1) return 1;
        return value * factorial(value - 1);
    }

}