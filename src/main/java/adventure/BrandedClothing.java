package adventure;

public class BrandedClothing extends Clothing implements Readable {

    private static final long serialVersionUID = -5459409634821222547L;

    /* CONSTRCUTORS: */

    /**
     * Default Clothing constructor.
     *
     */
    public BrandedClothing() {
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
    public BrandedClothing(int idParam, String nameParam, String desc) {
        super(idParam, nameParam, desc);
    }


    /* OTHER: */

    public final String read() {
        return "The " + getName() + " is branded!\n It reads:\n" + getLongDescription();
    }

    /** 
     * Prints BrandedClothing object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== BrandedClothing Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
