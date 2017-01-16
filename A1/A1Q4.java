
import java.util.Scanner;
import java.util.Random;


/**
 * The class <b>A1Q4</b> is an implementation of the
 * ``Old Maid'' card game, based on the Python implementation
 * given in ITI1020
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
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
	private static String[] playerDeck;

	/**
	* The current number of cards in the player's deck of cards
	*/
	private static int sizePlayerDeck;

	/**
	* Array used to store the computer's deck of cards
	*/
	private static String[] computerDeck;

	/**
	* The current number of cards in the computer's deck of cards
	*/
	private static int sizeComputerDeck;


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



		String[] suits = {"\u2660", "\u2661", "\u2662", "\u2663"};
		String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		sizeDeck = suits.length*ranks.length - 1;
		deck = new String[sizeDeck];
		int index = 0;
		for(int i =0 ; i < ranks.length; i++){
			for(int j =0 ; j < suits.length; j++){
				if(!(ranks[i]=="Q" && suits[j]=="\u2663")){
					deck[index++]= ranks[i] + " of " + suits[j];
				}
			}
		}
	}

	/** 
     * Waits for user input
     */
	private static void waitForUserInput(){
		sc.nextLine();
	}

	/**
	*  Deals the cards, taking sizeDeck cards out of deck, and deals them
	*  into playerDeck and computerDeck, starting with playerDeck
	*/
	private static void dealCards(){
		for (int i=0; i<deck.length; i++) {
			if (i%2==0) {
				System.out.println("" + sizePlayerDeck + "" + playerDeck.length + "");
				appendItem(playerDeck, sizePlayerDeck, deck[i]);
			}
			else {
				System.out.println("" + sizeComputerDeck + "" + computerDeck.length + "");
				appendItem(computerDeck, sizeComputerDeck, deck[i]);
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
	private static int removePairs(String[] deckOfCards, int currentSize){
	
// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		for(int i=0; i<currentSize; i++)
		{
			String firstCard=deckOfCards[i];
			int firstNumber;
			if(firstCard.length()==7)
			{
				firstNumber=Integer.parseInt(firstCard.substring(0,2));
			}
			else
			{
				firstNumber=Integer.parseInt(firstCard.substring(0,1));
			}
			for(int j=i; j<currentSize; j++)
			{
				String secondCard=deckOfCards[j];
				int secondNumber;
				if(secondCard.length()==7)
				{
					secondNumber=Integer.parseInt(secondCard.substring(0,2));
				}
				else
				{
					secondNumber=Integer.parseInt(secondCard.substring(0,1));
				}
				if(firstNumber==secondNumber)
				{
					removeItemByIndex(deckOfCards, currentSize, j);
					removeItemByIndex(deckOfCards, currentSize, i);
				}
			}
		}
		return currentSize;

	}

	/**
	*  Get a valid index of a card to be removed from computerDeck.
	*	Note: this method does not check that the input is indeed an integer and
	*	will crash if something else is provided.
	*  @return the valid input.
	*/
	private static int getValidInput(){
		System.out.println("Please a number between 1 and " + sizeComputerDeck);
		int userin = sc.nextInt();
		boolean flag = true;
		while (true) {
			if ((userin>=1) && (userin<=sizeComputerDeck)) {
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
				return (userin);
			}
			else {
				System.out.println("Invalid answer. Give me an integer between 1 and " + sizeComputerDeck + ": ");
				flag = true;
			}
		}
	
	}


	/**
	*  The actual game, as per the Python implementation
	*/
	public static void playGame(){
		shuffleArray(deck, sizeDeck);
		dealCards();
		boolean flag = true;
		System.out.println("Hello. My name is Robot and I am the dealer.");
		System.out.println("Welcome to my card game!");
		System.out.println("Your current deck of cards is: ");
		printArray(playerDeck, sizePlayerDeck);
		System.out.println("Do not worry. I cannot see the order of your cards.");
		System.out.println("Now discard all the pairs from your deck. I will do the same.");

		waitForUserInput();

		removePairs(playerDeck, sizePlayerDeck);
		removePairs(computerDeck, sizeComputerDeck);

		while (true) {
			if (sizePlayerDeck == 0) {
				System.out.println("Congratulations! You beat me at Old Maid.");
				flag = false; 
			}
			else if (sizeComputerDeck == 0) {
				System.out.println("That is unfortunate for you! I beat you at Old Maid.");
				flag = false;
			}

			System.out.println("*********************");
			System.out.println("Your turn");
			System.out.println("Your new hand is: ");

			printArray(playerDeck, sizePlayerDeck);

			System.out.println("I have " + sizeComputerDeck + " cards. If 1 represents my first card and " + sizeComputerDeck + " represents my last card,");
			System.out.println("which of my cards do you choose?");

			int userin=getValidInput();

			System.out.println("The card you chose is " + computerDeck[(userin-1)]);
			System.out.println("With " + computerDeck[userin-1] + " added to your hand, your current hand is: ");

			appendItem(playerDeck, sizePlayerDeck, computerDeck[userin-1]);
			removeItemByIndex(computerDeck, sizeComputerDeck, userin);
			printArray(playerDeck, sizePlayerDeck);

			System.out.println("After discarding pairs and shuffling, your hand is: ");
			removePairs(playerDeck, sizePlayerDeck);
			printArray(playerDeck, sizePlayerDeck);

			waitForUserInput();

			System.out.println("*********************");
			System.out.println("My turn");

			Random rand = new Random();
			int computerChoice = rand.nextInt((sizePlayerDeck-1) + 1)+1;

			if (computerChoice == 1) {
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
			System.out.println("" + sizeComputerDeck + "" + computerDeck.length + "");
			appendItem(computerDeck, sizeComputerDeck, playerDeck[computerChoice-1]);
			removeItemByIndex(playerDeck, sizePlayerDeck, computerChoice);
			removePairs(computerDeck, sizeComputerDeck);
			waitForUserInput();
		}


	}


	/**
     * The main method of this program. Creates the game object
     * and calls the playGame method on it.
     * @param args ignored
	 */    

 
	public static void main(String[] args){
	
		A1Q4 game = new A1Q4();		

		game.playGame();
	
		
	}
}