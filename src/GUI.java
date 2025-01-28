package src;
/*
 * Final Project for CS 225
 * Project Name: A Simulation of Five-Card Draw Poker
 * Class: GUI
 * Author: Jacob Attia
 * 
 * Class Description:
 * This class is where the program should be run from. It handles all of the display aspects of the program.
 * Originally, I was going to create methods in the Robot class to run betting/discard rounds in different ways based
 * on a number of strategies, but I did not have enough time to create intelligent strategies for the AI. Instead,
 * in this class, I programmed the Robot to make decisions based on a Random Number Generator. 
 * 
 * This class also handles the inputs of .png Image files. It loads in images for all 52 cards, and images of the back
 * of a card, to better recreate the Poker experience. 
 * 
 * The Exception Handling requirement of the project can be found here, in the function displayCards(). After the images have been loaded
 * into the images array, this exception handler will catch an exception if there happens to be error when creating ImageViews of 
 * the loaded images. This is NOT a File I/O Exception Handler, so it meets the requirement. 
 * 
 * Uncomment lines 174, 189, and 598 if you would like the console to print the robot's and player's cards during a hand
 * 
 * This class is equipped to run infinite games of Five-Card Draw Poker in succession
 * 
 * Bugs: I spent a long period of time running the GUI many times, and playing in many different ways, to track down
 * all of the hidden bugs. I believe I have fixed all of them except for one. Occasionally, after a hand has been completed,
 * the player's chip amount will not update correctly. I do not know why, because it seems to happen randomly. However, the structure
 * of the program is not affected by this (the bug does not crash the game, or stop anything else from working correctly).
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application {
	
	Pane pane1 = new Pane();
	
	Scene scene = new Scene(pane1, 500, 500);
	
	Manager manager = new Manager();
	
	Button btnStartGame = new Button("Start");
	Button btnPlayerGame = new Button("Player vs. AI");
	Text startGameText = new Text(125, 100, "Welcome to the Five-Card Draw Poker Simluation!");
	Text chooseGameText = new Text(125, 100, "Choose which game you would like to play: \n(In the future I would like to include an AI vs. AI game mode,"
			+ "\nbut, for now, please enjoy the Player vs. AI game mode)");
	Text playerGameText = new Text(125, 100, "In the future, I would like to work on creating more intelligent AI's,\nto test your skills.");
	Button btnBeginPlayerGame = new Button("Begin Player vs. AI Game");
	Button btnDeal = new Button("Deal the cards");
	Button btnBet50 = new Button("Bet 50");
	Button btnBet100 = new Button("Bet 100");
	Button btnBet150 = new Button("Bet 150");
	Button btnCheck = new Button("Check");
	Button btnFold = new Button("Fold");
	Button btnRaise50 = new Button("Raise 50");
	Button btnRaise100 = new Button("Raise 100");
	Button btnRaise150 = new Button("Raise 150");
	Button btnCall = new Button("Call");
	Button btnDiscard1 = new Button("Discard");
	Button btnDiscard2 = new Button("Discard");
	Button btnDiscard3 = new Button("Discard");
	Button btnDiscard4 = new Button("Discard");
	Button btnDiscard5 = new Button("Discard");
	Button btnExchange = new Button("Exchange");
	Button btnNewGame = new Button("New Game");
	Button btnClose = new Button("Exit Game");
	Text actionText = new Text();
	Text playerPotText = new Text();
	Text robot1PotText = new Text();
	Text playerAmountText = new Text();
	Text robot1AmountText = new Text();
	Image[] images = new Image[57];
	ImageView[] imageview = new ImageView[57];
	
	public GUI()
	{
		try {
			for (int i = 0; i < 52; i++) {
				images[i] = new Image(new FileInputStream(i + ".png"));
			}
			for (int i = 52; i < 57; i++) {
				images[i] = new Image(new FileInputStream("purple_back.png"));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//displayCards();
		startGame();
		setProperties();
		chooseGameType();
		playerGame();
		beginPlayerGame();
	}
	public void startGame()
	{
		pane1.getChildren().add(btnStartGame);
		pane1.getChildren().add(startGameText);
		startGameText.setFill(Color.RED);
	}
	
	public void chooseGameType()
	{	
		btnStartGame.setOnAction(e-> {
			pane1.getChildren().remove(btnStartGame);
			pane1.getChildren().remove(startGameText);
			pane1.getChildren().add(chooseGameText);
			pane1.getChildren().add(btnPlayerGame);
			pane1.getChildren().add(btnClose);
		});
		btnClose.setOnAction(e -> {
			Platform.exit();
		});
	}
	
	public void playerGame()
	{
		btnPlayerGame.setOnAction(e -> {
			pane1.getChildren().remove(chooseGameText);
			pane1.getChildren().remove(btnPlayerGame);
			pane1.getChildren().add(playerGameText);
			pane1.getChildren().add(btnBeginPlayerGame);
			pane1.getChildren().remove(actionText);
		});
	}
	
	public void beginPlayerGame()
	{
		
		btnBeginPlayerGame.setOnAction(e -> {
			pane1.getChildren().remove(playerGameText);
			pane1.getChildren().remove(btnBeginPlayerGame);
			pane1.getChildren().add(btnDeal);
			manager.robot1.chip_amount = 1000;
			manager.player.chip_amount = 1000;
			manager.robot1.chip_amount_beginning = 1000;
			manager.player.chip_amount_beginning = 1000;
			pane1.getChildren().add(actionText);
		});
		btnDeal(0);
		
	}
	
	public void btnDeal(int i) {
		
		if (i == 0) {
			btnDeal.setOnAction(e -> {
				manager.initializeDeck();
				manager.shuffleCards();
				//manager.getCards();
				displayCards("No");
				pane1.getChildren().add(playerAmountText);
				pane1.getChildren().add(robot1AmountText);
				pane1.getChildren().add(playerPotText);
				pane1.getChildren().add(robot1PotText);
				pane1.getChildren().remove(btnDeal);
				manager.numHands += 1;
				runHand("First");
			});
		}
		else {
			btnDeal.setOnAction(e -> {
				manager.initializeDeck();
				manager.shuffleCards();
				//manager.getCards();
				displayCards("No");
				pane1.getChildren().remove(btnDeal);
				manager.numHands += 1;
				runHand("First");
			});
		}
		
	}
	public void runHand(String bettingRoundType)
	{
		if (bettingRoundType == "First") {
			actionText.setText("The First Betting Round Ensues");
			btnDiscard1.setTextFill(Color.BLACK);
			btnDiscard2.setTextFill(Color.BLACK);
			btnDiscard3.setTextFill(Color.BLACK);
			btnDiscard4.setTextFill(Color.BLACK);
			btnDiscard5.setTextFill(Color.BLACK);
			for (int i = 0; i < 5; i ++) {
				manager.player.cardArray[i].discard_value = 0;
			}
		}
		else if (bettingRoundType == "Second") {
			actionText.setText("The Second Betting Round Ensues");
		}
		
		displayAmounts();
		bettingRound(bettingRoundType);
	}
	
	public void robotBettingRoundIfPlayerBets(String roundType) 
	{
		int x = randomFrom(1, 6);
		
		if (x == 1) {
			if (manager.player.current_pot + 50 <= manager.robot1.chip_amount_beginning) {
				if (manager.player.chip_amount >= 50) {
					manager.robot1.current_pot = (manager.player.current_pot + 50);
					manager.robot1.chip_amount = (manager.robot1.chip_amount_beginning - manager.robot1.current_pot);
					actionText.setText("The Robot Raises 50 Chips");
					bettingRound(roundType);
				}
				else {
					x = 4;
				}
			}
			else {
				x = 4;
			}
		}
		if (x == 2) {
			if (manager.player.current_pot + 100 <= manager.robot1.chip_amount_beginning) {
				if (manager.player.chip_amount >= 100) {
					manager.robot1.current_pot = (manager.player.current_pot + 100);
					manager.robot1.chip_amount = (manager.robot1.chip_amount_beginning - manager.robot1.current_pot);
					actionText.setText("The Robot Raises 100 Chips");
					bettingRound(roundType);
				}
				else {
					x = 4;
				}
				
			}
			else {
				x = 4;
			}
		}
		if (x == 3) {
			if (manager.player.current_pot + 150 <= manager.robot1.chip_amount_beginning) {
				if (manager.player.chip_amount >= 150) {
					manager.robot1.current_pot = (manager.player.current_pot + 150);
					manager.robot1.chip_amount = (manager.robot1.chip_amount_beginning - manager.robot1.current_pot);
					actionText.setText("The Robot Raises 150 Chips");
					bettingRound(roundType);
				}
				else {
					x = 4;
				}
			}
			else {
				x = 4;
			}
		}
		if (x == 4) {
			if (manager.player.current_pot - manager.robot1.current_pot < manager.robot1.chip_amount) {
				manager.robot1.current_pot = manager.player.current_pot;
				manager.robot1.chip_amount = (manager.robot1.chip_amount_beginning - manager.robot1.current_pot);
				actionText.setText("The Robot Calls the Player's Bet");
			}
			else {
				manager.robot1.current_pot += manager.robot1.chip_amount;
				manager.robot1.chip_amount = 0;
				actionText.setText("The Robot Calls the Player's Bet");
			}
			if (manager.robot1.chip_amount < 0) {
				manager.robot1.chip_amount = 0;
				manager.robot1.current_pot = 2000 - manager.player.chip_amount - manager.player.current_pot;
			}
			
			if (roundType == "First") {
				discardRound();
			}
			else if (roundType == "Second") {
				actionText.setText(manager.showWinner());
				manager.player.chip_amount_beginning = manager.player.chip_amount;
				manager.robot1.chip_amount_beginning = manager.robot1.chip_amount;
				if (manager.player.chip_amount != 0 && manager.robot1.chip_amount != 0) {
					pane1.getChildren().add(btnDeal);
					btnDeal(1);
				}
				else {
					manager.writeFile();
					actionText.setText(manager.readFile());
					pane1.getChildren().add(btnNewGame);
					btnNewGame();
				}
				
				displayAmounts();
				displayCards("Yes");
				
			}
			//Robot Calls, move on to discard round
		}
		if (x == 5) {
			handFold(1);
		}
		displayAmounts();
		//bettingRound(/*manager.player.chip_amount_beginning, manager.robot1.chip_amount_beginning*/);

	}
	
	public void robotBettingRoundIfPlayerChecks(String roundType) 
	{
		int x = randomFrom(1, 5);
		
		if (x == 1) {
			if (manager.robot1.chip_amount >= 50) {
				if (manager.player.chip_amount >= 50) {
					manager.robot1.current_pot += 50;
					manager.robot1.chip_amount -= 50;
					actionText.setText("The Robot Bets 50 Chips");
					bettingRound(roundType);
				}
				else {
					x = 4;
				}
			}
			else {
				x = 4;
			}
		}
		if (x == 2) {
			if (manager.robot1.chip_amount >= 100) {
				if (manager.player.chip_amount >= 100) {
					manager.robot1.current_pot += 100;
					manager.robot1.chip_amount -= 100;
					actionText.setText("The Robot Bets 100 Chips");
					bettingRound(roundType);
				}
				else {
					x = 4;
				}
			}
			else {
				x = 4;
			}
		}
		if (x == 3) {
			if (manager.robot1.chip_amount >= 150) {
				if (manager.player.chip_amount >= 150) {
					manager.robot1.current_pot += 150;
					manager.robot1.chip_amount -= 150;
					actionText.setText("The Robot Bets 150 Chips");
					bettingRound(roundType);
				}
				else {
					x = 4;
				}
			}
			else {
				x = 4;
			}
		}
		if (x == 4) {
			actionText.setText("The Robot Checks");
			if (roundType == "First") {
				discardRound();
			}
			else if (roundType == "Second") {
				actionText.setText(manager.showWinner());
				manager.player.chip_amount_beginning = manager.player.chip_amount;
				manager.robot1.chip_amount_beginning = manager.robot1.chip_amount;
				if (manager.player.chip_amount != 0 && manager.robot1.chip_amount != 0) {
					pane1.getChildren().add(btnDeal);
					btnDeal(1);
				}
				else {
					manager.writeFile();
					actionText.setText(manager.readFile());
					pane1.getChildren().add(btnNewGame);
					btnNewGame();
				}
				
				displayAmounts();
				displayCards("Yes");
			}
			//Robot Checks, move on to discard round
		}
		displayAmounts();
		//bettingRound(/*manager.player.chip_amount_beginning, manager.robot1.chip_amount_beginning*/);

	}
	
	public int randomFrom (int low, int high) {

		int randNum = 0;
		randNum = (int) (Math.random()*(high-low) + low);
		return randNum;
	}
	
	public void bettingRound(String roundType)
	{
		if (manager.player.current_pot == manager.robot1.current_pot) {
			manager.player.chip_amount_beginning = manager.player.chip_amount + manager.player.current_pot;
			if (manager.player.chip_amount >= 50) {
				pane1.getChildren().add(btnBet50);
			}
			if (manager.player.chip_amount >= 100) {
				pane1.getChildren().add(btnBet100);
			}
			if (manager.player.chip_amount >= 150) {
				pane1.getChildren().add(btnBet150);
			}
			pane1.getChildren().add(btnCheck);
			pane1.getChildren().add(btnFold);
			
			btnBet50.setOnAction(e -> {
				pane1.getChildren().remove(btnBet50);
				pane1.getChildren().remove(btnBet100);
				pane1.getChildren().remove(btnBet150);
				pane1.getChildren().remove(btnCheck);
				pane1.getChildren().remove(btnFold);
				manager.player.current_pot += 50;
				manager.player.chip_amount -= 50;
				robotBettingRoundIfPlayerBets(roundType);
				displayAmounts();
			});
			
			btnBet100.setOnAction(e -> {
				pane1.getChildren().remove(btnBet50);
				pane1.getChildren().remove(btnBet100);
				pane1.getChildren().remove(btnBet150);
				pane1.getChildren().remove(btnCheck);
				pane1.getChildren().remove(btnFold);
				manager.player.current_pot += 100;
				manager.player.chip_amount -= 100;
				robotBettingRoundIfPlayerBets(roundType);
				displayAmounts();
			});
			
			btnBet150.setOnAction(e -> {
				pane1.getChildren().remove(btnBet50);
				pane1.getChildren().remove(btnBet100);
				pane1.getChildren().remove(btnBet150);
				pane1.getChildren().remove(btnCheck);
				pane1.getChildren().remove(btnFold);
				manager.player.current_pot += 150;
				manager.player.chip_amount -= 150;
				robotBettingRoundIfPlayerBets(roundType);
				displayAmounts();
			});
			
			btnCheck.setOnAction(e -> {
				pane1.getChildren().remove(btnBet50);
				pane1.getChildren().remove(btnBet100);
				pane1.getChildren().remove(btnBet150);
				pane1.getChildren().remove(btnCheck);
				pane1.getChildren().remove(btnFold);
				robotBettingRoundIfPlayerChecks(roundType);
			});
			
			btnFold.setOnAction(e -> {
				pane1.getChildren().remove(btnBet50);
				pane1.getChildren().remove(btnBet100);
				pane1.getChildren().remove(btnBet150);
				pane1.getChildren().remove(btnCheck);
				pane1.getChildren().remove(btnFold);
				handFold(2);
				//End the hand
			});
			
		}
		else if (manager.robot1.current_pot > manager.player.current_pot) {
			//This means the robot has just raised, player cannot bet, only raise, call, or fold
			manager.player.chip_amount_beginning = manager.player.chip_amount + manager.player.current_pot;
			if (manager.robot1.current_pot + 150 <= manager.player.chip_amount_beginning) {
				pane1.getChildren().add(btnRaise150);
			}
			if (manager.robot1.current_pot + 100 <= manager.player.chip_amount_beginning) {
				pane1.getChildren().add(btnRaise100);
			}
			if (manager.robot1.current_pot + 50 <= manager.player.chip_amount_beginning) {
				pane1.getChildren().add(btnRaise50);
			}
			pane1.getChildren().add(btnCall);
			pane1.getChildren().add(btnFold);
			
			btnRaise50.setOnAction(e -> {
				pane1.getChildren().remove(btnRaise50);
				pane1.getChildren().remove(btnRaise100);
				pane1.getChildren().remove(btnRaise150);
				pane1.getChildren().remove(btnCall);
				pane1.getChildren().remove(btnFold);
				manager.player.current_pot = manager.robot1.current_pot + 50;
				manager.player.chip_amount = manager.player.chip_amount_beginning - manager.player.current_pot;
				robotBettingRoundIfPlayerBets(roundType);
				displayAmounts();
			});
			
			btnRaise100.setOnAction(e -> {
				pane1.getChildren().remove(btnRaise50);
				pane1.getChildren().remove(btnRaise100);
				pane1.getChildren().remove(btnRaise150);
				pane1.getChildren().remove(btnCall);
				pane1.getChildren().remove(btnFold);
				manager.player.current_pot = manager.robot1.current_pot + 100;
				manager.player.chip_amount = manager.player.chip_amount_beginning - manager.player.current_pot;
				robotBettingRoundIfPlayerBets(roundType);
				displayAmounts();
			});
			
			btnRaise150.setOnAction(e -> {
				pane1.getChildren().remove(btnRaise50);
				pane1.getChildren().remove(btnRaise100);
				pane1.getChildren().remove(btnRaise150);
				pane1.getChildren().remove(btnCall);
				pane1.getChildren().remove(btnFold);
				manager.player.current_pot = manager.robot1.current_pot + 150;
				manager.player.chip_amount = manager.player.chip_amount_beginning - manager.player.current_pot;
				robotBettingRoundIfPlayerBets(roundType);
				displayAmounts();
			});
			
			btnCall.setOnAction(e -> {
				pane1.getChildren().remove(btnRaise50);
				pane1.getChildren().remove(btnRaise100);
				pane1.getChildren().remove(btnRaise150);
				pane1.getChildren().remove(btnCall);
				pane1.getChildren().remove(btnFold);
				actionText.setText("The Player Calls the Robot's Bet");
				manager.player.current_pot = manager.robot1.current_pot;
				manager.player.chip_amount = manager.player.chip_amount_beginning - manager.player.current_pot;
				if (manager.player.chip_amount < 0) {
					manager.player.chip_amount = 0;
					manager.player.current_pot = manager.player.chip_amount_beginning;
				}
				displayAmounts();
				if (roundType == "First") {
					discardRound();
				}
				else if (roundType == "Second") {
					actionText.setText(manager.showWinner());
					manager.player.chip_amount_beginning = manager.player.chip_amount;
					manager.robot1.chip_amount_beginning = manager.robot1.chip_amount;
					if (manager.player.chip_amount != 0 && manager.robot1.chip_amount != 0) {
						pane1.getChildren().add(btnDeal);
						btnDeal(1);
					}
					else {
						manager.writeFile();
						actionText.setText(manager.readFile());
						pane1.getChildren().add(btnNewGame);
						btnNewGame();
					}
					
					
					displayAmounts();
					displayCards("Yes");
					
				}
				
				//Player calls, move on to discard round
			});
			
			btnFold.setOnAction(e -> {
				pane1.getChildren().remove(btnRaise50);
				pane1.getChildren().remove(btnRaise100);
				pane1.getChildren().remove(btnRaise150);
				pane1.getChildren().remove(btnCall);
				pane1.getChildren().remove(btnFold);
				handFold(2);
				//End the hand
			});
		}
		else if(manager.player.chip_amount == 0) {
			pane1.getChildren().add(btnCheck);
			pane1.getChildren().add(btnFold);
			
			btnCheck.setOnAction(e -> {
				pane1.getChildren().remove(btnCheck);
				pane1.getChildren().remove(btnFold);
				robotBettingRoundIfPlayerChecks(roundType);
			});
			
			btnFold.setOnAction(e -> {
				pane1.getChildren().remove(btnCall);
				pane1.getChildren().remove(btnFold);
				handFold(2);
				//End the hand
			});
			
		}
	}
	public void btnNewGame() {
		btnNewGame.setOnAction(e -> {
			pane1.getChildren().remove(btnNewGame);
			manager.initializeDeck();
			manager.shuffleCards();
			//manager.getCards();
			displayCards("No");
			manager.player.chip_amount = 1000;
			manager.player.chip_amount_beginning = 1000;
			manager.robot1.chip_amount = 1000;
			manager.robot1.chip_amount_beginning = 1000;
			manager.numHands = 1;
			manager.numHandsLost = 0;
			manager.numHandsWon = 0;
			displayAmounts();
			runHand("First");
			
		});
	}
	public void toggleButtonColor(Button btn, int value) {
		if (value == 0) {
			btn.setTextFill(Color.RED);
		}
		else if (value == 1) {
			btn.setTextFill(Color.BLACK);
		}
	}
	
	
	public void discardRound() {
		pane1.getChildren().add(btnDiscard1);
		pane1.getChildren().add(btnDiscard2);
		pane1.getChildren().add(btnDiscard3);
		pane1.getChildren().add(btnDiscard4);
		pane1.getChildren().add(btnDiscard5);
		pane1.getChildren().add(btnExchange);
		
		actionText.setText("Choose which cards to discard, then press on the \n'Exchange' Button to swap out those cards");
		
		
		
		btnDiscard1.setOnAction(e -> {
			toggleButtonColor(btnDiscard1, manager.player.cardArray[0].discard_value);
			manager.player.cardArray[0].discard_value = 1 - manager.player.cardArray[0].discard_value;
			System.out.println("Card #0 discard value is: " + manager.player.cardArray[0].discard_value);
		});
		
		btnDiscard2.setOnAction(e -> {
			toggleButtonColor(btnDiscard2, manager.player.cardArray[1].discard_value);
			manager.player.cardArray[1].discard_value = 1 - manager.player.cardArray[1].discard_value;
			System.out.println("Card #1 discard value is: " + manager.player.cardArray[1].discard_value);
		});
		
		btnDiscard3.setOnAction(e -> {
			toggleButtonColor(btnDiscard3, manager.player.cardArray[2].discard_value);
			manager.player.cardArray[2].discard_value = 1 - manager.player.cardArray[2].discard_value;
			System.out.println("Card #2 discard value is: " + manager.player.cardArray[2].discard_value);
		});
		
		btnDiscard4.setOnAction(e -> {
			toggleButtonColor(btnDiscard4, manager.player.cardArray[3].discard_value);
			manager.player.cardArray[3].discard_value = 1 - manager.player.cardArray[3].discard_value;
			System.out.println("Card #3 discard value is: " + manager.player.cardArray[3].discard_value);
		});
		
		btnDiscard5.setOnAction(e -> {
			toggleButtonColor(btnDiscard5, manager.player.cardArray[4].discard_value);
			manager.player.cardArray[4].discard_value = 1 - manager.player.cardArray[4].discard_value;
			System.out.println("Card #4 discard value is: " + manager.player.cardArray[4].discard_value);
		});
		
		btnExchange.setOnAction(e -> {
			pane1.getChildren().remove(btnDiscard1);
			pane1.getChildren().remove(btnDiscard2);
			pane1.getChildren().remove(btnDiscard3);
			pane1.getChildren().remove(btnDiscard4);
			pane1.getChildren().remove(btnDiscard5);
			pane1.getChildren().remove(btnExchange);
			manager.exchangeCards();
			displayCards("No");
			manager.player.chip_amount_beginning = manager.player.chip_amount;
			manager.robot1.chip_amount_beginning = manager.robot1.chip_amount;
			runHand("Second");
		});
	}
	
	public void handFold(int winner) {
		if (winner == 1) {
			actionText.setText("The Robot has Folded: The Player Wins the Hand!\n"
					+ "To play another hand, press 'Deal the Cards'");
			
			manager.player.chip_amount += manager.player.current_pot;
			manager.player.chip_amount += manager.robot1.current_pot;
			manager.player.current_pot = 0;
			manager.robot1.current_pot = 0;
			manager.player.chip_amount_beginning = manager.player.chip_amount;
			manager.robot1.chip_amount_beginning = manager.robot1.chip_amount;
			manager.numHandsWon++;
		}
		if (winner == 2) {
			actionText.setText("The Player has Folded: The Robot Wins the Pot!\n"
					+ "To play another hand, press 'Deal the Cards'");
			
			manager.robot1.chip_amount += manager.player.current_pot;
			manager.robot1.chip_amount += manager.robot1.current_pot;
			manager.player.current_pot = 0;
			manager.robot1.current_pot = 0;
			manager.player.chip_amount_beginning = manager.player.chip_amount;
			manager.robot1.chip_amount_beginning = manager.robot1.chip_amount;
			manager.numHandsLost++;
		}
		if (manager.robot1.chip_amount != 0 && manager.player.chip_amount != 0) {
			pane1.getChildren().add(btnDeal);
			displayAmounts();
			displayCards("Yes");
			btnDeal(1);
		}
		else {
			manager.writeFile();
			actionText.setText(manager.readFile());
			displayAmounts();
			displayCards("Yes");
		}
	}
	
	public void displayAmounts() {
		playerAmountText.setText("Player chips: \n" + manager.player.chip_amount);
		robot1AmountText.setText("Robot chips: \n" + manager.robot1.chip_amount);
		playerPotText.setText("Player Pot: \n" + manager.player.current_pot);
		robot1PotText.setText("Robot Pot: \n" + manager.robot1.current_pot);
	}
	
	public void displayCards(String dispRobot)
	{
		if (dispRobot == "No" || dispRobot == "All") {
			for (int i = 0; i < 57; i++) {
				pane1.getChildren().remove(imageview[i]);
			}
		}
		//Exception handling here, if for some reason the images don't exist yet, the method will catch and continue
		try {
			for (int i = 0; i < 57; i++) {
				imageview[i] = new ImageView(images[i]);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		int array2[] = {0, 0, 0, 0, 0};
		
		if (dispRobot != "All") {
			for (int i = 0; i < 5; i++) {
				imageview[manager.player.cardArray[i].card_value].setX(100 + (i*50));
				imageview[manager.player.cardArray[i].card_value].setY(400);
				imageview[manager.player.cardArray[i].card_value].setFitHeight(175);
				imageview[manager.player.cardArray[i].card_value].setFitWidth(100);
				pane1.getChildren().add(imageview[manager.player.cardArray[i].card_value]);
				array2[i] = manager.player.cardArray[i].card_value;
			}
		}
		
		
		if (dispRobot == "Yes") {
			pane1.getChildren().remove(imageview[52]);
			pane1.getChildren().remove(imageview[53]);
			pane1.getChildren().remove(imageview[54]);
			pane1.getChildren().remove(imageview[55]);
			pane1.getChildren().remove(imageview[56]);
			
			for (int i = 0; i < 5; i++) {
				imageview[manager.robot1.cardArray[i].card_value].setX(100 + (i * 50));
				imageview[manager.robot1.cardArray[i].card_value].setY(0);
				imageview[manager.robot1.cardArray[i].card_value].setFitHeight(175);
				imageview[manager.robot1.cardArray[i].card_value].setFitWidth(100);
				pane1.getChildren().add(imageview[manager.robot1.cardArray[i].card_value]);
			}
		}
		else if (dispRobot == "No"){
			for (int i = 52; i < 57; i++) {
				imageview[i].setX(100 + ((i - 52) * 50));
				imageview[i].setY(-100);
				imageview[i].setFitHeight(175);
				imageview[i].setFitWidth(100);
				pane1.getChildren().add(imageview[i]);
			}
			
		}
	}
	public void setProperties()
	{
		btnStartGame.setLayoutX(200);
		btnStartGame.setLayoutY(200);
		btnPlayerGame.setLayoutX(200);
		btnPlayerGame.setLayoutY(200);
		chooseGameText.setFill(Color.RED);
		playerGameText.setFill(Color.RED);
		btnBeginPlayerGame.setLayoutX(175);
		btnBeginPlayerGame.setLayoutY(200);
		btnDeal.setLayoutX(0);
		btnDeal.setLayoutY(250);
		playerAmountText.setLayoutX(420);
		playerAmountText.setLayoutY(400);
		robot1AmountText.setLayoutX(20);
		robot1AmountText.setLayoutY(100);
		btnBet50.setLayoutX(400);
		btnBet50.setLayoutY(180);
		btnBet100.setLayoutX(400);
		btnBet100.setLayoutY(220);
		btnBet150.setLayoutX(400);
		btnBet150.setLayoutY(260);
		btnCheck.setLayoutX(400);
		btnCheck.setLayoutY(300);
		btnFold.setLayoutX(400);
		btnFold.setLayoutY(340);
		btnRaise50.setLayoutX(400);
		btnRaise50.setLayoutY(180);
		btnRaise100.setLayoutX(400);
		btnRaise100.setLayoutY(220);
		btnRaise150.setLayoutX(400);
		btnRaise150.setLayoutY(260);
		btnCall.setLayoutX(400);
		btnCall.setLayoutY(300);
		playerPotText.setLayoutX(125);
		playerPotText.setLayoutY(300);
		robot1PotText.setLayoutX(125);
		robot1PotText.setLayoutY(200);
		actionText.setLayoutX(125);
		actionText.setLayoutY(250);
		btnDiscard1.setLayoutX(100);
		btnDiscard1.setLayoutY(375);
		btnDiscard2.setLayoutX(150);
		btnDiscard2.setLayoutY(375);
		btnDiscard3.setLayoutX(200);
		btnDiscard3.setLayoutY(375);
		btnDiscard4.setLayoutX(250);
		btnDiscard4.setLayoutY(375);
		btnDiscard5.setLayoutX(300);
		btnDiscard5.setLayoutY(375);
		btnExchange.setLayoutX(425);
		btnExchange.setLayoutY(300);
		btnNewGame.setLayoutX(0);
		btnNewGame.setLayoutY(250);
		btnClose.setLayoutX(0);
		btnClose.setLayoutY(475);
		actionText.setFill(Color.RED);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setScene(scene);
		primaryStage.setTitle("Five Card Draw Poker");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}