package console.handlers;
import java.util.ArrayList;

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

    ArrayList<String> listCategories(){
        VenueArray venueList = listVenues();
        ArrayList<String> categoryList = new ArrayList<String>();
        for (Venue venue : venueList) {
            if(!categoryList.contains(venue.category)){
                categoryList.add(venue.category);
            }
        }
        return categoryList;
    }

    VenueArray getVenueByCategory(int category){
        VenueArray venueList = listVenues();
        ArrayList<String> categoryList = listCategories();
        VenueArray venueByCategory = new VenueArray();
        for (Venue venue : venueList) {
            if(venue.category.equals(categoryList.get(category-1))){
                venueByCategory.add(venue);
            }
        }
        return venueByCategory;
    }

    ArrayList<String> getVenueNameByCategory(int category){
        VenueArray venues = getVenueByCategory(category);
        ArrayList<String> venueNames = new ArrayList<String>();
        for (Venue venue : venues){
            venueNames.add(venue.name);
        }
        return venueNames;
    }
    
    Venue getVenueByName(int index, ArrayList<String> venueList){
        return getVenueByName(venueList.get(index-1));
    }

    Venue getVenueByName(String searchName){
        VenueArray venueList = listVenues();
        for (Venue venue : venueList) {
            if(venue.name.equals(searchName)){
                return venue;
            }
        }
        return null;
    }
}
