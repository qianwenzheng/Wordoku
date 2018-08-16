package us.ihmc.games.wordoku;

public class WordokuGamePlay
{

   boolean puzzleSolved;
   boolean validMove = true;

   public boolean play(WordokuPlayerInterface player, WordokuPuzzle puzzle)
   {
      while (!puzzleSolved && validMove)
      {
         System.out.println(puzzle.printBoard());

         //NB: It is important that you getColumn LAST bc it increments numberOfMoves
         char letter = player.getLetter();
         int row = player.getRow();
         int column = player.getColumn();

         validMove = puzzle.modify(row, column, letter);
         puzzleSolved = puzzle.completeBoard();
      }
      return puzzleSolved;
   }
}
