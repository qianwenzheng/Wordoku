package us.ihmc.games.wordoku;

import org.junit.Assert;
import org.junit.Test;

public class WordokuGamePlayTest
{
   //Tests play with correct solutions
   @Test(timeout = 300000)
   public void testPlayWithValid()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      WordokuPlayerInterface player = new CorrectPlayer();
      WordokuGamePlay game = new WordokuGamePlay();
      Assert.assertTrue(game.play(player, puzzle));
   }

   //Tests play with incorrect entry (repeating letter in row, column, and box)
   @Test(timeout = 30000)
   public void testPlayWithInvalid()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      WordokuPlayerInterface player = new IncorrectPlayer();
      WordokuGamePlay game = new WordokuGamePlay();
      Assert.assertFalse(game.play(player, puzzle));
   }

}
