// Purpose:       Creates a parent class for game objects within rock, paper, scissors game.
// Programmer:    Araceli YM
// Created on:    7/30/24

// CIS263: Parent class for Rock, Paper, Scissors, and Dynamite child classes.
public class GameObject
{
   private String objectName;
   
   public GameObject(String object)
   {
      objectName = object;
   }
   public String getName()
   {
      return objectName;
   }
}