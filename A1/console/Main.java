package console;
import console.handlers.*;

class Main {
    public static void main(String[] args){
        MenuHandler menu = new MenuHandler();
        menu.menuLoop();
    }
}