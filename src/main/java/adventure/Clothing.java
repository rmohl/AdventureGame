package adventure;

public class Clothing extends Item implements Wearable {

    private static final long serialVersionUID = -1017602575749897666L;

    private boolean wearing;


    /* CONSTRCUTORS: */

    /**
     * Default Clothing constructor.
     *
     */
    public Clothing() {
        super();
        setWearing(false);
    }

    /**
     * Clothing constructor (with parameters).
     * 
     * @param idParam  id of item
     * @param nameParam  name of item
     * @param desc  description of item
     *
     */
    public Clothing(int idParam, String nameParam, String desc) {
        super(idParam, nameParam, desc);
        setWearing(false);
    }  


    /* SETTERS + GETTERS: */

    /**
     * Gets boolean determining if clothing item is being worn.
     *
     * @return the 'wearing' boolean of the current clothing item
     *
     */
    public boolean getWearing(){
        return wearing;
    }

    /**
     * Sets boolean determining if clothing item is being worn.
     *
     * @param wear  the 'wearing' boolean that will be set for the current clothing item
     *
     */
    public void setWearing(boolean wear){
        wearing = wear;
    } 


    /* OTHER: */

    public final String wear() {
        if (getWearing()){
            return "You are already wearing the " + getName() + ".";
        } else {
            setWearing(true);
            return "You are now wearing the " + getName() + ".";
        }
    }

    /** 
     * Prints Clothing object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Clothing Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
