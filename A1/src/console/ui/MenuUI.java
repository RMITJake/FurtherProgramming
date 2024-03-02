package console.ui;
public class MenuUI {
    private String banner;
    private String mainMenu;

    public String getBanner(){
        banner = "welcome to Venue Matcher";
        return banner;
    }

    public String getMainMenu(){
        mainMenu = ""
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
        return mainMenu;
    }
}
