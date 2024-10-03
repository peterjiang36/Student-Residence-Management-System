/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */


public class AddStudent implements Command {

    /**
     * Carry out the command!
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("-------Adding Student to Residence-------");
        String name = IOAccess.getInstance().readString("Enter the name of the student: ");

        String sSIN = IOAccess.getInstance().readString("Enter the social insurance number of the student: ");

        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        if (StudentMapAccess.getInstance().containsKey(sNSID)) {
            throw new IllegalStateException("Student with NSID " + sNSID + " already exists");
        }

        Student st = new Student(name, sSIN, sNSID);
        Student result = StudentMapAccess.getInstance().put(sNSID, st);

        // checking to make sure the key was unique
        if (result != null) {
            StudentMapAccess.getInstance().put(sNSID, result);  // put the original student back
            throw new IllegalStateException("NSID in the student dictionary even "
                    + "though containsKey failed.  Student " + name + " not entered.");
        }
    }
}
