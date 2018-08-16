package us.ihmc.games.wordoku;

/**
 * NB: This library currently only contains five sample puzzles, add more in the future. 
 * All puzzles have to contain 4 distinct letters
 * @author Tiffany Zheng
 *
 */

public class WordokuLibrary {
   
   static final int NUMBER_OF_PUZZLES = 5;
   static final String[] WORDS = { "sand", "seal", "love", "leap", "walk"};
   

	//Sample Puzzles (array of Strings that denotes original setup of a board)
	//Each element denotes letterRowNumColNum
	private final String[][] sample = 
	{
	      {"s00", "d12", "n22", "a30"}, //spells "sand"
			{"e03", "e11", "a13", "l21", "s23"}, //spells "seal"
	      {"l00", "o03", "e10", "v22"}, //spells "love"
	      {"a01", "l12", "e20", "p33"}, //spells "leap"
	      {"l01", "a03", "k21", "w31"}, //spells "walk"  
	};
	
	//Solutions for each sample board
	private final char[][][] solutions = {
	      {
            {'s', 'd', 'a', 'n'},
            {'n', 'a', 'd', 's'},
            {'d', 's', 'n', 'a'},
            {'a', 'n', 's', 'd'},
      },
	      {
         {'s', 'a', 'l', 'e'},
         {'l', 'e', 's', 'a'},
         {'e', 'l', 'a', 's'},
         {'a', 's', 'e', 'l'},
	      },
	      
	      {
	         {'l', 'v', 'e', 'o'},
	         {'e', 'o', 'l', 'v'},
	         {'o', 'e', 'v', 'l'},
	         {'v', 'l', 'o', 'e'},
	         },
	      {
	            {'l', 'a', 'p', 'e'},
	            {'p', 'e', 'l', 'a'},
	            {'e', 'p', 'a', 'l'},
	            {'a', 'l', 'e', 'p'},
	            },
	      {
	               {'w', 'l', 'k', 'a'},
	               {'k', 'a', 'w', 'l'},
	               {'a', 'k', 'l', 'w'},
	               {'l', 'w', 'a', 'k'},
	               }
	};
	
	
	/*
	 * Boards for Testing: NB: valid incomplete can be from sample; valid complete from solutions
	 */
	
	//Invalid board with invalid rows, columns, and 2x2 box
	public WordokuPuzzle invalidBoard() 
	{
	   char[][] puzzleArray = {
	         {'s', 'a', 'l', 'e'},
	         {'l', 'e', 's', 'a'},
	         {'e', 'l', 'a', 's'},
	         {'a', 's', 'e', 'e'},
	   };
	   return new WordokuPuzzle(puzzleArray);
	}
	
	//Invalid board with more than 4 distinct letters
	public WordokuPuzzle boardWithMoreThan4DistinctLetters() 
	{
      char[][] puzzleArray = {
            {'s', 'a', 'l', 'p'},
            {'l', 'e', 's', 'a'},
            {'e', 'l', 'a', 's'},
            {'a', 's', 'e', 'l'},
      };
      return new WordokuPuzzle(puzzleArray);
   }
   
	//Board with invalid diagonal word that is valid otherwise
	  public WordokuPuzzle boardWithInvalidWord() 
	  {
	      char[][] puzzleArray = {
	            {'a', 'e', 's', 'l'},
	            {'l', 's', 'e', 'a'},
	            {'e', 'a', 'l', 's'},
	            {'s', 'l', 'a', 'e'},
	      };
	      return new WordokuPuzzle(puzzleArray);
	   }
	  
	//Gets a puzzle from the sample puzzles given an index and builds a WordPuzzle from it
	public WordokuPuzzle get(int index) 
	{
		String[] boardSetup = sample[index];
		char[][] puzzle = new char[4][4];
		for (int i = 0; i < boardSetup.length; i++) {
			char letter = boardSetup[i].charAt(0);
			int row = Character.getNumericValue(boardSetup[i].charAt(1));
			int column = Character.getNumericValue(boardSetup[i].charAt(2));
			puzzle[row][column] = letter;
		}
		return new WordokuPuzzle(puzzle);
	}
	
	//Get methods for testing purposes
	
	//Gets the 'sand' puzzle
   public WordokuPuzzle getSand()
   {
      return this.get(0);
   }
	
   //Gets the 'seal' puzzle
  public WordokuPuzzle getSeal()
  {
     return this.get(1);
  }
  
	//Gets the solution of a specific sample puzzle given an index
	public char[][] getSolution(int index) 
	{
	   return solutions[index];
	}
	
	//For testing purposes: get solution for sand puzzle
	public char[][] getSandSolution() 
	{
	   return getSolution(0);
	}
}
