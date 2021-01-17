package adventure;

public class Command {

    private static final String[] COMMANDS = {"look", "inventory", "take", "go", "quit", "eat", "wear", "toss", "read"};
    private String action;
    private String noun;


    /* CONSTRUCTORS: */

    /**
     * Create a command object with default values.  
     * both instance variables are set to null
     * 
     */
    public Command() throws InvalidCommandException {
        this (null, null);
    }

    /**
     * Create a command object given only an action.  this.noun is set to null
     *
     * @param command  The first word of the command. 
     * 
     */
    public Command(String command) throws InvalidCommandException{
        this (command, null);
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param actionCommand  The first word of the command. 
     * @param nounCommand  The second word of the command.
     */
    public Command(String actionCommand, String nounCommand) throws InvalidCommandException{
        throwCommandExceptionIfTrue(actionCommand == null && nounCommand == null, "Invalid command.");
        throwCommandExceptionIfTrue(!validCommand(actionCommand), "Invalid command.");
        if (actionCommand != null && nounCommand != null) {
            throwCommandExceptionIfTrue(!sometimesValidTwoWordCommand(actionCommand), "Invalid command.");
        } else {
            throwCommandExceptionIfTrue(alwaysValidTwoWordCommand(actionCommand), "Invalid command.");
        }
        setAction(actionCommand);
        setNoun(nounCommand);
    }


    /* ACCESSORS */

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     * 
     */
    public String getActionWord() {
        return action;
    }

    /**
     * Gets the second word of this command.
     *
     * @return The second word of this command. Returns null if there was no
     * second word.
     * 
     */
    public String getNoun() {
        return noun;
    }

    /**
     * Gets the boolean value determining if the command has a second word or not.
     *
     * @return true if the command has a second word.
     * 
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }


    /* MUTATORS: */

    /**
     * Sets command's actionWord.
     *
     * @param actionWord  the action command of Command object
     *
     */
    public void setAction(String actionWord){
        action = actionWord;
    } 

    /**
     * Sets command's nounWord.
     *
     * @param nounWord  the noun command of Command object
     *
     */
    public void setNoun(String nounWord){
        noun = nounWord;
    } 


    /* OTHER: */

    /**
     * Throws InvalidCommandException if condition given is true.
     *
     * @param condition  the given condition 
     * @param message  the desired error message 
     * 
     */
    public void throwCommandExceptionIfTrue(boolean condition, String message) throws InvalidCommandException{
        if (condition){
            throw new InvalidCommandException(message);
        }
    }

    /**
     * Gets the array of valid commands.
     *
     * @return the array of valid commands.
     * 
     */
    public static String[] getCommands() {
        return COMMANDS;
    }

    /**
     * Determines if the given command is valid or not.
     *
     * @param command  the command to validate
     * @return the boolean determining if the given command is valid or not
     *
     */
    public boolean validCommand(String command){
        if (command == null) {
            return true;
        }
        for (String s: COMMANDS) {
            if (s.equalsIgnoreCase(command)) {
                return true;
            }
        }
        // else:
        return false;
    }

    /**
     * Determines if the given command is valid or not.
     *
     * @param command  the command to validate
     * @return the boolean determining if the given command is valid or not
     *
     */
    public boolean alwaysValidTwoWordCommand(String command){
        if (command.equals("go") || command.equals("take") || command.equals("eat") 
            || command.equals("wear") || command.equals("toss") || command.equals("read")) {
            return true;
        }
        // else:
        return false;
    }

    /**
     * Determines if the given command is valid or not.
     *
     * @param command  the command to validate
     * @return the boolean determining if the given command is valid or not
     *
     */
    public boolean sometimesValidTwoWordCommand(String command){
        if (alwaysValidTwoWordCommand(command) || command.equals("look")) {
            return true;
        }
        // else:
        return false;
    }

    /** 
     * Prints Command object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Command Object =====\n"
                + "Action: " + getActionWord() + "\n"
                + "Noun: " + getNoun() + "\n"
                + "Has noun: " + hasSecondWord() + "\n");
    }  
}
