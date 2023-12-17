import java.util.Scanner;

public class finalgameTest {
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);

       System.out.println("You will load up your spellbook with 5 spells.");
       System.out.println("You can choose a fire spell ('f'), or an ice spell ('i').");
       System.out.println("But you may only carry 5 in total, so choose wisely.");

    //    char spells = input.next().charAt(0);
       char spellCheck;
       int spellsCounter = 0;

       CharStack stack = new CharStack(5);

       for(int i = 0; i < 5; i++){

        char spells = input.next().charAt(0);
           if (spells == 'f'){
               System.out.println("You stocked a fire spell.");
               stack.push(spells);
               spellsCounter++;
           }
           else if (spells == 'i') {
               System.out.println("You stocked an ice spell.");
               stack.push(spells);
               spellsCounter++;
           }
           else {
               System.out.println("Input invalid. That is not a spell. Farewell.");
               System.exit(0);
           }
       }

       for(int i = 0; i < 5; i++) {
           spellCheck = stack.peek();

           if(spellCheck == 'f') {
               System.out.println("You cast a fire spell.");
               stack.pop();
               spellsCounter--;
           }
           else if(spellCheck == 'i') {
               System.out.println("You cast an ice spell.");
               stack.pop();
               spellsCounter--;
           }
           else {
               System.out.println("You are out of spells");
               stack.empty();
           }
       }
   }
}

class CharStack{
   char[] data;
   int t;

   CharStack(int capacity){ data = new char[capacity]; t = -1;}
   void push(char ch){ data[++t] = ch;}
   void pop(){t--;}
   char peek(){ return data[t];}
   boolean empty(){ return t < 0;}
}

