import java.util.*;
public class Deck{
    private Stack<Card> deck;
    public Deck(){
        deck = new Stack<Card>();
        for(int i = 2; i <= 14; i++){
            deck.push(new Card(i, Suit.CLUB));
        }
        for(int i = 2; i <= 14; i++){
            deck.push(new Card(i, Suit.SPADE));
        }
        for(int i = 2; i <= 14; i++){
            deck.push(new Card(i, Suit.HEART));
        }
        for(int i = 2; i <= 14; i++){
            deck.push(new Card(i, Suit.DIAMOND));
        }
    }
    public Stack<Card> getDeck(){
        return deck;
    }
    public void fisherYatesShuffle(){
        ArrayList<Card> list = new ArrayList<Card>();
        Random rand = new Random();
        while(!deck.isEmpty()){
            list.add(deck.pop());
        }
        while(!list.isEmpty()){
            int temp = rand.nextInt(list.size());
            deck.push(list.remove(temp));
        }
    }
    public void riffleShuffle(){
        Stack<Card> left = new Stack<Card>();
        Stack<Card> right = new Stack<Card>();
        Random rand = new Random();
        int size = deck.size();
        for(int i = 0; i < size/2; i++){
            left.push(deck.pop());
        }
        while(!deck.isEmpty()){
            right.push(deck.pop());
        }
        while(!left.isEmpty() || !right.isEmpty()){
            int temp = rand.nextInt(5) + 1;
            for(int i = 0; i < temp; i++){
                if(left.size() > 0){
                    deck.push(left.pop());
                }
            }
            temp = rand.nextInt(5) + 1;
            for(int i = 0; i < temp; i++){
                if(right.size() > 0){
                    deck.push(right.pop());
                }
            }
        }
    }
    public void printDeck(){
        Iterator<Card> itr = deck.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}