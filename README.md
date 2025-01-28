# Five-Card-Draw-Poker-Simulator

This is a program to simulate playing five card draw poker against an AI.

To run the simulation, clone and run src/GUI.java

Classes src/Player.java and src/Robot.java represent the player and AI respectively - and they both inherit src/GenericPlayer.java.

src/Card.java represents a Card object, with attributes for name, suit, and other values used in processing.

src/Manager.java creates player and AI objects, then creates a deck of Cards to operate the game with. It initializes and shuffles the deck, manages the pot values, exchanges Cards from players during the exchange round, and determines who wins the hands.
