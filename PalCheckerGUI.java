/* CODE DESCRIPTION:
 * PalCheckerGUI is a GUI program that checks whether the lines in a file
 * are palindromes.
 * It gets a file to read from from the user and prints out the results to 
 * the interface. 
 * It has a button to get to the next line in the file and read it to 
 * check if it is a palindrome.
 * It also has a button that lets the user quit at any point.
 * This program uses Stacks and Queues to store and compare the characters 
 * in each line of the file.
 * 
 */



import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PalCheckerGUI extends JFrame { // class declaration line
  
//JComponents declaration (all null at this point)
  JPanel instructPanel, inputPanel, resultPanel;
  JButton quit, nextLine;
  JLabel promptLabel;
  JTextArea instructArea, resultArea;
  JTextField fileField;
  Container window;//which will hold all the JPanels, 
//which will hold all the JComponents
  
  //main method
  public static void main(String[] args) {
    PalCheckerGUI palG = new PalCheckerGUI();
  }
  
  //method to get string of intsructions
  public String getInstruct() {
    return "Welcome to Palindrome Checker! \nEnter a filename and press enter."+
      "Press the \"Get Next Line\" button to make the program read the next"+
      " line in the input file and report if that line is a palindrome!";
  }
  
  
//constructor 
  public PalCheckerGUI(){
    
    window = this.getContentPane();
    window.setLayout(new BorderLayout());
    
    //instantiating instructPanel
    instructPanel = new JPanel();
    //setting background coloe
    instructPanel.setBackground(Color.orange);
    
    //setting border properties
    instructPanel.setBorder(BorderFactory.createLineBorder(Color.white,4));
    
    //nstantiating inputPanel and its properties
    inputPanel = new JPanel();
    inputPanel.setSize(600,80);
    inputPanel.setBackground(Color.gray);
    inputPanel.setLayout(new GridLayout(1,0));
    
    //instantiating promptLabel to prompt for filename
    promptLabel = new JLabel("Enter filename here:");

    
    
    inputPanel.add(promptLabel); // add promptLabel to inputPanel
    
    //instantiating instructArea with instruction text and its properties
    instructArea = new JTextArea(getInstruct());          
    instructArea.setEditable(false);
    instructArea.setSize(600, 200);
    instructArea.setBackground(Color.orange);
    instructArea.setLineWrap(true);
    instructArea.setWrapStyleWord(true);
    
    
    instructPanel.add(instructArea);  // add instructText to introPanel 
    
    
    //instantaiting resultArea with its properties
    resultArea = new JTextArea("");         
    resultArea.setEditable(false);  
    resultArea.setSize(600, 400);   
    resultArea.setBackground(Color.WHITE);
    resultArea.setLineWrap(true);
    resultArea.setWrapStyleWord(true);
    
    //instantaiting fielField to be an empty string with its properties
    fileField = new JTextField("");
    fileField.setBounds(100,150,100,50);
    fileField.setColumns(50);
    fileField.setBorder(BorderFactory.createLineBorder(Color.gray,2));
    fileField.addActionListener(new ActionListener() {
      //anonymous inner actionListener
      public void actionPerformed(ActionEvent ev) {
        reader(); //instantiating reader
      }});
    
    //instantaiting nextLine with its properties
    nextLine = new JButton("Get Next Line");
    nextLine.addActionListener(new ActionListener () {
      //anonymous inner actionLister
      public void actionPerformed(ActionEvent ev) {
        compare();//instantiating compare
      }
    });
    
    //instantaiting quit with its properties
    quit = new JButton("Quit");
    quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        System.exit(0);
      }});
    
    //instantaiting resultPanel with its properties    
    resultPanel = new JPanel();
    resultPanel.setBounds(10, 10, 600, 100);
    
    resultPanel.add(resultArea); //adding resultArea to the panel
    
    
    inputPanel.add(fileField);//adding fielField to inputPanel
    inputPanel.setBounds(10, 5, 600, 50);
    inputPanel.add(nextLine);//adding nextLine to inputPanel
    inputPanel.add(quit);//adding quit to inputPanel
    
    
    // add instructPanel to top of window
    window.add(instructPanel,BorderLayout.NORTH); 
    
    // add inputPanel to center of window
    window.add(inputPanel, BorderLayout.CENTER);     
    
    // add resultPanel to bottom of window
    window.add(resultPanel, BorderLayout.SOUTH);
    
    //setting properties of window
    this.setLocation(50, 50);
    this.pack();
    this.setSize(650, 200);
    this.setVisible(true);
  }//end of constructor
  
  //new buffered reader
  BufferedReader fileIn;
  
  boolean fileEntered = false;
    
  //reader method to read input file
    public void reader(){
      //if filename is entered
        if (!fileEntered) {
            try {
                //BufferedReader to read the file input
                fileIn = new BufferedReader 
                    (new FileReader (fileField.getText()));
                //change value of fileEntered to true
                fileEntered = true;
            } catch (Exception e) {}}
        else 
          //if filename is entered and user has pressed enter
            resultArea.setText("Press \"Get Next Line\" to get started");
    }
   
  
    //compare method to compare the original and reversed strings
    public void compare(){
    
    //Strings to hold each line of the file, and to hold its uppercase version
    String ucString = "";
    String line = "";
    
     
    try {
      //if the reader hasn't read to the end of the file 
      if ((line = fileIn.readLine()) != null) {
        
      //Stack and Queue to hold the characters of each line
      CharStack palStack;
      CharQueue palQueue;
      
      // Characters to hold each charcter as it gets popped/dequeued
      Character stackChar, queueChar;
      stackChar = new Character(' ');
      queueChar = new Character(' ');
      
      //boolean to store value of palindrome
      boolean pal = true;
      
      //StringTokenizer to get the blocks of string without punctuations
      StringTokenizer str;
      
      //instantiating palStack and palQueue
      palStack = new CharStack();
      palQueue = new CharQueue();
      
      //getting blocks of string, omitting the punctuations and whitespace
      str = new StringTokenizer
        (line.toUpperCase()," ,:.;-!?\"]['");
      
      //nested while loop to run while the string block has more tokens
      while (str.hasMoreTokens()){
        int i = 0;
        
        //adding the tokens to ucString
        ucString = str.nextToken();
        
        //while the counter i is less than length of ucString
        while(i < ucString.length()) {
          //push a character at index i on palStack
          palStack.push(new Character (ucString.charAt(i)));
          //enqueue a character at index i on palQueue
          palQueue.enqueue(new Character (ucString.charAt(i)));
          //adding 1 to i everytime the loop runs
          i++;
        }//ending loop for pushing characters
      }//ending loop for checking for more tokens  
      
      //while palStack is not empty
      while (!palStack.empty()){
        //if the characters in the queue and stack are not equal
        if (!((Character)palStack.pop()).equals 
              ((Character)palQueue.dequeue())) {
          //set pal to be false
          pal = false;
          //else break out of loop
          break;
        }
      }//end loop to compare characters
      
      //if the palStack has been completely popped and is empty
      if (palStack.empty())
        //set palStack to be true
        pal = true;
      
      //if pal is true
      if (pal){
        //set the resultArea text to say the line is a palindrome
        resultArea.setText("Yay! \"" + line + "\" is a palindrome!");
      }
      else{
        //else set the resultArea text to say the line is not a palindrome
        resultArea.setText("Oh no! \"" + line + "\" is not a palindrome!");
      }
      }}
    catch (Exception e) {}
  }//end of compare method
}//end of class

