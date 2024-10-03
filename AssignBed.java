/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */


import java.util.NoSuchElementException;

public class AssignBed  implements Command {
    /**
     * Carry out the command!
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("-------Assigning a Student to a Bed-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        if (st.getBedLabel() != -1)
            throw new IllegalStateException(" Student " + st
                    + " is already in a bed so cannot be assigned a new bed");

        int bedNum = IOAccess.getInstance().readInt("Enter the bed number for the student: ");

        if (bedNum < ResidenceAccess.getInstance().getMinBedLabel() || bedNum > ResidenceAccess.getInstance().getMaxBedLabel())
            throw new IllegalArgumentException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + ResidenceAccess.getInstance().getMinBedLabel()
                    + " and " + ResidenceAccess.getInstance().getMaxBedLabel());

        if (ResidenceAccess.getInstance().isOccupied(bedNum)) {
            throw new IllegalStateException("There is already a different Student in that bed.");
        } else {
            st.setBedLabel(bedNum);
            ResidenceAccess.getInstance().assignStudentToBed(st, bedNum);
        }
    }
}
