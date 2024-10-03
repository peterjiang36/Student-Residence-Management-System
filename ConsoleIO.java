/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import java.util.Scanner;


/**
 * ConsoleIO class implements the InputOutputInterface for console-based input and output
 * It utilizes a Scanner object to read inputs from the console and prints outputs to the console
 */
public class ConsoleIO implements InputOutputInterface {

    /**
     * The scanner used by the methods
     */
    private Scanner consoleIn;

    /**
     * Initialize an instance with the scanner to System.in
     */
    public ConsoleIO() {
        this.consoleIn = new Scanner(System.in);
    }



    /**
     * Display a prompt and read the string entered.
     *
     * @param prompt the string to be displayed as a prompt
     * @return the String read
     */
    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return consoleIn.nextLine();
    }

    /**
     * Display a prompt and read the int entered.
     *
     * @param prompt the string to be displayed as a prompt
     * @return the int read
     */
    @Override
    public int readInt(String prompt) {
        System.out.print(prompt);
        if (!consoleIn.hasNextInt()) {
            throw new IllegalArgumentException("Invalid input. Please enter an integer.");
        }
        return consoleIn.nextInt();
    }


    /**
     * Display the list of options and read an int that is the index of one of the options. The
     * first option is the default.
     *
     * @param options an array with the options that are presented to the user
     * @return the int specifying the array index for the option selected by the user
     */
    @Override
    public int readChoice(String[] options) {

        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        int choice = consoleIn.nextInt();
        consoleIn.nextLine();
        return choice;
    }

    /**
     * Output the String parameter.
     *
     * @param outString the string whose value is to be displayed
     */
    @Override
    public void outputString(String outString) {
        System.out.println(outString);
    }


    /**
     * Initialize a main to test all methods in ConsoleIO class.
     * @param args
     */
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();

        // Test readString
        String name = io.readString("Enter your name: ");
        io.outputString("Your name is: " + name);

        // Test readInt
        int age = io.readInt("Enter your age: ");
        io.outputString("Your age is: " + age);

        // Test readChoice
        String[] options = {"Option 1", "Option 2", "Option 3"};
        int choice = io.readChoice(options);
        io.outputString("You chose: " + options[choice]);

        // Test outputString
        io.outputString("This is a test string.");
    }
}