package us.ihmc.games.wordoku;

public class CorrectPlayer implements WordokuPlayerInterface
{

   private int numberOfMoves;

   public char getLetter()
   {
      return correctLetters[numberOfMoves];
   }

   public int getRow()
   {
      return correctPositions[numberOfMoves][0]; //row number corresponding with input letter
   }

   public int getColumn()
   {
      numberOfMoves++;
      return correctPositions[numberOfMoves - 1][1]; //column number corresponding with input letter
   }
}
