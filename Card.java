//  Programmer: Adam Curtin
//  Course: CS 145 Java
//  Date: 4/21/2023
//  Purpose: Create a card class

public class Card {
        
    // Variables
    private Suit suit;
    private Value value; // rank/face

    // Constructor Method
    // Create Card of Suit and value
    public Card(Suit suit, Value value) {
        this.value = value; 
        this.suit = suit;
    }

    // Public Methods
    // Set suit and value of Card
    public Suit getSuit() {
        return suit;
    } // end getSuit

    public Value getValue() { 
        return value;
    } // end getvalue

    public String toString() {
        return (value +" of "+ suit);
    } // end toString

}
