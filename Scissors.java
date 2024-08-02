// Purpose:          Creates a Scissors object for the rock paper scissors game.
// Programmer:       Araceli YM
// Created on:       8/1/24

public class Scissors extends GameObject implements ActionEventGameObject
{
   public Scissors()
   {
      super("Scissors");
   }
   
   public String getImagePath()
   {
      String scissorsImgPath = "C:\\Users\\Kiwi\\Downloads\\scissors.png";
      
      return scissorsImgPath;
   }
}