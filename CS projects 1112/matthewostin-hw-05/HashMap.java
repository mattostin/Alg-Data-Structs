/** Author: Matthew Ostin
 * This file implements the hash map data structure that uses a hash table
 * for storage and retrieval of kvps. It has operations for adding,
 * retrieving, and removeing mappings as well as clearing the entire map
 * The calss also usesa has function and a linked list approach to deal with
 * collisions and makes sure there is afficient access to the already stored data
 /**///---------------------------------------------------------------------------
public class HashMap implements Map {

    /**
     * Uses an array of ListNode, each Listnode is a bucket in the HashMap
     */
    private final ListNode[] buckets;

    /**
     *Makes another new HashMap with a specified number of buckets
     *
     * @param length the number of buckets in the HashMap
     */
    public HashMap(int length) {
        buckets = new ListNode[length];
    }
    //--------------------------------------------------------------
    //The method set, associated the specified value with the specified key
    //It calculated index for the key using hash function.
    //then traverses linked list in the corresponding bucket incrementing comparison count
    //in the profile array for each comparison. If key already exists the value is updated.
    //if key not found a new ListNode is created
    //--------------------------------------------------------------
    public void set(String key, String value, int[] profile) {
        int index = hash(key);
        ListNode current = buckets[index];

        while (current != null) {
            profile[0]++; // Increment the comparison count
            if (current.key.equals(key)) {
                current.value = value; // Updates that value
                return;
            }
            current = current.next;
        }

        // If key not found, create a new ListNode and add it to the bucket
        ListNode newNode = new ListNode(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }
    //----------------------------------------------------
    //Retrieves value associated with the specified key from hashmap, it then does the following
    //1. calculated the index for key using hash function
    //2. traverses linked list in corresponding bucket incrementing comparison count
    //3.If they key is found, it returns the value
    //4. if key not found it returns null
    //----------------------------------------------------
    public String get(String key, int[] profile) {
        int index = hash(key);
        ListNode current = buckets[index];

        while (current != null) {
            profile[0]++; // Increment comparison count
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        // Key not found
        return null;
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
    }

    //----------------------------------------------------------------------
    // Utilities
    //----------------------------------------------------------------------
    /**
     * Hash function that returns the index location of where an object should be put in the table.
     *
     * @param key the input string to be hashed
     * @return the index location of where an object should be put in the table
     */
    private int hash(String key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    /**
     * ListNode class to represent the elements in the HashMap's bucket.
     */
    private static class ListNode {
        String key;
        String value;
        ListNode next;

        ListNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
