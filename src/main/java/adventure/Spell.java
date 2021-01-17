package adventure;

public class Spell extends Item implements Readable {

    private static final long serialVersionUID = 7107662851663994578L;

    /* CONSTRCUTORS: */

    /**
     * Default Spell constructor.
     *
     */
    public Spell() {
        super();
    }

    /**
     * Spell constructor (with parameters).
     * 
     * @param idParam  id of item
     * @param nameParam  name of item
     * @param desc  description of item
     *
     */
    public Spell(int idParam, String nameParam, String desc) {
        super(idParam, nameParam, desc);
    }


    /* OTHER: */

    public final String read() {
        return "The " + getName() + " reads:\n" + getLongDescription();
    }

    /** 
     * Prints Spell object in desired format.
     * 
     * @return the String to be printed
     *
     */
    public String toString(){
        return ("===== Spell Object =====\n"
                + "Id: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Desc: " + getLongDescription() + "\n"
                + "Room: " + getContainingRoom().getName() + "\n"
                + "Type: " + getClass() + "\n");
    }  
}
