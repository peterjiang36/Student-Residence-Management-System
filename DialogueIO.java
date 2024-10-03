/*
 * Student name: Peter Jiang
 * Student #: 11379801
 * NSID: SZM243
 * Assignment 5
 * Professor: Dr. Jason Bowey
 */

import javax.swing.*;

public class DialogueIO extends AbstractDialogIO{
    /**
     * Display a dialog box to prompt use to enter a string.
     *
     * @param prompt the string to be displayed with a dialog box as a prompt
     * @return the String read
     */
    // readString(String prompt): Uses JOptionPane to acquire input from the user, and returns a String.
    @Override
    public String readString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    /**
     * Display a dialog box to prompt use to enter an integer.
     *
     * @param prompt the string to be displayed as a prompt
     * @return the int read
     */
    @Override
    public int readInt(String prompt) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer");
            }
        }
    }

    /**
     * Displays a message to the user in a dialog box.
     *
     * @param outString the string whose value is to be displayed
     */
    @Override
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }

    public static void main(String[] args) {
        DialogueIO io = new DialogueIO();

        // Test readString
        String name = io.readString("Enter your name: ");
        io.outputString("Your name is : " + name);

        // Test readInt
        int age = io.readInt("Enter your age: ");
        io.outputString("Your age is: " + age);

        // Test readChoice
        String[] options = {"Option 1", "Option 2", "Option 3"};
        int choice = io.readChoice(options);
        io.outputString("You chose: " + options[choice]);

        // Test outputString
        io.outputString("This is a test string");
    }
}
