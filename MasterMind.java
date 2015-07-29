import java.util.Random;
import java.util.Scanner;

public class MasterMind{
	final static double MIN_BET = 2.00;
	public static String difficulty, secretNumberString, guessedString;
	public static double moneyAvailable, bet, earned;
	public static boolean gameLoop = true, hasNotGuessed = true, firstPlay =true;
	public static int numberOfGuessesPossible, guessed, numberOfDigits, incorrectGuessses=0, numberCorrect=0, secretNumber, sum;
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
	}
	
	public static void difficulty(){
		int counter = 0;
		hasNotGuessed = true;
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
			bet = input.nextDouble();
		} while(betChecker(bet));
	}
	
	public static void playAgain(){
		firstPlay = false;
		incorrectGuessses = 0;
		System.out.println("Would you like to play again?(Yes/No)");
		String answer = input.next();
		if(answer.equalsIgnoreCase("YES") && moneyAvailable > 2)
			return;
		else if(answer.equalsIgnoreCase("yes") && moneyAvailable < 2)
			messageAndExit("You do not have enough money to play!\nBye Felisha!");
		else if(answer.equalsIgnoreCase("No"))
			messageAndExit("Ok, enjoy the rest of your day!");
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
				numberCorrect ++;
				sum += secretNumber[a];
			}
		}
		System.out.println("You got " + numberCorrect + " digits correct." 
				+ "The sum of the correct digits guessed is " + sum +"! \nTry Again!");
		sum =0;
		numberCorrect = 0;
	}
	
	public static void digitsChecker(int numberOfDigits){
		while(!(Integer.toString(guessed).length() == numberOfDigits)){
			System.out.println("You must enter a " + numberOfDigits + " digit number!");
			guessed = input.nextInt();
		}
	}
	
	public static void game(){
		while(gameLoop){
			if(firstPlay)
				initialize();
			difficulty();
			System.out.println(secretNumber);
			System.out.println("Guess the " +numberOfDigits + " digits number." );
			guessed = input.nextInt();
			digitsChecker(numberOfDigits);
			while(hasNotGuessed && incorrectGuessses != numberOfGuessesPossible){
				if(guessed != secretNumber){
					guess(numberToArray(guessed, numberOfDigits), numberOfDigits, numberToArray(secretNumber, numberOfDigits));
					guessed = input.nextInt();
					digitsChecker(numberOfDigits);
					incorrectGuessses++;
				}
				
				else
					hasNotGuessed = false;
			}
			
			if(guessed == secretNumber){
				earned = moneyAvailable + ((bet * numberOfDigits) * (numberOfGuessesPossible 
						- incorrectGuessses)/ numberOfGuessesPossible);
				moneyAvailable = moneyAvailable + earned;
				System.out.println("You won :)!!!\nYou had "+ incorrectGuessses +" incorrect tries!"
						+ "You earned $" + earned + "\nYou now have $" + moneyAvailable + " available!");
			}
			if(incorrectGuessses == numberOfGuessesPossible){
				System.out.println("You have lost, you have exceeded the max tries allowed.");
				moneyAvailable = moneyAvailable - bet;
				System.out.println("The number was " + secretNumber + "\nYou now have $" + moneyAvailable 
						+ " at your dispense.");
			}
			playAgain();
		}
	}
	
	public static void main(String[] args) {
		game();
	}
}
	
