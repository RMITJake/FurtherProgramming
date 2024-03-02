package console;
import java.util.Scanner;
import console.handlers.*;

class Main {
    public static void main(String[] args){
        System.out.println("Running main");
        MenuHandler menu = new MenuHandler();
        menu.displayMenu();
    }
}