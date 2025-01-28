package src;

/*
 * Final Project for CS 225
 * Project Name: A Simulation of Five-Card Draw Poker
 * Class: Robot
 * Author: Jacob Attia
 * 
 * Class Description:
 * This class is a child of GenericPlayer, fulfilling the inheritance requirement of the project. It represents the AI, during
 * a player vs. AI game. I had originally created the Player and Robot classes as children of the GenericPlayer class for the purpose
 * of having an AI loaded with strategies to make it fundamentally different from the Player, but, as described in the project, I did not
 * have enough time to complete that aspect of the game. Furthermore, the way I went about creating the game had me changing the chips and
 * exchanging the cards in other methods, so by the time I finished, there was almost no need for the Player and Robot classes. However,
 * they serve their purpose now, and the game works almost perfectly the way it is. 
 * 
 * List of Attributes:
 * strategy_num: in the future this will hold the number of the strategy used by the robot, which will be factored into 
 * corresponding methods for betting and exchanging cards
 * 
 * 
 * List of Methods:
 * null
 * 
 * Bugs: No bugs are known
 * 
 */
public class Robot extends GenericPlayer
{
	int strategy_num;
}