public class DialogueTree {
   private DialogueNode rootNode;
   private DialogueNode currentNode;

   public DialogueTree(DialogueNode rootNode) {
       this.rootNode = rootNode;
       this.currentNode = rootNode;
   }

   public DialogueNode continueTree(String response) {
       DialogueNode nextNode = currentNode.getResponse(response);
       if (nextNode != null) {
           currentNode = nextNode;
       }
       return currentNode;
   }

   public DialogueNode getCurrentNode() {
       return currentNode;
   }
   public DialogueNode getRootNode(String responseName) {
        return rootNode;
    }
}


// public class DialogueTree {
//     private DialogueNode rootNode;
 
//     public DialogueTree(DialogueNode rootNode) {
//         this.rootNode = rootNode;
//     }

//     public DialogueNode getRootNode(String responseName) {
//         return rootNode;
//     }
 
//     public DialogueNode getNextNode(String responseName) {
//         return rootNode.getResponse(responseName);
//     }
//  }