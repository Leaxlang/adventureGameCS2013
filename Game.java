import java.util.HashMap;
import java.util.Map;

public class Game {
   private static final String VILLAGE = null;
private HashMap<String, Character> characters;

   public Game() {
       characters = new HashMap<>();
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
 
    // while (playerResponse.equals("")) {
        // Display the dialogue
        System.out.println(currentNode.getDialogue());
 
        // Display the responses
        System.out.println("You can answer:");
        for (Map.Entry<String, DialogueNode> entry : currentNode.getAllResponses().entrySet()) {
            System.out.println(entry.getKey());
        }
 
        // Get the player's response
        playerResponse = System.console().readLine();
 
        // Update the game's state and display the next line of dialogue
        currentNode = character.getNextNode(playerResponse);
         handlePlayerInteraction(character, playerResponse, currentNode);

    // }   
   }
    public void handlePlayerInteraction(Character character, String playerResponse, DialogueNode currentNode) {
    // currentNode = character.interact(playerResponse);
    
        // Display the dialogue
        System.out.println(currentNode.getDialogue());
 
         if (currentNode.getAllResponses().isEmpty()) {
                    // If it doesn't, stop the recursion
                    return;
                }
        if (playerResponse.equals("exit")) {
                            return;
                        }

        // Display the responses
        System.out.println("You can answer:");
        for (Map.Entry<String, DialogueNode> entry : currentNode.getAllResponses().entrySet()) {
            System.out.println(entry.getKey());
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


           
        
        // Initialize characters
        //WIZARD
        //Create the dialogue nodes 
        DialogueNode nodeWizard1 = new DialogueNode("Good morning, the time has come for your next task! You will have to go to the village of +" + VILLAGE +". They are haunted by a demon. Your task is to defeat the demon and to figure out how to defeat him. What are you waiting for? Let's go!");
        DialogueNode nodeWizard2 = new DialogueNode("Good luck!");
        //Connect the nodes
        nodeWizard1.addResponse("Already on my way!", nodeWizard2);

        //BAKERY
        // Create the dialogue nodes
        DialogueNode nodeBakery1 = new DialogueNode("Hello, how can I help you?");
        DialogueNode nodeBakery2 = new DialogueNode("I don't know much, but I heard it's in the forest.");
        DialogueNode nodeBakery3 = new DialogueNode("NO! When you go you'll die! Like many villagers already have!");
        DialogueNode nodeBakery4 = new DialogueNode("There have been some cases where the children in the village disappeared. We believe that was the demon too!");
        DialogueNode nodeBakery5 = new DialogueNode("The demon hides in the shadow's. You can't see him until it's already too late. ");
        DialogueNode nodeBakery6 = new DialogueNode("Do you want to buy some bread now?");
        DialogueNode nodeBakery7 = new DialogueNode("Here you go! Have a nice day and good luck on your quest!");
        DialogueNode nodeBakery8 = new DialogueNode("Then why are you in a bakery??");

        // Connect the nodes
        nodeBakery1.addResponse("Can I have some bread?", nodeBakery7);
        nodeBakery1.addResponse("What can you tell me about the demon?", nodeBakery2);
        nodeBakery2.addResponse("Have you ever been there?", nodeBakery3);
        nodeBakery2.addResponse("Has the demon ever been to the village?", nodeBakery4);
        nodeBakery3.addResponse("What does the demon do?", nodeBakery5);
        nodeBakery3.addResponse("Thank you for your help!", nodeBakery6);
        nodeBakery4.addResponse("What does the demon do?", nodeBakery5);
        nodeBakery4.addResponse("Thank you for your help!", nodeBakery6);
        nodeBakery5.addResponse("Thank you for your help!", nodeBakery6);
        nodeBakery6.addResponse("Yes, please!",nodeBakery7);
        nodeBakery6.addResponse("No, I don't like bread.",nodeBakery8);

       
        
        // Create the dialogue tree
        DialogueTree dialogueTreeBakery = new DialogueTree(nodeBakery1);
        DialogueTree dialogueTreeWizard = new DialogueTree(nodeWizard1);

        // Create the characters
        Character wizard = new Character("Wise Wizard", "This is the wise wizards who's apprentice you are.", dialogueTreeWizard);
        Character baker = new Character("Bakery Lady", "She is the woman behind the counter of the town bakery.", dialogueTreeBakery);

        // Add the characters to the game
        game.addCharacter(baker);
        game.addCharacter(wizard);



        game.handlePlayerInteraction(wizard);

        System.out.println("You've left the Tower, where do you want to go now? \n The [Tower] \n The [Village] \n The [Forest]");
            String playerResponse = System.console().readLine();

            switch (playerResponse) {
                case "Tower":
                    System.out.println("So you've decided to go to the Village... /n");

                    boolean inVillage = true;
                    while(inVillage){
                        System.out.println("You see some people and places, where do you want to go? /n [Bakery] /[Out of the village]");
                        String villageResponse = System.console().readLine();
                        switch (villageResponse) {
                            case "Bakery":
                                    game.handlePlayerInteraction(baker);
                                break;
                            case "Out of the village":
                                inVillage = false;
                            break;
                        
                            default:
                            System.out.println("That's not a valid option.");
                                break;
                        }
                    }

                    break;
                case "Village":

                    break;

                case "Forest":

                    break;
                default:
                    break;
            }




        // System.out.println("YOU MADE IT OUT!");
    


    }
}
