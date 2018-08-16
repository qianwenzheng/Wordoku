package us.ihmc.games.wordoku;

public interface WordokuPlayerInterface
{

   //Modifiable values for testing
   char[] correctLetters = new char[] {'d', 'a', 'n', 'n', 'a', 's', 'd', 's', 'a', 'n', 's', 'd'};
   char[] incorrectLetters = new char[] {'s', 'n', 'a', 'd', 'a', 's', 'a', 'n', 's', 'd'};

   int[][] correctPositions = {{0, 1}, {0, 2}, {0, 3}, {1, 0}, {1, 1}, {1, 3}, {2, 0}, {2, 1}, {2, 3}, {3, 1}, {3, 2}, {3, 3}};
   int[][] incorrectPositions = {{0, 1}, {0, 2}, {0, 3}, {1, 0}, {1, 1}, {1, 3}, {2, 5}, {2, 1}, {2, 3}, {3, 1}, {3, 2}, {3, 3}};

   char getLetter();

   int getRow();

   int getColumn();
}
