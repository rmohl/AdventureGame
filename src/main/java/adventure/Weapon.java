package adventure;

public class Weapon extends Item implements Tossable {

    private static final long serialVersionUID = -81261580785844089L;

    /* CONSTRCUTORS: */

    /**
     * Default Weapon constructor.
     *
     */
    public Weapon() {
        super();
    }

    /**
     * Weapon constructor (with parameters).
     * 
     * @param idParam  id of item
     * @param nameParam  name of item
     * @param desc  description of item
     *
     */
    public Weapon(int idParam, String nameParam, String desc) {
        super(idParam, nameParam, desc);
    }


    /* OTHER: */

    public final String toss() {
        return "You tossed a weapon called the " + getName() + ".";
    }

    /** 
     * Prints Weapon object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Weapon Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
