// Purpose:       Handles the actions associated with taking a turn in rock, paper, scissors, game.
// Programmer:    Araceli YM
// Created on:    7/30/24

import  java.util.Random;

public class PlayTurn
{  
   private int comScore = 0;
   private int playerScore = 0;
   private String choice;
   
   final String choiceList[] = {"Rock", "Paper", "Scissors", "Dynamite"};
   
   Rock rock = new Rock();
   Paper paper = new Paper();
   Scissors scissors = new Scissors();
   Dynamite dynamite = new Dynamite();
   
   public void generateComChoice(boolean dynamiteAvailable)
   {
      Random rand = new Random();
      int max;
      int min = 0;
      int choiceID;
      
      if (dynamiteAvailable)
      {
         max = 3;
         choiceID = rand.nextInt(max - min + 1);
         choice = choiceList[choiceID];
      }
      else
      {
         // Computer can select any choice EXCEPT dynamite.
         max = 2;
         choiceID = rand.nextInt(max - min + 1);
         choice = choiceList[choiceID];
      }
   }
   
   public String returnImagePath()
   {
      String imagePath = "";
      
      if (choice == "Rock")
      {
         imagePath = rock.getImagePath();
      }
      else if (choice == "Paper")
      {
         imagePath = paper.getImagePath();
      }
      else if (choice == "Scissors")
      {
         imagePath = scissors.getImagePath();
      }
      else if (choice == "Dynamite")
      {
         imagePath = dynamite.getImagePath();
      }
      
      return imagePath;
   }
   
   public String getComChoice()
   {
      return choice;
   }
   
   // CIS263: This method throws my custom exception.
   public int compareObjects(GameObject gameObject) throws TieGameException
   {
      String playerObject = gameObject.getName();
      int gameResults = 0;
      
      if (choice == playerObject)
      {
         throw new TieGameException();
      }
      else if (choice == "Dynamite")
      {
         // COM played dynamite, dynamite is an automatic win.
         gameResults = 2;
      }
      else if (playerObject == "Dynamite")
      {
         // Player played dynamite, dynamite is an automatic win.
         gameResults = 1;
      }
      
      else if (playerObject == "Rock")
      {
         if (choice == "Scissors")
         {
            // Player won.
            gameResults = 1;
         }
         else
         {
            // COM won. Both dynamite and paper win against rock.
            gameResults = 2;
         }
      }
      else if (playerObject == "Paper")
      {
         if (choice == "Rock")
         {
          // Player won.
            gameResults = 1;
         }
         else
         {
            // COM won. Both dynamite and scissors win against paper.
            gameResults = 2;
         }
      }
      else if (playerObject == "Scissors")
      {
         if (choice == "Paper")
         {
          // Player won.
            gameResults = 1;
         }
         else
         {
            // COM won. Both dynamite and rock win against scissors.
            gameResults = 2;
         }
      }
      
      if (gameResults == 1)
      {
         playerScore = playerScore + 1;
      }
      else
      {
         comScore = comScore + 1;
      }
      return gameResults;
   }
   
   public int getPlayerScore()
   {
      // playerScore = playerScore + 1;
      return playerScore;
   }
   
   public int getComScore()
   {
      // comScore = comScore + 1;
      return comScore;
   }
   
   public void resetScores()
   {
      playerScore = 0;
      comScore = 0;
   }
}