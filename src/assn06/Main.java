package assn06;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post-insertion root that is
        // returned may be different from the original root because of the insertion.
        // result should be about 6.

        for (int i=0; i<50; i++) {
            avl_bst = avl_bst.insert((int) (Math.random()*100));
        }
        System.out.println(avl_bst.height());

        // Now insert 50 integers in increasing order which would
        // cause a simple BST to become very tall but for our
        // self-balancing tree won't be too bad (should be 7)

        for (int i=0; i<50; i++) {
            avl_bst = avl_bst.insert(i);
        }
        System.out.println(avl_bst.height());

        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(20);
        tree.insert(11);
        tree.insert(50);
        tree.insert(4);
        tree.insert(6);
        tree.insert(15);
        tree.insert(3);
        tree.insert(16);
        tree.insert(17);
        tree.insert(2);

        tree.remove(20);
        tree.remove(4);
        tree.remove(3);

        tree.printTree();
    }
}

