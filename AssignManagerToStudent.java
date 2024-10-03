/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */


import java.util.NoSuchElementException;

public class AssignManagerToStudent implements Command {
    /**
     * Carry out the command!
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("-------Assigning a new Manager-Student Association-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID  " + sNSID);

        String mEN = IOAccess.getInstance().readString("Enter the employee number of the manager: ");
        Manager mgr = ManagerMapAccess.getInstance().get(mEN);
        if (mgr == null)
            throw new NoSuchElementException("There is no manager with employee number " + mEN);
        else {
            st.addManager(mgr);
            mgr.addStudent(st);
        }
    }
}
