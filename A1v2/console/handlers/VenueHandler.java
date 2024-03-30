package console.handlers;

import java.util.List;
import java.util.HashMap;
import console.models.Venue;

class VenueHandler{
    FileHandler file;
    private String VENUES = "venues.csv";

    public VenueHandler(){}
    public VenueHandler(FileHandler file){
        this.file = file;
    }

    HashMap<Integer, Venue> retrieveVenues(){
        HashMap<Integer, Venue> venues = new HashMap<Integer, Venue>();
        List<List<String>> records = file.getLineFromCSV(VENUES);

        records.remove(0); // remove headers
        int id = 0;

        for(List<String> record : records){
            id++;
            venues.put(id, new Venue(record.get(0),Integer.parseInt(record.get(1)),record.get(2),record.get(3),Integer.parseInt(record.get(4))));
        }
        return venues;
    }

    HashMap<Integer, String> getCategories(){
        HashMap<Integer, Venue> venues = retrieveVenues();
        HashMap<Integer, String> categories = new HashMap<Integer, String>();
        int id = 0;
        for(int venue : venues.keySet()){
            if(!categories.containsValue(venues.get(venue).getCategory())){
                id++;
                categories.put(id, venues.get(venue).getCategory());
            }
        }
        return categories;
    }

    HashMap<Integer, Venue> getVenueByCategory(String category){
        HashMap<Integer, Venue> allVenues = retrieveVenues();
        HashMap<Integer, Venue> venues = new HashMap<Integer, Venue>();

        int id = 0;
        for(int venueId : allVenues.keySet()){
            if(allVenues.get(venueId).getCategory().contains(category)){
                id++;
                venues.put(id, allVenues.get(venueId));
            }
        }

        return venues;
    }

    Venue searchVenueByName(String searchName){
        HashMap<Integer, Venue> venues = retrieveVenues();
        for(int venueId : venues.keySet()){
            if(venues.get(venueId).getName().equals(searchName)){
                return venues.get(venueId);
            }
        }
        return null;
    }
}