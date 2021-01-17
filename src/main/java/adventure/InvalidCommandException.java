package adventure;

public class InvalidCommandException extends Exception{

    private static final long serialVersionUID = 6851582943810881390L;

    
    /* CONSTRCUTORS: */

    /**
     * Default InvalidCommandException constructor.
     *
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * InvalidCommandException constructor (with parameters).
     * 
     * @param message  error message 
     *
     */
    public InvalidCommandException(String message) {
        super(message);
    }

}
