/*
Author: Matthew Ostin
This file implements a TreeMap data structure that stores kvps. The TreeMap uses
a balanced binary tree search for efficient storage and retrieval. It supports operations
such as adding mappings, retrieving values by key, and clearing the map. The tree can be 
rebalanced after every insertion for the best performance. The balancing algorithm used in this
implementation focuses on correctness rather than the efficiency.
*/
//----------------------------------------------------------------------------------------------
import java.util.List;       
import java.util.ArrayList;
public class TreeMap implements Map {

   // the Treenode of the Treemap
    private TreeNode root;
    //determines if the tree should be rebalanced after insertion
    private boolean rebalance;

    private static class TreeNode {
        String key;
        String value;
        TreeNode left;
        TreeNode right;
    
        public TreeNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    //-----------------------------------
    /**
     * Constructs a new TreeMap.
     */
    //-----------------------------------
    public TreeMap(boolean rebalance) {
        this.root = null;
        this.rebalance = rebalance;
    }

    
    
    //associated the specified value with the specific key in this treemap
    //if treemap contains mapping for the key, the old value is replaced
    // after setting the value, the method updates root and rebalances if enabled
    public void set(String key, String value, int[] profile) {
        root = set(key, value, root, profile);
        if (rebalance) {
            balance();
        }
    }


    // Recursive helper method for inserting kvps into tree
    private TreeNode set(String key, String value, TreeNode node, int[] profile) {
        if (node == null) {
            return new TreeNode(key, value);
        }

        profile[0]++; // Increment comparison count
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = set(key, value, node.left, profile);
        } else if (cmp > 0) {
            node.right = set(key, value, node.right, profile);
        } else {
            node.value = value;
        }
        return node;
    }

    // returns the value associated with specified key in this TreeMap
    public String get(String key, int[] profile) {
        return get(key, root, profile);
    }

    //recursive helper function for searching
    private String get(String key, TreeNode node, int[] profile) {
        if (node == null) {
            return null;
        }

        profile[0]++; // Increment comparison count
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(key, node.left, profile);
        } else if (cmp > 0) {
            return get(key,node.right, profile);
        }else{
            return node.value;
        }
    }
    /**
 * Removes all the mappings from this map.
 */
public void clear() {
    root = null;
}

//----------------------------------------------------------------------
// Utilities
//----------------------------------------------------------------------

/**
 * Balances the tree to maintain optimal insert and search efficiency.
 * Note: This algorithm focuses on correctness and is not the most efficient algorithm available.
 * Please research different algorithms that solve the balancing problem for improved performance.
 */
private void balance() {
    List<TreeNode> nodes = new ArrayList<TreeNode>();
    // Sorts tree from given root
    populate(root, nodes);

    // Return null if root has no children
    if (nodes.size() == 0) return;

    this.root = balance(nodes, 0, nodes.size() - 1);
}

/**
 * Recursive helper in the balancing operation to support balance.
 *
 * @param nodes a list of nodes
 * @param start the start index within the list
 * @param end   the end index within the list
 * @return the local root after balancing is performed on the subtree
 */
private TreeNode balance(List<TreeNode> nodes, int start, int end) {
    int mid = (start + end) / 2;
    TreeNode node = nodes.get(mid);
    if (start == end) {
        node.left = null;
        node.right = null;
        return node;
    }
    // Recursively balance tree on left and right children using middle node as root
    if (!(mid - 1 < start)) {
        node.left = balance(nodes, start, mid - 1);
    } else {
        node.left = null;
    }

    if (!(mid + 1 > end)) {
        node.right = balance(nodes, mid + 1, end);
    } else {
        node.right = null;
    }

    return node;
}

/**
 * Recursive helper in the balancing operation to put list items into the tree.
 *
 * @param node the root of the subtree to balance
 * @param list the list of nodes to balance
 */
private void populate(TreeNode node, List<TreeNode> list) {
    if (node == null) return;
    populate(node.left, list);
    list.add(node);
    populate(node.right, list);
}

}