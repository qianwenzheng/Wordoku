package us.ihmc.games.wordoku;

import java.util.Scanner;

public class HumanWordokuPlayer implements WordokuPlayerInterface
{

   public char getLetter()
   {

      System.out.println("Enter the letter you want to input \n");
      Scanner in = new Scanner(System.in);
      return in.next().charAt(0);

   }

   public int getRow()
   {

      System.out.println("Enter the row number (0-3) \n");
      Scanner in = new Scanner(System.in);
      return in.nextInt();

   }

   public int getColumn()
   {

      System.out.println("Enter the column number (0-3) \n");
      Scanner in = new Scanner(System.in);
      return in.nextInt();

   }
}
