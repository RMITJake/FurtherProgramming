package console.handlers;

import java.util.List;
import java.util.HashMap;
import console.models.Venue;

class VenueHandler{
    private FileHandler file;
    private String VENUES = "venues.csv";

    public VenueHandler(FileHandler file){
        this.file = file;
    }

    HashMap<Integer, Venue> retrieveVenues(HashMap<Integer, Venue> venueList){
        List<List<String>> records = file.getLineFromCSV(VENUES);

        records.remove(0); // remove headers
        int id = 0;

        for(List<String> record : records){
            id++;
            if(venueList.get(id) == null){
                venueList.put(id, new Venue(
                    record.get(0),
                    Integer.parseInt(record.get(1)),
                    record.get(2),
                    record.get(3),
                    Integer.parseInt(record.get(4)
                )));
            }
        }
        return venueList;
    }

    HashMap<Integer, String> getCategories(HashMap<Integer, Venue> venueList){
        HashMap<Integer, String> categories = new HashMap<Integer, String>();
        int id = 0;
        for(int venue : venueList.keySet()){
            if(!categories.containsValue(venueList.get(venue).getCategory())){
                id++;
                categories.put(id, venueList.get(venue).getCategory());
            }
        }
        return categories;
    }

    HashMap<Integer, Venue> getVenueByCategory(String category, HashMap<Integer, Venue> venueList){
        HashMap<Integer, Venue> venueFiltered = new HashMap<Integer, Venue>();

        int id = 0;
        for(int venueId : venueList.keySet()){
            if(venueList.get(venueId).getCategory().contains(category)){
                id++;
                venueFiltered.put(id, venueList.get(venueId));
            }
        }

        return venueFiltered;
    }

    HashMap<Integer, Venue> searchVenueByName(String searchName, HashMap<Integer, Venue> venueList){
        HashMap<Integer, Venue> searchVenues = new HashMap<Integer, Venue>();
        int newId = 0;
        for(int venueId : venueList.keySet()){
            if(venueList.get(venueId).getName().toLowerCase().contains(searchName.toLowerCase())){
                newId++;
                searchVenues.put(newId, venueList.get(venueId));
            }
        }
        return searchVenues;
    }

    Venue getVenueByName(String searchName, HashMap<Integer, Venue> venueList){
        for(int venueId : venueList.keySet()){
            if(venueList.get(venueId).getName().equals(searchName)){
                return venueList.get(venueId);
            }
        }
        return null;
    }
}