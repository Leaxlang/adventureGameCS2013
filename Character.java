public class Character {
    private String name;
    private String description;
    private DialogueTree dialogueTree;
 
    public Character(String name, String description, DialogueTree dialogueTree) {
        this.name = name;
        this.description = description;
        this.dialogueTree = dialogueTree;
    }
    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Character(){}

    public void setName(String name){
        this.name = name;
    }
    public void setDesc(String description){
        this.description = description;
    }
    public void setdialogueTree(DialogueTree dialogueTree){
        this.dialogueTree = dialogueTree;
    }

    public String getName() {
        return name;
    }
 
    public String getDescription() {
        return description;
    }
    public DialogueNode getNode() {
        return dialogueTree.getRootNode(name);
    }
 public DialogueNode getNextNode(String choice) {
        return dialogueTree.continueTree(choice);
    }
 
//      public DialogueNode interact(String choice) {
//        return dialogueTree.getNextNode(choice);
//    }
 }