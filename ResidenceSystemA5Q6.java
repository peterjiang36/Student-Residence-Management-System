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
public class ResidenceSystemA5Q6 {

    /**
     * Initialize an instance of the residence management residence
     * relies on user-input to get the relavent information
     */
    public ResidenceSystemA5Q6() {


        // get the residence information
        IOAccess.getInstance().outputString("-------Getting Residence information-------");
        String name = IOAccess.getInstance().readString("Enter the name of the Residence: ");
        int firstBedNum = IOAccess.getInstance().readInt("Enter the integer label of the first bed: ");
        int lastBedNum = IOAccess.getInstance().readInt("Enter the integer label of the last bed: ");

        ResidenceAccess.initialize(name, firstBedNum, lastBedNum);
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

        Command[] commands = new Command[10];
        commands[1] = new SystemState();
        commands[2] = new AddStudent();
        commands[3] = new AddManager();
        commands[4] = new AssignManagerToStudent();
        commands[5] = new DisplayEmptyBeds();
        commands[6] = new AssignBed();
        commands[7] = new ReleaseStudent();
        commands[8] = new DropAssociation();
        commands[9] = new SystemState();

        int task = -1;
        ResidenceSystemA5Q6 sys;

        IOAccess.getInstance().outputString("-------Initializing the system-------");

        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new ResidenceSystemA5Q6();
                break;
            } catch (RuntimeException e) {
                IOAccess.getInstance().outputString(e.getMessage());
            }
        }

        IOAccess.getInstance().outputString("-------Running the system-------");
        while (task != 1) {
            try {
                String[] options = {
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
                        "Enter your selection {1-9}: "};

                task = IOAccess.getInstance().readChoice(options);

                if (task >= 1 && task <= 9 && commands[task] != null) {
                    commands[task].execute();
                } else {
                    IOAccess.getInstance().outputString("Invalid option, try again.");
                }
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