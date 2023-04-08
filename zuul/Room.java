import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList; // or java.util.*; and replace the above

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Author Manit Jawa 101215842 used the sample solutions of A1 provided by
 * instructors and updated them by adding his code for A2.
 * 
 * @author Manit Jawa 101215842
 * @version A2 1.0 March 16 2023 (updated sample A1 solutions provided by instructors)
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall 
 * @version A1 Solution
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private static ArrayList<Room> rooms = new ArrayList<Room>();  // stores all rooms

    // the items in this room
    private ArrayList<Item> items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        rooms.add(this);
    }
    
    /**
     * Add an item to the room, best to check that it's not null.
     * 
     * @param item The item to add to the room
     */
    public void addItem(Item item) 
    {
        if (item!=null) { // not required, but good practice
            items.add(item);
        }
    }

    /**
     * Define an exit from this room.
     * 
     * @param direction The direction of the exit
     * @param neighbour The room to which the exit leads
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * Returns a short description of the room, i.e. the one that
     * was defined in the constructor
     * 
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Items: 
     *        a chair weighing 5 kgs.
     *        a table weighing 10 kgs.
     *     
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString()
            + "\nItems:" + getItems();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return Details of the room's exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Return a String representing the items in the room, one per line.
     * 
     * @return A String of the items, one per line
     */
    public String getItems() 
    {
        // let's use a StringBuilder (not required)
        StringBuilder s = new StringBuilder();
        for (Item i : items) {
            s.append("\n    " + i.getDescription());
        }
        return s.toString(); 
    }

    /**
     * Returns the ArrayList of rooms.
     * 
     * @return The ArrayList rooms
     */
    public static ArrayList getRooms(){
        return rooms;
    }
    
    /**
     * Removes an item from the room 
     * and returns it.
     * 
     * @param item_name The name of the item to be removed
     * @return The item removed
     */
    public Item removeItem(String item_name){

        Item return_item = new Item("null", 0, "null");
        for (Item i: items){
            if (i.getName().equals(item_name)){
                return_item = i;
                break;
            }
        }
        items.remove(return_item);
        return return_item;
    }
}

