/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import java.util.NoSuchElementException;

public class ReleaseStudent implements Command {

    /**
     * Carry out the command!
     */
    @Override
    public void execute() {

        IOAccess.getInstance().outputString("-------Releasing a Student from a Bed-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        ResidenceAccess.getInstance().freeBed(st.getBedLabel());
        st.setBedLabel(-1);
    }
}
