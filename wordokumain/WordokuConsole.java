package us.ihmc.games.wordoku;

import java.util.Random;

public class WordokuConsole
{
   /**
    * Simulates console version of game
    * The console version is slightly more sophisticated than GUI version in that
    * it verifies each entry the user makes for validity whereas the GUi verifies 
    * the whole board at submission
    */
   public static void main(String[] args)
   {
      System.out.println("A twist on the classic Sudoku board game using a 4x4 grid of letters."
            + "The goal of the game is to place four given letters in each space of the grid"
            + "such that each column, row, and 2x2 box contains each distinct letter only once."
            + "The main diagonal of the 4x4 grid must also spell out a valid English word.");
      System.out.println("In this Wordoku program, a '_' character represents an empty square.");

      //The user plays the puzzle
      WordokuGamePlay game = new WordokuGamePlay();
      Random rand = new Random();
      WordokuLibrary library = new WordokuLibrary();
      int samplePuzzleIndex = rand.nextInt(WordokuLibrary.NUMBER_OF_PUZZLES);
      WordokuPuzzle puzzle = library.get(samplePuzzleIndex);
      boolean win = game.play(new HumanWordokuPlayer(), puzzle);

      if (win)
         System.out.println("Congratulations! \n" + puzzle.printBoard());
      else
         System.out.println("Sorry, you made an invalid move!");
   }
}
