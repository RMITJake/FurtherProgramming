package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericAnimalList<T extends Animal> {
    List<T> list = new ArrayList<T>();
    public GenericAnimalList(){

    }
    public void populate(List<T> aList){
        list.addAll(aList);
        
    }
    
    public List<T> getList() {
    	return list;
    }
    
}
