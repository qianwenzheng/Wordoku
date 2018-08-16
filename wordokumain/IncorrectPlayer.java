package us.ihmc.games.wordoku;

public class IncorrectPlayer implements WordokuPlayerInterface
{

   private int numberOfMoves;

   public char getLetter()
   {
      return incorrectLetters[numberOfMoves];
   }

   public int getRow()
   {
      return incorrectPositions[numberOfMoves][0]; //row number corresponding with input letter
   }

   public int getColumn()
   {
      numberOfMoves++;
      return incorrectPositions[numberOfMoves - 1][1]; //column number corresponding with input letter
   }
}
