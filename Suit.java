public enum Suit{
    SPADE, HEART, DIAMOND, CLUB;
    public String toString(){
        if(this.equals(DIAMOND)){
            return "♦";
        }
        if(this.equals(HEART)){
            return "♥";
        }
        if(this.equals(SPADE)){
            return "♠";
        }
        if(this.equals(CLUB)){
            return "♣";
        }
        return "";
    }
}