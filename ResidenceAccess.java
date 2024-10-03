/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */


/**
 * A singleton class manages the instance of a Residence
 */
public class ResidenceAccess {

    /**
     * private static attribute object for Residence
     */
    private static Residence residence;

    /**
     * private constructor to ensure no instance of this class is created
     */
    private ResidenceAccess() {}

    /**
     * Initializes an instance of Residence with specified name and bed number range
     * This method should only be called once. If called again after the Residence has been
     * initialized, it throws an IllegalStateException
     * @param name The name of the Residence
     * @param first The number of the first bed in the Residence
     * @param last  The number of the last bed in the Residence
     */
    public static void initialize(String name, int first, int last) {

        if (residence != null) {
            throw new IllegalStateException("Residence has already been initialized.");
        }

        residence = new Residence(name, first, last);
    }

    /**
     * Returns the singleton instance of Residence.
     * This method should only be called after the Residence has been initialized.
     * @return  The single instance of Residence
     */
    public static Residence getInstance() {

        if (residence == null) {
            throw new IllegalStateException("Residence is not initialized. Call initialize() first.");
        }

        return residence;
    }
}
