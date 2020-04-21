package tree;

public class AVLTree {

    private class Node {
        int value;
        Node left;
        Node right;
        byte height;

        Node(int value) {
            this.value = value;
            height = 1;
        }
    }

    private Node root;

    private StringBuilder treeAsString;

    public void add(int value) {
        root = add(root, value);
    }



    /**
     *
     * Adds a new node after its parent
     *
     * */
    private Node add(Node parent, int value) {
        if (parent == null)
            return new Node(value);
        if (value < parent.value)
            parent.left = add(parent.left, value);
        if (value > parent.value)
            parent.right = add(parent.right, value);
        return rebalance(parent);
    }



    /**
     *
     * Find the height of the tree with root in "node"
     *
     * */
    private byte height(Node node) {
        byte h1 = 0;
        byte h2 = 0;
        if (node == null)
            return 0;
        if (node.left != null)
            h1 = node.left.height;
        if (node.right != null)
            h2 = node.right.height;
        return (byte) (h1 > h2 ? h1 + 1 : h2 + 1);
    }


    /**
     *
     * Corrects the balance of a tree
     *
     * */
    private Node rebalance(Node node) {
        node.height = height(node);
        byte balance = balance(node);
        /**
         *
         * if right subtree is bigger than left
         *
         * */
        if (balance == -2) {
            /**
             *
             *
             *          *
             *        /  \
             *       *    *
             *          /  \
             *         *    *
             *        /
             *       *
             *
             * */
            if (balance(node.left) > 0)
                node.left = rotateRight(node.left);
            return rotateLeft(node);
        }
        /**
         *
         * if left subtree is bigger than right
         *
         * */
        if (balance == 2) {


            /**
             *
             *
             *                 *
             *               /  \
             *             *     *
             *           /   \
             *          *     *
             *                 \
             *                  *
             *
             * */

            if (balance(node.right) < 0)
                node.right = rotateLeft(node.right);
            return rotateRight(node);
        }
        return node;
    }


    /**
     *
     * Calculates the balance of a node
     *
     * */
    private byte balance(Node node) {
        if (node == null)
            return 0;
        else {
            byte left = 0;
            byte right = 0;
            if (node.left != null)
                left = node.left.height;
            if (node.right != null)
                right = node.right.height;
            return (byte) (left - right);
        }
    }


    /**
     * S I N G L E        R I G H T       R O T A T E
     * ______________________________________________
     *        input:
     *              ___
     *            /    \
     *                \|/
     *                 '
     *      axis -> $
     *             / \
     * newRoot -> #   *
     *          /  \
     *         *    @
     *        /
     *       *
     *________________________________________________
     *       result:
     *
     *
     *
     *   newRoot ->  #
     *             /  \
     *            *    $ <<-- axis
     *           /    / \
     *          *    @   *

     *_________________________________________________
     *
     * */
    private Node rotateRight(Node axis) {
        Node newRoot = axis.left;
        axis.left = newRoot.right;
        newRoot.right = axis;
        axis.height = height(axis);
        newRoot.height = height(newRoot);
        return newRoot;
    }


    private Node rotateLeft(Node axis) {
        Node newRoot = axis.right;
        axis.right = newRoot.left;
        newRoot.left = axis;
        axis.height = height(axis);
        newRoot.height = height(newRoot);
        return newRoot;
    }


    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node parent, int value) {
        if (parent == null)
            return null;
        if (value < parent.value)
            parent.left = delete(parent.left, value);
        else if (value > parent.value)
            parent.right = delete(parent.right, value);
        else {
            if (parent.right == null)
                return parent.left;
            Node minLeft = parent.right;
            while (minLeft.left != null)
                minLeft = minLeft.left;
            minLeft.right = deleteMin(minLeft);
            minLeft.left = parent.left;
            return rebalance(minLeft);
        }
        return rebalance(parent);
    }


    private Node deleteMin(Node parent) {
        if (parent.left == null)
            return parent.right;
        parent.left = deleteMin(parent.left);
        return rebalance(parent);
    }

    @Override
    public String toString() {
        treeAsString = new StringBuilder();
        Node tmp = root;
        preOrder(tmp);
        String tree = treeAsString.toString().trim();
        treeAsString = null;
        return tree;
    }


    private void preOrder(Node node) {
        if (node == null)
            return;
        treeAsString.append(node.value + " ");
        if (node.left != null)
            preOrder(node.left);
        if (node.right != null)
            preOrder(node.right);
    }



}
