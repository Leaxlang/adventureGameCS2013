public class DialogueTree {
    private DialogueNode rootNode;
 
    public DialogueTree(DialogueNode rootNode) {
        this.rootNode = rootNode;
    }
 
    public DialogueNode getNextNode(String responseName) {
        return rootNode.getResponse(responseName);
    }
 }