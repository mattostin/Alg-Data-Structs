/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Spring 2023

Tile: Homework 3 working with Array & Linked Lists 

/*This code implements a class "ArrayList" that implements
the MusicCatalog interface. It contains the some of the methods allow for
adding, removing, and manipulating songs in the music catalog. This also
checks the integrity of the catalog and printing information about the about 
the songs. The class uses an array-based list to store the catalog items and
implements the following methods: add,remove,clear,IsEmpty,count,get,contains,
isIntegrityValid, toString, and publish. Further, the class also defines a private
class CatalogItem that encapsulates a Song object.
 */
/* 
authors: <Matthew Ostin>, Charles Peeke
--------------------------------------------------------------------------*/

public class ArrayList implements MusicCatalog {
    /// For an array-based list, the array itself
    private CatalogItem[] data;
    /// The counter to track the number of elements in the list
    private int count;
    
    /// Parameterless Constructor
    public ArrayList() {
        count = 0;
        data = new CatalogItem[2];
    }
   
    // The add function here adds a new Song to the Catalog
    // the end of the music catalog
    //so increases the music catalog
    public void add(Song song) {
        // If the num of items in the array is equal to it's length,
        //the array is full and needs to be resized
        if (count == data.length) { 
            //create a new array with doubled size of old
            CatalogItem[] newData = new CatalogItem[data.length * 2];
            //Copy contents of old array into new array
            System.arraycopy(data, 0, newData, 0, data.length);
            //Set the data reference to the new array.
            data = newData;
        }
        //Create a new CatalogItem object with specified Song object
        CatalogItem newItem = new CatalogItem(song);
        //Add the new CatalogItem object to data array
        data[count] = newItem;
        count++;
        
    }
    
    

    // The song function remove takes removes one specific song
    // from the list and therefore shortens the list
    // then returns removedSong
    public Song remove() {
        if (count == 0) {
            return null; // empty list if null is returned
        }
        CatalogItem removedItem = data[0];// get first item in list
        Song removedSong = removedItem.getSong();// get song from the first item
        // Shift all remaining items left by one index
        System.arraycopy(data, 1, data, 0, count - 1);//copy all items 
        //after the first item one inedex to the left
        count--;
        return removedSong;

        
    }

    //This function gets rid of all songs
    // then it resets the catalog to empty 
    // then returns the removed song
    public Song remove(Song song) {
        for (int i = 0; i < count; i++) {
            //check if current item matches the song needed to be removed  
            if (data[i].getSong().equals(song)) {
                //if a match is found, remove the item from catalog
                CatalogItem removedItem = data[i];
                Song removedSong = removedItem.getSong();
                // Shift all remaining items left by one index to fill gap left my removed item
                System.arraycopy(data, i + 1, data, i, count - i - 1);
                count--;
                return removedSong;
            }
        }
        //if there is no match then return null
        return null; 
    }
    
    
    //The clear function removes all songs 
    // and resets to an empty state
    public void clear() {
        data = new CatalogItem[2];//creates a new array of CatalogItem objects
        // with a length of 2, overwriting the previous array
        count = 0;//indicated line is empty
    }
    
    //The is Empty function checks to see if there are no songs
    public boolean isEmpty() {
        return count == 0;

    }
    
    //Returns number of songs currently stored
    // in the catalog
    public int count() {
        return count;
    }
    
    //Returns song on a specific index
    // Index < count of catalog
    //null is returned if not
    public Song get(int i) {
        //check if index is out of bounds
        if (i < 0 || i >= count) {
            return null; // index out of bounds
        }
        // Get the CatalogItem object at the specific index
        CatalogItem item = data[i];
        return item.getSong();
    }
       
    

    
    //The function "contains" identifies if a
    // reference to the song exists
    public boolean contains(Song song) {   
        //loop through each catalog item and check for the song
        for (int i = 0; i < count; i++) {
            if (data[i].getSong().equals(song)) {
                //if found return true
                return true;
            }
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
            return false;
        }
        if(data == null) {
            return false;
        }
        if(count > data.length) {
            return false;
        }
        for(int i = 0; i < count; i++) {
            if(data[i] == null) {
                return false;
            }
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "ArrayList::allocated=" + data.length;
        s += ", count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        for(int i = 0; i < count; i++) {
            if(i > 0) {
                s += ", ";
            }
            s += data[i].getSong().getTitle();
            s += " | ";
            s += data[i].getSong().getYear();
        }
        s += "]";
        return s;
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;

        for(int i = 0; i < count; i++) {
            int curYear = data[i].getSong().getYear();
            if (curYear < oldYear) oldYear = curYear;
            if (curYear > newYear) newYear = curYear;
        }
        clear();
        return new int[] { oldYear, newYear };
        
    }

}
