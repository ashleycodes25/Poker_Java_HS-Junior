import java.util.*;
public class Player{
    private ArrayList<Card> hand;
    private String name;
    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public String getName(){
        return name;
    }

    public List<Card> getHand(){
        return hand;
    }

    public int blackJackHandValue(){
        int count = 0;
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).getValue() >= 2 && hand.get(i).getValue() <= 10){
                count += hand.get(i).getValue();
            }
            else if(hand.get(i).getValue() == 11 || hand.get(i).getValue() == 12 || hand.get(i).getValue() == 13){
                count += 10;
            }
            else if(hand.get(i).getValue() == 1){
                count += 11;
            }
        }
        for(int i = 0; i < hand.size(); i++){
            if(count > 21 && hand.get(i).getValue() == 1){
                count -= 10;
            }
        }
        return count;
    }

    public void printHand(){
        System.out.print("[");
        for(int i = 0; i < hand.size(); i++){
            System.out.print(hand.get(i).toString());
            if(i < hand.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void sortHand(){
        for(int i = 0; i < hand.size(); i++){
            for(int j = 0; j < hand.size()-1; j++){
                int cardOne = hand.get(j).getValue();
                int cardTwo = hand.get(j+1).getValue();
                if(cardTwo < cardOne){
                    Card temp = hand.get(j);
                    hand.set(j, hand.get(j+1));
                    hand.set(j+1, temp);
                }
            }
        }
    }

    public int checkHand(){
        if(bestConsecutiveCards() && sameSuit()){
            return 1;
        }
        else if(consecutiveCards() && sameSuit()){
            return 2;
        }
        else if(fourOfAKind()){
            return 3;
        }
        else if(fullHouse()){
            return 4;
        }
        else if(sameSuit()){
            return 5;
        }
        else if(consecutiveCards()){
            return 6;
        }
        else if(threeOfAKind()){
            return 7;
        }
        else if(hasTwoPairs()){
            return 8;
        }
        else if(hasPair()){
            return 9;
        }
        else{
            return 10;
        }
    }

    public boolean sameSuit(){
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getSuit() != hand.get(i+1).getSuit()){
                return false;
            }
        }
        return true;
    }

    public boolean consecutiveCards(){
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() != hand.get(i+1).getValue()-1){
                return false;
            }
        }
        return true;
    }

    public boolean bestConsecutiveCards(){
        if(hand.get(0).getValue() != 10){
            return false;
        }
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() != hand.get(i+1).getValue()-1){
                return false;
            }
        }
        return true;
    }
    
    public boolean fullHouse(){
        Set<Integer> set = new HashSet<Integer>();
        int count = 0;
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                set.add(hand.get(i).getValue());
                count++;
            }
        }
        if(count == 3 && set.size() == 2){
            return true;
        }
        return false;
    }

    public boolean fourOfAKind(){
        int count = 0;
        int handValue = 0;
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                if(handValue == 0){
                    handValue = hand.get(i).getValue();
                    count++;
                }
                else if(hand.get(i).getValue() == handValue){
                    count++;
                }
            }
        }
        if(count == 3){
            return true;
        }
        return false;
    }

    public boolean threeOfAKind(){
        int count = 0;
        int handValue = 0;
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                if(handValue == 0){
                    handValue = hand.get(i).getValue();
                    count++;
                }
                else if(hand.get(i).getValue() == handValue){
                    count++;
                }
            }
        }
        if(count == 2){
            return true;
        }
        return false;
    }

    public boolean hasPair(){
        int count = 0;
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                count++;
            }
        }
        if(count == 1){
            return true;
        }
        return false;
    }

    public boolean hasTwoPairs(){
        int count = 0;
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                count++;
            }
        }
        if(count == 2){
            return true;
        }
        return false;
    }

    public int ofAKindValue(){
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                return hand.get(i).getValue();
            }
        }
        return 0;
    }
    
    public List<Integer> fullHouseValue(){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < hand.size()-1; i++){
            if(!map.containsKey(hand.get(i).getValue())){
                map.put(hand.get(i).getValue(), 1);
                list.add(hand.get(i).getValue());
            }
            else{
                map.put(hand.get(i).getValue(), map.get(hand.get(i).getValue()) + 1);
            }
        }
        if(map.get(list.get(0)) == 3){
            list.add(list.remove(0));
        }
        return list;
    }
    public List<Integer> twoPairValue(){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < hand.size()-1; i++){
            if(!map.containsKey(hand.get(i).getValue())){
                map.put(hand.get(i).getValue(), 1);
            }
            else{
                map.put(hand.get(i).getValue(), map.get(hand.get(i).getValue()) + 1);
                list.add(hand.get(i).getValue());
            }
        }
        if(list.get(0) < list.get(1)){
            list.add(list.remove(0));
        }
        return list;
    }
}