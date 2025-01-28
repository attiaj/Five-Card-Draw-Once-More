package src;
/*
 * Final Project for CS 225
 * Project Name: A Simulation of Five-Card Draw Poker
 * Class: Card
 * Author: Jacob Attia
 * 
 * Class Description:
 * This class contains all the descriptions that a Card would have in real life, plus some extra ones, for the purpose
 * of making it easier to input images in the GUI, and easier to tell the cards apart when determining the type of hand
 * a Player or Robot has.  
 * 
 * List of Attributes:
 * card_name: the name of the card, ex. "Jack"
 * card_suit: the suit of the card, ex. "Spades"
 * card_value: the "value of the card" corresponding to its image number (0 is a 2 of hearts, 51 is an Ace of Spades)
 * discard_value: when the user clicks the "discard" button in the GUI over a certain card, the discard_value of that
 * card becomes 1, which tells the manager to exchange the card. It is reset to 0 at the beginning of a hand.
 * cardNameValue: this exists to make it easier to determine the type of hand a player has. A Two has a cardNameValue of 2,
 * and a Jack has a cardNameValue of 11
 * 
 * List of Methods:
 * null
 * 
 * Bugs: No bugs are known
 * 
 */

public class Card
{
	String card_name;
	
	String card_suit;
	
	int card_value;
	
	int discard_value = 0;
	
	int cardNameValue;
}