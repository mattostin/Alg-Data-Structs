/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2023
author: <Matthew Ostin>

This class encapsulates the logic to model scheduling a set of classes for a university
--------------------------------------------------------------------------*/

public class ClassSchedule {
    /// Performs a deep copy of the input schedule and returns the deep copy.
    /// This operation might be used to make a permanent backup of the data
    /// as it would make a unique and unlinked copy of the data.
    /// @param schedule the schedule array to copy
    /// @return the deep copy of the schedule array that were copied
    public static String[][] clone(String[][] schedule) {
        //TODO: FILL IN YOUR CODE HERE
        String[][] schedcopy = new String[schedule.length][];
        for (int i = 0; i < schedule.length; i++) {
            schedcopy[i] = new String[schedule[i].length]; // initialize the array first
            for (int l = 0; l < schedule[i].length; l++) {
                schedcopy[i][l] = schedule[i][l];
            }
        }
        return schedcopy;
    }

    /// A referential copy (shallow copy of each row) and not an 
    /// element-wise copy (deep copy).  We are sorting elements with respect
    /// to the original data rather than generating a new set of data.
    /// @param schedule data containing the rows to reference
    /// @return an array containing a shallow copy of the input schedule 
    ///         rows
    public static String[][] createView(String[][] schedule) {
        // TODO : Implement Here
        String [][] copy = new String [schedule.length][];
        for( int i =0; i<schedule.length; i++){
            copy[i] = schedule[i];
        }

        return copy;
    }
 
 
    //--------------------------------------------------------------------- 
    /// Compute the differential between start time (index 3) and end time 
    /// (index 4). The differential is not maintained in the data but is
    /// a virtual field derived by the calculation performed here
    /// @param classInfo a record from the scheduling data
    /// @return the difference in time between the end time and start time
    ///         in minutes
    public static int differential(String[] classInfo) {
        // TODO : Implement Here
        String startTime = classInfo[3];
        String endTime = classInfo[4];
    
        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMinute = Integer.parseInt(startTime.substring(3, 5));
    
        int endHour = Integer.parseInt(endTime.substring(0, 2));
        int endMinute = Integer.parseInt(endTime.substring(3, 5));
    
        int totalStartMinutes = startHour * 60 + startMinute;
        int totalEndMinutes = endHour * 60 + endMinute;
    
        return totalEndMinutes - totalStartMinutes;
        
    }

    //--------------------------------------------------------------------- 
    /// This utility function converts a time string from the "HH:mm:ss" 
    /// format into a value representing minutes
    /// @param time a string representing a time in "HH"mm:ss" format
    /// @return an integer representing the time converted to minutes
    private static int duration(String time) {
        String[] tokens = time.split(":");
        int h = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int t = h * 60 + m;
        return t;
    }

    //--------------------------------------------------------------------- 
    /// Performs a comparison between two classes that is equivalent to a 
    /// less than operation so that a sort can use this function to order 
    /// classes. The less than criteria is an evaluation between the 
    /// differentials of two classes.
    /// @param class1 a class record that is used as the "left" operand for
    ///        a less than comparison 
    /// @param class2 a class record that is used as the "right" operand for
    ///        a less than comparison 
    /// @return returns true if the computed differential for class1 is less
    ///         than the computed differential for class2; otherwise, 
    ///         returns false (false implies that differential for class1 is
    ///         greater than or equal to class2)
    public static boolean lessThan(String[] class1, String[] class2) {
        // TODO : Implement Here
        int diff1 = differential(class1);
    int diff2 = differential(class2);
    return diff1 < diff2;
}
    //--------------------------------------------------------------------- 
    /// Swaps references to classes.  Note that this is a "shallow" swap and
    /// not a "deep" swap meaning we swap at a reference level (between rows
    /// in view) and not at the value level
    /// @param view A shallow copy of a set of classes 
    /// @param i the index of the first reference to swap
    /// @param j the index of the second reference to swap
    public static void swapClasses(String[][] view, int i, int j) {
        // TODO : Implement Here
        String [] swapClasses = view[i] ;
        view[i] = view [j];
        view[j] = swapClasses;
    
            
        }

    //--------------------------------------------------------------------- 
    /// Sorts (shallow) a set of references to classes in descending order 
    /// subject to the differential between ups and downs using selection 
    /// sort
    /// @param view A shallow copy of a set of classes 
    /// @return an array of profile information of 3 buckets with the 
    ///         respective buckets containing a count of 0: allocations, 
    ///         1:comparisons, and 2: swaps
    public static int[] sortSelection(String[][] view) {

        // profile[0:allocs (ignore profile), 1:comparisons, 2:swaps]
        int[] profile = new int[3];
       
    profile[0] = 3;

    for (int i = 0; i < view.length - 1; i++) {
        int minimumIndex = i;
        for (int j = i + 1; j < view.length; j++) {
            profile[1]++;
            if (lessThan(view[minimumIndex], view[j])) {
                minimumIndex = j;
            }
        }
        if (minimumIndex != i) {
            String[] temp = view[i];
            view[i] = view[minimumIndex];
            view[minimumIndex] = temp;
            profile[2]++;
        }
    }
    return profile;
}
   

    //--------------------------------------------------------------------- 
    /// Sorts (shallow) a set of references to classes in descending order 
    /// subject to the differential between ups and downs using bubble 
    /// sort
    /// @param view A shallow copy of a set of classes 
    /// @return an array of profile information of 3 buckets with the 
    ///         respective buckets containing a count of 0: allocations, 
    ///         1:comparisons, and 2: swaps
    public static int[] sortBubble(String[][] view) {

    // profile[0:allocs (ignore profile), 1:comparisons, 2:swaps]
    
    int[] profile = new int[3];
        
        int i;
        profile[0]++;
        String[] temp;
        profile[0]++;
        int j;
        profile[0]++;

        for( i =0; i<view.length-1; i++){
            for( j=0;j < view.length-i-1; j++){
                profile[1]++;

                if(lessThan(view[j],view[j+1])){
                    //swap needed
                    
                    temp = view[j];
                    view[j] = view[j+1];
                    view[j+1] = temp;

                    profile[2]++;
                    }
                }
            }
        
            return profile;
    }
      
    



    //--------------------------------------------------------------------- 
    /// Sorts (shallow) a set of references to classes in descending order 
    /// subject to the differential between ups and downs using insertion 
    /// sort
    /// @param view A shallow copy of a set of classes 
    /// @return an array of profile information of 3 buckets with the 
    ///         respective buckets containing a count of 0: allocations, 
    ///         1:comparisons, and 2: swaps
   
    public static int[] sortInsertion(String[][] view) {
        int[] profile = new int[3];
        profile[0] = 3; // set the number of allocations to 3
    
        for (int i = 1; i < view.length; i++) {
            String[] key = view[i];
            int j = i - 1;
            profile[1]++;
    
            // move elements that are greater than the key to the right
            while (j >= 0 && lessThan(view[j], key)) {
                view[j+1] = view[j];
                j--;
                profile[1]++; // increment comparison count
                profile[2]++; // increment swap count
            }
    
            view[j+1] = key;
        }
    
        return profile;
    }
    
    
    // helper method to compare two string arrays


    
    
              

    
   
    
        
    //--------------------------------------------------------------------- 
    /// Sorts (shallow) a set of references to classes in descending order 
    /// subject to the differential between ups and downs using a quicksort.
    /// @param view A shallow copy of a set of classes 
    /// @return an array of profile information of 3 buckets with the 
    ///         respective buckets containing a count of 0: allocations, 
    ///         1:comparisons, and 2: swaps
    public static int[] sortQuicksort(String[][] view) {
        int[] profile = new int[2];
        quicksort(view, 0, view.length - 1, profile);
        return profile;
    }
    
    private static void quicksort(String[][] view, int low, int high, int[] profile) {
        if (low < high) {
            int pivotIndex = partition(view, low, high, profile);
            quicksort(view, low, pivotIndex - 1, profile);
            quicksort(view, pivotIndex + 1, high, profile);
        }
    }
    
    private static int partition(String[][] view, int low, int high, int[] profile) {
        String[] pivot = view[high];
        int i = low - 1;
    
        for (int j = low; j <= high - 1; j++) {
            if (compare(view[j], pivot) <= 0) {
                i++;
                swap(view, i, j, profile);
            }
            profile[0]++; // increment comparisons
        }
        swap(view, i + 1, high, profile);
        return i + 1;
    }
    
    private static int compare(String[] a, String[] b) {
        for (int i = 0; i < a.length && i < b.length; i++) {
            int cmp = a[i].compareTo(b[i]);
            if (cmp != 0) {
                return cmp;
            }
        }
        return Integer.compare(a.length, b.length);
    }
    
    private static void swap(String[][] view, int i, int j, int[] profile) {
        String[] temp = view[i];
        view[i] = view[j];
        view[j] = temp;
        profile[1]++; // increment swaps
    }
}    