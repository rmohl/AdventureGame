package adventure;

public class Food extends Item implements Edible {

    private static final long serialVersionUID = 5647487339355667488L;

    /* CONSTRCUTORS: */

    /**
     * Default Food constructor.
     *
     */
    public Food() {
        super();
    }

    /**
     * Food constructor (with parameters).
     * 
     * @param idParam  id of item
     * @param nameParam  name of item
     * @param desc  description of item
     *
     */
    public Food(int idParam, String nameParam, String desc) {
        super(idParam, nameParam, desc);
    }


    /* OTHER: */

    public final String eat() {
        setContainingRoom(null);
        return "You consumed the " + getName() + ".";
    }

    /** 
     * Prints Food object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Food Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
