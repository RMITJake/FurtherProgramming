/*
 * Jake Kent
 * s3905550
*/
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args){
        Program1 p1 = new Program1();
        Program2 p2 = new Program2();
        Program3 p3 = new Program3();
        Program4 p4 = new Program4();
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
        if(userInput == this.magicNumber){
            System.out.println(guessCorrect + this.magicNumber);
        } else if(userInput < this.magicNumber){
            System.out.println(guessLowMessage);
        } else if(userInput > this.magicNumber){
            System.out.println(guessHighMessage);
        }
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

class Program3 {
    public Program3(){
        int rows;
        Scanner scn = new Scanner(System.in);
        System.out.print("How many rows you want in this pattern: ");
        rows = Integer.parseInt(scn.nextLine());
        System.out.println("Here is your pattern!");
        for(int row=1; row <= rows; row++){
            for(int col=1; col <= row; col++){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}

class Program4 {
    String name;
    String[] nameSplit;
    String initials = "";

    public Program4(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = scn.nextLine();
        System.out.println("your name is " + name);
        nameSplit = name.split(" ", 5);

        for(String ch : nameSplit){
            initials += ch.charAt(0);
        }

        System.out.println("your initials are " + initials);
    }
}