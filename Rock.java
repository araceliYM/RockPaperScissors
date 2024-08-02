// Purpose:          Creates a Rock object for the rock paper scissors game.
// Programmer:       Araceli YM
// Created on:       7/28/24
// Notes for prof:   I have many comments that you didn't require. I specified assignment specific comments by prefacing them with "CIS263:"

public class Rock extends GameObject implements ActionEventGameObject
{
   public Rock()
   {
      super("Rock");
   }
   
   public String getImagePath()
   {
      String rockImgPath = "C:\\Users\\Kiwi\\Downloads\\rock.png";
      
      return rockImgPath;
   }
}