import java.util.ArrayList;

/**
 *  This class is part of the "Adventure in the House" application. 
 * "Adventure in the House" is a very simple, text based adventure game.  
 *
 *
 * @author Kaue Macruz
 * @version 1.0
 */
public class Items
{
    public String itemName;
    public String itemDescription;
    /**
     * Create an item described "description". Initially, 
     * "description" is something like "a flshlight" or
     * "key".
     * @param description The item's description.
     */
    public Items(String itemName, String itemDescription)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }
    /**
     * @return The description of the items.
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    /**
     * @return The name of the items.
     */
    public String getItemName()
    {
        return itemName;
    }
    /**
     *checks if item exists in arraylist
     */
    public static boolean contains(String objName, ArrayList<Items> inventory) {
        boolean contains = false;
        for(Items item: inventory) {
            if(item.getItemName().equals(objName)){
                contains = true;
            }
        }
        return contains;
    }
    /**
     * returns object by its name
     */
    public static Items returnObjByName(String objName, ArrayList<Items> inventory) {
        Items object = new Items("", "");
        for(Items item: inventory) {
            if(item.getItemName().equals(objName)){
                object = item;
            }
        }
        return object;
    }
    /**
     * @return string with name of the items and its description.
     */
    @Override
    public String toString() {
    return "Item: " + this.getItemName() + 
           ", " + this.getItemDescription();
    }
}
