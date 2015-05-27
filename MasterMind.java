import java.util.Random;
import java.util.Scanner;

public class MasterMind{
	final static double MIN_BET = 2.00;
	public static String difficulty, secretNumberString, guessedString;
	public static double moneyAvailable;
	public static boolean gameLoop = true, hasNotGuessed = true;
	public static int numberOfGuessesPossible, guessed, numberOfDigits, incorrectGuessses, secretNumber, sum;
	static Random random = new Random();
	public static Scanner input = new Scanner(System.in);
	
	public static void messageAndExit(String message){
		System.out.println(message);
		System.exit(0);
	}
	
	public static boolean betChecker(double moneyToBet){
		if(moneyToBet > moneyAvailable){
			System.out.println("You can not bet more money than you have, try again.");
			return true;
		}
		else if(moneyToBet < 2.00){
			System.out.println("You must bet at least $2.");
			return true;
		}
		return false;
	}
	
	public static void initialize(){
		int counter = 0;
		String userAnswer;
		do{
			System.out.println("Would you like to play Mastermind (yes/no)?");
			userAnswer = input.next();
		} while(!(userAnswer.equalsIgnoreCase("yes") || userAnswer.equalsIgnoreCase("no")));
		
		if(userAnswer.equalsIgnoreCase("no")){
			messageAndExit("Okay have a good day!");
		}
		else
			System.out.println("Enter the amount of money you have to play");
		moneyAvailable = input.nextDouble();
		if(moneyAvailable < 2)
			messageAndExit("You need at least $2 to play, goodbye!");
		do{
			if(counter > 0)
				System.out.println("Not a correct difficulty.");
			System.out.println("Enter a difficulty beginner, intermediate, or advanced...");
			difficulty = input.next();
			counter++;
		} while(!(difficulty.equalsIgnoreCase("beginner")||difficulty.equalsIgnoreCase("intermediate")|| difficulty.equalsIgnoreCase("advanced")));
		
		if(difficulty.equalsIgnoreCase("beginner")){
			numberOfGuessesPossible = 30;
			numberOfDigits = 3;
			secretNumber = random.nextInt(900) +100;
		}
		
		else if(difficulty.equalsIgnoreCase("intermediate")){
			numberOfGuessesPossible = 20;
			numberOfDigits = 4;
			secretNumber = random.nextInt(9000) +1000;
		}
		
		else if(difficulty.equalsIgnoreCase("advanced")){
			numberOfGuessesPossible = 10;
			numberOfDigits = 5;
			secretNumber = random.nextInt(90000) +10000;
		}
		
		do{
			System.out.println("Enter the amount of money you want to bet");
		} while(betChecker(input.nextDouble()));
	}
	
	
	public static int[] numberToArray(int numberToChange, int numberOfDigits){
		int [] array = new int [numberOfDigits];
		for(int a =0; a < Integer.toString(numberToChange).length(); a++){
			array[a] =  Integer.parseInt(Integer.toString(numberToChange).substring(a, a+1));
		}
		return array;
	}
	
	public static void guess(int [] guess, int numDigits, int[] secretNumber){
		for(int a = 0; a < numDigits; a++ ){
			if(guess[a] == secretNumber[a]){
				sum += secretNumber[a];
			}
		}
	}
	
	public static boolean digitsChecker(int numberGuessed, int numberOfDigits){
		if(Integer.toString(numberGuessed).length() == numberOfDigits){
			return false;
		}
		
		else
			return true;
	}
	
	public static void game(){
		//initialize should be called here??? 
		while(gameLoop){
			System.out.println("Guess the " +numberOfDigits + " digits number." );
			guessed = input.nextInt();
			while(digitsChecker(guessed, numberOfDigits)){
				System.out.println("You must enter a " + numberOfDigits + " digit number!");
				guessed = input.nextInt();
			}
			//TODO check the guesses compare to number, get the sums
			while(hasNotGuessed){
				if(guessed != secretNumber){
					guess(numberToArray(guessed, numberOfDigits), numberOfDigits, numberToArray(secretNumber, numberOfDigits));
					System.out.println("The sum of the correct digits guessed is " + sum +"! \nTry Again!");
					guessed = input.nextInt();
					sum =0;
					
				}
				
				else
					hasNotGuessed = false;
			}
			
			System.out.println("Congrats you have won! \nDo you want to play again?");
		}
	}
	
	public static void main(String[] args) {
		initialize();
		System.out.println("THE SECRET NUMBER IS " + secretNumber);
		game();
	}
}
	
