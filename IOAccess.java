/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */


/**
 * A singleton class managers the instance of InputOutputInterface
 */
public class IOAccess {

    /**
     * private static attribute io for InputOutputInterface
     */
    private static InputOutputInterface io;

    /**
     * Private constructor to ensure on instance of the class can be created
     */
    private IOAccess() {}

    /**
     * initialize an instance of InputOutInterface by user's choice of Console or Dialogue
     */
    public static void initialize() {
        InputOutputInterface tempIo = new ConsoleIO();
        String choice = tempIo.readString(
                "Choose IO method: " +
                        "\n\t1: Console" +
                        "\n\t2: Dialogue" +
                        "\nEnter your selection {1-2}: ");

        if (choice.equals("2")) {
            io = new DialogueIO();
        } else {
            io = tempIo;
        }
    }

    /**
     * Returns the singleton instance of InputOutputInterface.
     * @return The single instance of InputOutputInterface
     */
    public static InputOutputInterface getInstance() {

        if (io == null) {
            initialize();
        }

        return io;
    }
}
