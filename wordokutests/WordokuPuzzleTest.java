package us.ihmc.games.wordoku;

import org.junit.Assert;
import org.junit.Test;

import us.ihmc.games.wordoku.WordokuPuzzle.Boxes;

//Tests validity for a specific game board (1st in library array)
public class WordokuPuzzleTest
{

   //Tests originalPositions with correct ones
   @Test(timeout = 30000)
   public void testGetOriginalPositionsWithValid()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      int[][] expected = new int[16][2];
      expected[0] = new int[] {0, 0};
      expected[1] = new int[] {1, 2};
      expected[2] = new int[] {2, 2};
      expected[3] = new int[] {3, 0};
      System.out.println(puzzle.getOriginalPositions()[3][0]);
      Assert.assertArrayEquals(expected, puzzle.getOriginalPositions());
   }

   //Tests originalPositions with wrong ones
   @Test(timeout = 30000)
   public void testGetOriginalPositionsWithInvalid()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      int[][] expected = {{1, 0}, {1, 2}, {2, 2}, {3, 0}};
      Assert.assertNotEquals(expected, puzzle.getOriginalPositions());
   }

   //Tests for 4 distinct letters on valid incomplete board
   @Test(timeout = 30000)
   public void testDiffLettersWithValidIncomplete()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      int numLetters = puzzle.differentLetters().size();
      Assert.assertEquals(numLetters, 4);
   }

   //Tests for 4 distinct letters on board with 5 distinct ones
   @Test(timeout = 30000)
   public void testDiffLettersWithBoardWith5Distinct()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().boardWithMoreThan4DistinctLetters();
      Assert.assertEquals(puzzle.differentLetters().size(), 5);
   }

   //Tests if the board is complete on incomplete board
   @Test(timeout = 30000)
   public void testCompleteBoardWithIncomplete()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      Assert.assertFalse(puzzle.completeBoard());

   }

   //Tests if the board is complete on complete board
   @Test(timeout = 30000)
   public void testCompleteBoardWithComplete()
   {
      WordokuPuzzle puzzle = new WordokuPuzzle(new WordokuLibrary().getSandSolution());
      Assert.assertTrue(puzzle.completeBoard());

   }

   //Tests validLetter using a valid letter
   @Test(timeout = 30000)
   public void testValidLetter()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      Assert.assertTrue(puzzle.validLetter('n'));

   }

   //Tests validLetter using an invalid letter
   @Test(timeout = 30000)
   public void testInvalidLetter()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      Assert.assertFalse(puzzle.validLetter('q'));

   }

   //Tests validRow, validColumn, and validBox on valid complete board
   @Test(timeout = 30000)
   public void testValidCompleteBoard()
   {
      WordokuPuzzle puzzle = new WordokuPuzzle(new WordokuLibrary().getSandSolution());
      Assert.assertTrue("Row 0 is not valid", puzzle.validRow(3));
      Assert.assertTrue("Column 0 is not valid", puzzle.validColumn(3));
      Assert.assertTrue("Topleft box is not valid", puzzle.valid2x2Box(Boxes.getBox(3, 3)));
   }

   //Tests validRow, validColumn, and validBox on valid incomplete board
   @Test(timeout = 30000)
   public void testValidIncompleteBoard()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      Assert.assertTrue("Row 0 is not valid", puzzle.validRow(3));
      Assert.assertTrue("Column 0 is not valid", puzzle.validColumn(3));
      Assert.assertTrue("Topleft box is not valid", puzzle.valid2x2Box(Boxes.getBox(3, 3)));
   }

   //Tests validRow, validColumn, and validBox on invalid board
   @Test(timeout = 30000)
   public void testInvalidBoard()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().invalidBoard();
      Assert.assertFalse("Row 0 is valid", puzzle.validRow(3));
      Assert.assertFalse("Column 0 is valid", puzzle.validColumn(3));
      Assert.assertFalse("TopRight box is valid", puzzle.valid2x2Box(Boxes.getBox(3, 3)));
   }

   //Tests if the word on the main diagonal is valid with board with valid word
   @Test(timeout = 30000)
   public void testDiagonalIsWordWithValidBoard()
   {
      WordokuPuzzle puzzle = new WordokuPuzzle(new WordokuLibrary().getSandSolution());
      Assert.assertTrue(puzzle.diagonalIsWord());

   }

   //Tests if the word on the main diagonal is valid with board with invalid word
   @Test(timeout = 30000)
   public void testDiagonalIsWordWithInvalidBoard()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().boardWithInvalidWord();
      Assert.assertFalse(puzzle.diagonalIsWord());

   }

   //Tests printBoard on complete board
   @Test(timeout = 30000)
   public void testPrintCompleteBoard()
   {
      WordokuPuzzle puzzle = new WordokuPuzzle(new WordokuLibrary().getSandSolution());
      String expectedBoard = "s d a n \nn a d s \nd s n a \na n s d \n";
      Assert.assertEquals(expectedBoard, puzzle.printBoard());
   }

   //Tests printBoard on incomplete board
   @Test(timeout = 30000)
   public void testPrintIncompleteBoard()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSand();
      String expectedBoard = "s _ _ _ \n_ _ d _ \n_ _ n _ \na _ _ _ \n";
      Assert.assertEquals(expectedBoard, puzzle.printBoard());
   }

   //Tests getArray
   @Test(timeout = 30000)
   public void testGetArray()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSeal();
      char[][] actual = puzzle.getArray();
      char[][] expected = {{0, 0, 0, 'e'}, {0, 'e', 0, 'a'}, {0, 'l', 0, 's'}, {0, 0, 0, 0},};
      Assert.assertArrayEquals(expected, actual);
   }

   //Tests modify with valid modification
   @Test(timeout = 30000)
   public void testValidModify()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSeal();
      char[][] expected = {{0, 0, 'l', 'e'}, {0, 'e', 0, 'a'}, {0, 'l', 0, 's'}, {0, 0, 0, 0},};
      Assert.assertTrue(puzzle.modify(0, 2, 'l'));
      char[][] actual = puzzle.getArray();
      Assert.assertArrayEquals(expected, actual);
   }

   //Tests modify by trying to modify original given slot)
   @Test(timeout = 30000)
   public void testModifyOriginalSlots()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSeal();
      Assert.assertFalse(puzzle.modify(0, 3, 's'));
   }

   //Tests modify by using a letter not part of the 4 distinct ones given
   @Test(timeout = 30000)
   public void testModifyInvalidLetter()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSeal();
      Assert.assertFalse(puzzle.modify(0, 2, 'q'));
   }

   //Tests modify by using a row and column that is out of bounds
   @Test(timeout = 30000)
   public void testModifyOutOfBounds()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSeal();
      Assert.assertFalse(puzzle.modify(-1, 4, 's'));
   }

   //Tests modify by using a move that causes repeating letters
   @Test(timeout = 30000)
   public void testModifyRepeatingLetters()
   {
      WordokuPuzzle puzzle = new WordokuLibrary().getSeal();
      Assert.assertFalse(puzzle.modify(0, 1, 'e'));
   }

}
