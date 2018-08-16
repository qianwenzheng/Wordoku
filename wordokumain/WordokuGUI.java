package us.ihmc.games.wordoku;

import java.awt.*;
import java.util.Random;

import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WordokuGUI extends Application
{
   private WordokuLibrary library;
   private int samplePuzzleIndex;
   private GridPane grid;

   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage theStage)
   {

      //Sets up the GUI components
      theStage.setTitle("Wordoku");
      theStage.setHeight(600);
      theStage.setWidth(600);
      Group root = new Group();
      Scene scene = new Scene(root);
      theStage.setScene(scene);
      scene.setFill(Color.ANTIQUEWHITE);
      Canvas canvas = new Canvas(600, 600);
      root.getChildren().add(canvas);

      //Title
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.setTextAlign(TextAlignment.CENTER);
      gc.setFill(Color.ROYALBLUE);
      gc.setStroke(Color.BLACK);
      gc.setLineWidth(2);
      Font theFont = Font.font("Copperplate Gothic Bold", FontWeight.BOLD, 48);
      gc.setFont(theFont);
      gc.fillText("Wordoku", canvas.getWidth() / 2, 50);
      gc.strokeText("Wordoku", canvas.getWidth() / 2, 50);

      //Board
      grid = new GridPane();
      grid.setPrefSize(300, 300);
      grid.setHgap(5);
      grid.setVgap(5);

      for (int i = 0; i < 4; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            TextField field = new TextField();
            field.setPrefHeight(75);
            field.setPrefWidth(75);
            field.setAlignment(Pos.CENTER);
            field.setFont(Font.font("Courier", FontWeight.BOLD, 20));
            grid.add(field, i, j);
         }
      }
      grid.setLayoutY(100);
      grid.setLayoutX(canvas.getWidth() / 2 - 150);
      root.getChildren().add(grid);

      //Generate new board button
      Button generate = new Button("New Board");
      generate.setLayoutX(canvas.getWidth() / 4);
      generate.setLayoutY(450);
      generate.setStyle(" -fx-background-color: linear-gradient(#ff5400, #be1d00);\r\n" + "    -fx-background-radius: 30;\r\n"
            + "    -fx-background-insets: 0;\r\n" + "    -fx-text-fill: white;");
      root.getChildren().add(generate);

      //Get hint button
      Button hint = new Button("Hint");
      hint.setLayoutX(canvas.getWidth() / 4 + 100);
      hint.setLayoutY(450);
      hint.setStyle("-fx-background-color: \r\n" + "        #c3c4c4,\r\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\r\n"
            + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\r\n" + "    -fx-background-radius: 30;\r\n"
            + "    -fx-background-insets: 0,1,1;\r\n" + "    -fx-text-fill: black;\r\n"
            + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
      root.getChildren().add(hint);

      //Clear button
      Button clear = new Button("Clear");
      clear.setLayoutX(canvas.getWidth() / 2);
      clear.setLayoutY(450);
      clear.setStyle("-fx-background-color: \r\n" + "        #c3c4c4,\r\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\r\n"
            + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\r\n" + "    -fx-background-radius: 30;\r\n"
            + "    -fx-background-insets: 0,1,1;\r\n" + "    -fx-text-fill: black;\r\n"
            + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
      root.getChildren().add(clear);

      //Submit button
      Button submit = new Button("Submit");
      submit.setLayoutX(canvas.getWidth() / 2 + 60);
      submit.setLayoutY(440);
      submit.setStyle("-fx-background-color: " + "linear-gradient(#ffd65b, #e68400)," + "linear-gradient(#ffef84, #f2ba44), "
            + "linear-gradient(#ffea6a, #efaa22)," + "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"
            + "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));" + "-fx-background-radius: 30;"
            + "-fx-background-insets: 0,1,2,3,0;" + "-fx-text-fill: #654b00;" + "-fx-font-weight: bold;" + "-fx-font-size: 14px;" + "-fx-padding: 10 20 10 20");
      root.getChildren().add(submit);

      //Simulates GUI version of the game
      library = new WordokuLibrary();
      Random rand = new Random();
      samplePuzzleIndex = rand.nextInt(WordokuLibrary.NUMBER_OF_PUZZLES);
      newBoard();

      submit.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent e)
         {
            endGame();
         }
      });

      generate.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent e)
         {
            int previousIndex = samplePuzzleIndex;
            while (previousIndex == samplePuzzleIndex)
            {
               samplePuzzleIndex = rand.nextInt(WordokuLibrary.NUMBER_OF_PUZZLES);
            }
            newBoard();
         }
      });

      clear.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent e)
         {
            newBoard();
         }
      });

      hint.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent e)
         {
            for (Node node : grid.getChildren())
            {
               if (((TextField) node).getText().equals(""))
               {
                  ((TextField) node).setText(library.getSolution(samplePuzzleIndex)[grid.getRowIndex(node)][grid.getColumnIndex(node)] + "");
                  return;
               }
            }
         }
      });

      theStage.show();

   }

   //Verifies the solution given by the user
   public boolean submitSolution()
   {
      //Reads in the letters on the board and compares it to solution
      char[][] given = new char[4][4];
      char[][] solution = library.getSolution(samplePuzzleIndex);
      int index = 0;
      for (int i = 0; i < 4; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            TextField current = this.getField(i, j);
            String input = current.getText();
            if (input.length() != 1)
               return false;
            else
            {
               given[i][j] = current.getText().charAt(0);
               if (solution[i][j] != given[i][j])
                  return false;
               index++;
            }
         }
      }
      return true;
   }

   //Gives the user a message depending on if they win or lose
   public void endGame()
   {
      boolean solved = submitSolution();
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Wordoku");
      if (solved)
      {
         alert.setHeaderText("Congratulations!");
         alert.setContentText("You solved the puzzle!");
      }
      else
      {
         alert.setHeaderText("Sorry, try again!");
         alert.setContentText("The solution you entered is invalid.");
      }
      alert.show();
   }

   //Sets up a new board
   public void newBoard()
   {
      WordokuPuzzle puzzle = library.get(samplePuzzleIndex);
      char[][] puzzleArray = puzzle.getArray();

      //Sets up the board with original letters
      for (int i = 0; i < 4; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            char letter = puzzleArray[i][j];
            TextField field = this.getField(i, j);
            if (letter != Character.MIN_VALUE)
            {
               field.setText(puzzleArray[i][j] + "");
               field.setDisable(true);
            }
            else
            {
               field.setText("");
               field.setDisable(false);
            }
         }
      }
   }

   //Gets the textfield at a certain position in the grid
   public TextField getField(int row, int column)
   {
      ObservableList<Node> children = grid.getChildren();
      for (Node node : children)
      {
         if (grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column)
         {
            return (TextField) node;
         }
      }
      return null;
   }
}