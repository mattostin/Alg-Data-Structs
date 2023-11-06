/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Spring 2023


Tile: Homework 3 working with array & linked lists 

// This file focuses on linked lists, again using functions like
//// within this file such as add, remove, clear, emptying, 
//count, and get.
authors: <Matthew Ostin>, Charles Peeke
--------------------------------------------------------------------------*/
import java.util.List;
public class LinkedList implements MusicCatalog {

    /// For a linked-list based list, the head pointer
    private CatalogItem head;
    /// The counter to track the number of elements in the list 
    private int count;
    private List<Song> songList;
   
    public LinkedList() {
        count = 0;
        head = null;
    }
   
    /*It initializes the count vairable and sets it to zero
    then sets the head point to null. The purpose of this constructor
    is to create a new and empty linked list
    */
    public void add(Song song) {
        // Create a new CatalogITem with given song
        CatalogItem item = new CatalogItem(song);
        //if catalog empty, set the new item ass the head
        if (head == null) {
            head = item;
        } else {
            //otherwise go to end of the list and add there
            CatalogItem it = head;
            while (it.next != null) {
                it = it.next;
            }
          it.next = item;
        }
        count++;
        
    }
        

    
    // Removes the first song from the catalog and returns it 
    //if catalog is empty then it returns null
    public Song remove() {
        //Starts by checking if the catalog is empty
        if (head == null) {
            return null;
        }
        //get song from the head CatalogItem
        Song song = head.getSong();
        //update the head to point to the next item in list
        head = head.next;
        count--;
     
        return song;
    }
    //Removes the given song from the catalog and returns it
    //if song is not found in catalog, return null
    public Song remove(Song song) {
        // Check if catalog is empty
        if (head == null) {
            return null;
        }
        //check if the song is at the end of the lsit
        if (head.getSong().equals(song)) {
            Song removed = head.getSong();
            head = head.next;
            count--;
            songList.remove(song);
            return removed;
        }

        CatalogItem it = head;
        while (it.next != null && !it.next.getSong().equals(song)) {
            it = it.next;
        }
        //if the song is found, remove and return it
        if (it.next != null) {
            Song removed = it.next.getSong();
            it.next=(it.next.next);
            count--;
           
            return removed;
        }

        return null;
    }
    
   
    
    //Clears catalog by setting the head to null
    //then resets the count to zero
    public void clear() {
        //set the head to null to clear list
        head = null;
        //reset the count to zero to reflect empty list
        count = 0;
        
    }
    
      
    
    
    //Returns true if the catalog is empty
    //@return True if the catalog is empty, otherwise false
    public boolean isEmpty() {
        return count == 0;

    }
    
    //Returns the number of songs in the catalog
    public int count() {
        return count;
    }
    
    // Returns the song at the specified index in the catalog
    //otherwise null if the index is out of range
    public Song get(int i) {
        // Check if index is within bounds
        if (i >= 0 && i < count) {
            //Goes through list to find the song at the specified index
            CatalogItem item = head;
            for (int j = 0; j < i; j++) {
                item = item.next;
            }
            return item.getSong();
        } else {
            return null;
        }
    }
    

    //Returns true if the catalog contains the specific song, false otherwise
    //@param song The song object to search for in the catalog.
    //@return True if catalog contains the song, false otherwise
    public boolean contains(Song song) {
        //Goes through list to find the specific song
        CatalogItem it = head;
        while (it != null) {
            if (it.getSong().equals(song)) {
                //if song is found return true
                return true;
            }
            it = it.next;
        }
        //not found return false
        return false;
    }
    

    //--------------------------------------------------------------------
    // Utilities
    //--------------------------------------------------------------------

    /// Returns a truth value indicating whether the catalog's structural
    /// integrity remains valid.  If the integrity is no longer valid,
    /// then the catalog should be invalidated and usage should not be trusted
    /// @return true if the catalog integrity is valid; otherwise, false
    public boolean isIntegrityValid() {
        if(count < 0) {
            System.out.println("1");
            return false;
        }
        if(count == 0 && head == null) {
            return true;
        }
        if(count == 1 && head != null && head.next == null) {
            return true;
        }

        int n = 1;
        CatalogItem it = head;
        while(it.next != null) {
            it = it.next;
            n++;
        }

        if(n != count) {
            System.out.println("2");
            return false;
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "LinkedList::count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        CatalogItem it = head;
        while(it != null) {
            if(it != head) {
                s += ", ";
            }
            s += it.getSong().getTitle();
            s += " | ";
            s += it.getSong().getYear();
            it = it.next;
        }
        s += "]";

        return s; 
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int[] years = new int[2];
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;

        CatalogItem it = head;
        while(it != null) {
            int curYear = it.getSong().getYear();
            if (curYear < oldYear) {
                oldYear = curYear;
                years[0] = oldYear;
            }
            if (curYear > newYear) {
                newYear = curYear;
                years[1] = newYear;
            }
            it = it.next;
        }
        clear();
        return years;
    }

}
