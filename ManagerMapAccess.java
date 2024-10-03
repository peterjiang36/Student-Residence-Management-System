/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import java.util.TreeMap;

/**
 * A singleton class to access the dictionary containing the manager
 */
public class ManagerMapAccess {

    /**
     * private static attribute dictionary for managers
     */
    private static TreeMap<String, Manager> dictionary;

    /**
     * Private constructor to ensure that no instance of this class is created
     */
    private ManagerMapAccess() {}


    /**
     * Return the dictionary that maps Strings to Manager objects
     * @return The singleton instance of the TreeMap mapping Strings to Manager objects.
     */
    public static TreeMap<String, Manager> getInstance() {

        if (dictionary == null) {
            dictionary = new TreeMap<String, Manager>();
        }

        return dictionary;
    }
}
