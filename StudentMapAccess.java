/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import java.util.TreeMap;

/**
 * A singleton class to access the dictionary containing the student
 */
public class StudentMapAccess {

    /**
     * private static attribute dictionary for students
     */
    private static TreeMap<String, Student> dictionary;

    /**
     * Private constructor to ensure that no instance of this class is created
     */
    private StudentMapAccess() {}

    /**
     * Return the dictionary that maps Strings to Student objects
     * @return The singleton instance of the TreeMap mapping Strings to Student objects.
     */
    public static TreeMap<String, Student> getInstance() {

        if (dictionary == null) {
            dictionary = new TreeMap<String, Student> ();
        }

        return dictionary;
    }
}
