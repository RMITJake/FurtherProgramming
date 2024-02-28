/*
 * Jake Kent
 * s3905550
*/
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args){
        // Program1 p1 = new Program1();
        Program2 p2 = new Program2();
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
    double degrees;
    double radians;
    double sine;
    double cosine;
    double tangent;

    public Program2(){
        System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n", "Degrees", "Radians", "Sine", "Cosine", "Tangent");
        // System.out.printf("%-10.0f\t%-10.4f\t%-10.4f\t%-10.4f\t%-10.4f\n", degrees, radians, sine, cosine, tangent);
        Formatter(30.0);
        Formatter(60.0);
    }

    public void Formatter(double deg){
        this.degrees = deg;
        this.radians = Math.toRadians(degrees);
        this.sine = Math.sin(radians);
        this.cosine = Math.cos(radians);
        this.tangent = Math.tan(radians);

        System.out.printf("%-10.0f\t%-10.4f\t%-10.4f\t%-10.4f\t%-10.4f\n", this.degrees, this.radians, this.sine, this.cosine, this.tangent);
    }
}

class Program3 {}
class Program4 {}
class Program5 {}