// Purpose:          Writes scores from rock,paper,scissors game to a file.
// Programmer:       Araceli YM
// Created on:       7/28/24

// CIS263: This class writes scores from the linkedlist to a file.

import java.io.*;
import java.util.*;

public class ScoresFile
{
   public void saveScoresToFile(LinkedList<String> scoresList) throws IOException
   {
         BufferedWriter writer = new BufferedWriter(new FileWriter("ScoresList_RPS.txt", true));
         
         Date saveDate = new Date();
         
         String currDate = saveDate.toString();
         writer.write(currDate + ". The scores were:");
         writer.newLine();
         
         for (int i = 0; i < scoresList.size(); i++)
         {
            writer.write(scoresList.get(i));
            writer.newLine();
         }
         
         writer.close();
   }
}