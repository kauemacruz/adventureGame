import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This class is the main class of the "Adventure in the House" application. 
 *  "Adventure in the House" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery, pick up items and use them in order to finish 
 *  the game depending on the mission. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Kaue Macruz
 * @version 1.0
 */

public class Game 
{
    private Parser parser;
    private static Room currentRoom;
    // an array list inventory with all items was created 
    //to make it easier to handle them all
    // another inventory just for the player 
    public static ArrayList<Items> playerInventory;
    public static ArrayList<Items> allItemsInventory;
    //boolean to help to handle if the doors have been unlocked before 
    //the user navigates through them
    public static boolean isBasementDoorLocked; 
    public static boolean isFrontDoorLocked;
    //boolean that controls if the game is finished or not
    public static boolean finished;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //initialize variables, arrays and methods
        parser = new Parser();
        playerInventory = new ArrayList<>();    
        allItemsInventory = new ArrayList<>(); 
        isBasementDoorLocked = true;
        isFrontDoorLocked = true;
        createRooms();
        createItems();
        finished = false;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private static void createRooms()
    {
        Room outside, mainRoom, bathroom1, tvRoom, kitchen, outdoorArea, basement, mainBedroom, secondBedroom, bathroom2, hallway;
      
        // create the rooms
        outside = new Room("outside the main entrance of the house");
        mainRoom = new Room("in the main room");
        bathroom1 = new Room("in the bathroom 1");
        tvRoom = new Room("in the TV room");
        kitchen = new Room("in the kitchen where you can find the fuse box");
        outdoorArea = new Room("in the outdoor area");
        basement = new Room("in the basement");
        mainBedroom = new Room("in the main bedroom");
        secondBedroom = new Room("in the second bedroom");
        bathroom2 = new Room("in the bathroom 2");
        hallway = new Room("in the hallway upstairs");
        
        // initialise room exits
        //ORDER: north east south west upstairs downstairs
        outside.setExits(mainRoom, null, null, null, null, null);
        mainRoom.setExits(null, bathroom1, outside, tvRoom, hallway, null);
        bathroom1.setExits(null, null, null, mainRoom, null, null);
        tvRoom.setExits(null, mainRoom, kitchen, null, null, null);
        kitchen.setExits(tvRoom, null, null, outdoorArea, null, basement);
        outdoorArea.setExits(null, kitchen, null, null, null, null);
        basement.setExits(null, null, null, null, kitchen, null);
        mainBedroom.setExits(null, null, hallway, null, null, null);
        secondBedroom.setExits(null, null, null, hallway, null, null);
        bathroom2.setExits(null, hallway, null, null, null, null);
        hallway.setExits(mainBedroom, secondBedroom, null, bathroom2, null, mainRoom);

        currentRoom = outside;  // start game outside
    }
    /**
     * Create all the items and link their exits together.
     */
    public static void createItems()
    {
        Items flashlight, battery, beer, dog, fuse, key, remote, key1;
      
        // create the items
        flashlight = new Items("FLASHLIGHT", "you can see in the dark");
        battery = new Items("BATTERY", "you have to use it in the flashlight");
        beer = new Items("BEER", "there is no soccer game without a beer");
        dog = new Items("DOG", "it needs to go outdoor");
        fuse = new Items("FUSE", "place it in the fuse box");
        key = new Items("KEY", "you need to open the basement door");
        key1 = new Items("KEY1", "you need to open the front door");
        remote = new Items("REMOTE", "you need it to turn on the tv");
        
        //add them to an inventory that will hold all items
        allItemsInventory.add(flashlight);
        allItemsInventory.add(battery);
        allItemsInventory.add(beer);
        allItemsInventory.add(dog);
        allItemsInventory.add(fuse);
        allItemsInventory.add(key);
        allItemsInventory.add(remote);
        playerInventory.add(key1);
        //add flashlight and battery to specific rooms
        Room.addItemsToRooms(allItemsInventory);
    }
   
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        //print message with instructions
        printWelcome();
        //start timer
        StopWatch.start();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        while (! finished) {
            Command command = parser.getCommand();
            processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
        System.exit(0);
    }

    /**
     * Print out the opening message for the player.
     */
    public static void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Adventure in the house!");
        System.out.println("This is an adventure game where there is no light in the house and you want to watch a soccer game that is about to start.");
        System.out.println("Your first mission is to find a flashlight, find the battery for it and use it.");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You have 10 minutes to complete all missions before the game starts");
        //show the current room which is outside
        Room.getLongDescription(currentRoom);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public static void processCommand(Command command) 
    {

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        String secondWord = command.getSecondWord();
        if (commandWord.equals("HELP")) {
            printHelp();
        }
        else if (commandWord.equals("GO")) {
            goRoom(command);
        }
        else if (commandWord.equals("TAKE")) {
            Room.take(command, currentRoom, playerInventory);
        }
        else if (commandWord.equals("DROP")) {
            Room.drop(command, currentRoom, playerInventory);
        }
        else if (commandWord.equals("INVENTORY")) {
            inventory(playerInventory);
        }
         else if (commandWord.equals("LOOK")) {
            look(command);
        }
        else if (commandWord.equals("USE")) {
            use(command);
        }
        else if (commandWord.equals("QUIT")) {
            Scanner sc = new Scanner(System.in);
            //resets all lists and room location
            System.out.println("Would you like to try again? Y/N");
            String input = sc.next();
            if(input.equals("y") || input.equals("Y")){
                quit(command);
                finished = false;
                printWelcome();
                StopWatch.timer.cancel();
                StopWatch.start();
            } 
            else{
                finished = quit(command);
            }
        }
    }

     /** 
     * Try to use and item, otherwise print an error message.
     */
    private static void use(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            System.out.println("Use what?");
            return;
        }
        String secondWord = command.getSecondWord();
        
        if(secondWord.equals("BATTERY")){
                Room.useBattery(playerInventory, allItemsInventory);
            }
            else if(secondWord.equals("FUSE")){
                Room.useFuse(playerInventory, allItemsInventory, currentRoom);
            }
            else if(secondWord.equals("KEY")){
                isBasementDoorLocked = Room.useKey(playerInventory, currentRoom, "KEY");
            }
            else if(secondWord.equals("KEY1")){
                isFrontDoorLocked = Room.useKey(playerInventory, currentRoom, "KEY1");
            }
            else if(secondWord.equals("REMOTE")){
                Room.useRemote(playerInventory, currentRoom);
            }
            else{
                System.out.println("Dont know what you mean");
            }
    }
    /** 
     * Try to look an item in room, otherwise print an error message.
     */
    private static void look(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            Room.getLongDescription(currentRoom);
            return;
        }
        else{
            String secondWord = command.getSecondWord();
            if(Room.look(currentRoom, secondWord).equals("")){
                System.out.println("Item not in this room");
            }
            else{
                System.out.println(Room.look(currentRoom, secondWord));
            }
        }
    }
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private static void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around your house.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(" go quit help inventory take drop use look");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private static void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("NORTH")) {
            if(currentRoom.getDescription() == "outside the main entrance of the house"){
                if(!isFrontDoorLocked){
                    nextRoom = currentRoom.northExit;
                }
                else{
                    nextRoom = currentRoom;
                    System.out.println("This door is locked! Use key1!");
                }
            }
            else{
                nextRoom = currentRoom.northExit;
            }
        }
        if(direction.equals("EAST")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("SOUTH")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("WEST")) {
            nextRoom = currentRoom.westExit;
        }
        if(direction.equals("UPSTAIRS")) {
            nextRoom = currentRoom.upstairsExit;
        }
        if(direction.equals("DOWNSTAIRS")) {
            if(currentRoom.getDescription() == "in the kitchen where you can find the fuse box"){
                if(!isBasementDoorLocked){
                    nextRoom = currentRoom.downstairsExit;
                }
                else{
                    nextRoom = currentRoom;
                    System.out.println("This door is locked! Find the key and use it!");
                }
            }
            else if(currentRoom.getDescription() == "in the hallway upstairs"){
                nextRoom = currentRoom.downstairsExit;
            }
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            Room.getLongDescription(currentRoom);
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    public static boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            playerInventory.clear();
            allItemsInventory.clear();
            Room.outsideItems.clear(); 
            Room.mainRoomItems.clear();
            Room.bathroom1Items.clear();
            Room.tvRoomItems.clear();
            Room.kitchenItems.clear();
            Room.outdoorAreaItems.clear();
            Room.basementItems.clear();
            Room.mainBedroomItems.clear();
            Room.secondBedroomItems.clear();
            Room.bathRoomItems.clear();
            Room.hallwayItems.clear();
            createRooms();
            createItems();
            
            return true ;  // signal that we want to quit
        }
    } 
    /** 
     * shows inventory
     */
    private static void inventory(ArrayList<Items> playerInventory) 
    {
        String items = "Items available in inventory:";
        for(Items item: playerInventory) {
            items += "\r" + item; 
        } 
        System.out.println(items);
    }
}
