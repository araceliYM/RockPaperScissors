// Purpose:       Handles the exception created when the computer and player tie.
// Programmer:    Araceli YM
// Created on:    7/31/24

// CIS263: Custom exception class which is thrown by the PlayTurn class.
public class TieGameException extends Exception
{
   @Override
   public String getMessage()
   {
      String message = "There was a tie! No points awarded.";
      return message;
   }
}