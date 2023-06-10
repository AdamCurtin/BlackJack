//  Programmer: Adam Curtin
//  Course: CS 145 Java
//  Date: 4/21/2023
//  Purpose: This will have the user play a game of blackjack against the dealer.
//  The method for playing the game of blackjack is here.
//  Programmed in both Visual Studio Code and Eclipse(partially)

import java.util.Scanner;

public class BlackJackGameMain {
    public static void main(String[] args) {
        
        System.out.println("Hello, this is a game of blackjack");
        instructions();

        // Create deck and start the game
        Deck playingDeck = new Deck();
        playingDeck.createDeck();
        playingDeck.shuffle();
        // Create a deck for the player and Dealer
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game loop
        while(playerMoney > 0) {
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney) {
                System.out.println(" You can't bet more money than you have. See front desk.");
                break;
            } // end if playerBet

            boolean endRound = false;

            // Deal cards to player
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            // Deal cards to dealer
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true) { // start inner while
                System.out.print("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is worth: " + playerDeck.cardsValue());

                // Display dealer hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Face Down]");

                // What does player want to do?
                System.out.println("Would you like to hit (1) or stay (2)");
                int response = userInput.nextInt();

                // If hit
                if(response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() -1).toString());
                    // Bust if over 21
                    if(playerDeck.cardsValue() > 21) {
                        System.out.println("Bust! Hand value is: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    } // end if hand is over player bust
                } // end if player picks 'hit'
                // Player Chooses 'stay'
                if(response==2) {
                    break;
                } // end if player stays
            } // end inner while loop

            // Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            // See if dealer has more points than player
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
                System.out.println("Dealer Wins!");
                playerMoney -= playerBet;
                endRound = true;
            } // end if dealer wins

            // Dealer Draws at 16, stays at 17
            while((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Hits: " + dealerDeck.getCard(dealerDeck.deckSize() -1 ).toString());
            } // end while dealer draws

            // Display hand total for dealer
            System.out.println(" Dealer's Hand is worth: " + dealerDeck.cardsValue());

            // Check if dealer busted
            if((dealerDeck.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer busts! You win!");
                playerMoney += playerBet;
                endRound = true;
            } // end if dealer busted

            // Check for tied hands
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Hands Tied. Push.");
                endRound = true;
            } // end if a tie

            // Player wins
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("You win!");
                playerMoney += playerBet;
                endRound = true;
            } // end if player wins

            // Reshuffle
            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of Hand. Shuffling");

        } // end blackjack game
    
       System.out.println("You are out of money. Game Over!");
    } // end main method

    public static void instructions() {
        System.out.println("All number cards (2-10) score the value indicated on them,");
        System.out.println("The face cards (Jack, Queen, King) score 10 points and Ace can either be treated as 1 or 11. ");
        System.out.println("The object of the game is to score 21 points.");
        System.out.println("At the beginning of each round, all players place their bets."); 
        System.out.println("After the bets have been placed, the player is dealt two cards, face up.");
        System.out.println("The dealer receives two cards, one face-up and another face-down.");
        System.out.println("The players can either HIT or STAND.");
        System.out.println("If the player calls out HIT, they are given an extra card.");
        System.out.println("The player may choose to HIT again");
        System.out.println("When the player chooses STAND, that is their final score.");
        System.out.println("If the player scores over 21, that is a BUST and the player loses.");
        System.out.println("The player must score more than the dealer hand to win.");
        System.out.println("Once the player chooses STAND, the dealer may HIT or STAND.");
        System.out.println("Once the dealer reaches a valid hand, the scores are compared.");
        System.out.println("Beating the dealer wins you the bet.");
        System.out.println("Lose all of your money, get a game over.");   
        } // end instructions
} // end BlackJackGameMain class
