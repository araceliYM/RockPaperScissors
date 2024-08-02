// Purpose:          Creates a Dynamite object for the rock paper scissors game.
// Programmer:       Araceli YM
// Created on:       8/1/24

public class Dynamite extends GameObject implements ActionEventGameObject
{
   public Dynamite()
   {
      super("Dynamite");
   }
   
   public String getImagePath()
   {
      String dynamiteImgPath = "C:\\Users\\Kiwi\\Downloads\\dynamite.png";
      
      return dynamiteImgPath;
   }
}