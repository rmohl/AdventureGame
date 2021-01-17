package adventure;

public class SmallFood extends Food implements Tossable {

    private static final long serialVersionUID = 1966187067279980252L;

    /* CONSTRCUTORS: */

    /**
     * Default Clothing constructor.
     *
     */
    public SmallFood() {
        super();
    }

    /**
     * Clothing constructor (with parameters).
     * 
     * @param idParam  id of item
     * @param nameParam  name of item
     * @param desc  description of item
     *
     */
    public SmallFood(int idParam, String nameParam, String desc) {
        super(idParam, nameParam, desc);
    }


    /* OTHER: */

    public final String toss() {
        return "You tossed a type of small food called " + getName() + ".";
    }

    /** 
     * Prints SmallFood object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== SmallFood Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
