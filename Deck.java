//  Programmer: Adam Curtin
//  Course: CS 145 Java
//  Date: 4/21/2023
//  Purpose: Create the Deck manager class.
//  Contains various methods to manipulate the deck.

import java.util.ArrayList;
import java.util.Random;

public class Deck {

  // variables
  private ArrayList<Card> cards;

  // Constructor
  public Deck () {
    this.cards = new ArrayList<Card>();
  } // end Deck

  // Generate the deck
  public void createDeck() {
    for(Suit cardSuit : Suit.values()){
      for(Value cardValue : Value.values()){
        // Add new card to the deck
        this.cards.add(new Card(cardSuit, cardValue));
      } // end inner for loop
    } // end outer for loop
  } // end createDeck

  // Shuffle method
  public void shuffle() {
    ArrayList<Card> temporaryDeck = new ArrayList<Card>();
    Random random = new Random ();
    int randomCardIndex = 0;
    int originalSize = this.cards.size(); // use original size to not mess up for loop
    for(int i = 0; i < originalSize; i++) {
      // Generate randomIndex rand.nextInt((max - min) + 1) + min;
      randomCardIndex = random.nextInt((this.cards.size()-1 -0) + 1) + 0;
      temporaryDeck.add(this.cards.get(randomCardIndex));
      // remove from original deck
      this.cards.remove(randomCardIndex);
    } // end for loop
    this.cards = temporaryDeck;
  } // end shuffle method

  // print deck to console
  public String toString() {
    String cardListOutput = "";
    for(Card aCard : this.cards) {
      cardListOutput += "\n " + aCard.toString();
    }
    return cardListOutput;
  } // end toString

  // remove card from the deck
  public void removeCard(int i) {
    this.cards.remove(i);
  } // remove card end

  // get card from the deck
  public Card getCard(int i) {
    return this.cards.get(i);
  } // end getCard

  public void addCard(Card addCard) {
    this.cards.add(addCard);
  } // end addCard

  // Draws from the deck
  public void draw(Deck comingFrom) {
    this.cards.add(comingFrom.getCard(0));
    comingFrom.removeCard(0);
  } // end draw method
  
  // Deck Size
  public int deckSize() {
    return this.cards.size();
  } // end deckSize

  public void moveAllToDeck(Deck moveTo) {
    int thisDeckSize = this.cards.size();

    // put cards into moveTo
    for(int i = 0; i < thisDeckSize; i++) {
        moveTo.addCard(this.getCard(i));
    } // end for addCard
    for(int i = 0; i < thisDeckSize; i++) {
        this.removeCard(0);
    } // end for removeCard
  } // end moveAllToDeck method

  // return total values in players or dealers deck
  public int cardsValue() {
    int totalValue = 0;
    int aces = 0;

    for(Card aCard : this.cards) {
      switch(aCard.getValue()) {
        case TWO: totalValue += 2; 
        break;
        case THREE: totalValue += 3;
        break;
        case FOUR: totalValue += 4;
        break;
        case FIVE: totalValue += 5;
        break;
        case SIX: totalValue += 6;
        break;
        case SEVEN: totalValue += 7;
        break;
        case EIGHT: totalValue += 8;
        break;
        case NINE: totalValue += 9;
        break;
        case TEN: totalValue += 10;
        break;
        case JACK: totalValue += 10;
        break;
        case QUEEN: totalValue += 10;
        break;
        case KING: totalValue += 10;
        break;
        case ACE: totalValue += 1;
        break;
      } // end Case Switch
    } // end for aCard

    // handles ace's two value possiblities
    for (int i = 0; i < aces; i++) {
      if(totalValue > 10) {
        totalValue += 1;
      } // end if hand greater than 10
      else {
        totalValue += 11;
      } // end else
    } // end for loop
    return totalValue;
  } // end cardsValue method

  

} // end Deck Class

