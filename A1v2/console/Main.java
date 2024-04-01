package console;
import console.handlers.*;
import console.tests.FileHandlerTest;

class Main {
    public static void main(String[] args){
        for(String arg : args){
            if(arg.equals("test")){
                FileHandlerTest fTest = new FileHandlerTest();
            }
        }
        MenuHandler menu = new MenuHandler();
        menu.menuLoop();
    }
}