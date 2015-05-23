/*
* Mastermind.java
* Author: [Carlos A. Hernandez]
* Submission Date: [10-17-14] *
* Purpose: The program is a number guessing that is able to loop as many times as the user wants. Along with
* the user's demand of playing the program is able to give the user a sense of winning money or losing it.
* The user must have at least 2$ to play and bet atleast than amount. The program is also able to handle
* various forms of one input.
* 
* Statement of Academic Honesty: 
* 
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. */

import java.util.Scanner;
public class MasterMind {
	public static final double MIN_BET = 2.00;

	public static void main(String[] args) {
	 int secretCode, userNum , guessedRight, tM, m, h, t , o ,sum, difficultyNum, 
	 addIt, numDigits, timesPlayed, maxGuesses, incorrectGuesses;
	 double userMoney, newMoney, moneyBet;
	 String userChoice, userDifficulty, tenMilDigitRand, milDigitRand, hundredsDigitRand,
	 tensDigitRand, onesDigitRand, codeSecret, tenMilDigit, milDigit, hundredsDigit , 
	 tensDigit, onesDigit, numUser, adv, inter, beg;
	 boolean theNots, testingMode, guessedTenMil, guessedMil, guessedHundreds, 
	 guessedTens, guessedOnes, easy, med, hard, digitsEqual, fiveDigits, fourDigits,
	 threeDigits, goBackUp;
	
	 timesPlayed=0;
	 userMoney=0;
	 guessedRight= 0;
	 sum=0;
	 maxGuesses=0;
	 newMoney=0.0;
	 tM=0;
	 m=0;
	 h=0;
	 t= 0;
	 o =0;
	 difficultyNum=0;
	 addIt=0;
	 numDigits=0;
	 incorrectGuesses=0;
	 numUser ="";
	 adv= "advanced";
	 inter= "intermediate";
	 beg = "beginner";
	 testingMode = true;
	 goBackUp = false;

	 //Empty until used
	 tenMilDigitRand =null;
	 tenMilDigit=null;
	 milDigitRand=null;
	 milDigit=null;
	  
	 
	 
	 Scanner user = new Scanner(System.in);
	 System.out.println("Would you like to play Mastermind (yes/no)?");
	 userChoice = user.nextLine();
	 
	 while (userChoice.equalsIgnoreCase("yes") || !(userChoice.equalsIgnoreCase("no")))
	 {
		 if (userChoice.equalsIgnoreCase("yes"))
		 {
			 if(timesPlayed == 0)
			 {
				System.out.println("Enter the amount of money you have to play: ");
				userMoney = user.nextDouble(); 
			 }
			 
			 
			 if (userMoney >= MIN_BET)
			 {
				 incorrectGuesses=0;
				 System.out.print("Please enter the level of difficulty (" + beg +", " + inter);
				 System.out.println(", " + adv + "): " );
				 
				 userDifficulty = user.nextLine();
				 if(timesPlayed ==0)
				 userDifficulty = user.nextLine();
				 easy = userDifficulty.equalsIgnoreCase(beg);
				 med = userDifficulty.equalsIgnoreCase(inter);
				 hard =userDifficulty.equalsIgnoreCase(adv);
				 theNots = !userDifficulty.equalsIgnoreCase(beg) && 
						 !userDifficulty.equalsIgnoreCase(inter)
						 && !userDifficulty.equalsIgnoreCase(adv);
				 
				 while (theNots)
				 {
					System.out.println("Sorry, this is not a correct level.");
					System.out.print("Please enter the level of difficulty (" + beg 
							+", " + inter + ", " + adv + "): " );					
					userDifficulty = user.nextLine();
					easy = userDifficulty.equalsIgnoreCase(beg);
					med = userDifficulty.equalsIgnoreCase(inter);
					hard =userDifficulty.equalsIgnoreCase(adv);
					if (easy||med||hard)
						break;
				 }
				 
				 // The if statements set certain variables depending on difficulty
				 if (easy)
					{
						difficultyNum=1;
						addIt=0;
						numDigits = 3;
						maxGuesses=30;
					}
				 
				 /* On intermediate(med/medium) and advanced(hard)addIt has a value,
				  *  this value determines the shift of the digits later in the program
				  */
				 if (med)
					{
						difficultyNum=10;
						addIt=1;
						numDigits = 4;
						maxGuesses=20;
					}
						
				 if (hard)
					{
						difficultyNum=100;
						addIt=2;
						numDigits=5;
						maxGuesses=10;
					}
					
				 System.out.println("Enter the amount of money you want to bet");
				 moneyBet= user.nextDouble();
				
				 while(userMoney < moneyBet || moneyBet < MIN_BET)
				 {
					 if(userMoney < moneyBet)
						System.out.println("Sorry, you cannot bet more money than what "
								+ "you have.");
						
					
					 else
						System.out.println("Sorry, the minimum amount of money is"
								+ " 2.00 dollars.");
					 System.out.println("Enter the amount of money you want to bet");
					 moneyBet=user.nextDouble();
				}
				 
				 //Secret Number in INTEGER form
				 secretCode = (int)( Math.random() * (90 * (difficultyNum*10)) ) + 
				 (10 * (difficultyNum*10));
				 // Secret Number in String form to Compare
				 codeSecret = Integer.toString(secretCode);
				 
				 while (easy || med || hard)
				 {
					Scanner userNumber = new Scanner(System.in);
						
					if (testingMode)
						System.out.println("(Testing Mode - the " + numDigits +" digits number: "  +codeSecret +")");
					
					//---Do while Loop that won't exit loop until user enters the correct number of digits---
					do {
						digitsEqual = false;
						System.out.println("Please guess the "+ numDigits + " digits number: ");
						userNum = userNumber.nextInt();
						numUser = Integer.toString(userNum);
						fiveDigits = numUser.length() == 5;
						fourDigits = numUser.length() == 4;
						threeDigits = numUser.length() == 3;
					if(hard && !fiveDigits)
						{
						System.out.println("The guess you have entered is ill formed.");
						digitsEqual =true;
						}
					if(med && !fourDigits)
						{
						System.out.println("The guess you have entered is ill formed.");
						digitsEqual = true;
						}
					if(easy && !threeDigits)
						{
						System.out.println("The guess you have entered is ill formed.");
						digitsEqual =true;
						}
					}while(digitsEqual);
					// ----------------------------Do while Loop end -----------------------------------------
					
					if (hard)
						tenMilDigitRand=codeSecret.substring(0,1);
					if (med || hard)
						milDigitRand= codeSecret.substring(0+(addIt-1),1+(addIt-1));
					hundredsDigitRand = codeSecret.substring(0+addIt,1+addIt);
					tensDigitRand = codeSecret.substring(1+addIt, 2+addIt);
					onesDigitRand = codeSecret.substring(2+addIt, 3+addIt);
					
					if (hard)
						tenMilDigit= numUser.substring(0,1);
					if (med || hard)
						milDigit= numUser.substring(0+(addIt-1),1+(addIt-1));
					hundredsDigit = numUser.substring(0+addIt,1+addIt);
					tensDigit= numUser.substring(1+addIt,2+addIt);
					onesDigit= numUser.substring(2+addIt,3+addIt);
					
					//The following if statements are only executed if the boolean exp is true
					if(hard)
					{
						//this boolean is set to true if user guessed digit
						guessedTenMil= tenMilDigitRand.equals(tenMilDigit);
						if (guessedTenMil)
						{
						guessedRight++;
						tM = Integer.parseInt(tenMilDigit);
						}
					}
						
					if(med || hard)
					{
						guessedMil= milDigitRand.equals(milDigit);
						if (guessedMil)
						{
						guessedRight++;
						m = Integer.parseInt(milDigit);
						}
					}
					// These are executed regardless of difficulty 	
					guessedHundreds = hundredsDigitRand.equals(hundredsDigit);
					guessedTens = tensDigitRand.equals(tensDigit);
					guessedOnes = onesDigitRand.equals(onesDigit);
					
					if (guessedHundreds)
					{
						guessedRight++;
						h = Integer.parseInt(hundredsDigit);
					}
					if (guessedTens)
					{
						guessedRight++;
						t = Integer.parseInt(tensDigit);
					}
					if (guessedOnes)
					{
						guessedRight++;
						o = Integer.parseInt(onesDigit);
					}
					//Sum of the numbers the user guessed
					sum= tM+m+h+t+o;

					System.out.println("Number of correct digits: " +guessedRight);
					System.out.println("Sum: " + sum);
					// We set all the Variable back to zero so we don't have them on the
					// next loop.
					tM=0;
					m=0;
					h=0;
					t=0;
					o=0;
					sum=0;
					
					//If the user's correct guesses is equal to the number of digits they win
					if (guessedRight == codeSecret.length())
					{
					 newMoney= (moneyBet* numDigits
							 * (maxGuesses-incorrectGuesses)
							 )/maxGuesses;
					 System.out.println("\t You Won!!!");
					 System.out.println("\t You have had " + incorrectGuesses + 
							 " wrong guesses.");
					 System.out.println("\t You have earned "+ newMoney );
					 userMoney= userMoney + newMoney;
					 System.out.println("\t Your balance is now " + userMoney + 
							 " dollars");
					 
					 //Ask to play again 
					 System.out.println("Would you like to play again (yes/no)?");
					 userChoice = user.nextLine();
					 userChoice = user.nextLine();
					 
					 while(!userChoice.equalsIgnoreCase("yes") && 
								 !userChoice.equalsIgnoreCase("no"))
					 {
						 System.out.println("Answer is invalid. Please enter yes/no.");
						 userChoice= user.nextLine();
					 }
		
					 // If the user wants to play again we jump out of loop 
					 if (userChoice.equalsIgnoreCase("yes"))
						 {
						 goBackUp=true;
						 timesPlayed++;
						 guessedRight=0;
						 break;
						 }
					 if (userChoice.equalsIgnoreCase("no"))
					 {
						 System.out.println("The game has terminated. "
						 		+ "Bye. Come to play again!!");
						 System.exit(0);
					 }
					}
					// If the user doesn't guess all digits we continue
					else
					{
					 //Reset number of correct guesses
					 guessedRight=0;
					 // The user now has incorrectly guessed
					 incorrectGuesses++;
					 
					 // When the user meet the max guesses, they lose
					 	if(incorrectGuesses == maxGuesses)
					 	{
							 System.out.println("\t Sorry, you lost!!!");
							 System.out.println("\t You have guessed " + incorrectGuesses + 
									 " times.");
							 System.out.println("\t The number was " + codeSecret);
							 userMoney = userMoney - moneyBet;
							 System.out.println("\t You have lost " + moneyBet
									 + " dollars.");
							 System.out.println("\t Your balance is now " + userMoney
									 + " dollars.");
							 
							 // Ask to play, REGARDLESS of their balance, the beginning of the
							 // loop determines whether or not the user has enough money to play
							 System.out.println("Would you like to play again (yes/no)?");
							 userChoice = user.nextLine();
							 userChoice = user.nextLine();
							 
		
							 while(!userChoice.equalsIgnoreCase("yes") && 
									 !userChoice.equalsIgnoreCase("no"))
							 {
								 System.out.println("Answer is invalid. Please enter yes/no.");
								 userChoice= user.nextLine();
							 }
			
							 if (userChoice.equalsIgnoreCase("yes"))
								 {
								 goBackUp=true;
								 timesPlayed++;
								 guessedRight=0;
								 break;
								 }
							 if (userChoice.equalsIgnoreCase("no"))
							 {
								 System.out.println("The game has terminated. "
								 		+ "Bye. Come to play again!!");
								 System.exit(0);
							 } 
					 	}
					}
				}
			 // goes to the top of the main loop if user wants to replay
				 if(goBackUp = true)
				 {
					 continue;
				 }
			 }
			 
			 else
			 {
				 System.out.println("Sorry, you should have at least 2.0 dollars "
					 		+ "to play the game. Bye!");
				 System.exit(0);
			 }
		}
		
		else 
		{
			 System.out.println("Answer is invalid. Please enter yes/no.");
			 userChoice = user.nextLine();
		}
	}
	 System.out.println("Bye, see you next time.");
	 System.exit(0);
	}
}
