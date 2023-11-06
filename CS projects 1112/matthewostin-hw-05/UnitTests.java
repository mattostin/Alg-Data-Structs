/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2022
author: James Taylor

A suite of unit tests that validate the methods and behaviors for 
map implementations
--------------------------------------------------------------------------*/

public class UnitTests {
    static String[][] mapdata;

    public static void main(String[] args) {

        mapdata = MapReader.getData("mapdata");

        validateMap(new TreeMap(true), "BINARY TREE as MAP");
        System.out.println();
        validateMap(new HashMap(100), "HASH TABLE as MAP");
        System.out.println();
    }

    /// Validates a map type using a battery of tests.  Report is generated
    /// via text output.
    /// @param map the map to validate
    /// @return pfx a text prefix to annotate the tests
    public static void validateMap(Map map, String pfx) {
        int passes = 0;
        int fails = 0;
        boolean result;
        String value;

        System.out.println(pfx);

		
        // BLOCK 1 - Retrieve teh value associated with the last key in map
        //if value not found, increment passes
        //otherwise increment the fails counter and print fail
        value = map.get(mapdata[mapdata.length-1][0], new int[1]);
        if( value != null ) {
            fails++;
            System.out.println("::BLOCK 1::TEST 1::FAILED.");
        } else {
            passes++;
        }

		// Set a kvp in the map and retrieve the associated value with key
        //if value not found, increment passes
        //otherwise increment the fails counter and print fail
        map.set(mapdata[0][0], mapdata[0][1], new int[1]);
        value = map.get(mapdata[0][0], new int[1]);
        if( value == null || value != mapdata[0][1] ) {
            fails++;
            System.out.println("::BLOCK 1::TEST 2::FAILED.");
        } else {
            passes++;
        }
		
		// Retrieve the last value associated with last key in map
        //if value not found, increment passes
        //otherwise increment the fails counter and print fail
        value = map.get(mapdata[mapdata.length-1][0], new int[1]);
        if( value != null ) {
            fails++;
            System.out.println("::BLOCK 1::TEST 3::FAILED.");
        } else {
            passes++;
        }
		
		// SEt a kvp and retrieve the associated value with key
        //if value matches the value increment passes counter
        //otherwise increment fails and print fail message
		map.set(mapdata[0][0], mapdata[1][1], new int[1]);
		value = map.get(mapdata[0][0], new int[1]);
		if( value == null || value != mapdata[1][1] ) {
            fails++;
            System.out.println("::BLOCK 1::TEST 4::FAILED.");
        } else {
            passes++;
        }
		map.set(mapdata[0][0], mapdata[0][1], new int[1]);

        
		// BLOCK 2 - Set multiple kvp in map then retrieve the value with the first key
        //if value is foudn and matches the expected value, increment passes
        // otherwise increment fails and print fail
        map.set(mapdata[1][0], mapdata[1][1], new int[1]);
        map.set(mapdata[2][0], mapdata[2][1], new int[1]);
        value = map.get(mapdata[0][0], new int[1]);
        if( value == null || value != mapdata[0][1] ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 1::FAILED.");
        } else {
            passes++;
        }
		
		// Retrieve the value associated with the last key in map
        //if value not found, increment the passes counter
        // otherwise increment the fails counter and print fail message
		value = map.get(mapdata[mapdata.length-1][0], new int[1]);
        if( value != null ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 2::FAILED.");
        } else {
            passes++;
        }

        // Retreive the value associated with the second key in map
        // if the value is foudn and matches the value increment passes
        // otherwise increment fail and print fail message
		value = map.get(mapdata[1][0], new int[1]);
        if( value == null || value != mapdata[1][1] ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 3::FAILED.");
        } else {
            passes++;
        }
        
		// Retrieve the value associated with the third key in map
        //if value found and matches expected value, increment passes
        //counter, otherwise, icnrement the fails counter and print fail message
		value = map.get(mapdata[2][0], new int[1]);
        if( value == null || value != mapdata[2][1] ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 4::FAILED.");
        } else {
            passes++;
        }
		
		map.clear();
		
		// Retrieve the value associated with the first key in map after clearing map
        // if value not found increment passes counter other increment fails counter
        // print fail message
		value = map.get(mapdata[0][0], new int[1]);
        if( value != null ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 5::FAILED.");
        } else {
            passes++;
        }
		
		// Retreive the value associated with the last key in map after clearing map
        // if value not found, increment the passes counter other increment fails counter and
        // print fail message
		value = map.get(mapdata[mapdata.length-1][0], new int[1]);
        if( value != null ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 6::FAILED.");
        } else {
            passes++;
        }

        // Set multiple kvps in the map and retrieve the value associated with first key
        // if value found and matches expected value, increment passes counter
        // otherwise increment hte fails counter and print fail message
		map.set(mapdata[0][0], mapdata[0][1], new int[1]);
        map.set(mapdata[1][0], mapdata[1][1], new int[1]);
        map.set(mapdata[2][0], mapdata[2][1], new int[1]);
        value = map.get(mapdata[0][0], new int[1]);
        if( value == null || value != mapdata[0][1] ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 7::FAILED.");
        } else {
            passes++;
        }
        
		// Retrieve the value associated with the last key in map after setting
        // multiple kvps
        //if value not found, increment passes, other wise increment fails and print
        //fail message
		value = map.get(mapdata[mapdata.length-1][0], new int[1]);
        if( value != null ) {
            fails++;
            System.out.println("::BLOCK 2::TEST 8::FAILED.");
        } else {
            passes++;
        }

        
		// BLOCK 3 - 
        //clear the map and then build map with large data set
        // if all existing key can be located in map, increment passes counter
        //otherwise increment fails counter
        map.clear();
        buildMap( map, new int[1] );

        //Validate all existing key in the large data set
        // if all existing keys can be foudn in map, increment passes counter
        //otherwise increment fails counter and print fail message
		result = validateAllExistingKeys( map );
        if( !result ) {
            fails++;
            System.out.println("::BLOCK 3::TEST 1::FAILED: unable to locate all keys for the large data set.");
        } else {
            passes++;
        }

        // Validate nonexistent keys in the large data set
        // if no keys in the large data set are match, increment passes coutner
        //otherwise increment fails counter and print fail message
		result = validateNonexistentKeys( map );
        if( !result ) {
            fails++;
            System.out.println("::BLOCK 3::TEST 2::FAILED: matched a key in the large data set that should not exist.");
        } else {
            passes++;
        }

        // report summary
        //Check if there are any failures
        if( fails > 0 ) {
            System.out.print( "OVERALL : FAILED " );
        } else {
            System.out.print( "OVERALL : PASS " );
        }
        System.out.println("[passes=" + passes + ", fails=" + fails + "]");

    }
    //print the number of passes and fails

    /// inserts all the kvp data in the mapdata global into the designated
    /// map.
    /// @param map the map to insert data into
    /// @param profile the profile array in which to store comparisons
    public static void buildMap( Map map, int[] profile ) {
        for(int i = 0; i < mapdata.length; i++ ) {
            map.set(mapdata[i][0], mapdata[i][1], profile);
        }
    }

    /// validates a map containing all the information in mapdata
    /// @param map the map to validate
    /// @return true if all of the mapdata can be retrieved from the map;
    ///         otherwise, false
    public static boolean validateAllExistingKeys( Map map ) {
        for( int i = 0; i < mapdata.length; i++ ) {
            String key = mapdata[i][0];
            String value = map.get(key, new int[1]);

            if( value == null ) {
                return false;
            }

            if( value != mapdata[i][1] ) {
                return false;
            }
        }
        return true;
    }

    /// validates a map does not return values for invalid keys
    /// @param map the map to validate
    /// @return true the map never returns a value for a random sequence
    ///         of characters; otherwise, false
    public static boolean validateNonexistentKeys( Map map ) {
        for( int i = 0; i < mapdata.length; i++ ) {
            String key = Utilities.randomString( mapdata[i][0].length() );
            String value = map.get(key, new int[1]);

            if( value != null ) {
                return false;
            }
        }
        return true;
    }
}

