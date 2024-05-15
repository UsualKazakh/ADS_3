import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();

        bst.put(10, "Value 10");
        bst.put(5, "Value 5");
        bst.put(15, "Value 15");
        bst.put(3, "Value 3");
        bst.put(7, "Value 7");

        System.out.println("Value associated with key 3: " + bst.get(3));
        bst.delete(3);
        System.out.println("Value associated with key 3 after deletion: " + bst.get(3));

        System.out.println("Minimum key in the BST: " + bst.minKey());
        System.out.println("Maximum key in the BST: " + bst.maxKey());
        System.out.println("Height of the BST: " + bst.height());

        bst.balance();

        MyHashTable<MyTestingClass, String> map = new MyHashTable<>();

        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(1000);
            MyTestingClass object = new MyTestingClass(id);
            map.put(object, "Value " + i);
        }

        map.printBucketSizes();
    }
}