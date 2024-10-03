/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import java.util.Collection;
import java.util.InputMismatchException;

/**
 * A simple residence management system with only one ResidenceAccess.getInstance().  Students and managers can be created,
 * and students assigned to a manager and/or assigned a bed within the ResidenceAccess.getInstance().
 */
public class ResidenceSystemA5Q5 {

    /**
     * Initialize an instance of the residence management residence
     * relies on user-input to get the relavent information
     */
    public ResidenceSystemA5Q5() {


        // get the residence information
        IOAccess.getInstance().outputString("-------Getting Residence information-------");
        String name = IOAccess.getInstance().readString("Enter the name of the Residence: ");
        int firstBedNum = IOAccess.getInstance().readInt("Enter the integer label of the first bed: ");
        int lastBedNum = IOAccess.getInstance().readInt("Enter the integer label of the last bed: ");

        ResidenceAccess.initialize(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new student and then add the student
     * to the dictionary of all StudentMapAccess.getInstance().
     */
    public void addStudent() {

        Command cmd = new AddStudent();
        cmd.execute();
    }


    /**
     * Read the information for a new manager and then add the manager
     * to the dictionary of all managers.
     */
    public void addManager() {

            Command cmd = new AddManager();
            cmd.execute();
    }


    /**
     * Assign a manager to take care of a student.
     */
    public void assignManagerToStudent() {

        Command cmd = new AssignManagerToStudent();
        cmd.execute();
    }

    /**
     * Assign a student to a specific bed.
     */
    public void assignBed() {

        Command cmd = new AssignBed();
        cmd.execute();
    }

    /**
     * Drop the association between a manager and a student.
     */
    public void dropAssociation() {

        Command cmd = new DropAssociation();
        cmd.execute();
    }

    /**
     * Displays the system state
     */
    public void systemState() {

        Command cmd = new SystemState();
        cmd.execute();
    }

    /**
     * Display the empty beds for the ResidenceAccess.getInstance().
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds() {

       Command cmd = new DisplayEmptyBeds();
       cmd.execute();
    }


    /**
     * Release a student from the ResidenceAccess.getInstance().
     * Method is just a stub, needs to be implemented
     */
    public void releaseStudent() {

        Command cmd = new ReleaseStudent();
        cmd.execute();
    }



    /**
     * Return a string representation of the ResidenceSystem
     *
     * @return a string representation of the ResidenceSystem
     */
    public String toString() {
        String result = "\nThe students in the system are:";
        Collection<Student> patientsColl = StudentMapAccess.getInstance().values();
        for (Student p : patientsColl)
            result = result + p;
        result = result + "\n-------\nThe managers in the system are:";
        Collection<Manager> managersColl = ManagerMapAccess.getInstance().values();
        for (Manager d : managersColl)
            result = result + d;
        result = result + "\n-------\nThe residence is " + ResidenceAccess.getInstance();
        return result;
    }

    /**
     * Run the residence management system.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        int task = -1;
        ResidenceSystemA5Q5 sys;

        IOAccess.getInstance().outputString("-------Initializing the system-------");

        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new ResidenceSystemA5Q5();
                break;
            } catch (RuntimeException e) {
                IOAccess.getInstance().outputString(e.getMessage());
            }
        }

        IOAccess.getInstance().outputString("-------Running the system-------");
        while (task != 1) {
            try {
                task = IOAccess.getInstance().readChoice(new String[]{
                        "\nOptions:",
                        "\t1: Quit",
                        "\t2: Add a new student",
                        "\t3: Add a new manager",
                        "\t4: Assign a manager to a student",
                        "\t5: Display the empty beds of the residence",
                        "\t6: Assign a student a bed",
                        "\t7: Release a student",
                        "\t8: Drop manager-student association",
                        "\t9: Display current system state",
                        "Enter your selection {1-9}: "});


                if      (task == 1) sys.systemState();
                else if (task == 2) sys.addStudent();
                else if (task == 3) sys.addManager();
                else if (task == 4) sys.assignManagerToStudent();
                else if (task == 5) sys.displayEmptyBeds();
                else if (task == 6) sys.assignBed();
                else if (task == 7) sys.releaseStudent();
                else if (task == 8) sys.dropAssociation();
                else if (task == 9) sys.systemState();
                else IOAccess.getInstance().outputString("Invalid option, try again.");
            }
            catch (InputMismatchException e) {
                // thrown by the Scanner if the user types something unexpected
                IOAccess.getInstance().outputString("Use an integer to choose a menu item!");
            }
            catch (RuntimeException e) {
                // No matter what other exception is thrown, this catches it
                // Dealing with it means discarding whatever went wrong 
                // and starting the loop over.  Easy for the programmer,
                // tedious for the user.
                IOAccess.getInstance().outputString(e.getMessage());
            }
        }

        IOAccess.getInstance().outputString("-------System terminated-------");
    }
}