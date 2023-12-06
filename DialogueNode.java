public class DialogueNode {
    private String dialogue;
    private HashMap<String, DialogueNode> responses;
 
    public DialogueNode(String dialogue) {
        this.dialogue = dialogue;
        responses = new HashMap<>();
        // Initialize responses
    }
 
    public String getDialogue() {
        return dialogue;
    }
 
    public DialogueNode getResponse(String responseName) {
        return responses.get(responseName);
    }
 }