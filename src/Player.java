package src;
/*
 * Final Project for CS 225
 * Project Name: A Simulation of Five-Card Draw Poker
 * Class: Player
 * Author: Jacob Attia
 * 
 * Class Description:
 * This class is a child of GenericPlayer, fulfilling the inheritance requirement of the project. It represents the player, during
 * a player vs. AI game. I had originally created the Player and Robot classes as children of the GenericPlayer class for the purpose
 * of having an AI loaded with strategies to make it fundamentally different from the Player, but, as described in the project, I did not
 * have enough time to complete that aspect of the game. Furthermore, the way I went about creating the game had me changing the chips and
 * exchanging the cards in other methods, so by the time I finished, there was almost no need for the Player and Robot classes. However,
 * they serve their purpose now, and the game works almost perfectly the way it is. 
 * 
 * List of Attributes:
 * null
 * 
 * 
 * List of Methods:
 * chooseRobotStrategy(): in the future, this method will allow the player to choose a strategy for the robot
 * betting_round(): in the future this method will be used to simulate a betting round for the player
 * discard_round(): in the future this method will be used to simulate a discard round for the player
 * 
 * Bugs: No bugs are known
 * 
 */

public class Player extends GenericPlayer
{	
	
	public int chooseRobotStrategy()
	{
		return 0;
	}
	
	public void betting_round()
	{
		
	}
	
	public void discard_round()
	{
		
	}
}