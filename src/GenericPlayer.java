package src;
/*
 * Final Project for CS 225
 * Project Name: A Simulation of Five-Card Draw Poker
 * Class: GenericPlayer
 * Author: Jacob Attia
 * 
 * Class Description:
 * This class is a parent of Player and Robot, fulfilling the inheritance requirement of the project. It holds the value
 * of the chips the player and AI hold, and their cards. The main purpose of this class is to determine what type of hand   
 * 
 * List of Attributes:
 * chip_amount: the amount of chips a player/robot holds at any given time
 * chip_amount_beginning: the amount of chips the player/robot holds at the beginning of a hand (useful for allocating chips after a raise)
 * current_pot: the current amount a player/robot has bet into the hand
 * handRank: the numbered rank of a hand, from 9 being a Royal Flush, to 0 being a High Card (useful in showWinner() in manager)
 * firstCardType: the card name that determines the specifics of a hand rank. If this is a "Three" and hand type is a Pair,
 * then the hand type is a Pair of Threes
 * secondCardType: similar to firstCardType, but for hands that require more specifics, such as 2 pairs
 * flushType: the suit a player has a flush of
 * highestCardNumber: useful when calculating the winner of a potential draw ex. who has the better pair?
 * cardArray: an array of 5 cards to represent the five cards held during a hand
 * sortedCardArray: sorted array of a player/robot's cards, useful in determining the hand type
 * 
 * 
 * List of Methods:
 * getHandType(): sorts the array of cards, and uses the methods described below to determine the type of hand a player or robot holds,
 * then sends that type to Manager, which then sends it to GUI, to be displayed
 * isRoyalFlush(): checks if hand is a Royal Flush
 * isStraightFlush(): checks if hand is a Straight Flush
 * isFourOfAKind(): checks if hand is a Four of a Kind
 * isFullHouse(): checks if hand is a Full House
 * isFlush(): checks if hand is a Flush
 * isStraight(): checks if hand is a Straight
 * isThreeOfAKind(): checks if hand is a Three of a Kind
 * isTwoPair(): checks if hand is Two Pairs
 * isPair(): checks if hand is a Pair
 * isHighCard(): checks if hand does not meet requirements of any of the above checking methods
 * 
 * Bugs: No bugs are known
 * 
 */
import java.util.Arrays;

public class GenericPlayer
{
	
	int chip_amount = 0;
	
	int chip_amount_beginning;
	
	int current_pot = 0;
	
	int handRank;
	
	String firstCardType;
	
	String secondCardType;
	
	String flushType;
	
	int highestCardNumber;
	
	Card[] cardArray = new Card[5];
	
	Card[] sortedCardArray = new Card[5];
	
	public String getHandType()
	{
		Manager manager = new Manager();
		manager.initializeDeck();
		//sort list, then run through the possibilities
		int[] sortedArrayValues = new int[5];
		for (int i = 0; i < 5; i++) {
			sortedArrayValues[i] = cardArray[i].card_value;
		}
		
		Arrays.sort(sortedArrayValues);
		
		for (int i = 0; i < 5; i++) {
			sortedCardArray[i] = new Card();
			sortedCardArray[i] = manager.deck[sortedArrayValues[i]];
		}
		if (isRoyalFlush(sortedCardArray) == true) {
			handRank = 9;
			return "Royal Flush";
		}
		else if (isStraightFlush(sortedCardArray) == true) {
			handRank = 8;
			return "Straight Flush";
		}
		else if (isFourOfAKind(sortedCardArray) == true) {
			handRank = 7;
			return "Four Of A Kind";
		}
		else if (isFullHouse(sortedCardArray) == true) {
			handRank = 6;
			return "Full House";
		}
		else if (isFlush(sortedCardArray) == true) {
			handRank = 5;
			return "Flush";
		}
		else if (isStraight(sortedCardArray) == true) {
			handRank = 4;
			return "Straight";
		}
		else if (isThreeOfAKind(sortedCardArray) == true) {
			handRank = 3;
			return "Three Of A Kind";
		}
		else if (isTwoPair(sortedCardArray) == true) {
			handRank = 2;
			return "Two Pairs";
		}
		else if (isPair(sortedCardArray) == true) {
			handRank = 1;
			return "Pair";
		}
		else if (isHighCard(sortedCardArray) == true) {
			handRank = 0;
			return "High Card";
		}
		
		
		
		return "test";
	}
	public boolean isRoyalFlush(Card[] cards) {
		if (cards[0].card_name == "Ten" && cards[1].card_name == "Jack" && cards[2].card_name == "Queen" && 
				cards[3].card_name == "King" && cards[4].card_name == "Ace" && isFlush(cards) == true) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean isStraightFlush(Card[] cards) {
		if (isFlush(cards) == true && isStraight(cards) == true) {
			firstCardType = cards[4].card_name;
			if (cards[0].cardNameValue == 2) {
				firstCardType = cards[3].card_name;
			}
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isFourOfAKind(Card[] cards) {
		if ((cards[0].cardNameValue == cards[1].cardNameValue && cards[1].cardNameValue == cards[2].cardNameValue && cards[2].cardNameValue == cards[3].cardNameValue)
			|| (cards[1].cardNameValue == cards[2].cardNameValue && cards[2].cardNameValue == cards[3].cardNameValue && cards[3].cardNameValue == cards[4].cardNameValue)) {
			firstCardType = cards[2].card_name;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isFullHouse(Card[] cards) {
		if ((cards[0].cardNameValue == cards[1].cardNameValue && cards[1].cardNameValue == cards[2].cardNameValue && cards[3].cardNameValue == cards[4].cardNameValue) ||
			(cards[0].cardNameValue == cards[1].cardNameValue && cards[2].cardNameValue == cards[3].cardNameValue && cards[3].cardNameValue == cards[4].cardNameValue)) {
			firstCardType = cards[4].card_name;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isFlush(Card[] cards) {
		if (cards[0].card_suit == cards[1].card_suit && cards[1].card_suit == cards[2].card_suit && cards[2].card_suit == cards[3].card_suit &&
				 cards[3].card_suit == cards[4].card_suit) {
			flushType = cards[0].card_suit;
			firstCardType = cards[4].card_name;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isStraight(Card[] cards) {
		int smallestValue = cards[0].cardNameValue;
		if (cards[1].cardNameValue == smallestValue + 1 && cards[2].cardNameValue == smallestValue + 2 && cards[3].cardNameValue == smallestValue + 3 &&
				cards[4].cardNameValue == smallestValue + 4) {
			firstCardType = cards[4].card_name;
			return true;
		}
		else if (cards[1].cardNameValue == smallestValue + 1 && cards[2].cardNameValue == smallestValue + 2 && cards[3].cardNameValue == smallestValue + 3 &&
				cards[4].cardNameValue == 14) {
			firstCardType = cards[3].card_name;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isThreeOfAKind(Card[] cards) {
		if ((cards[0].cardNameValue == cards[1].cardNameValue && cards[1].cardNameValue == cards[2].cardNameValue) || 
				(cards[1].cardNameValue == cards[2].cardNameValue && cards[2].cardNameValue == cards[3].cardNameValue) ||
				(cards[2].cardNameValue == cards[3].cardNameValue && cards[3].cardNameValue == cards[4].cardNameValue)) {
			firstCardType = cards[2].card_name;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isTwoPair(Card[] cards) {
		if ((cards[0].cardNameValue == cards[1].cardNameValue && cards[2].cardNameValue == cards[3].cardNameValue) ||
				(cards[0].cardNameValue == cards[1].cardNameValue && cards[3].cardNameValue == cards[4].cardNameValue) ||
				(cards[1].cardNameValue == cards[2].cardNameValue && cards[3].cardNameValue == cards[4].cardNameValue)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isPair(Card[] cards) {
		if (cards[0].cardNameValue == cards[1].cardNameValue || cards[1].cardNameValue == cards[2].cardNameValue) {
			firstCardType = cards[1].card_name;
			highestCardNumber = cards[1].cardNameValue;
			return true;
		}
		else if ((cards[2].cardNameValue == cards[3].cardNameValue) || (cards[3].cardNameValue == cards[4].cardNameValue)) {
			firstCardType = cards[3].card_name;
			highestCardNumber = cards[3].cardNameValue;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isHighCard(Card[] cards) {
		if (isRoyalFlush(cards) == false && isStraightFlush(cards) == false && isFourOfAKind(cards) == false && isFullHouse(cards) == false &&
			isFlush(cards) == false && isStraight(cards) == false && isThreeOfAKind(cards) == false && isTwoPair(cards) == false && isPair(cards) == false) {
			firstCardType = cards[4].card_name;
			return true;
		}
		else {
			return false;
		}
	}
}