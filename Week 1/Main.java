/*
 * Jake Kent
 * s3905550
*/
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Program1 p1 = new Program1();
    }
}

class Program1 {
    int magicNumber;
    int userGuess;
    int upperLimit = 100;
    String guessCorrect = "Yes, the number is ";
    String guessLowMessage = "Your guess is too low";
    String guessHighMessage = "Your guess is too high";
    Scanner scn = new Scanner(System.in);

    public Program1(){
        Random randomNumber = new Random();
        this.magicNumber = randomNumber.nextInt(upperLimit);
        Debug("magicNumber = " + magicNumber);

        do{
            System.out.print("Enter your guess: ");
            userGuess = Integer.parseInt(scn.nextLine());
            ValidateGuess(userGuess);
        }
        while(userGuess != this.magicNumber);
    }

    public void ValidateGuess(int userInput){
        //correct
        if(userInput == this.magicNumber){
            System.out.println(guessCorrect + this.magicNumber);
        //too low
        } else if(userInput < this.magicNumber){
            System.out.println(guessLowMessage);
        //too high
        } else if(userInput > this.magicNumber){
            System.out.println(guessHighMessage);
        }
    }

    public void Debug(String log){
        System.out.println("DEBUG: " + log);
    }
}

class Program2 {

}

class Program3 {}
class Program4 {}
class Program5 {}