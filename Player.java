// Purpose:          Java GUI used for rock, paper, scissors game.
// Programmer:       Araceli YM
// Created on:       8/1/24

public class Player
{
   private String firstName;
   private String lastName;
   
   public Player(String newFirstName)
   {
      this.firstName = newFirstName;
      this.lastName = "";
   }
   
   // CIS263: This is the overloaded constructor require by the assignment, allows players to enter a last name instead of just a first name.
   public Player(String newFirstName, String newLastName)
   {
      this.firstName = newFirstName;
      this.lastName = newLastName;
   }
   
   public void setFirstName(String newName)
   {
      this.firstName = newName;
   }
   
   public void setLastName(String newName)
   {
      this.lastName = newName;
   }
   
   public String getFirstName()
   {
      return firstName;
   }
   
   public String getLastName()
   {
      return lastName;
   }
   
   // CIS263: This method overrides the common toString method included with java.
   @Override
   public String toString()
   {
      return(this.getFirstName() + " " + this.getLastName());
   }
}