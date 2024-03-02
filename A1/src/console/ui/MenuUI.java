package console.ui;
public class MenuUI {
    private String menuText;

    public String getBanner(){
        menuText = "welcome to Venue Matcher";
        return menuText;
    }

    public String getMainMenu(){
        menuText = ""
        +"---------------\n"
        +"> Select from main menu\n"
        +"---------------\n"
        +"1) List current job requests\n"
        +"2) Browse venue by category\n"
        +"3) Search venue by name\n"
        +"4) Auto-match events with suitable venues\n"
        +"5) Show order summary\n"
        +"6) Exit\n"
        +"Please select: ";
        return menuText;
    }
}
