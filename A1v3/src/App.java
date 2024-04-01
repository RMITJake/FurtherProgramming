package src;
import src.handlers.MenuHandler;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        MenuHandler menu = new MenuHandler();
        menu.menuLoop();
    }
}
