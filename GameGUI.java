// Purpose:          Java GUI used for rock, paper, scissors game.
// Programmer:       Araceli YM
// Created on:       7/28/24
// Notes for prof:   I have many comments that you didn't require. I specified assignment specific comments by prefacing them with "CIS263:" 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.Dimension;
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage; 

// CIS263: Extending JFrame for use with the user inerface.
public class GameGUI extends JFrame implements ActionListener
{
   final String startingImgPath = "C:\\Users\\Kiwi\\Downloads\\rpsBackground.png";
   
   // Used to create a menu for the game.
   private JMenuBar menuBar;
   private JMenu  rulesMenu;
   private JMenuItem instructions;
   private JMenuItem shortcutTips;
   private JMenuItem guide;
   private JMenuItem dynamiteGuide;
   
   private JPanel gamePanel;                                // CIS263: Java Swing element.
   
   // CIS263: Third comment for Swing elements, I have more but didn't want to clutter the file.
   private JTextArea messageArea = new JTextArea(10, 20);
   private JScrollPane scrollPane;

   private JLabel comPlayStatus;                            // Used to label objectImageArea.
   private JLabel objectImageArea;                          // Used to display the choice the computer selects (rock, paper, scissors, dynamite).
   
   // CIS263: Type of java Swing element.
   private JButton startButton;
   private JButton rockButton;
   private JButton paperButton;
   private JButton scissorsButton;
   private JButton dynamiteButton;
   private JButton saveScoresButton;
   
   private boolean savingOn;
   private JCheckBox toggleDynamiteMode;                    // Used to allow player to decide if they want to play with dynamite or without.
   private boolean dynamiteModeOn = false;
   
   // Used to track the amount of times a user selects each object.
   private JLabel scoresLabel;
   private JList<String> scoresList;
   private DefaultListModel<String> scoresListModel;
      // CIS263: LinkedList used to store game scores, also used to write to file.
   private LinkedList<String> scoresStats = new LinkedList<String>();
   
   Player player;
   PlayTurn newTurn = new PlayTurn();
   int gameResults = 0;
   
   public GameGUI()
   {
      setTitle("Rock, Paper, Scissors!");
      
      gamePanel = new JPanel(new GridBagLayout());
      
      // CIS263: Layout manager used for the interface.
      GridBagConstraints layoutConst = new GridBagConstraints();
      layoutConst.anchor = GridBagConstraints.NORTH;
      layoutConst.insets = new Insets(10, 10, 10, 10);
      
      comPlayStatus = new JLabel("COM: Let's play!");
      
      objectImageArea = new JLabel();
      updateComImage(startingImgPath);
      
      startButton = new JButton("Start game");
      startButton.addActionListener(this);
      startButton.setMnemonic('1');
      
      saveScoresButton = new JButton("Save scores");
      saveScoresButton.setToolTipText("Saves the scores to a file");
      saveScoresButton.addActionListener(this);
      
      rockButton = new JButton("Rock");
      rockButton.addActionListener(this);
      rockButton.setVisible(false);
      rockButton.setMnemonic('2');
      
      paperButton = new JButton("Paper");
      paperButton.addActionListener(this);
      paperButton.setVisible(false);
      paperButton.setMnemonic('3');
      
      scissorsButton = new JButton("Scissors");
      scissorsButton.addActionListener(this);
      scissorsButton.setVisible(false);
      scissorsButton.setMnemonic('4');
      
      dynamiteButton = new JButton("Dynamite");
      dynamiteButton.addActionListener(this);
      dynamiteButton.setVisible(false);
      dynamiteButton.setMnemonic('5');
      
      messageArea.setText("Messages will appear here. Open the menu for game instructions.");
      messageArea.setEditable(false);
      messageArea.setLineWrap(true);
      messageArea.setWrapStyleWord(true);
      
      scrollPane = new JScrollPane(messageArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
      toggleDynamiteMode = new JCheckBox("Dynamite mode");
      toggleDynamiteMode.setToolTipText("Gives both the player and the computer the option to play with dynamite. See menu for more details.");
      toggleDynamiteMode.addItemListener(new ItemListener () {
                                         public void itemStateChanged(ItemEvent e)
                                         {
                                             if(e.getStateChange() == 1)
                                             {
                                                dynamiteModeOn = true;
                                                messageArea.setText("Start new game to use dynamite!");
                                             }
                                             else
                                             {
                                                dynamiteModeOn = false;
                                                messageArea.setText("Start new game to remove option");
                                             }
                                         }
                                         });
      
      // GUI elements used to show user scores.
      scoresLabel = new JLabel("Scores:");
      
      scoresListModel = new DefaultListModel<>();
      scoresList = new JList<>(scoresListModel);
      scoresList.setPreferredSize(new Dimension(150, 100));
      
      // Set the layout for the user interface
      layoutConst.gridx = 0;
      layoutConst.gridy = 0;
      gamePanel.add(scoresLabel, layoutConst);
      
      layoutConst.gridx = 0;
      layoutConst.gridy = 1;
      gamePanel.add(scoresList, layoutConst);
      
      layoutConst.gridx = 1;
      layoutConst.gridy = 0;
      gamePanel.add(comPlayStatus, layoutConst);
      
      layoutConst.gridx = 1;
      layoutConst.gridy = 1;
      gamePanel.add(objectImageArea, layoutConst);
      
      layoutConst.gridx = 2;
      layoutConst.gridy = 0;
      gamePanel.add(toggleDynamiteMode, layoutConst);
      
      layoutConst.gridx = 2;
      layoutConst.gridy = 1;
      gamePanel.add(saveScoresButton, layoutConst);
      
      layoutConst.gridx = 1;
      layoutConst.gridy = 2;
      gamePanel.add(startButton, layoutConst);
      
      layoutConst.gridx = 0;
      layoutConst.gridy = 3;
      gamePanel.add(rockButton, layoutConst);
      
      layoutConst.gridx = 1;
      layoutConst.gridy = 3;
      gamePanel.add(paperButton, layoutConst);
      
      layoutConst.gridx = 2;
      layoutConst.gridy = 3;
      gamePanel.add(scissorsButton, layoutConst);
      
      layoutConst.gridx = 3;
      layoutConst.gridy = 3;
      gamePanel.add(dynamiteButton, layoutConst);
     
      layoutConst.gridx = 1;
      layoutConst.gridy = 4;
      gamePanel.add(scrollPane, layoutConst);
      
      initializeMenu();
      
      // Set the border and add panel to frame
      gamePanel.setBorder(BorderFactory.createTitledBorder(
                          BorderFactory.createEtchedBorder(), "Game Play"));
                          
      add(gamePanel);
      pack();
      setLocationRelativeTo(null);
   }
   
   public void initializeMenu()
   {
      // Initializes menu and implements action listeners.
      menuBar = new JMenuBar();
      
      rulesMenu = new JMenu("Game Rules");
      
      instructions = new JMenuItem("How to play");
      instructions.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e)
                     {
                        messageArea.setText("Select an object and see if you won against the computer!");
                     }
                    });
                    
      shortcutTips = new JMenuItem("Shortcut keys guide");
      shortcutTips.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e)
                     {
                        messageArea.setText("Select the Alt key and any of the following options: " + "\nStart[1], Rock[2], Paper[3], Scissors[4]");
                     }
                    });
      
      guide = new JMenuItem("Game guide");
      guide.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e)
                     {
                        messageArea.setText("Rock wins against scissors." + "\nPaper wins against rock." + "\nScissors wins against paper.");
                     }
                    });
                    
      dynamiteGuide = new JMenuItem("Dynamite mode guide");
      dynamiteGuide.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e)
                     {
                        messageArea.setText("Dynamite wins against rock and scissors." + "\nDynamite loses against paper.");
                        messageArea.append("\nSelect the Alt key and [5] to use shortcuts.");
                     }
                    });
      
      rulesMenu.add(instructions);              
      rulesMenu.add(guide);
      rulesMenu.add(shortcutTips);
      rulesMenu.add(dynamiteGuide);
      
      menuBar.add(rulesMenu);
      
      setJMenuBar(menuBar);
   }
   
   public void updateComImage(String photoID)
   {
      // CIS263: Updates the image displayed in objectImageArea to display the choice the computer made.
      try
      {
         File imageFile = new File(photoID);
         BufferedImage image = ImageIO.read(imageFile);
         
         Image finalImage = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
         
         // Set the image on the JLabel
         objectImageArea.setIcon(new ImageIcon(finalImage));
         objectImageArea.setVisible(true);

      }
      catch (IOException e)
      {
         objectImageArea.setText("Image unavailable.");
      }
   }
   
   public void doTurnActions(GameObject object)
   {
      // These lines of code are used by every game object button.
      try
      {
         gameResults = newTurn.compareObjects(object);
            
         // CIS263: Utilizing linked list here to update scores
         int playerScore = newTurn.getPlayerScore();
         int comScore= newTurn.getComScore();
            
         String scoresForPlayer = (player.toString() + ": " + playerScore);
         String scoresForCom = ("COM: " + comScore);
            
         scoresStats.set(0, scoresForPlayer);
         scoresStats.set(1, scoresForCom);
         scoresListModel.removeAllElements();
         scoresListModel.addElement(scoresForPlayer);
         scoresListModel.addElement(scoresForCom);
         
         if (gameResults == 1)
         {
            messageArea.append("\nYou won!");
         }
         else if (gameResults == 2)
         {
            messageArea.append("\nYou lost :(");
         }  
      }
      catch (TieGameException e)    // CIS263: Handling the custom exception thrown in PlayTurn class.
      {
         messageArea.setText(e.getMessage());
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent event)
   {
      JButton sourceEvent = (JButton) event.getSource();
      if (sourceEvent == startButton)
      {
         // Create new player                        
         String playerName = JOptionPane.showInputDialog(null, " Enter a name for the player:");
         String[] names = playerName.split("\\s+'");
         
         if(names.length >= 2) 
         {     
            // Used to determine if player has last name
            String firstName = names[0];
            String lastName = names[1];
            player = new Player(firstName, lastName);
         }
         else
         {
            player = new Player(playerName);
         }
         
         messageArea.setText(player.toString() + ", you may begin!");
         
         newTurn.resetScores();
         scoresListModel.removeAllElements();
         
         if (dynamiteModeOn == false)
         {
            rockButton.setVisible(true);
            paperButton.setVisible(true);
            scissorsButton.setVisible(true);
            
            dynamiteButton.setVisible(false);
         }
         else
         {
            rockButton.setVisible(true);
            paperButton.setVisible(true);
            scissorsButton.setVisible(true);
            dynamiteButton.setVisible(true);
         }
         scoresStats.add(player.toString() + ": 0");
         scoresStats.add("COM: 0");
      }
      else if (sourceEvent == rockButton)
      {
         messageArea.setText(player.toString() + "played: Rock");
         
         newTurn.generateComChoice(dynamiteModeOn);
         messageArea.append("\nCOM played: " + newTurn.getComChoice());
         
         updateComImage(newTurn.returnImagePath());
         
         Rock rock = new Rock();
         
         doTurnActions(rock);         
      }
      else if (sourceEvent == paperButton)
      {
         messageArea.setText(player.toString() + "played: Paper");
         
         newTurn.generateComChoice(dynamiteModeOn);
         messageArea.append("\nCOM played: " + newTurn.getComChoice());
         
         updateComImage(newTurn.returnImagePath());
         
         Paper paper = new Paper();
         
         doTurnActions(paper);    
      }
      else if (sourceEvent == scissorsButton)
      {
         messageArea.setText(player.toString() + "played: Scissors");
         
         newTurn.generateComChoice(dynamiteModeOn);
         messageArea.append("\nCOM played: " + newTurn.getComChoice());
         
         updateComImage(newTurn.returnImagePath());
         
         Scissors scissors = new Scissors();
         
         doTurnActions(scissors);
      }
      else if (sourceEvent == dynamiteButton)
      {
         messageArea.setText(player.toString() + "played: Dynamite");
         
         newTurn.generateComChoice(dynamiteModeOn);
         messageArea.append("\nCOM played: " + newTurn.getComChoice());
         
         updateComImage(newTurn.returnImagePath());
         
         Dynamite dynamite = new Dynamite();
         
         doTurnActions(dynamite);
      }
      else if (sourceEvent == saveScoresButton)
      {
         ScoresFile scores = new ScoresFile();
         
         try
         {
            scores.saveScoresToFile(scoresStats);
            
            messageArea.setText("Scores sucessfully saved to file named: ScoresList_RPS.txt");
         }
         catch (IOException e)
         {
            messageArea.setText("Unable to write to file. Try again later.");
         }
      }
   }
   public static void main(String[] args)
   {
      GameGUI rpsGame = new GameGUI();
      
      rpsGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      rpsGame.setPreferredSize(new Dimension(800, 600));
      rpsGame.pack();
      rpsGame.setVisible(true);
   }
}
