package us.ihmc.games.wordoku;

import java.util.HashSet;
import java.util.Set;

public class WordokuPuzzle
{
   //game board
   private char[][] board;

   //Dimensions
   private static final int ROWS = 4;
   private static final int COLUMNS = 4;
   private static final int MINIMUM_INDEX = 0;

   //Boxes
   public enum Boxes
   {
      TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;

      public static Boxes getBox(int row, int column)
      {
         if (row < 2 && column < 2)
            return TOP_LEFT;
         else if (row < 2 && column > 2)
            return TOP_RIGHT;
         else if (row > 2 && column < 2)
            return BOTTOM_LEFT;
         else
            return BOTTOM_RIGHT;
      }
   }

   //Original set of 4 distinct letters on the board
   private Set<Character> originalLetters;

   //Dimensions of original positions array
   private static final int NUMBER_OF_POSITIONS = 16;
   private static final int NUMBER_OF_DIMENSIONS = 2;

   //Positions of letters that came with the original board
   private int[][] originalPositions;

   //Number of letters on the original board
   private int numberOfOriginal;

   //Dictionary of Valid Words 
   private String[] validWords = WordokuLibrary.WORDS;

   public WordokuPuzzle(char[][] b)
   {
      board = b;
      originalLetters = this.differentLetters();
      originalPositions = getOriginalPositions();
   }

   //Gets the array of original positions that the user cannot edit
   public int[][] getOriginalPositions()
   {
      numberOfOriginal = 0;
      int[][] positions = new int[NUMBER_OF_POSITIONS][NUMBER_OF_DIMENSIONS];

      for (int row = 0; row < ROWS; row++)
      {
         for (int column = 0; column < COLUMNS; column++)
         {
            if (board[row][column] != Character.MIN_VALUE)
            {
               positions[numberOfOriginal][0] = row;
               positions[numberOfOriginal][1] = column;
               numberOfOriginal++;
            }
         }
      }
      return positions;

   }

   //Gets all the distinct letters of the board
   public Set<Character> differentLetters()
   {
      Set<Character> set = new HashSet<Character>();
      for (int row = 0; row < ROWS; row++)
      {
         for (int column = 0; column < COLUMNS; column++)
         {
            set.add(board[row][column]);
         }
      }
      set.remove(Character.MIN_VALUE);
      return set;
   }

   //Returns true if the board has no more blank spaces
   public boolean completeBoard()
   {
      for (int row = 0; row < ROWS; row++)
      {
         for (int column = 0; column < COLUMNS; column++)
         {
            if (board[row][column] == 0)
            {
               return false;
            }
         }
      }
      return true;

   }

   //Returns true if a given letter is a part of the original set of letters and thus valid
   public boolean validLetter(char letter)
   {
      return originalLetters.contains(letter);
   }

   //Returns true if a given row does not have any repeating letters 
   public boolean validRow(int rowNumber)
   {
      Set<Character> lettersInRow = new HashSet<Character>();

      //Checks for repeating letters
      for (char currentLetter : board[rowNumber])
      {
         //char currentLetter = board[rowNumber][i];
         if (currentLetter != 0)
         {
            if (lettersInRow.contains(currentLetter))
            {
               return false; //contains a repeating letter
            }
            else
            {
               lettersInRow.add(currentLetter);
            }
         }
      }

      return true;
   }

   //Tests if a given column does not have any repeating letters 
   public boolean validColumn(int columnNumber)
   {
      Set<Character> lettersInColumn = new HashSet<Character>();

      //Checks for repeating letters
      for (int i = 0; i < COLUMNS; i++)
      {
         char currentLetter = board[i][columnNumber];
         if (currentLetter != 0)
         {
            if (lettersInColumn.contains(currentLetter))
            {
               return false; //contains a repeating letter
            }
            else
            {
               lettersInColumn.add(currentLetter);
            }
         }
      }

      return true;
   }

   //Tests if a given box does not have any repeating letters 
   public boolean valid2x2Box(Boxes whichBox)
   {
      //Row and column numbers for topleft letter of each 2x2 box
      int rowNumber;
      int columnNumber;

      switch (whichBox)
      {
      case TOP_LEFT:
         rowNumber = columnNumber = 0;
         break;
      case TOP_RIGHT:
         rowNumber = 0;
         columnNumber = 2;
         break;
      case BOTTOM_LEFT:
         rowNumber = 2;
         columnNumber = 0;
         break;
      case BOTTOM_RIGHT:
         rowNumber = columnNumber = 2;
         break;
      default:
         throw new RuntimeException("Not a valid box type");
      }

      //Checks for repeating letters

      Set<Character> set = new HashSet<Character>();
      char[] boxLetters = {board[rowNumber][columnNumber], board[rowNumber][columnNumber + 1], board[rowNumber + 1][columnNumber],
            board[rowNumber + 1][columnNumber + 1]};
      for (char letter : boxLetters)
      {
         if (letter != 0)
         {
            if (set.contains(letter))
               return false;
            else
               set.add(letter);
         }
      }

      return true;
   }

   //Returns true if the main diagonal spells out a valid word
   public boolean diagonalIsWord()
   {
      String word = "" + board[0][0] + board[1][1] + board[2][2] + board[3][3];
      for (int i = 0; i < validWords.length; i++)
      {
         if (validWords[i].equals(word))
            return true;
      }
      return false;
   }

   //Print the current state of the board
   public String printBoard()
   {
      String boardString = "";

      for (int row = 0; row < 4; row++)
      {
         for (int column = 0; column < 4; column++)
         {
            if (board[row][column] != 0)
               boardString = boardString + board[row][column];
            else
               boardString = boardString + "_";
            boardString = boardString + " ";
         }
         boardString = boardString + "\n";
      }
      return boardString;
   }

   //Method to modify a slot on the board (must not be any of the originally filled in slots)
   //NOTE TO CHECK VALIDITY OF ROW AND COL NUMBER AND LETTER WHEREVER YOU CALL THIS METHOD
   public boolean modify(int row, int column, char letter)

   {
      //Exits if the row or column is out of bounds
      if (row < MINIMUM_INDEX || row >= ROWS || column < MINIMUM_INDEX || column >= COLUMNS)
         return false;

      //Exits if the letter is not a part of the 4 distinct ones given
      if (!validLetter(letter))
         return false;

      //Exits if the slot to be modified is in a slot that was prefilled
      for (int i = 0; i < numberOfOriginal; i++)
      {
         if (originalPositions[i][0] == row && originalPositions[i][1] == column)
            return false;
      }

      //Modifies the board
      board[row][column] = letter;

      //Exits if this modification causes repeating letters in row, column, or box
      if (!validRow(row) || !validColumn(column) || !valid2x2Box(Boxes.getBox(row, column)))
      {
         board[row][column] = Character.MIN_VALUE;
         return false;
      }
      return true;
   }

   //Return the char array board
   public char[][] getArray()
   {
      return board;
   }

}
