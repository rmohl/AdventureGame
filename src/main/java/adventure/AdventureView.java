package adventure;

// Visual imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

// Design imports
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


/* SETUP: */

public class AdventureView extends JFrame implements ActionListener{

    private static final long serialVersionUID = 7783168322966475601L;

    // constants made to better handle 'magic number' checkstyle errors
    private static final int TEN = 10;
    private static final int FIFTEEN = 15;

    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    private Game game;
    private Adventure adventure;
    private Player player;
    private Parser parser;

    private JButton quitButton;
    private JButton loadNewFileButton;
    private JButton loadSavedGameButton;
    private JButton changeNameButton;
    private JButton saveGameButton;
    private JButton enterButton;

    private JTextArea gamePlayTextArea;
    private JScrollPane scrollPane;
    private JTextArea inventoryTextArea;

    private JTextField inputField;
    private JLabel nameLabel;

    private GridBagConstraints layoutConst;
    private JFileChooser fileChooser;
    private boolean intro;

    public AdventureView(Game myGame) {

        super();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Adventure Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpDefaultFile(myGame);

        setLayout(new GridBagLayout());
        fileChooser = new JFileChooser();
        makeVisuals();

    }

    private void setUpDefaultFile(Game myGame){
        game = myGame;
        Game.setAdventure(game.generateAdventure(game.loadDefault()));
        adventure = Game.getAdventure();
        player = Game.getPlayer();
        parser = Game.getParser();
        intro = false;
    }

    private void makeVisuals(){
        // buttons
        createQuitButton();
        createLoadNewFileButton();
        createLoadSavedGameButton();
        createChangeNameButton();
        createSaveGameButton();
        createEnterButton();

        //text areas
        createGamePlayTextArea();
        createInventoryTextArea();

        // text field
        createInputField();

        // message label
        createNameLabel();
    }

    // buttons
    private void createQuitButton() {
        quitButton = new JButton("Quit game");
        quitButton.addActionListener(this);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(TEN, TEN, TEN, TEN);
        add(quitButton, layoutConst);
    }

    private void createLoadNewFileButton() {
        loadNewFileButton = new JButton("Load new file");
        loadNewFileButton.addActionListener(this);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(TEN, TEN, TEN, TEN);
        add(loadNewFileButton, layoutConst);
    }

    private void createLoadSavedGameButton() {
        loadSavedGameButton = new JButton("Load saved game");
        loadSavedGameButton.addActionListener(this);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 2;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(TEN, TEN, TEN, TEN);
        add(loadSavedGameButton, layoutConst);
    }

    private void createChangeNameButton() {
        changeNameButton = new JButton("Change name");
        changeNameButton.addActionListener(this);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = (2 + 1);
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(TEN, TEN, TEN, TEN);
        add(changeNameButton, layoutConst);
    }

    private void createSaveGameButton() {
        saveGameButton = new JButton("Save game");
        saveGameButton.addActionListener(this);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = (2 + 2);
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(TEN, TEN, TEN, TEN);
        add(saveGameButton, layoutConst);
    }

    private void createEnterButton() {
        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = (2 + 1);
        layoutConst.gridy = TEN / 2;
        layoutConst.insets = new Insets(TEN / 2, TEN, FIFTEEN, TEN);
        layoutConst.gridwidth = 2;
        add(enterButton, layoutConst);
    }


    // text areas
    private void createGamePlayTextArea() {
        gamePlayTextArea = new JTextArea(FIFTEEN + TEN, 2 * 2 * TEN);
        gamePlayTextArea.setEditable(false);
        gamePlayTextArea.setText("Welcome to Rachael's default adventure game!\n\nWhat is your name?");
        createScrollPlane();
    }

    private void createScrollPlane() {
        scrollPane = new JScrollPane(gamePlayTextArea);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.insets = new Insets(TEN, 2 * TEN, 2 * TEN, TEN);
        layoutConst.gridwidth = (2 + 1);
        layoutConst.gridheight = 2 * 2;
        add(scrollPane, layoutConst);
    }

    private void createInventoryTextArea() {
        inventoryTextArea = new JTextArea(TEN + 2 + 1, 2 * TEN);
        inventoryTextArea.setEditable(false);
        inventoryTextArea.setText(player.displayInventory());

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = (2 + 1);
        layoutConst.gridy = 2;
        layoutConst.insets = new Insets(TEN, TEN, TEN, TEN);
        layoutConst.gridwidth = 2;
        layoutConst.gridheight = 2;
        add(inventoryTextArea, layoutConst);
    }


    // text field
    private void createInputField() {
        inputField = new JTextField(FIFTEEN);
        inputField.setEditable(true);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = (2 + 1);
        layoutConst.gridy = 2 * 2;
        layoutConst.insets = new Insets(FIFTEEN, TEN, TEN / 2, TEN);
        layoutConst.gridwidth = 2;
        add(inputField, layoutConst);
    }


    // message label
    private void createNameLabel() {
        nameLabel = new JLabel(" ");

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.insets = new Insets(2 * TEN, TEN, 2 * TEN, TEN);
        layoutConst.gridwidth = 2 * 2;
        add(nameLabel, layoutConst);
    }


    /* GAMEPLAY: */

    public final void actionPerformed(ActionEvent e) {
        String input = "";
        File readFile = null;

        JButton sourceEvent = (JButton) e.getSource();
        handleButton(sourceEvent, input, readFile);

        finalVisuals();

    }

    private void handleButton(JButton sourceEvent, String input, File readFile){
        if (sourceEvent == enterButton) {
            handleEnterButton(input);
            
        } 
        if (sourceEvent == changeNameButton) {
            gamePlayTextArea.setText("What would you like to change your name to?");

        } 
        if (sourceEvent == saveGameButton) {
            gamePlayTextArea.setText("What would you like to name your save file?");
        }
        handleLoadButton(sourceEvent, readFile);
    }

    private void handleLoadButton(JButton sourceEvent, File readFile){
        if (sourceEvent == loadNewFileButton) {
            handleLoadFileButton(readFile);

        } 
        if (sourceEvent == loadSavedGameButton) {
            handleLoadSaveButton(readFile);

        }
        if (sourceEvent == quitButton) {
            quitGame();
        }
    }

    private void finalVisuals() {
        if (!intro && !player.getName().equals("")) {
            gamePlayTextArea.setText("Hello, " + player.getName() + "!\n" + Game.WELCOME_MESSAGE
            + adventure.printCurrentRoom("l"));
            intro = true;
        }
        if (!player.getName().equals("")) {
            nameLabel.setText("You are playing as " + player.getName() + ".");
        }
        inventoryTextArea.setText(player.displayInventory());
        inputField.setText("");
    }

    private void handleEnterButton(String input){
        input = inputField.getText();

        if (gamePlayTextArea.getText().equals("What would you like to change your name to?")) {
            handleEnterButtonChangeName(input);

        } else if (player.getName().equals("")) {
            player.setName(input.trim());

        } else if (gamePlayTextArea.getText().equals("What would you like to name your save file?")) {
            handleEnterButtonSave(input);
        
        }else {
            handleEnterButtonHandleCommand(input);
        }
    }

    private void handleEnterButtonChangeName(String input){
        player.setName(input.trim());
        gamePlayTextArea.setText("You have changed your name to " + player.getName() + "!\n\n"
                                + adventure.printCurrentRoom("s"));
    }

    private void handleEnterButtonSave(String input){
        player.setSaveName(input);
        gamePlayTextArea.setText("Okay, your save file will be called '" + player.getSaveGameName() + "'.\n\n"
                                + adventure.printCurrentRoom("s"));
        try {
            game.save();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Game could not be saved.");
        }
    }

    private void handleEnterButtonHandleCommand(String input){
        try {
            gamePlayTextArea.setText(parser.handleCommandInput(parser.parseUserCommand(input)));
        } catch (InvalidCommandException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void handleLoadFileButton(File readFile){
        // got rid of filechooserVal
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            readFile = fileChooser.getSelectedFile();
            if (readFile.canRead()) {
                try {
                    loadFile(readFile);
                } catch (Exception ex) {
                    loadFileError(ex);
                }
            }
        }
    }

    private void loadFile(File readFile) throws Exception{
        adventure.initializeAdventure(game.loadAdventureJson(readFile.getPath()));
        Game.setAdventure(game.generateAdventure(game.loadAdventureJson(readFile.getPath())));
        adventure = Game.getAdventure();
        player = Game.getPlayer();
        parser = Game.getParser();
        gamePlayTextArea.setText("Your file was loaded successfully!\n\nWelcome to the '" 
                                + readFile.getName() + "' adventure game!\n\nWhat is your name?");
        intro = false;
        nameLabel.setText(" ");
    }

    private void loadFileError(Exception ex) {
        if (ex.getMessage() == null) {
            JOptionPane.showMessageDialog(this, "Something went wrong while opening the file.");
        } else {
            JOptionPane.showMessageDialog(this, "FILE ERROR - " + ex.getMessage());
        }
    }

    private void handleLoadSaveButton(File readFile){
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            readFile = fileChooser.getSelectedFile();
            if (readFile.canRead()) {
                try {
                    loadSave(readFile);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Something went wrong while loading the file.");
                }
            }
        }
    }

    private void loadSave(File readFile) throws Exception{
        game.load(readFile.getPath());
        adventure = Game.getAdventure();
        player = Game.getPlayer();
        parser = Game.getParser();
        gamePlayTextArea.setText("Welcome back, " + player.getName() + "!\n\n"
                                + adventure.printCurrentRoom("l"));
        intro = true;
    }

    private void quitGame(){
        System.exit(1);
    }


    /* MAIN */

    public static void main(String[] args) {
        Game game = new Game();
        AdventureView gui = new AdventureView(game);
        gui.setVisible(true);
    }

}
