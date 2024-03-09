package console.handlers;
import console.models.Venue;
import console.models.VenueArray;

public class VenueHandler {
    FileHandler file;
    String headers;

    public VenueHandler(FileHandler file){
        this.file = file;
    }

    public VenueArray listVenues(){
        VenueArray venueList = new VenueArray();
        headers = "";
        for (String line : this.file.readCSV("venues.csv")){
            if(headers == ""){
                headers = line;
            } else {
                venueList.add(deserialiseVenue(line));
            }
        }
        return venueList;
    }

    public Venue deserialiseVenue(String csv){
        String[] splitString = csv.split(",");
        Venue venue = new Venue(splitString[0], Integer.parseInt(splitString[1]), splitString[2], splitString[3]);
        return venue;
    }
}
