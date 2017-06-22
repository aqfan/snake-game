=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: angelf
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D arrays:
  
  Feedback: What types are you storing in the 2D array? 
  Think about how you are representing what is going in each element.
  
  A game of snakes consists of a rectangular grid (the "board"). I will use a 2D array to represent
  the board and store what object is at each position. Each position may have either a snake head, 
  a snake body, a food, or a null object. For example, as the snake moves around, the board will be 
  updated so that each position on the board corresponds to the new location of the snake's body 
  parts. The positions where the snake moves off of will be replaced with a null object. If a food 
  is eaten, the position of the food will be replaced by the snake head and the snake will gain 
  another joint. 

  2. I/O 
  
  Feedback: no feedback
  
  My Snake implementation will use I/O to store highscores. A player will be asked if they would 
  like to save their score and if they say yes, they will be asked to input a username. 
  The username and score will be saved into a text file. At the end of each game, the program will 
  read and parse the data from that text file and show the high scores to the user. There will be 
  multiple levels of difficulty so each submission will automatically include a difficulty tag so 
  that only the high scores from the proper difficulty would be shown. 

  3. Testable Component
  
  Feedback: no feedback
  
  The main state of my game will be the board (2D array) and the snake. The snake will be modeled by
  an ArrayList of snake objects. Each snake object may be a snake head or a snake body. Each snake 
  object will also contain it's current x and y coordinates and direction. I will test that moving 
  the snake updates the coordinates and updates the location of the snake on the board. I'll also
  test that eating foods will cause the snake to gain an extra body part and update the 2D array 
  to show the new part. I will also test that the snake's head touching the borders of the 
  board or the snake's head touching the snake's body will cause the game to be over.


  4. Collections
  
  Note: My original proposal used Inheritance/Dynamic Dispatch but I realized that what I wanted to 
  do was already implemented by the given GameObj class. So, I used collections instead. 
  
  Feedback for Inheritance: don't see the relation between them.. might as well just make separate classes?
  
  I will use an ArrayList to represent the snake. The first element of the ArrayList will contain 
  the snake head while the rest of the ArrayList will contain the snake body parts. Each part of 
  the snake is an Object with a position, direction, etc. I need to use an ArrayList because first, 
  it is resizable. If I used an array, then I would have to make a new array and transfer memory 
  each time the snake grows. Second, an ArrayList will allow me easy access to ordered elements. 
  The way the snake's body parts move depend on the direction of the part before it. A Set or Map 
  would not allow me to access the elements by index unless I make a new comparator, which would 
  be unnecessary if I used an ArrayList. I did not use a LinkedList because an ArrayList is faster 
  for the most part and I do not need the Deque properties of a LinkedList.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game - the main class. It contains different JPanels that represent the screens. Also contains
  some variables that are accessible by all classes.
  
  GameLogic - the logic behind the game. It is ran when the user presses the start button. This
  class creates a game board and basically runs the whole game.
  
  Board - the board of the game. It is a 2D array that creates a grid. Each position of the grid
  stores either a snake or a foodObj. This class is what the user sees and it gets updated every
  time the user makes a move.
  
  InfoPanel - the bottom panel that the user see when user starts the game. Contains information
  about the current score and level of difficulty of the game.
  
  GameObj - the abstract object class that keeps data such as position, the max x and y 
  coordinates, direction, and type. Contains skeleton nextLocation(), move(), and draw() methods 
  as well as a method to determine whether an object will hit the wall.
  
  FoodObj - the food object class that extends from the GameObj class. Nothing special about this
  it just draws a yellow circle at a specific location.
  
  Snake - the snake object class that extends from the GameObj class. It overrides the GameObj's 
  nextLocation(), move(), and draw() classes. It draws the snake head and snake body differently, 
  depending on what type was used to create the snake object.
  
  Start Menu - the main menu. It is the first thing that the user sees when the user runs the 
  Game.java file. It has options to view the HelpMenu, to change the settings, to exit, or to
  to start the game.
  
  HelpMenu - the help menu. It shows the game controls and has a back button to go back to the
  start menu.
  
  SettingsMenu - the settings menu. It allows the user to select the level of difficulty of the 
  game. Once the difficulty is selected, the user is taken back to the Start Menu.
  
  EndMenu - the menu that the user may choose to enter after the game ends. Once the game ends,
  the user can choose to save their score and will then be taken to the end menu where the user
  may input a user name and then save their score. The user may also view the top 5 high scores
  for the current level of difficulty or to exit the game. 
  
  HighScoreUpdater - the class that reads the highscores from the highscores.txt file and then
  creates TreeMaps mapping the username to the score. Also takes in a username, score, and
  difficulty and write that onto the highscores file.
  
  Direction - the enum class that contains the directions used in the Game.
  
  Type - the enum class that contains the types of objects used in the Game.
  
  GameTest - the class that uses JUnit tests to test the GameLogic class.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  I mostly had trouble figuring out the logic of my game and how to effectively move the snake. 
  I also wanted my game to physically look good so I had to do a lot of googling and learn how to 
  show different screens. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think there is pretty good separation of functionality. The main logic is contained in GameLogic
   so a developer would be able to easily edit the logic. I also have a different class for every 
   screen, which separates their functionality. Even the way I get the highscores is in a separate class.
  
  Private state is encapsulated well because the variables that are only accessed by one class is 
  private. Every class except for the main game class has private variables (some with getters/settters)
  while the main game class has a few public static variables so that all classes can access them.
  
  I would refactor my screen classes because I can potentially put them all into one class, 
  but each with a separate label. 



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  Buttons were made with: https://dabuttonfactory.com/
  
  I learned how to add background images from: http://www.sourcecodester.com/tutorials/java/7488/java-catch-eggs-game-programming-part1.html
  
  I also read the JavaDocs for most of the libraries that I used.
