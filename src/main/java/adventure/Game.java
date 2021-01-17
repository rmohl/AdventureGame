package adventure;

// java util imports
import java.util.Scanner;

// java IO imports
import java.io.FileReader;
import java.io.Reader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

// JSON imports
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game{

   public static final String WELCOME_MESSAGE = "\nThis is an adventure game! Explore the map and interact " 
                                                   + "\nwith items using simple one- or two-word commands. "
                                                   + "\nUse 'go' [direction] to move around the map. "
                                                   + "\nUse 'look' by itself or with [item] to get a better "
                                                   + "\ndescription of your surroundings. "
                                                   + "\nUse 'take' [item] to add items to your inventory. "
                                                   + "\nUse 'inventory' to see the items you have. "
                                                   + "\nUse 'eat', 'wear', 'toss', and 'read' commands with "
                                                   + "\nitems in your inventory to use them. "
                                                   + "\n\nHave fun!\n\n";
   private static Scanner sc;
   private static Parser parser;
   private static Adventure adventure;
   private static Player player;


   /* CONSTRUCTORS: */

   /**
    * Default Item constructor.
    *
    */
   public Game(){
      setSc(new Scanner(System.in));
      setParser(new Parser());
      setAdventure(null);
      setPlayer(null);
   }


   /* MAIN: */

   public static void main(String[] args){

      Game theGame = new Game();
      String input = "";

      System.out.println();
      setAdventure(theGame.gameIntro(args));

      while (!(input.equals("quit"))){
         System.out.print("\n> ");
         input = getSc().nextLine();
         
         // Handling user input:
         System.out.println();
         input = theGame.handleInput(input);

      }
   }


   /* FILE-PARSING METHODS: */
   
   /**
    * Sets up adventure using given json object.
    *
    * @param obj  the json object that the player is using
    * @return the Adventure object created from organizing given json object info
    *
    */
   public Adventure generateAdventure(JSONObject obj){
      setPlayer(new Player());
      setAdventure(new Adventure());

      try{
         getAdventure().initializeAdventure(obj);
      } catch (Exception e){
         generateAdventureError(e);
      }

      return adventure;
   }

   /**
    * Sets up adventure using given json object.
    *
    * @param e  the Exception that was caught in generateAdventure();
    *
    */
   public void generateAdventureError(Exception e){
      if (e.getMessage() == null) {
         System.out.println("Something went wrong with opening the file");
      } else {
         System.out.println(e.getMessage());
      }
      System.exit(1);
   }

   /**
    * Parses/loads given json file.
    *
    * @param filename  the json file that the player wants to open
    * @return the JSONObject created from parsing the given json file
    *
    */
   public JSONObject loadAdventureJson(String filename){
      JSONParser jsonParser = new JSONParser();
      JSONObject parsedFile;

      try (Reader reader = new FileReader(filename)) {
         parsedFile = (JSONObject) jsonParser.parse(reader);
      } catch (Exception e) {
         parsedFile = null;
      }

      return parsedFile;
   }

   /**
    * Parses/loads default file given inputstream.
    *
    * @param inputStream  the json file that the player wants to open
    * @return the JSONObject created from parsing the given json file
    *
    */
   public JSONObject loadAdventureJson(InputStream inputStream){
      JSONParser jsonParser = new JSONParser();
      JSONObject parsedFile;

      try (InputStreamReader reader = new InputStreamReader(inputStream)) {
         parsedFile = (JSONObject) jsonParser.parse(reader);
      } catch (Exception e) {
         parsedFile = null;
      }

      return parsedFile;
   }

   /**
    * Loads adventure and parses JSON file when the player uses a default file.
    *
    * @return the parsed JSON file
    */
    public JSONObject loadDefault(){
      JSONObject parsedFile = null;

      System.out.println("Loading default file ...");
      try {
         InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("default.json");
         parsedFile = loadAdventureJson(inputStream);
      } catch (Exception e) {
         System.out.println("The file does not exist.");
         System.exit(1);
      }

      return parsedFile;
   }


   /* GAMEPLAY METHODS: */

   /**
    * Does game introduction.
    *
    * @param args  the command line arguments that were entered when the program was ran
    * @return the Adventure object that was loaded in the game introduction
    *
    */
   public Adventure gameIntro(String[] args) {
      if (args.length < 2){
         newFileBeginning(loadDefault());
      } else if (args[0].equals("-a")) {
         newFileBeginning(loadAdventureJson(args[1]));
      } else if (args[0].equals("-l")) {
         loadAdventureFile(args[1]);
      } else {
         gameIntroError();
      }

      return adventure;
   }

   /**
    * Loads parsed JSONObject and prints the beginnning of an adventure when the player uses a new or default file.
    * 
    * @param parsedFile  the parsed JSONObject to be loaded
    *
    */
    private void newFileBeginning(JSONObject parsedFile){
      setAdventure(generateAdventure(parsedFile));
      System.out.println("The file was loaded successfully! \n");
      welcome();
   }

   /**
    * Prints the beginning of/welcoming to the adventure.
    *
    */
   private void welcome(){
      System.out.println("Welcome to Rachael's adventure game!\n\nWhat is your name?");   
      System.out.print("\n> ");
      String input = getSc().nextLine();
      getPlayer().setName(input);
      System.out.println("\nHello, " + getPlayer().getName() + "!");   
      input = yesOrNo("Would you like instructions? (y/n)");
      if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {   
         System.out.print(WELCOME_MESSAGE);
      }
      System.out.print("\n" + getAdventure().printCurrentRoom("l"));
   }

   /**
    * Handles output when loading an Adventure file.
    *
    * @param filename  the saved adventure file to be read
    *
    */
   private void loadAdventureFile(String filename){
      try {
         load(filename);
         System.out.println("The file was loaded successfully! \n");
      } catch (Exception e) {
         System.out.println("ERROR - Something went wrong while opening the file.");
         System.exit(1);
      }
      System.out.println("Welcome back, " + getPlayer().getName() + "!\n");
      System.out.print(getAdventure().printCurrentRoom("l"));
   }

   /**
    * Reads in information from a saved adventure file.
    *
    * @param filename  the saved adventure file to be read
    *
    */
   public void load(String filename) throws Exception{
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      setAdventure((Adventure)in.readObject());
      setPlayer((Player)in.readObject());
      in.close();
   }

   /**
    * gameIntro() helper; displays error message.
    *
    */
    private void gameIntroError(){
      System.out.println("ERROR - improper command line arguments used. Try:\n"
                         + "$ java -jar <pathToExecutableJarWithDependencies> -[a|l] <saveFileOrJsonFile>");
      System.exit(1);
   }

   /**
    * Interprets String of user input taken from main game loop.
    *
    * @param input  String of user input 
    * @return String telling main how input was handled
    *
    */
   public String handleInput(String input){
      if (!(input.equals("quit"))){
         try{
            System.out.println(getParser().handleCommandInput(getParser().parseUserCommand(input)));
         } catch (InvalidCommandException e){
            System.out.println(e.getMessage());
         }
      } else {
         input = endGame(yesOrNo("Are you sure you want to quit? (y/n)"));
      }
      return input;
   }

   /**
    * Handles the ending of the game.
    *
    * @param input  the yes or no input that determines if player would like to end game or not
    *
    * @return String value that determines if the user exits main game loop or not
    *
    */
   public String endGame(String input){
      if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
         saveGame();
         System.out.println("\nThanks for playing!\n");
         getSc().close();
         return "quit";
      } else {
         System.out.println("\nAwesome, let's continue!\n");
         System.out.print(getAdventure().printCurrentRoom("l"));
         return "";
      }
   }

   /**
    * Handles saving process.
    *
    */
   public void saveGame(){
      String input = yesOrNo("\nWould you like to save your game? (y/n)");
      if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
         chooseSaveName();
         try {
            save();
         } catch (Exception e){
            System.out.println("ERROR - Game could not be saved.");
         }
      } 
   }

   /**
    * Handles save game name choosing.
    *
    */
   private void chooseSaveName(){
      System.out.print("\nWhat would you like to name your save file?\n\n> ");
      String input = getSc().nextLine();
      getPlayer().setSaveName(input);
      System.out.println("\nOkay, your save file will be called '" + getPlayer().getSaveGameName() + "'.");
   }

   /**
    * Handles serialization.
    *
    */
   public void save() throws Exception{
      FileOutputStream fileOutput = new FileOutputStream(getPlayer().getSaveGameName());
      ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

      objectOutput.writeObject(getAdventure());
      objectOutput.writeObject(getPlayer());

      objectOutput.close();
      fileOutput.close();
      System.out.println("Game saved.");
   }


   /* ACCESSORS */

   /**
    * Gets game's Scanner object.
    *
    * @return the current game's Scanner object
    *
    */
   public static Scanner getSc(){
      return sc;
   }

   /**
    * Gets game's Adventure object.
    *
    * @return the current game's Adventure object
    *
    */
   public static Adventure getAdventure(){
      return adventure;
   }

   /**
    * Gets game's Player object.
    *
    * @return the current game's Player object
    *
    */
   public static Player getPlayer(){
      return player;
   }

   /**
    * Gets game's Parser object.
    *
    * @return the current game's Parser object
    *
    */
   public static Parser getParser(){
      return parser;
   }


   /* MUTATORS */

   /**
    * Sets game's Scanner object.
    *
    * @param scanner  the Scanner object to be set
    *
    */
   public void setSc(Scanner scanner){
      sc = scanner;
   }

   /**
    * Sets game's Adventure object.
    *
    * @param ad  the object to be set as game's Adventure
    *
    */
   public static void setAdventure(Adventure ad){
      adventure = ad;
   }

   /**
    * Sets game's Player object.
    *
    * @param play  the object to be set as game's Player
    *
    */
   public void setPlayer(Player play){
      player = play;
   }

   /**
    * Sets game's Parser object.
    *
    * @param parse  the object to be set as game's Parser
    *
    */
   public void setParser(Parser parse){
      parser = parse;
   }


   /* OTHER: */

   /**
    * Handles a 'yes' or 'no' question loop.
    *
    * @param question  the yes or no question being asked
    * @return the user's response to the 'yes' or 'no' question
    *
    */
   public static String yesOrNo(String question) {
      System.out.print(question + "\n\n> ");
      String input = getSc().nextLine();
      while (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n") 
               || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no"))) {
         System.out.println("\nPlease input a valid answer - 'y' or 'n'.");
         System.out.print(question + "\n\n> ");
         input = getSc().nextLine();
      }
      return input;
   }

   /** 
    * Prints Game object in desired format.
    *
    * @return the String to be printed
    *
    */
   public String toString(){
      return ("CURRENT GAME:\n" + getPlayer() + getAdventure());
   }  
}
