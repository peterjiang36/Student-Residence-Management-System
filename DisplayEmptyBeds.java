/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

public class DisplayEmptyBeds implements Command {
    /**
     * Carry out the command!
     */
    @Override
    public void execute() {

        IOAccess.getInstance().outputString("The empty beds in the Residence are: " + ResidenceAccess.getInstance().availableBeds());
    }
}
