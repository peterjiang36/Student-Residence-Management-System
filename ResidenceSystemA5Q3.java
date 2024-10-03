/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

/**
 * A simple residence management system with only one residence.  Students and managers can be created,
 * and students assigned to a manager and/or assigned a bed within the residence.
 */
public class ResidenceSystemA5Q3 {

    private static InputOutputInterface io = new DialogueIO();


    /**
     * The keyed dictionary of all students.
     */
    private Map<String, Student> students;

    /**
     * The keyed dictionary of all managers.
     */
    private Map<String, Manager> managers;

    /**
     * The residence to be handled.
     */
    private Residence residence;

    /**
     * Initialize an instance of the residence management residence
     * relies on user-input to get the relavent information
     */
    public ResidenceSystemA5Q3() {

        students = new TreeMap<String, Student>();
        managers = new TreeMap<String, Manager>();

        // get the residence information
        io.outputString("-------Getting Residence information-------");
        String name = io.readString("Enter the name of the Residence: ");
        int firstBedNum = io.readInt("Enter the integer label of the first bed: ");
        int lastBedNum = io.readInt("Enter the integer label of the last bed: ");

        residence = new Residence(name, firstBedNum, lastBedNum);
    }

    public static void setIo(InputOutputInterface io) {
        ResidenceSystemA5Q3 .io = io;
    }

    /**
     * Read the information for a new student and then add the student
     * to the dictionary of all students.
     */
    public void addStudent() {
        io.outputString("-------Adding Student to Residence-------");
        String name = io.readString("Enter the name of the student: ");

        String sSIN = io.readString("Enter the social insurance number of the student: ");

        String sNSID = io.readString("Enter the NSID of the student: ");

        if (students.containsKey(sNSID)) {
            throw new IllegalStateException("Student with NSID " + sNSID + " already exists");
        }

        Student st = new Student(name, sSIN, sNSID);
        Student result = students.put(sNSID, st);

        // checking to make sure the key was unique
        if (result != null) {
            students.put(sNSID, result);  // put the original student back
            throw new IllegalStateException("NSID in the student dictionary even "
                    + "though containsKey failed.  Student " + name + " not entered.");
        }
    }

    /**
     * Read the information for a new manager and then add the manager
     * to the dictionary of all managers.
     */
    public void addManager() {
        io.outputString("-------Adding Manager to Residence-------");
        String mName = io.readString("Enter the manager's name: ");
        String mSIN = io.readString("Enter the manager's SIN: ");
        String mEN = io.readString("Enter the manager's employee number: ");
        if (managers.containsKey(mEN))
            throw new IllegalStateException("Manager not added as there already "
                    + "is a manager with the employee number " + mEN);

        String response = io.readString("Is the manager a consultant? (yes or no): ");

        Manager mgr;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            mgr = new Consultant(mName, mSIN, mEN);
        else
            mgr = new Manager(mName, mSIN, mEN);

        // check to make sure the manager name doesn't already exist
        Manager sameNumbermanager = managers.put(mEN, mgr);
        if (sameNumbermanager != null) {
            // if put() returns a reference, then a manager was already stored with the same EN,
            // so put it back, and signal an error.
            managers.put(mEN, sameNumbermanager); // put the original manager back
            throw new IllegalStateException("Employee number in the manager dictionary even though "
                    + "containsKey failed.  Manager " + mName + " not entered.");
        }
    }

    /**
     * Assign a manager to take care of a student.
     */
    public void assignManagerToStudent() {
        io.outputString("-------Assigning a new Manager-Student Association-------");
        String sNSID = io.readString("Enter the NSID of the student: ");

        Student st = students.get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID  " + sNSID);

        String mEN = io.readString("Enter the employee number of the manager: ");
        Manager mgr = managers.get(mEN);
        if (mgr == null)
            throw new NoSuchElementException("There is no manager with employee number " + mEN);
        else {
            st.addManager(mgr);
            mgr.addStudent(st);
        }
    }

    /**
     * Assign a student to a specific bed.
     */
    public void assignBed() {
        io.outputString("-------Assigning a Student to a Bed-------");
        String sNSID = io.readString("Enter the NSID of the student: ");

        Student st = students.get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        if (st.getBedLabel() != -1)
            throw new IllegalStateException(" Student " + st
                    + " is already in a bed so cannot be assigned a new bed");

        int bedNum = io.readInt("Enter the bed number for the student: ");

        if (bedNum < residence.getMinBedLabel() || bedNum > residence.getMaxBedLabel())
            throw new IllegalArgumentException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + residence.getMinBedLabel()
                    + " and " + residence.getMaxBedLabel());

        if (residence.isOccupied(bedNum)) {
            throw new IllegalStateException("There is already a different Student in that bed.");
        } else {
            st.setBedLabel(bedNum);
            residence.assignStudentToBed(st, bedNum);
        }
    }

    /**
     * Drop the association between a manager and a student.
     */
    public void dropAssociation() {
        io.outputString("-------Dropping a new Manager-Student Association-------");
        String sNSID = io.readString("Enter the NSID of the student: ");

        Student st = students.get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        String mEN = io.readString("Enter the employee number of the manager: ");

        // check if the manager is known
        Manager mgr = managers.get(mEN);
        if (mgr == null)
            throw new NoSuchElementException("There is no manager with employee number " + mEN);

        String sNSID2 = st.getNSID();
        if(!sNSID.equals(sNSID2))
            throw new IllegalStateException("NSIDs are not equal for a studnet: " + sNSID + " " + sNSID2);

        // check if the manager records the student
        if (!mgr.hasStudent(sNSID2))
            throw new IllegalStateException("This manager is not associated with this student:" + sNSID2);

        // check if the student records the manager
        if (!st.hasManager(mEN))
            throw new IllegalStateException("This manager and this student are incorrectly "
                    + "associated.  The manager has the student, "
                    + "but the student does not have the manager");

        // the student and manager are associated, so we remove each from the other's record
        st.removeManager(mEN);
        mgr.removeStudent(sNSID);
    }

    /**
     * Displays the system state
     */
    public void systemState() {
        io.outputString(this.toString());
    }

    /**
     * Display the empty beds for the residence.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds() {
       io.outputString("The empty beds in the Residence are: " + residence.availableBeds());
    }


    /**
     * Release a student from the residence.
     * Method is just a stub, needs to be implemented
     */
    public void releaseStudent() {
        io.outputString("-------Releasing a Student from a Bed-------");
        String sNSID = io.readString("Enter the NSID of the student: ");

        Student st = students.get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        residence.freeBed(st.getBedLabel());
        st.setBedLabel(-1);
    }



    /**
     * Return a string representation of the ResidenceSystem
     *
     * @return a string representation of the ResidenceSystem
     */
    public String toString() {
        String result = "\nThe students in the system are:";
        Collection<Student> patientsColl = students.values();
        for (Student p : patientsColl)
            result = result + p;
        result = result + "\n-------\nThe managers in the system are:";
        Collection<Manager> managersColl = managers.values();
        for (Manager d : managersColl)
            result = result + d;
        result = result + "\n-------\nThe residence is " + residence;
        return result;
    }

    /**
     * Run the residence management system.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int task = -1;
        ResidenceSystemA5Q3 sys;

        io.outputString("-------Initializing the system-------");

        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new ResidenceSystemA5Q3();
                break;
            } catch (RuntimeException e) {
                io.outputString(e.getMessage());
            }
        }

        io.outputString("-------Running the system-------");
        while (task != 1) {
            try {
                task = io.readChoice(new String[]{
                        "\nOptions:",
                        "\t1: Quit",
                        "\t2: Add a new student",
                        "\t3: Add a new manager",
                        "\t4: Assign a manager to a student",
                        "\t5: Display the empty beds of the residence",
                        "\t6: Assign a student a bed",
                        "\t7: Release a student",
                        "\t8: Drop manager-student association",
                        "\t9: Display current system state"});


                if      (task == 1) sys.systemState();
                else if (task == 2) sys.addStudent();
                else if (task == 3) sys.addManager();
                else if (task == 4) sys.assignManagerToStudent();
                else if (task == 5) sys.displayEmptyBeds();
                else if (task == 6) sys.assignBed();
                else if (task == 7) sys.releaseStudent();
                else if (task == 8) sys.dropAssociation();
                else if (task == 9) sys.systemState();
                else io.outputString("Invalid option, try again.");
            }
            catch (InputMismatchException e) {
                // thrown by the Scanner if the user types something unexpected
                io.outputString("Use an integer to choose a menu item!");
            }
            catch (RuntimeException e) {
                // No matter what other exception is thrown, this catches it
                // Dealing with it means discarding whatever went wrong 
                // and starting the loop over.  Easy for the programmer,
                // tedious for the user.
                io.outputString(e.getMessage());
            }
        }

        io.outputString("-------System terminated-------");
    }
}