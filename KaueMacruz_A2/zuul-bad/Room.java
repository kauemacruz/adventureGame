import java.util.ArrayList;
import java.util.Arrays; 
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Adventure in the House" application. 
 * "Adventure in the House" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west, upstairs and downstairs.  For each direction, the room\
 * stores a reference to the neighboring room, or null 
 * if there is no exit in that direction.
 * 
 * @author  Kaue Macruz
 * @version 1.0
 */
public class Room 
{
    public String description;
    public String exits;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room upstairsExit;
    public Room downstairsExit;
    //array list for each room
    public static ArrayList<Items> outsideItems = new ArrayList<>();
    public static ArrayList<Items> mainRoomItems = new ArrayList<>();
    public static ArrayList<Items> bathroom1Items = new ArrayList<>();
    public static ArrayList<Items> tvRoomItems = new ArrayList<>();
    public static ArrayList<Items> kitchenItems = new ArrayList<>();
    public static ArrayList<Items> outdoorAreaItems = new ArrayList<>();
    public static ArrayList<Items> basementItems = new ArrayList<>();
    public static ArrayList<Items> mainBedroomItems = new ArrayList<>();
    public static ArrayList<Items> secondBedroomItems = new ArrayList<>();
    public static ArrayList<Items> bathRoomItems = new ArrayList<>();
    public static ArrayList<Items> hallwayItems = new ArrayList<>();
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }
    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room upstairs, Room downstairs) 
    {
        if(north != null) {
            northExit = north;
        }
        if(east != null) {
            eastExit = east;
        }
        if(south != null) {
            southExit = south;
        }
        if(west != null) {
            westExit = west;
        }
         if(upstairs != null) {
            upstairsExit = upstairs;
        }
        if(downstairs != null) {
            downstairsExit = downstairs;
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
     /**
     * Print out exit options and the description of the room.
     * Here we print the exits and the description of the room.
     */
    public static void getLongDescription(Room currentRoom) 
    {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.println(Room.getExitString(currentRoom));
        System.out.println(getItemsInRoom(currentRoom));
        
    }
     /**
     * Returns exit options of the room.
     * Here we print the exits of the room.
     */
    public static String getExitString(Room currentRoom) 
    {
        String exits = "Exits: ";
        if(currentRoom.northExit != null) {
            exits += "north ";
        }
        if(currentRoom.eastExit != null) {
            exits += "east ";
        }
        if(currentRoom.southExit != null) {
            exits +="south ";
        }
        if(currentRoom.westExit != null) {
            exits += "west ";
        }
         if(currentRoom.upstairsExit != null) {
            exits +="upstairs ";
        }
        if(currentRoom.downstairsExit != null) {
            exits += "downstairs ";
        }
        return exits;
    }
    /**
     * Returns items and their descriptions in room.
     * 
     */
    public static String getItemsInRoom(Room currentRoom) 
    {
        String itemsInRoom = "";
        if(currentRoom.getDescription() == "outside the main entrance of the house"){
            itemsInRoom += getItems(outsideItems);
        }
        if(currentRoom.getDescription() == "in the main room"){
            itemsInRoom += getItems(mainRoomItems);
        }
        if(currentRoom.getDescription() == "in the bathroom 1"){
            itemsInRoom += getItems(bathroom1Items);
        }
        if(currentRoom.getDescription() == "in the TV room"){
            itemsInRoom += getItems(tvRoomItems);
        }
        if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
            itemsInRoom += getItems(kitchenItems); 
        }
        if(currentRoom.getDescription() == "in the outdoor area"){
            itemsInRoom += getItems(outdoorAreaItems);
        }
        if(currentRoom.getDescription() == "in the basement"){
            itemsInRoom += getItems(basementItems);
        }
        if(currentRoom.getDescription() == "in the main bedroom"){
            itemsInRoom += getItems(mainBedroomItems);
        }
        if(currentRoom.getDescription() == "in the second bedroom"){
            itemsInRoom += getItems(secondBedroomItems);
        }
         if(currentRoom.getDescription() == "in the bathroom 2"){
            itemsInRoom += getItems(bathRoomItems);
        }
         if(currentRoom.getDescription() == "in the hallway upstairs"){
            itemsInRoom += getItems(hallwayItems);
        }
        return itemsInRoom;
    }
     /**
     * Returns items names.
     * 
     */
    public static String getItems(ArrayList<Items> itemsInCurRoom) 
    {
        String items = "";
        if(itemsInCurRoom.size() > 0){
            items += "Items available in this room:";
            //for loop to get each single item in the list
            for(Items item: itemsInCurRoom) {
                items += "\r\n" + item.getItemName(); 
            }   
        }
        else{
            System.out.println("No items in this room");
        }
        return items;
    }
        /**
     * Returns items and their descriptions in room.
     * 
     */
    public static String look(Room currentRoom, String itemName) 
    {
        String itemsInRoom = "";
        if(currentRoom.getDescription() == "outside the main entrance of the house"){
            itemsInRoom += getItems2(outsideItems, itemName);
        }
        if(currentRoom.getDescription() == "in the main room"){
            itemsInRoom += getItems2(mainRoomItems, itemName);
        }
        if(currentRoom.getDescription() == "in the bathroom 1"){
            itemsInRoom += getItems2(bathroom1Items, itemName);
        }
        if(currentRoom.getDescription() == "in the TV room"){
            itemsInRoom += getItems2(tvRoomItems, itemName);
        }
        if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
            itemsInRoom += getItems2(kitchenItems, itemName); 
        }
        if(currentRoom.getDescription() == "in the outdoor area"){
            itemsInRoom += getItems2(outdoorAreaItems, itemName);
        }
        if(currentRoom.getDescription() == "in the basement"){
            itemsInRoom += getItems2(basementItems, itemName);
        }
        if(currentRoom.getDescription() == "in the main bedroom"){
            itemsInRoom += getItems2(mainBedroomItems, itemName);
        }
        if(currentRoom.getDescription() == "in the second bedroom"){
            itemsInRoom += getItems2(secondBedroomItems, itemName);
        }
         if(currentRoom.getDescription() == "in the bathroom 2"){
            itemsInRoom += getItems2(bathRoomItems, itemName);
        }
         if(currentRoom.getDescription() == "in the hallway upstairs"){
            itemsInRoom += getItems2(hallwayItems, itemName);
        }
        return itemsInRoom;
    }
     /**
     * Returns items description.
     * 
     */
    public static String getItems2(ArrayList<Items> itemsInCurRoom, String itemName) 
    {
        String items = "";
        if(itemsInCurRoom.size() > 0){
            for(Items item: itemsInCurRoom) {
                if(item.getItemName().equals(itemName)){
                    items += item.getItemDescription();
                }
            }   
        }
        else{
            System.out.println("No items in this room");
        }
        return items;
    }
      /**
     * Add items to their rooms
     */
    public static void addItemsToRooms(ArrayList<Items> allItemsInventory) 
    {   
        if(Items.contains("FLASHLIGHT", allItemsInventory)){
            Items flashlight = Items.returnObjByName("FLASHLIGHT", allItemsInventory);
            allItemsInventory.remove(flashlight);
            mainBedroomItems.add(flashlight); 
        }
        if(Items.contains("BATTERY", allItemsInventory)){
            Items battery = Items.returnObjByName("BATTERY", allItemsInventory);
            allItemsInventory.remove(battery);
            secondBedroomItems.add(battery); 
        }
    }
    /** 
     * Try to take an item in the room. If item not in the room, return 
     * message, else take item and store in the player's inventory
     */
    public static void take(Command command, Room currentRoom, ArrayList<Items> playerInventory) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take
            System.out.println("Take what?");
            return;
        }

        String itemToTake = command.getSecondWord().toUpperCase();
        
        // Try to take item in the current room.
        if(currentRoom.getDescription() == "outside the main entrance of the house"){
            if(outsideItems.size() > 0){
                addToInventory(itemToTake, outsideItems, playerInventory); 
            }
            else{
                System.out.println("No items in this room");
            } 
        }
        if(currentRoom.getDescription() == "in the main room"){
            if(mainRoomItems.size() > 0){
                addToInventory(itemToTake, mainRoomItems, playerInventory); 
            }
            else{
                System.out.println("No items in this room");
            } 
        }
        if(currentRoom.getDescription() == "in the bathroom 1"){
            if(bathroom1Items.size() > 0){
                addToInventory(itemToTake, bathroom1Items, playerInventory);    
            }
            else{
                System.out.println("No items in this room");
            } 
        }
        if(currentRoom.getDescription() == "in the TV room"){
            if(tvRoomItems.size() > 0){
                addToInventory(itemToTake, tvRoomItems, playerInventory);
            }
            else{
                System.out.println("No items in this room");
            } 
        }
        if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
            if(kitchenItems.size() > 0){
                addToInventory(itemToTake, kitchenItems, playerInventory); 
            }
            else{
                System.out.println("No items in this room");
            }   
        }
        if(currentRoom.getDescription() == "in the outdoor area"){
            if(outdoorAreaItems.size() > 0){
                addToInventory(itemToTake, outdoorAreaItems, playerInventory);
            }
            else{
                System.out.println("No items in this room");
            }
        }
        if(currentRoom.getDescription() == "in the basement"){
            if(basementItems.size() > 0){
                addToInventory(itemToTake, basementItems, playerInventory);
            }
            else{
                System.out.println("No items in this room");
            }
        }
        if(currentRoom.getDescription() == "in the main bedroom"){
            if(mainBedroomItems.size() > 0){
                addToInventory(itemToTake, mainBedroomItems, playerInventory);               
            }
            else{
                System.out.println("No items in this room");
            }
        }
        if(currentRoom.getDescription() == "in the second bedroom"){
            if(secondBedroomItems.size() > 0){
                addToInventory(itemToTake, secondBedroomItems, playerInventory); 
            }
            else{
                System.out.println("No items in this room");
            }
        }
         if(currentRoom.getDescription() == "in the bathroom 2"){
            if(bathRoomItems.size() > 0){
                addToInventory(itemToTake, bathRoomItems, playerInventory);
            }
            else{
                System.out.println("No items in this room");
            }
        }
         if(currentRoom.getDescription() == "in the hallway upstairs"){
            if(hallwayItems.size() > 0){
                addToInventory(itemToTake, hallwayItems, playerInventory);
            }
            else{
                System.out.println("No items in this room");
            }
        }
    }
    /** 
     * Add Item to players inventory
     */
    public static void addToInventory(String itemToTake, ArrayList<Items> itemsInRoom, ArrayList<Items> playerInventory) 
    {
        if(Items.contains(itemToTake.toUpperCase(), itemsInRoom)){
            Items item = Items.returnObjByName(itemToTake.toUpperCase(), itemsInRoom);
            if(playerInventory.size() < 5){
                itemsInRoom.remove(item);
                playerInventory.add(item);
                System.out.println(itemToTake + " was added to your inventory");
            }
            else{
                System.out.println("You need to drop an item to collect more items. Max of 5 in inventory");
            } 
        }
        else{
            System.out.println(itemToTake + " Does not exist or is not in this room");
        }
    }
    /** 
     * Try to drop an item in the room. If item not in the inventory, return 
     * message, else drop item and store in the room array
     */
    public static void drop(Command command, Room currentRoom, ArrayList<Items> playerInventory) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop
            System.out.println("Drop what?");
            return;
        }

        String itemToDrop = command.getSecondWord();
        
        // Try to take item in the current room.
        if(currentRoom.getDescription() == "outside the main entrance of the house"){
            dropFromInventory(itemToDrop, outsideItems, playerInventory); 
        }
        if(currentRoom.getDescription() == "in the main room"){
            dropFromInventory(itemToDrop, mainRoomItems, playerInventory); 
        }
        if(currentRoom.getDescription() == "in the bathroom 1"){
            dropFromInventory(itemToDrop, bathroom1Items, playerInventory);    
        }
        if(currentRoom.getDescription() == "in the TV room"){
            dropFromInventory(itemToDrop, tvRoomItems, playerInventory);
        }
        if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
            dropFromInventory(itemToDrop, kitchenItems, playerInventory);  
        }
        if(currentRoom.getDescription() == "in the outdoor area"){
            dropFromInventory(itemToDrop, outdoorAreaItems, playerInventory);
        }
        if(currentRoom.getDescription() == "in the basement"){
            dropFromInventory(itemToDrop, basementItems, playerInventory);
        }
        if(currentRoom.getDescription() == "in the main bedroom"){
            dropFromInventory(itemToDrop, mainBedroomItems, playerInventory);               
        }
        if(currentRoom.getDescription() == "in the second bedroom"){
            dropFromInventory(itemToDrop, secondBedroomItems, playerInventory); 
        }
         if(currentRoom.getDescription() == "in the bathroom 2"){
            dropFromInventory(itemToDrop, bathRoomItems, playerInventory);
        }
         if(currentRoom.getDescription() == "in the hallway upstairs"){
            dropFromInventory(itemToDrop, hallwayItems, playerInventory);
        }
    }
    /** 
     * drop item from players inventory
     */
    public static void dropFromInventory(String itemToDrop, ArrayList<Items> itemsInRoom, ArrayList<Items> playerInventory) 
    {
        if(Items.contains(itemToDrop.toUpperCase(), playerInventory)){
            Items item = Items.returnObjByName(itemToDrop.toUpperCase(), playerInventory);
            playerInventory.remove(item);
            itemsInRoom.add(item);
            System.out.println(itemToDrop + " was dropped from your inventory"); 
        }
        else{
            System.out.println(itemToDrop + " Does not exist or is not in this room");
        }
    }
    /** 
     * Use battery if player has a flashlight
     */
    public static void useBattery(ArrayList<Items> playerInventory, ArrayList<Items> allItemsInventory) 
    {
        if(Items.contains("FLASHLIGHT", playerInventory)){
            if(Items.contains("BATTERY", playerInventory)){
                Items fuse = Items.returnObjByName("FUSE", allItemsInventory);
                allItemsInventory.remove(fuse);
                bathroom1Items.add(fuse);
                System.out.println("Congrats, now your flashlight has battery");
                System.out.println("Your next mission is to find a fuse that has now been added to a room and USE it in the fuse box located in the kitchen");  
            }
            else{
                System.out.println("You need a battery and a flashlight to use this command");            
            }
        }
        else{
            System.out.println("You need a battery and a flashlight to use this command");            
        }        
    }
     /** 
     * Use fuse if player has a fuse and is in the kitchen
     */
    public static void useFuse(ArrayList<Items> playerInventory, ArrayList<Items> allItemsInventory, Room currentRoom) 
    {
        if(Items.contains("FUSE", playerInventory)){
            if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
                playerInventory.remove(Items.returnObjByName("FUSE", playerInventory));
                Items key = Items.returnObjByName("KEY", allItemsInventory);
                allItemsInventory.remove(key);
                mainRoomItems.add(key);
                Items beer = Items.returnObjByName("BEER", allItemsInventory);
                allItemsInventory.remove(beer);
                kitchenItems.add(beer);
                Items remote = Items.returnObjByName("REMOTE", allItemsInventory);
                allItemsInventory.remove(remote);
                tvRoomItems.add(remote);
                Items dog = Items.returnObjByName("DOG", allItemsInventory);
                allItemsInventory.remove(dog);
                basementItems.add(dog);
                System.out.println("Congrats, now you have light in the house, now you are able to find new items that were added in the house");
                System.out.println("Your next mission is to find the key for the basement door and take your dog that is trapped in there and needs to go to the outdoor area");  
            } 
            else{
                System.out.println("You gotta go to the kitchen to use the fuse");            
            }  
        }
        else{
            System.out.println("You need a fuse to use this command");            
        }
    }
     /** 
     * Use key if player has a key or key1 and is in the kitchen or outside
     */
    public static boolean useKey(ArrayList<Items> playerInventory, Room currentRoom, String key) 
    {
        boolean isDoorLocked = true;
        if(Items.contains(key, playerInventory)){
            if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
                isDoorLocked = false;
                System.out.println("The basement door has been unlocked! You can now go downstairs, get your dog and take it outdoor!");            
            } 
            else if(currentRoom.getDescription() == "outside the main entrance of the house"){
                isDoorLocked = false;
                System.out.println("The front door has been unlocked! You can now go inside!");            
            } 
            else{
                System.out.println("You gotta be in the right room to use that key");            
            }
        }
        else{
            System.out.println("You need a key to use this command");            
        }
        return isDoorLocked;
    }
     /** 
     * Use remote if player has a key and is in the tv room
     */
    public static void useRemote(ArrayList<Items> playerInventory, Room currentRoom) 
    {
        if(Items.contains("REMOTE", playerInventory)){
            if(currentRoom.getDescription() == "in the TV room"){
                if(Items.contains("DOG", outdoorAreaItems)){
                    if(Items.contains("BEER", playerInventory)){
                        System.out.println("Congrats!! You have completed all missions of the game");
                        System.out.println("Please try again if you like or type 'quit' to finish the game");
                        Command command = new Command("QUIT", null);;
                        Game.quit(command);
                        Game.printWelcome();
                        StopWatch.timer.cancel();
                        StopWatch.start();
                    }
                    else{
                        System.out.println("How can watch a game without your precious beer??");            
                    }
                }
                else{
                    System.out.println("Your dog is not in the outdoor area! Have you taken it outdoor to poop?");            
                }
            } 
            else{
                System.out.println("You gotta go to the TV room to use the key");            
            }  
        }
        else{
            System.out.println("You need a remote control to use this command");            
        }
    }
}
