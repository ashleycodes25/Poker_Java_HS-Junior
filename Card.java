public class Card{
   private int value;
   private Suit suit;
   public Card(int value, Suit suit){
       this.value = value;
       this.suit = suit;
   }
   public int getValue(){
       return value;
   }
   public Suit getSuit(){
       return suit;
   }
   public String getTextValue(){
       if(value >= 2 && value <= 10){
           return String.valueOf(value);
       }
       else if(value == 11){
           return "Jack";
       }
       else if(value == 12){
           return "Queen";
       }
       else if(value == 13){
           return "King";
       }
       else if(value == 14){
           return "Ace";
       }
       return "";
   }
   public String toString(){
       return getTextValue() + suit.toString();
   }
}