// Purpose:          Creates a Paper object for the rock paper scissors game.
// Programmer:       Araceli YM
// Created on:       8/1/24

public class Paper extends GameObject implements ActionEventGameObject
{
   public Paper()
   {
      super("Paper");
   }
   
   public String getImagePath()
   {
      String paperImgPath = "C:\\Users\\Kiwi\\Downloads\\paper.png";
      
      return paperImgPath;
   }
}