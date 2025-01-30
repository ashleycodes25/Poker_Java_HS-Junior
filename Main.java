import java.util.*;
public class Main{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        int round = 1;
        int playerScore = 0;
        int computerScore = 0;
        int playerHandType = 0;
        int computerHandType = 0;
        boolean replace = false;
        boolean game = true;
        System.out.println("Welcome to Poker! Please enter your name:");
        Player p = new Player(scan.nextLine());
        Player c = new Player("Computer");
        Deck d = new Deck();
        for(int i = 0; i < 3; i++){
            d.riffleShuffle();
        }
        d.fisherYatesShuffle();
        do{
            System.out.printf("Round %d Score:%n%s: %d Computer: %d%n%n", round, p.getName(), playerScore, computerScore);
            deal(p, c, d);
            System.out.println("Would you like to replace any cards? Type Y or N:");
            replace = yesOrNo(scan.nextLine());
            if(replace){
                System.out.println("Please type the indexes of the cards you would like to replace: ");
                System.out.println("Ex: 124");
                replace(scan.nextLine(), p, d);
                replace = false;
                System.out.println();
            }
            playerHandType = p.checkHand();
            computerHandType = c.checkHand();
            printPlayerHandType(playerHandType, p);
            printComputerHandType(computerHandType, c);
            String finalScore = checkScore(playerHandType, computerHandType, p, c);
            if(finalScore == "player"){
                playerScore++;
                System.out.println("Congratulations! You won!");
            }
            else if(finalScore == "computer"){
                computerScore++;
                System.out.println("Uh oh... You lost. Better luck next time!");
            }
            else if(finalScore == "tie"){
                System.out.println("Oops, you tied! No points for anyone!");
            }
            System.out.printf("The current score is:%n%s: %d Computer: %d%n%n", p.getName(), playerScore, computerScore);
            System.out.println("Would you like to play another round? Type Y or N:");
            game = yesOrNo(scan.nextLine());
            if(game){
                System.out.println("Ok! Get ready!");
                round++;
                playerHandType = 0;
                computerHandType = 0;
                p.getHand().clear();
                c.getHand().clear();
            }
            else{
                System.out.printf("Ok! Thank you for playing %s!%n", p.getName());
                System.out.printf("The final score was:%n%s: %d Computer: %d%n", p.getName(), playerScore, computerScore);
                if(playerScore > computerScore){
                    System.out.println("You won!");
                }
                else if(playerScore < computerScore){
                    System.out.println("You lost.");
                }
                else{
                    System.out.println("You tied!");
                }
            }
        }
        while(game);
    }

    public static void deal(Player p, Player c, Deck d){
        for(int i = 0; i < 5; i++){
            p.getHand().add(d.getDeck().pop());
            c.getHand().add(d.getDeck().pop());
        }
        p.sortHand();
        c.sortHand();
        System.out.println(p.getName() + ", here is your hand for this round!");
        p.printHand();
    }

    public static void replace(String cardIndexes, Player p, Deck d){
        int temp;
        for(int i = 0; i < cardIndexes.length(); i++){
            temp = Integer.parseInt(cardIndexes.substring(i, i+1));
            p.getHand().remove(temp);
            p.getHand().add(temp, d.getDeck().pop());
        }
        p.sortHand();
    }

    public static boolean yesOrNo(String input){
        boolean temp = false;
        switch(input){
            case "Y":
                temp = true;
                break;
            case "N":
                temp = false;
                break;
            default:
                System.out.println("Please type either 'Y' or 'N'");
                temp = yesOrNo(scan.nextLine());
                break;
        }
        return temp;
    }

    public static void printPlayerHandType(int handType, Player p){
        System.out.println(p.getName() + ", here is your hand after replacement!");
        p.printHand();
        if(handType == 1){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a Royal Flush!");
        }
        else if(handType == 2){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a Straight Flush!");
        }
        else if(handType == 3){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's Four of a Kind!");
        }
        else if(handType == 4){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a Full House!");
        }
        else if(handType == 5){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's Flush!");
        }
        else if(handType == 6){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a Straight!");
        }
        else if(handType == 7){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's Three of a Kind!");
        }
        else if(handType == 8){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a Two Pair!");
        }
        else if(handType == 9){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a Pair!");
        }
        else if(handType == 10){
            System.out.println("Your hand type is: " + handType);
            System.out.println("That's a High Card!");
        }
        System.out.println();
    }

    public static void printComputerHandType(int handType, Player c){
        System.out.println("Here is the computer's hand!");
        c.printHand();
        if(handType == 1){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a Royal Flush!");
        }
        else if(handType == 2){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a Straight Flush!");
        }
        else if(handType == 3){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's Four of a Kind!");
        }
        else if(handType == 4){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a Full House!");
        }
        else if(handType == 5){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's Flush!");
        }
        else if(handType == 6){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a Straight!");
        }
        else if(handType == 7){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's Three of a Kind!");
        }
        else if(handType == 8){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a Two Pair!");
        }
        else if(handType == 9){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a Pair!");
        }
        else if(handType == 10){
            System.out.println("Their hand type is: " + handType);
            System.out.println("That's a High Card!");
        }
        System.out.println();
    }

    public static String checkScore(int playerHandType, int computerHandType, Player p, Player c){
        if(playerHandType < computerHandType){
            return "player";
        }
        else if(playerHandType > computerHandType){
            return "computer";
        }
        else{
            return checkTies(playerHandType, p, c);
        }
    }

    public static String checkTies(int handType, Player p, Player c){
        if(handType == 1){
            return checkRoyalFlush(p, c);
        }
        else if(handType == 2){
            if(!compareCardValues(p, c).equals("tie")){
                return compareCardValues(p, c);
            }
            else{
                return checkRoyalFlush(p, c);
            }
        }
        else if(handType == 3){
            return checkFourOfAKind(p, c);
        }
        else if(handType == 4){
            return checkFullHouse(p, c);
        }
        else if(handType == 5){
            if(!compareCardValues(p, c).equals("tie")){
                return compareCardValues(p, c);
            }
            else{
                return checkRoyalFlush(p, c);
            }
        }
        else if(handType == 6){
            return compareCardValues(p, c);
        }
        else if(handType == 7){
            return checkThreeOfAKind(p, c);
        }
        else if(handType == 8){
            return checkTwoPair(p, c);
        }
        else if(handType == 9){
            return checkPair(p, c);
        }
        else if(handType == 10){
            return compareCardValues(p, c);
        }
        return ""; 
    }

    public static String checkRoyalFlush(Player p, Player c){
        if(p.getHand().get(0).getSuit().ordinal() < c.getHand().get(0).getSuit().ordinal()){
            return "player";
        }
        return "computer";
    }

    public static String compareCardValues(Player p, Player c){
        for(int i = 4; i >= 0; i--){
            if(p.getHand().get(i).getValue() > c.getHand().get(i).getValue()){
                return "player";
            }
            else if(p.getHand().get(i).getValue() < c.getHand().get(i).getValue()){
                return "computer";
            }
        }
        return "tie";
    }

    public static String checkFourOfAKind(Player p, Player c){
        if(p.ofAKindValue() > c.ofAKindValue()){
            return "player";
        }
        else if(p.ofAKindValue() < c.ofAKindValue()){
            return "computer";
        }
        else{
            return ofAKindKicker(p, c);
        }
    }

    public static String ofAKindKicker(Player p, Player c){
        int highestPlayerCard = 0;
        int highestComputerCard = 0;
        for(int i = 0; i < p.getHand().size(); i++){
            if(p.getHand().get(i).getValue() != p.ofAKindValue() && p.getHand().get(i).getValue() > highestPlayerCard){
                highestPlayerCard = p.getHand().get(i).getValue();
            }
            if(c.getHand().get(i).getValue() != c.ofAKindValue() && c.getHand().get(i).getValue() > highestComputerCard){
                highestComputerCard = c.getHand().get(i).getValue();
            }
        }
        if(highestPlayerCard > highestComputerCard){
            return "player";
        }
        else if(highestPlayerCard < highestComputerCard){
            return "computer";
        }
        return "tie";
    }

    public static String checkFullHouse(Player p, Player c){
        List<Integer> playerValues = p.fullHouseValue();
        List<Integer> computerValues = c.fullHouseValue();
        if(playerValues.get(1) > computerValues.get(1)){
            return "player";
        }
        else if(playerValues.get(1) < computerValues.get(1)){
            return "computer";
        }
        else if(playerValues.get(0) > computerValues.get(0)){
            return "player";
        }
        else if(playerValues.get(0) < computerValues.get(0)){
            return "computer";
        }
        else{
            return "tie";
        }
    }

    public static String checkThreeOfAKind(Player p, Player c){
        if(p.ofAKindValue() > c.ofAKindValue()){
            return "player";
        }
        else if(p.ofAKindValue() < c.ofAKindValue()){
            return "computer";
        }
        return ofAKindKicker(p, c);
    }
    
    public static String checkTwoPair(Player p, Player c){
        List<Integer> playerValues = p.twoPairValue();
        List<Integer> computerValues = c.twoPairValue();
        if(playerValues.get(0) > computerValues.get(0)){
            return "player";
        }
        else if(playerValues.get(0) < computerValues.get(0)){
            return "computer";
        }
        else if(playerValues.get(1) > computerValues.get(1)){
            return "player";
        }
        else if(playerValues.get(1) < computerValues.get(1)){
            return "computer";
        }
        else{
            return twoPairKicker(p, c);
        }
    }
    
    public static String twoPairKicker(Player p, Player c){
        List<Integer> playerValues = p.twoPairValue();
        List<Integer> computerValues = c.twoPairValue();
        int playerHandValue = 0;
        int computerHandValue = 0;
        int highestPlayerCard = 0;
        int highestComputerCard = 0;
        for(int i = 0; i < p.getHand().size(); i++){
            playerHandValue = p.getHand().get(i).getValue();
            computerHandValue = c.getHand().get(i).getValue();
            if(playerHandValue != playerValues.get(0) && playerHandValue != playerValues.get(1)){
                highestPlayerCard = playerHandValue;
            }
            if(computerHandValue != computerValues.get(0) && computerHandValue != computerValues.get(1)){
                highestComputerCard = computerHandValue;
            }
        }
        if(highestPlayerCard > highestComputerCard){
            return "player";
        }
        else if(highestPlayerCard < highestComputerCard){
            return "computer";
        }
        return "tie";
    }
    
    public static String checkPair(Player p, Player c){
        if(p.ofAKindValue() > c.ofAKindValue()){
            return "player";
        }
        else if(p.ofAKindValue() < c.ofAKindValue()){
            return "computer";
        }
        return ofAKindKicker(p, c);
    }
}
