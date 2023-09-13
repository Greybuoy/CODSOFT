import java.util.Random;
import java.util.Scanner;

 public class Guess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int lowerBound = 1;  // Minimum number
        int upperBound = 100; // Maximum number
        int maxAttempts = 10; // Maximum attempts allowed per round
        int rounds = 0; // Number of rounds played
        int totalAttempts = 0; // Total attempts made
        int bestScore = Integer.MAX_VALUE; // Best score (fewest attempts) across rounds
        
        boolean playAgain = true;
        
        System.out.println("Welcome to the Number Guessing Game!");
        
        while (playAgain) {
            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int numberOfAttempts = 0;
            boolean hasGuessedCorrectly = false;
            
            System.out.println("\nRound " + (rounds + 1) + ":");
            System.out.println("I'm thinking of a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");
            
            while (!hasGuessedCorrectly && numberOfAttempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfAttempts++;
                totalAttempts++;
                
                if (userGuess < lowerBound || userGuess > upperBound) {
                    System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ".");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Higher! Attempts remaining: " + (maxAttempts - numberOfAttempts));
                } else if (userGuess > numberToGuess) {
                    System.out.println("Lower! Attempts remaining: " + (maxAttempts - numberOfAttempts));
                } else {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the number " + numberToGuess + " in " + numberOfAttempts + " attempts.");
                    
                    if (numberOfAttempts < bestScore) {
                        bestScore = numberOfAttempts;
                    }
                }
            }
            
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + numberToGuess + ".");
            }
            
            System.out.println("Round " + (rounds + 1) + " ended. Your best score so far: " + bestScore + " attempts.");
            
            // Ask if the user wants to play another round
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = scanner.next().toLowerCase();
            if (!playAgainChoice.equals("yes")) {
                playAgain = false;
            }
            
            rounds++;
        }
        
        System.out.println("\nGame Over!");
        System.out.println("Total rounds played: " + rounds);
        System.out.println("Total attempts made: " + totalAttempts);
        System.out.println("Your best score across rounds: " + bestScore + " attempts.");
        
        scanner.close();
    }
}
