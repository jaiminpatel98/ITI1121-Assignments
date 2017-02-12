
import java.util.Scanner;
import java.util.Random;


/**
 * The class <b>A1Q4</b> is an implementation of the
 * ``Old Maid'' card game, based on the Python implementation
 * given in ITI1020
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 * 
 *Authors for this iteration: Sam Worrod (8653389) and Jaimin Patel (8721083)
 *Course: ITI 1121 A
 *Assignment 1
 *Question: 4
 */

public class A1Q4{

	/** 
     * Prints the strings contained in arrayOfStrings
     * 
     *   @param arrayOfStrings the array of Strings
     *   @param currentSize the number of strings in the arrayOfStrings,
     *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
     */

	public static void printArray(String[] arrayOfStrings, int currentSize){
		if( arrayOfStrings == null || currentSize > arrayOfStrings.length) {
			System.out.println("ArrayStringsTools.printArray: wrong call");
			return ;
		}
		for(int i = 0; i < currentSize-1; i++){
			System.out.print(arrayOfStrings[i] + ", ");
		}
		System.out.println(arrayOfStrings[currentSize-1]);
	}

	/** 
     * Does a lexicographic sorting of arrayOfStrings. 
     * It simply relies on the sort(Object[]) method
	 * of java.util.Arrays. 
	 * Note that it sorts in lexicographic order, so 
	 * "10" is before "2", "A" is before "Q" etc. 
	 * This is not important for this game, but wouldn't 
	 * be suitable in some other cases
     * 
     *   @param arrayOfStrings the array of Strings
     *   @param currentSize the number of strings in the arrayOfStrings,
     *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
     */
	public static void sortArray(String[] arrayOfStrings, int currentSize){
		if( arrayOfStrings == null || currentSize > arrayOfStrings.length) {
			System.out.println("ArrayStringsTools.sortArray: wrong call");
			return ;
		}
		java.util.Arrays.sort(arrayOfStrings, 0, currentSize );
	}



	/** 
     * Randomly shuffles arrayOfStrings. 
     * 
     *   @param arrayOfStrings the array of Strings
     *   @param currentSize the number of strings in the arrayOfStrings,
     *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
     */
	public static void shuffleArray(String[] arrayOfStrings, int currentSize){
		if( arrayOfStrings == null || currentSize > arrayOfStrings.length) {
			System.out.println("ArrayStringsTools.shuffleArray: wrong call");
			return ;
		}
		
		java.util.Random generator = new java.util.Random();

		for(int i = currentSize-1 ; i > 1 ; i--){
			swapItems(arrayOfStrings, i,generator.nextInt(i-1));
		}
	}

	private static void swapItems(String[] arrayOfStrings, int i, int j){
		String intermediate = arrayOfStrings[i];
		arrayOfStrings[i]=arrayOfStrings[j];
		arrayOfStrings[j]=intermediate;
	}


	/** 
     * Removes the string at  arrayOfStrings[itemToRemove] and
     * 
     *   @param arrayOfStrings the array of Strings
     *   @param currentSize the number of strings in the arrayOfStrings,
     *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
     *   @param itemToRemove index of the item to remove from arrayOfStrings
     *   @return the new size of the modified arrayOfStrings
     */

	public static int removeItemByIndex(String[] arrayOfStrings, int currentSize, int itemToRemove){

		if( arrayOfStrings == null || currentSize > arrayOfStrings.length) {
			System.out.println("ArrayStringsTools.removeItemByIndex: wrong call");
			return currentSize;
		}
		if( itemToRemove < 0 || itemToRemove >= currentSize ) {
			System.out.println("ArrayStringsTools.removeItem: item " 
				+ itemToRemove + " out of bounds. Array Unchanged.");
			return currentSize;
		}

		int i;
		for( i = itemToRemove; i < currentSize-1; i++){
			arrayOfStrings[i] = arrayOfStrings[i+1];
		}
		arrayOfStrings[i]= null;
		return currentSize-1;
	}

	/** 
     * Appends itemToAdd at the end of arrayOfStrings 
     * 
     * 
     *   @param arrayOfStrings the array of Strings
     *   @param currentSize the number of strings in the arrayOfStrings,
     *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
     *   @param itemToAdd the String to add to  arrayOfStrings
     *   @return the new size of the modified arrayOfStrings
     */

 	public static int appendItem(String[] arrayOfStrings, int currentSize, String itemToAdd){

		if( arrayOfStrings == null || currentSize > arrayOfStrings.length) {
			System.out.println("ArrayStringsTools.appendItem: wrong call");
			return currentSize;
		}

		if( currentSize == arrayOfStrings.length) {
			System.out.println("ArrayStringsTools.appendItem: array full. Array Unchanged.");
			return currentSize;
		}
		arrayOfStrings[currentSize++]=itemToAdd;
		return currentSize;
	} 	

	/**
	* Array used to store the full deck of cards,
	*/
	private static String[] deck;

	/**
	* The current number of cards in the full deck of cards
	*/
	private static int sizeDeck;

	/**
	* Array used to store the player's deck of cards
	*/
	private static String[] playerDeck = new String [26];

	/**
	* The current number of cards in the player's deck of cards
	*/
	private static int sizePlayerDeck = 0;

	/**
	* Array used to store the computer's deck of cards
	*/
	private static String[] computerDeck = new String [25];

	/**
	* The current number of cards in the computer's deck of cards
	*/
	private static int sizeComputerDeck = 0;


	/**
	* An instance of java.util.Scanner, which will get input from the
	* standard input
	*/
 	private static Scanner sc;

	/**
	* An instance of java.util.Random, to generate random numbers
	*/
 	private static Random generator;

	/** 
     * Constructor of the class. Creates the full deck of cards
     */
 
 	public  A1Q4(){
		
		sc = new Scanner(System.in);
		generator = new Random();



		String[] suits = {"♥", "♠", "♦", "♣"};//creation of suits array
		String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"}; //creation of ranks array
		sizeDeck = suits.length*ranks.length - 1;//deck is equal to the number of combinations minus the 1 card taken out
		deck = new String[sizeDeck];//creates array with size 51
		int index = 0;//starts at 0 and goes through every position
		for(int i =0 ; i < ranks.length; i++){//nested for loop to go through every combination
			for(int j =0 ; j < suits.length; j++){//see above
				if(!(ranks[i]=="Q" && suits[j]=="♥")){//takes out this card
					deck[index++]= ranks[i] + " of " + suits[j];//adds card to deck
				}
			}
		}
	}

	/** 
     * Waits for user input
     */
	private static void waitForUserInput(){
		sc.nextLine();//waits for user
	}

	/**
	*  Deals the cards, taking sizeDeck cards out of deck, and deals them
	*  into playerDeck and computerDeck, starting with playerDeck
	*/
	private static void dealCards(){
		for (int i=0; i<deck.length; i++) {//goes through every card in the deck
			if (i%2==0) {//alternates cards
				sizePlayerDeck=appendItem(playerDeck, sizePlayerDeck, deck[i]);//adds card to players hand
			}
			else {
				sizeComputerDeck=appendItem(computerDeck, sizeComputerDeck, deck[i]);//adds cards to computers hand
			}
		}

	}

	/**
	*  Removes all the pairs of cards from the array deckOfCards, that currently
	* contains currentSize cards. deckOfCards is unsorted. It should also not
	* be sorted once the method terminates. 
	*
    *   @param deckOfCards the array of Strings representing the deck of cards
    *   @param currentSize the number of strings in the arrayOfStrings,
    *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
    *   @return the number of cards in deckOfCards once the pair are removed
    */
	private static int removePairs(String[] deckOfCards, int currentSize){ //takes out pairs, takes the cards and the number of cards
		sortArray(deckOfCards, currentSize); //puts cards in order
		int i = 0;//current spot in array
		int rank1 = 0; //values of the cards to be checked
		int rank2 = 0;//values of the cards to be checked
		while (i<currentSize-1) {//goes until every card has been checked
			if (deckOfCards[i].substring(0,1).equals("A") == false && deckOfCards[i].substring(0,1).equals("K") == false && deckOfCards[i].substring(0,1).equals("Q") == false && deckOfCards[i].substring(0,1).equals("J") == false && deckOfCards[i+1].substring(0,1).equals("A") == false && deckOfCards[i+1].substring(0,1).equals("K") == false && deckOfCards[i+1].substring(0,1).equals("Q") == false && deckOfCards[i+1].substring(0,1).equals("J") == false) { //if the card is not a Letter
				if (deckOfCards[i].substring(0,2).equals("10") == false || deckOfCards[i+1].substring(0,2).equals("10") == false) {//if the card is not the 10 of something
					rank1 = Integer.parseInt(deckOfCards[i].substring(0,1));//first card
					rank2 = Integer.parseInt(deckOfCards[i+1].substring(0,1));//second card
					if (rank1 == rank2) {//if cards are equal
						currentSize=removeItemByIndex(deckOfCards, currentSize, i); //remove cards
						currentSize=removeItemByIndex(deckOfCards,currentSize, i);
					}
					else
					{
						i++;//moves on to the next card
					}
				}
				else {
					rank1 = Integer.parseInt(deckOfCards[i].substring(0,2)); //first card
					rank2 = Integer.parseInt(deckOfCards[i+1].substring(0,2));//second card
					if (rank1 == rank2) {//if cards have the same value
						currentSize=removeItemByIndex(deckOfCards, currentSize, i);//removes cards
						currentSize=removeItemByIndex(deckOfCards,currentSize, i);
					}
					else
					{
						i++;//moves on to the next card
					}
				}
			}
			else if (deckOfCards[i].substring(0,1).equals(deckOfCards[i+1].substring(0,1))) //if cards have the same value
			{
					currentSize=removeItemByIndex(deckOfCards, currentSize, i);//removes cards
					currentSize=removeItemByIndex(deckOfCards,currentSize, i);

			}
			else
			{
				i++;///moves on to the next card
			}
			
		}
		shuffleArray(deckOfCards, currentSize);//reshuffles hand

		return (currentSize);//returns number of cards left

	}

	/**
	*  Get a valid index of a card to be removed from computerDeck.
	*	Note: this method does not check that the input is indeed an integer and
	*	will crash if something else is provided.
	*  @return the valid input.
	*/
	private static int getValidInput(){
		System.out.println("Please a number between 1 and " + sizeComputerDeck);
		int userin = sc.nextInt(); //gets input, stores as integer
		boolean flag = true; //runs until correct number given
		while (true) {//keeps user in loop
			if ((userin>=1) && (userin<=sizeComputerDeck)) { // if it is a valid number
				if (userin == 1) {
					System.out.println("You chose my "+ userin + "st card");
				}
				else if (userin == 2) {
					System.out.println("You chose my " + userin + "nd card");
				}
				else if (userin == 3) {
					System.out.println("You chose my " + userin + "rd card");
				}
				else {
					System.out.println("You chose my " + userin + "th card");
				}
				return (userin); //returns the number the player picked
			}
			else {//if it is not valid
				System.out.println("Invalid answer. Give me an integer between 1 and " + sizeComputerDeck + ": ");
				userin = sc.nextInt();//has them pick a new number
				flag = true;
			}
		}
	
	}


	/**
	*  The actual game, as per the Python implementation
	*/
	public static void playGame(){ //play game method
		shuffleArray(deck, sizeDeck); //randomizes cards
		dealCards(); //distributes cards
		boolean flag = true;//keeps the game running for multiple rounds
		System.out.println("Hello. My name is Robot and I am the dealer.");
		System.out.println("Welcome to my card game!");
		System.out.println("Your current deck of cards is: ");
		printArray(playerDeck, sizePlayerDeck); //displays player deck
		System.out.println("Do not worry. I cannot see the order of your cards.");
		System.out.println("Now discard all the pairs from your deck. I will do the same.");

		waitForUserInput();//waits for user

		sizePlayerDeck=removePairs(playerDeck, sizePlayerDeck);//takes out pairs from player deck
		sizeComputerDeck=removePairs(computerDeck, sizeComputerDeck); //takes out pairs from computer deck

		while (true) { //game runs until someone wins
			if (sizePlayerDeck == 0) { //checks to see if anyone won
				System.out.println("Congratulations! You beat me at Old Maid.");
				flag = false; 
				break;
			}
			else if (sizeComputerDeck == 0) {
				System.out.println("That is unfortunate for you! I beat you at Old Maid.");
				flag = false;
				break;
			}
			else {
				System.out.println("*********************");
				System.out.println("Your turn");
				System.out.println("Your current hand is: ");

				printArray(playerDeck, sizePlayerDeck); //prints player hand

				System.out.println("I have " + sizeComputerDeck + " cards. If 1 represents my first card and " + sizeComputerDeck + " represents my last card,");
				System.out.println("which of my cards do you choose?");

				int userin=getValidInput(); //get input from the uses

				System.out.println("The card you chose is " + computerDeck[userin-1]);

				sizePlayerDeck=appendItem(playerDeck, sizePlayerDeck, computerDeck[userin-1]); //adds player choice to deck

				System.out.println("With " + computerDeck[userin-1] + " added to your hand, your current hand is: ");

				sizeComputerDeck=removeItemByIndex(computerDeck, sizeComputerDeck, userin-1);//takes card from computer deck
				printArray(playerDeck, sizePlayerDeck); //prints hand after gaining card
				sizePlayerDeck=removePairs(playerDeck, sizePlayerDeck); //takes out any pairs in the players deck
				if (sizePlayerDeck == 0) { // checks to see if anyone won
					System.out.println("Congratulations! You beat me at Old Maid.");
					flag = false; 
					break;
				}
				else if (sizeComputerDeck == 0) {
					System.out.println("That is unfortunate for you! I beat you at Old Maid.");
					flag = false;
					break;
				}

				System.out.println("After discarding pairs and shuffling, your hand is: ");
				
				printArray(playerDeck, sizePlayerDeck); //prints player hand
				waitForUserInput(); //waits for user
				System.out.println("*********************");
				System.out.println("My turn");

				Random rand = new Random();
				int computerChoice = rand.nextInt((sizePlayerDeck-1) + 1)+1; //computer picks a card using the random function. Increased by 2 to accurately represent card
				if (computerChoice == 1) {//different display based on card choice
					System.out.println("I chose your 1st card");
				}
				else if (computerChoice == 2) {
					System.out.println("I chose your 2nd card");
				}
				else if (computerChoice == 3) {
					System.out.println("I chose you 3rd card");
				}
				else {
					System.out.println("I chose your " + computerChoice + "th card");
				}

				sizeComputerDeck=appendItem(computerDeck, sizeComputerDeck, playerDeck[computerChoice-1]); //adds card to computer deck
				sizePlayerDeck=removeItemByIndex(playerDeck, sizePlayerDeck, computerChoice-1); //removes card from player deck
				sizeComputerDeck=removePairs(computerDeck, sizeComputerDeck);//checks for pairs
				waitForUserInput();//waits for user
			}
		}


	}


	/**
     * The main method of this program. Creates the game object
     * and calls the playGame method on it.
     * @param args ignored
	 */    

 
	public static void main(String[] args){
		StudentInfo.display();
		A1Q4 game = new A1Q4();		

		game.playGame();
	
		
	}
}