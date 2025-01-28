package src;
/*
 * Final Project for CS 225
 * Project Name: A Simulation of Five-Card Draw Poker
 * Class: Manager
 * Author: Jacob Attia
 * 
 * Class Description:
 * This class handles most of the structural aspects of the program, such as shuffling and dealing cards, exchanging 
 * cards during the discard round, and determining the winner of a hand/updating the chip amounts after a hand has been completed. 
 * The class also contains a Player and Robot, with which it completes those aspects.
 * 
 * This class also handles the File I/O requirement of the Project. Throughout the game, the Manager will update its attributes
 * to save the total number of hands played, number of hands won by the player, and number of hands lost by the player. After
 * a game has been completed, the Manager writes the results of the game to a file named "savedResults.txt", for the user to 
 * view any time after exiting the program. The Manager also reads the results in this file, and sends it to the GUI, for the 
 * user to view on display after the game is completed.
 * 
 * List of Attributes:
 * player: a Player, child of GenericPlayer, represents the person playing the game
 * robot1: a Robot, child of GenericPlayer, represents the AI the player plays against
 * deck: an array of 52 Cards, represent the standard deck of cards used in poker
 * savedResults: the File which contains the results of a game after it has been completed
 * numHands: this updates throughout the game to hold the total number of hands that have been played
 * numHandsWon: this updates throughout the game to hold the total number of hands won by the player
 * numHandsLost: this updates throughout the game to hold the total number of hands lost by the player
 * 
 * List of Methods:
 * writeFile(): writes the results of the game to the file mentioned above
 * readFile(): reads the results from the file and sends it to the GUI to be displayed
 * getCards(): optional method, to see the cards the player and robot have displayed in the console
 * initializeDeck(): initializes the 52 cards in the deck with their corresponding values
 * shuffleCards(): shuffles the cards in the deck using a Random Number Generator and deals the cards to player and robot
 * exchangeCards(): exchanges the selected cards for new ones; used in the discard round
 * showWinner(): this calls the getHandType() method from GenericPlayer, sends the results of the hand to the GUI to display,
 * and uses the 3 methods below to distribute the chips accordingly
 * playerWinMoney(): distributes the chips after a hand the player has won
 * robotWinMoney(): distributes the chips after a hand the player has lost
 * drawMoney(): distributes the chips after a hand the player and robot have tied in
 * checkArray(): used in shuffleCards(), this checks to see if the array of cards already has a specific value
 * randomFrom(): taken from the RaceGame lab, returns a random number from the low number to the high number - 1
 * 
 * Bugs: The last bug I fixed here happened during the discard round. A player would choose to discard an amount of cards, and
 * the exchangeCards() function would give the player one card, and then, sometimes, the next card given would be the same as the 
 * first card. This caused a duplicate child added error in the GUI, which stopped the game from working. I believe it is fixed now,
 * but I don't believe I have tested it enough to confirm that statement 100%. Other than that, no bugs are known.
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Manager
{	
	Player player = new Player();
	
	Robot robot1 = new Robot();
	
	Card[] deck = new Card[52];
	
	File savedResults = new File("savedResults.txt");
	
	int numHands = 0;
	int numHandsWon = 0;
	int numHandsLost = 0;
	
	public void writeFile() {
		try {
			FileWriter fileWriter = new FileWriter(savedResults);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			
			if (player.chip_amount > robot1.chip_amount) {
				bufferedWriter.write("The Player has beaten the Robot! Good Job!");
				bufferedWriter.newLine();
				bufferedWriter.write("Hands Won: " + numHandsWon);
				bufferedWriter.newLine();
				bufferedWriter.write("Hands Lost: " + numHandsLost);
				bufferedWriter.newLine();
				bufferedWriter.write("Total Hands: " + numHands);
				bufferedWriter.close();
			}
			else {
				bufferedWriter.write("The Robot has beaten the Player! F in the Chat Please");
				bufferedWriter.newLine();
				bufferedWriter.write("Hands Won: " + numHandsWon);
				bufferedWriter.newLine();
				bufferedWriter.write("Hands Lost: " + numHandsLost);
				bufferedWriter.newLine();
				bufferedWriter.write("Total Hands: " + numHands);
				bufferedWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String readFile() {
		
		String line1 = null;
		String line2 = null;
		String line3 = null;
		String line4 = null;
		String returnline = null;
		try {
			FileReader fileReader = new FileReader(savedResults);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			try {
				line1 = bufferedReader.readLine();
				line2 = bufferedReader.readLine();
				line3 = bufferedReader.readLine();
				line4 = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnline = line1 + "\n" + line2 + ", " + line3 + ", " + line4;
		return returnline;
		
	}
	
	public void getCards()
	{
		for (int i = 0; i < 5; i++)
		{
			System.out.println("Player Card " + i + " is the " + player.cardArray[i].card_name + " of " + player.cardArray[i].card_suit);
		}
		for (int i = 0; i < 5; i++)
		{
			System.out.println("Robot Card " + i + " is the " + robot1.cardArray[i].card_name + " of " + robot1.cardArray[i].card_suit);
		}
	}
	
	public void initializeDeck()
	{
		for (int i = 0; i < 52; i++)
		{
			deck[i] = new Card();
			deck[i].card_value = i;
			if (i < 4) {
				deck[i].card_name = "Two";
				deck[i].cardNameValue = 2;
			}else if (i < 8) {
				deck[i].card_name = "Three";
				deck[i].cardNameValue = 3;
			}else if (i < 12) {
				deck[i].card_name = "Four";
				deck[i].cardNameValue = 4;
			}else if (i < 16) {
				deck[i].card_name = "Five";
				deck[i].cardNameValue = 5;
			}else if (i < 20) {
				deck[i].card_name = "Six";
				deck[i].cardNameValue = 6;
			}else if (i < 24) {
				deck[i].card_name = "Seven";
				deck[i].cardNameValue = 7;
			}else if (i < 28) {
				deck[i].card_name = "Eight";
				deck[i].cardNameValue = 8;
			}else if (i < 32) {
				deck[i].card_name = "Nine";
				deck[i].cardNameValue = 9;
			}else if (i < 36) {
				deck[i].card_name = "Ten";
				deck[i].cardNameValue = 10;
			}else if (i < 40) {
				deck[i].card_name = "Jack";
				deck[i].cardNameValue = 11;
			}else if (i < 44) {
				deck[i].card_name = "Queen";
				deck[i].cardNameValue = 12;
			}else if (i < 48) {
				deck[i].card_name = "King";
				deck[i].cardNameValue = 13;
			}else if (i < 52) {
				deck[i].card_name = "Ace";
				deck[i].cardNameValue = 14;
			}
			if (i == 0 || i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 ||
				i == 32 || i == 36 || i == 40 || i == 44 || i == 48) {
				deck[i].card_suit = "Hearts";
			}
			else if (i == 1 || i == 5 || i == 9 || i == 13 || i == 17 || i == 21 || i == 25 || i == 29 ||
				i == 33 || i == 37 || i == 41 || i == 45 || i == 49) {
				deck[i].card_suit = "Diamonds";
			}
			else if (i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22 || i == 26 || i == 30 ||
				i == 34 || i == 38 || i == 42 || i == 46 || i == 50) {
				deck[i].card_suit = "Clubs";
			}
			else if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19 || i == 23 || i == 27 || i == 31 ||
				i == 35 || i == 39 || i == 43 || i == 47 || i == 51) {
				deck[i].card_suit = "Spades";
			}
			
						
		}
		
	}
	public void shuffleCards()
	{
		int[] ownerArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int j = 0;
		while (j < 10)
		{
			int cardIndex;
			cardIndex = randomFrom(0, 53) - 1;
			if (cardIndex < 0) {
				cardIndex = 0;
			}
			if(checkArray(ownerArray, cardIndex) == false)
			{
				ownerArray[j] = cardIndex;
				j++;
			}
		}
		for (int x = 0; x < 5; x++)
		{
			player.cardArray[x] = deck[ownerArray[x]];
			robot1.cardArray[x] = deck[ownerArray[x + 5]];
		}
	}
	
	public void exchangeCards() {
		Card dummycard1 = player.cardArray[0];
		Card dummycard2 = player.cardArray[1];
		Card dummycard3 = player.cardArray[2];
		Card dummycard4 = player.cardArray[3];
		Card dummycard5 = player.cardArray[4];
		for (int i = 0; i < 5; i++) {
			if (player.cardArray[i].discard_value == 1) {
				int x = 0;
				
				while (x == 0) {
					int cardIndex = randomFrom(0, 53) - 1;
					if (cardIndex < 0) {
						cardIndex = 0;
					}
					
					if (cardIndex != dummycard1.card_value && cardIndex != dummycard2.card_value &&
							cardIndex != dummycard3.card_value && cardIndex != dummycard4.card_value &&
							cardIndex != dummycard5.card_value && cardIndex != robot1.cardArray[0].card_value &&
							cardIndex != robot1.cardArray[1].card_value && cardIndex != robot1.cardArray[2].card_value &&
							cardIndex != robot1.cardArray[3].card_value && cardIndex != robot1.cardArray[4].card_value) {
						player.cardArray[i] = deck[cardIndex];
						x = 1;
					}
				}
				if (i == 0) {
					dummycard1 = player.cardArray[i];
				}else if (i == 1) {
					dummycard2 = player.cardArray[i];
				}else if (i == 2) {
					dummycard3 = player.cardArray[i];
				}else if (i == 3) {
					dummycard4 = player.cardArray[i];
				}else if (i == 4) {
					dummycard5 = player.cardArray[i];
				}
			}
		}
	}
	public String showWinner() {
		String playerHandType = player.getHandType();
		String robotHandType = robot1.getHandType();
		
		if (player.handRank > robot1.handRank) {
			int playerRank = player.handRank;
			playerWinMoney();
			numHandsWon++;
			
			if (playerRank == 9 || playerRank == 6) {
				return "With a " + playerHandType + ", the Player Wins!";
			}else if (playerRank == 8 || playerRank == 4) {
				return "With a(n) " + player.firstCardType + " high " + playerHandType + ", \nthe Player Wins!";
			}else if (playerRank == 7 || playerRank == 3) {
				return "With " + playerHandType + " of " + player.firstCardType + "s, \nthe Player Wins";
			}else if (playerRank == 5) {
				return "With a Flush of " + player.flushType + ", \nthe Player Wins";
			}else if (playerRank == 2) {
				return "With Two Pairs, the Player Wins";
			}else if (playerRank == 1) {
				return "With a Pair of " + player.firstCardType + "s, the Player Wins";
			}
		}
		else if (robot1.handRank > player.handRank) {
			int robotRank = robot1.handRank;
			robotWinMoney();
			numHandsLost++;
			
			if (robotRank == 9 || robotRank == 6) {
				return "With a " + robotHandType + ", the Robot Wins!";
			}else if (robotRank == 8 || robotRank == 4) {
				return "With a(n) " + robot1.firstCardType + " high " + robotHandType + ", \nthe Robot Wins!";
			}else if (robotRank == 7 || robotRank == 3) {
				return "With " + robotHandType + " of " + robot1.firstCardType + "s, \nthe Robot Wins";
			}else if (robotRank == 5) {
				return "With a Flush of " + robot1.flushType + ", \nthe Robot Wins";
			}else if (robotRank == 2) {
				return "With Two Pairs, the Robot Wins";
			}else if (robotRank == 1) {
				return "With a Pair of " + robot1.firstCardType + "s, the Robot Wins";
			}
		}
		else {
			if (player.handRank == 9) {
				drawMoney();
				return "It's a draw with two Royal Flushes!\nWhat are the Chances of that?";
			}
			else if (player.handRank == 8) {
				if (player.sortedCardArray[4].cardNameValue > robot1.sortedCardArray[4].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With a(n) " + player.firstCardType + " high " + playerHandType + ", \nthe Player Wins!";
				}
				else if (player.sortedCardArray[4].cardNameValue > robot1.sortedCardArray[4].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With a(n) " + robot1.firstCardType + " high " + playerHandType + ", \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with two Straight Flushes!";
				}
			}
			else if (player.handRank == 7) {
				if (player.sortedCardArray[2].cardNameValue > robot1.sortedCardArray[2].cardNameValue){
					playerWinMoney();
					numHandsWon++;
					return "With " + playerHandType + " of " + player.firstCardType + "s, \nthe Player Wins";
				}
				else if (robot1.sortedCardArray[2].cardNameValue > player.sortedCardArray[2].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With " + robotHandType + " of " + robot1.firstCardType + "s, \nthe Player Wins";
				}
			}
			else if (player.handRank == 6) {
				if (player.sortedCardArray[4].cardNameValue > robot1.sortedCardArray[4].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With a(n) " + player.firstCardType + " high " + playerHandType + ", \nthe Player Wins!";
				}
				else if (robot1.sortedCardArray[4].cardNameValue > player.sortedCardArray[4].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With a(n) " + robot1.firstCardType + " high " + robotHandType + ", \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with two Full Houses!";
				}
			}
			else if (player.handRank == 5) {
				if (player.sortedCardArray[4].cardNameValue > robot1.sortedCardArray[4].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With a(n) " + player.firstCardType + " high " + playerHandType + ", \nthe Player Wins!";
				}
				else if (robot1.sortedCardArray[4].cardNameValue > player.sortedCardArray[4].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With a(n) " + robot1.firstCardType + " high " + robotHandType + ", \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with two Flushes!";
				}
			}
			else if (player.handRank == 4) {
				if (player.sortedCardArray[4].cardNameValue > robot1.sortedCardArray[4].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With a(n) " + player.firstCardType + " high " + playerHandType + ", \nthe Player Wins!";
				}
				else if (robot1.sortedCardArray[4].cardNameValue > player.sortedCardArray[4].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With a(n) " + robot1.firstCardType + " high " + robotHandType + ", \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with two Straights!";
				}
			}
			else if (player.handRank == 3) {
				if (player.sortedCardArray[2].cardNameValue > robot1.sortedCardArray[2].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With " + player.firstCardType + " of " + playerHandType + "s, \nthe Player Wins!";
				}
				else if (robot1.sortedCardArray[2].cardNameValue > player.sortedCardArray[2].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With " + robot1.firstCardType + " of " + robotHandType + "s, \nthe Robot Wins!";
				}
			}
			else if (player.handRank == 2) {
				if (player.sortedCardArray[3].cardNameValue > robot1.sortedCardArray[3].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With a(n) " + player.firstCardType + " high " + playerHandType + ", \nthe Player Wins!";
				}
				else if (robot1.sortedCardArray[3].cardNameValue > player.sortedCardArray[3].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With a(n) " + robot1.firstCardType + " high " + robotHandType + ", \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with Two Pairs Each!";
				}
			}
			else if(player.handRank == 1) {
				if (player.highestCardNumber > robot1.highestCardNumber) {
					playerWinMoney();
					numHandsWon++;
					return "With a Pair of " + player.firstCardType + "s, \nthe Player Wins!";
				}
				else if (robot1.highestCardNumber > player.highestCardNumber) {
					robotWinMoney();
					numHandsLost++;
					return "With a Pair of " + robot1.firstCardType + "s, \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with a Pair each!";
				}
			}
			else {
				if (player.sortedCardArray[4].cardNameValue > robot1.sortedCardArray[4].cardNameValue) {
					playerWinMoney();
					numHandsWon++;
					return "With a(n) " + player.firstCardType + " High Card, \nthe Player Wins!";
				}
				else if (robot1.sortedCardArray[4].cardNameValue > player.sortedCardArray[4].cardNameValue) {
					robotWinMoney();
					numHandsLost++;
					return "With a(n) " + robot1.firstCardType + " High Card, \nthe Robot Wins!";
				}
				else {
					drawMoney();
					return "It's a draw with the same High Cards!";
				}
			}
		}
		return "Yup";
	}
	public void playerWinMoney() {
		player.chip_amount = 2000 - robot1.chip_amount;
		player.current_pot = 0;
		robot1.current_pot = 0;
	}
	public void robotWinMoney() {
		robot1.chip_amount = 2000 - player.chip_amount;
		player.current_pot = 0;
		robot1.current_pot = 0;
	}
	public void drawMoney() {
		robot1.chip_amount += robot1.current_pot;
		player.chip_amount = 2000 - robot1.chip_amount;
		player.current_pot = 0;
		robot1.current_pot = 0;
	}
	public boolean checkArray(int[] array, int number)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == number)
			{
				return true;
			}
		}
		return false;
	}
	public int randomFrom (int low, int high) {

		int randNum = 0;
		randNum = (int) (Math.random()*(high-low) + low);
		return randNum;
	}
}