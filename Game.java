import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Game {
    private static HashMap<String, Character> characters;
    HashMap<String, DialogueNode> dialogueMap = new HashMap<>();
    Art art = new Art();
    private static boolean talkedToBeggar = false;
    private static boolean gotBread = false;

    public Game() {
        // HashMap<String, Character> characters = new HashMap<>();

        characters = new HashMap<>();
        art.Eldoria();
    }

    public void restart() {
        // Reset game variables
        talkedToBeggar = false;
        gotBread = false;
     
        // Clear the dialogue map and character map
        dialogueMap.clear();
        characters.clear();
     
        // Reinitialize the game
        this.init();
     }
     

    public void addCharacter(Character character) {
        characters.put(character.getName(), character);
    }

    public Character getCharacter(String name) {
        return characters.get(name);
    }

    public void handlePlayerInteraction(Character character) {
        String playerResponse = "";
        DialogueNode currentNode = character.getNode();
        
        // Display the dialogue
        System.out.println("\n ------------ \n \n " + currentNode.getDialogue());

        // Display the responses
        System.out.println("\n ------------ \n You can answer:");
        for (Map.Entry<String, DialogueNode> entry : currentNode.getAllResponses().entrySet()) {
            System.out.println(" - [" + entry.getKey() + "]");
        }

        // Get the player's response
        playerResponse = System.console().readLine();

        // Update the game's state and display the next line of dialogue
        currentNode = character.getNextNode(playerResponse);
        handlePlayerInteraction(character, playerResponse, currentNode);

    }

    public void handlePlayerInteraction(Character character, String playerResponse, DialogueNode currentNode) {
        // currentNode = character.interact(playerResponse);

        // Display the dialogue
        System.out.println("\n ------------ \n \n " + currentNode.getDialogue());

        if (currentNode.equals(dialogueMap.get("Bakery7"))) {
            gotBread = true;
        }
         if (currentNode.equals(dialogueMap.get("Beggar2"))) {
            talkedToBeggar = true;
        }
         if (currentNode.equals(dialogueMap.get("HelpWizard4"))) {
            Art.GameOver();
            System.out.println("\nYou lost your apprenticeship.");
            System.exit(0);
        }
        if (currentNode.getAllResponses().isEmpty()) {
            // If dialogue is over, stop the recursion
            return;
        }
        if (playerResponse.equals("exit")) {
            return;
        }

        // Display the responses
        System.out.println("\n ------------ \n You can answer:");

        for (Map.Entry<String, DialogueNode> entry : currentNode.getAllResponses().entrySet()) {
            System.out.println(" - [" + entry.getKey() + "]");
        }

        // Get the player's response
        playerResponse = System.console().readLine();

        // Update the game's state and display the next line of dialogue
        currentNode = character.getNextNode(playerResponse);

        handlePlayerInteraction(character, playerResponse, currentNode);
    }

    public static void main(String[] args) {
        // Create an instance of the Game class
        Game game = new Game();
     
        // Game loop
        while (true) {
            // Call the init method to initialize the game
            game.init();
     
            // Ask the player if they want to play again
            System.out.println("\nWant to play again? \n[Y] \n[N]");
            String playAgain = System.console().readLine();
            if (!playAgain.equals("Y")) {
                break;
            }
     
            // Restart the game
            game.restart();
        }
     }
     
    public void init() {
     
     
        // Create an instance of the Game class
        Game game = new Game();

        // Read in the dialogue
        try {
            BufferedReader br = new BufferedReader(new FileReader("Dialogue.txt"));
            String line = null;

            // First pass: Add all DialogueNodes to the dialogueMap
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                String name = parts[0].trim();
                String dialogue = parts[1].trim();

                DialogueNode dialogueNode = new DialogueNode(name, dialogue);
                game.dialogueMap.put(name, dialogueNode);
            }

            // Second pass: Add responses to the DialogueNodes
            br = new BufferedReader(new FileReader("Dialogue.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

          String name = parts[0].trim();

                DialogueNode dialogueNode = game.dialogueMap.get(name);
                if (parts.length >2) {
    
                    for (int i = 2; i < parts.length; i += 2) {
                        String response = parts[i].trim();
                        String nextNodeName = parts[i + 1].trim();

                        DialogueNode nextNode = game.dialogueMap.get(nextNodeName);
                        dialogueNode.addResponse(response, nextNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TEST FOR NODES
        // for (String key : game.dialogueMap.keySet()) {
        //     System.out.println("Key: " + key);
        // }

        // for (DialogueNode value : game.dialogueMap.values()) {
        //     System.out.println("Value: " + value);
        //     System.out.println("Dialogue:" + value.getDialogue());
        //     System.out.println("Responens: " + value.getAllResponses() + "\n");
        //     // value.getNextNode();
        // }

        // Create the dialogue trees
        DialogueTree dialogueTreeBakery = new DialogueTree(game.dialogueMap.get("Bakery1"));
        DialogueTree dialogueTreeWizard = new DialogueTree(game.dialogueMap.get("Wizard1"));
        DialogueTree dialogueTreeHelpWizard = new DialogueTree(game.dialogueMap.get("HelpWizard1"));
        DialogueTree dialogueTreeGuard = new DialogueTree(game.dialogueMap.get("Guard1"));
        DialogueTree dialogueTreeTavern = new DialogueTree(game.dialogueMap.get("TavernLady1"));
        DialogueTree dialogueTreeDrunkenGuy = new DialogueTree(game.dialogueMap.get("DrunkenGuy1"));
        DialogueTree dialogueLittleGirl = new DialogueTree(game.dialogueMap.get("LittleGirl1"));
        DialogueTree dialogueTreeOldWomen = new DialogueTree(game.dialogueMap.get("OldWomen1"));
        DialogueTree dialogueTreeBeggar = new DialogueTree(game.dialogueMap.get("Beggar1"));
        DialogueTree dialogueTreeHungryBeggar = new DialogueTree(game.dialogueMap.get("HungryBeggar1"));

        // Create the characters
        try {
            File myObj = new File("Characters.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                String desc = parts[1];

                // Create a new Character object and add it to the game
                Character character = new Character(name, desc);
                // game.addCharacter(character);
                characters.put(name, character);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Connect characters to dialogueTrees
        characters.get("Wise Wizard").setdialogueTree(dialogueTreeWizard);
        characters.get("Help Wizard").setdialogueTree(dialogueTreeHelpWizard);
        characters.get("Bakery Lady").setdialogueTree(dialogueTreeBakery);
        characters.get("Village Guard").setdialogueTree(dialogueTreeGuard);
        characters.get("Tavern Lady").setdialogueTree(dialogueTreeTavern);
        characters.get("Drunken Guy").setdialogueTree(dialogueTreeDrunkenGuy);
        characters.get("Little Girl").setdialogueTree(dialogueLittleGirl);
        characters.get("Old Women").setdialogueTree(dialogueTreeOldWomen);
        characters.get("Beggar").setdialogueTree(dialogueTreeBeggar);
        characters.get("Hungry Beggar").setdialogueTree(dialogueTreeHungryBeggar);

        game.handlePlayerInteraction(characters.get("Wise Wizard"));

        System.out.println(
                "\n You've left the Tower, where do you want to go now? \n The [Tower] \n The [Village] \n The [Forest]");
        String playerResponse = System.console().readLine();
        boolean gameActive = true;

        while (gameActive) {
            switch (playerResponse) {
                case "Tower":
                boolean inTower = true;
                    while (inTower) {
                    game.handlePlayerInteraction(characters.get("Help Wizard"));
                    inTower = false;
                                System.out.println(
                                        "\n You've left the Tower, where do you want to go now? \n The [Tower] \n Back to the [Village] \n The [Forest]");
                                playerResponse = System.console().readLine();           
                    }
                    break;
                
                case "Village":
                System.out.println("\n So you've decided to go to the Village... \n");
                    boolean inVillage = true;
                    while (inVillage) {
                        System.out.println("You see some people and places, where do you want to go? \n [Bakery] \n" + //
                                " [Beggar] \n [Tavern] \n [Guard] \n [Little Girl] \n [Old Women] \n [Out of the village]");
                        playerResponse = System.console().readLine();
                        switch (playerResponse) {
                            case "Out of the village":
                                inVillage = false;
                                System.out.println(
                                        "\n You've left the Village, where do you want to go now? \n The [Tower] \n The [Village] \n The [Forest]");
                                playerResponse = System.console().readLine();
                                break;
                            case "Bakery":
                                game.handlePlayerInteraction(characters.get("Bakery Lady"));
                                break;
                            case "Beggar":
                                if(gotBread){
                                    game.handlePlayerInteraction(characters.get("Beggar"));
                                }else{
                                    game.handlePlayerInteraction(characters.get("Hungry Beggar"));
                                }
                                break;
                            case "Guard":
                                game.handlePlayerInteraction(characters.get("Village Guard"));
                                break;
                            case "Tavern":
                                game.handlePlayerInteraction(characters.get("Tavern Lady"));
                                break;
                            case "Little Girl":
                                game.handlePlayerInteraction(characters.get("Little Girl"));
                                break;
                            case "Old Women":
                                game.handlePlayerInteraction(characters.get("Old Women"));
                                break;
                            case "Drunken Guy":
                                game.handlePlayerInteraction(characters.get("Drunken Guy"));
                                break;

                            default:
                                System.out.println("That's not a valid option.");
                                break;
                        }
                    }
                    break;
                case "Forest":
                    if (talkedToBeggar) {
                        System.out.print("YOU DID IT! YOU Won AGAINST THE DEMON!");
                        Art.Win();
                        System.exit(0);
                    } else {
                        Art.GameOver();
                        System.out.println("\nYou have no idea on how to beat the demon so you died.");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("That's not a valid option.");
                    break;
            }
        }

    }
    

}
