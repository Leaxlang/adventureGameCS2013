import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.tree.TreeNode;

public class Game {
   private static final String VILLAGE = "Eldoria";
    private static HashMap<String, Character> characters;
    private static boolean talkedToBeggar = false;
    private static boolean gotBread = false; 


    DialogueNode nodeBakery1 = new DialogueNode("Bakery Lady: Hello, how can I help you?");
        DialogueNode nodeBakery2 = new DialogueNode("Bakery Lady: I don't know much, but I heard it's in the forest.");
        DialogueNode nodeBakery3 = new DialogueNode("Bakery Lady: NO! When you go you'll die! Like many villagers already have!");
        DialogueNode nodeBakery4 = new DialogueNode("Bakery Lady: There have been some cases where the children in the village disappeared. We believe that was the demon too!");
        DialogueNode nodeBakery5 = new DialogueNode("Bakery Lady: The demon hides in the shadow's. You can't see him until it's already too late. ");
        DialogueNode nodeBakery6 = new DialogueNode("Bakery Lady: Do you want to buy some bread now?");
        DialogueNode nodeBakery7 = new DialogueNode("Bakery Lady: Here you go! Have a nice day and good luck on your quest!");
        DialogueNode nodeBakery8 = new DialogueNode("Bakery Lady: Then why are you in a bakery??");

   public Game() {
    // HashMap<String, Character> characters = new HashMap<>();
    
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
        System.out.println("\n ------------ \n You can answer:");
        for (Map.Entry<String, DialogueNode> entry : currentNode.getAllResponses().entrySet()) {
            System.out.println(" - [" + entry.getKey() + "]");
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

        if(currentNode.equals(nodeBakery7)){
            gotBread = true;
            System.out.print("YOU GOT HTE BREAD!");
        }
 
         if (currentNode.getAllResponses().isEmpty()) {
                    // If it doesn't, stop the recursion
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

//         try {
//    File myObj = new File("Bakery.txt");
//    Scanner scan = new Scanner(myObj);
//    DialogueNode currentNode = new DialogueNode("Bakery Lady: Hello, how can I help you?");
//    while (scan.hasNextLine()) {
//        String line = scan.nextLine();
//        String[] parts = line.split(",");
//        String responseText = parts[0];
//        String nextNodeName = parts[1];
//        DialogueNode nextNode = new DialogueNode(nextNodeName);
//        currentNode.addResponse(responseText, nextNode);
//        currentNode = nextNode;
//    }
//    scan.close();
// } catch (FileNotFoundException e) {
//    System.out.println("An error occurred.");
//    e.printStackTrace();
// }


//         try {
//             File myObj = new File("Dialogue.txt");
//             Scanner scan = new Scanner(myObj);
//             while (scan.hasNextLine()) {
//                 String line = scan.nextLine();
//                 String[] parts = line.split("=");
//                 DialogueNode node = new DialogueNode(parts[0]);
//                 for (int i = 1; i < parts.length; i += 2) {
//                     String response = parts[i];
//                     String nextNodeText = parts[i + 1];
//                     DialogueNode nextNode = new DialogueNode(nextNodeText);
//                     node.addResponse(response, nextNode);
//                 }

//                  if (lineParts.length > 1) {
//         // create children
//         TreeNode<String> child = getOrCreateTreeNode(stringToTreeNode, lineParts[1]);
//         parent.leftChild = child;
//         child.parent = parent; 
//         for (int i = 2; i < lineParts.length; i++) {
//             TreeNode<String> nextChild = getOrCreateTreeNode(stringToTreeNode, lineParts[i]);
//             nextChild.parent = parent;
//             child.rightSibling = nextChild;
//             child = nextChild;
//         }
//             }
//             scan.close();
//          } catch (FileNotFoundException e) {
//             System.out.println("An error occurred.");
//             e.printStackTrace();
//          }

        //  Path path = Paths.get("Dialogue.txt");
        //  List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        // for (String line : lines) {
        //     String[] parts = line.split("\\.addResponse\\(\"");
        //     String nodeName = parts[0];
        //     String response = parts[1].split("\", nodeBakery")[0];
        //     String nextNodeName = parts[1].split("\", nodeBakery")[1];
        //     DialogueNode node = nodes.get(nodeName);
        //     DialogueNode nextNode = nodes.get(nextNodeName);
        //     node.addResponse(response, nextNode);
        // }
       

        
        // Initialize characters
        //WIZARD
        //Create the dialogue nodes 
        DialogueNode nodeWizard1 = new DialogueNode("\n Wizard: Good morning, the time has come for your next task! You will have to go to the village of +" + VILLAGE +". They are haunted by a demon. Your task is to defeat the demon and to figure out how to defeat him. What are you waiting for? Let's go!");
        DialogueNode nodeWizard2 = new DialogueNode("Wizard: Good luck!");
        //Connect the nodes
        nodeWizard1.addResponse("Already on my way!", nodeWizard2);

        //BAKERY
        // Create the dialogue nodes
        DialogueNode nodeBakery1 = new DialogueNode("Bakery Lady: Hello, how can I help you?");
        DialogueNode nodeBakery2 = new DialogueNode("Bakery Lady: I don't know much, but I heard it's in the forest.");
        DialogueNode nodeBakery3 = new DialogueNode("Bakery Lady: NO! When you go you'll die! Like many villagers already have!");
        DialogueNode nodeBakery4 = new DialogueNode("Bakery Lady: There have been some cases where the children in the village disappeared. We believe that was the demon too!");
        DialogueNode nodeBakery5 = new DialogueNode("Bakery Lady: The demon hides in the shadow's. You can't see him until it's already too late. ");
        DialogueNode nodeBakery6 = new DialogueNode("Bakery Lady: Do you want to buy some bread now?");
        DialogueNode nodeBakery7 = new DialogueNode("Bakery Lady: Here you go! Have a nice day and good luck on your quest!");
        DialogueNode nodeBakery8 = new DialogueNode("Bakery Lady: Then why are you in a bakery??");

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
         
        // Character wizard = new Character("Wise Wizard", "This is the wise wizards who's apprentice you are.", dialogueTreeWizard);
        // Character bakery = new Character("Bakery Lady", "She is the woman behind the counter of the town bakery.", dialogueTreeBakery);

        // // Add the characters to the game
        // game.addCharacter(bakery);
        // game.addCharacter(wizard);

    characters.get("Wise Wizard").setdialogueTree(dialogueTreeWizard);    
    characters.get("Bakery Lady").setdialogueTree(dialogueTreeBakery);


        game.handlePlayerInteraction(characters.get("Wise Wizard"));

        System.out.println("\n You've left the Tower, where do you want to go now? \n The [Tower] \n The [Village] \n The [Forest]");
String playerResponse = System.console().readLine();
boolean gameActive = true;

while (gameActive){
   switch (playerResponse) {
       case "Tower":
           // Handle tower interaction
           break;
       case "Village":
           boolean inVillage = true;
           while(inVillage){
               System.out.println("\n So you've decided to go to the Village... \n");
               System.out.println("You see some people and places, where do you want to go? \n [Bakery] \n" + //
                       " [Beggar] \n [Out of the village]");
               playerResponse = System.console().readLine();
               switch (playerResponse) {
                  case "Bakery":
                  handlePlayerInteraction(characters.get("Bakery Lady"));
                      // Handle bakery interaction
                      break;
                  case "Out of the village":
                      inVillage = false;
                      System.out.println("\n You've left the Village, where do you want to go now? \n The [Tower] \n The [Village] \n The [Forest]");
                      playerResponse = System.console().readLine();
                      break;
                       case "Beggar":
                            System.out.print("Secret solves.");
                            talkedToBeggar = true; 
                            break;
                  default:
                      System.out.println("That's not a valid option.");
                      break;
               }
           }
           break;
       case "Forest":
           if(talkedToBeggar){
                        System.out.print("YOU DID IT!");
                        System.exit(0);
                    }
                    else{
                        System.out.print("U ded");
                    }
           break;
       default:
           System.out.println("That's not a valid option.");
           break;
   }
}

            }




        // System.out.println("YOU MADE IT OUT!");
    


    }

