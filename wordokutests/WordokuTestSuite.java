package us.ihmc.games.wordoku;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import us.ihmc.commons.MutationTestFacilitator;

@RunWith(Suite.class)
@Suite.SuiteClasses({WordokuGamePlayTest.class, WordokuPuzzleTest.class})
public class WordokuTestSuite
{
   public static void main(String[] args) throws URISyntaxException, IOException
   {
      MutationTestFacilitator mutationTestFacilitator = new MutationTestFacilitator();

      mutationTestFacilitator.addClassesToMutate(WordokuGamePlay.class, WordokuPuzzle.class, CorrectPlayer.class);

      mutationTestFacilitator.addTestClassesToRun(WordokuGamePlayTest.class, WordokuPuzzleTest.class);

      mutationTestFacilitator.doMutationTest();
      mutationTestFacilitator.openResultInBrowser();
   }
}
