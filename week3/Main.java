import java.util.*;

class Main{
    public static void main(String[] args){
        ProgramOne one = new ProgramOne();
        ProgramTwo two = new ProgramTwo();
    }
}

class ProgramOne{
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, -2, 5, -10));

    ProgramOne(){
        printList(list);
        removeNegaitives(list);
        printList(list);
        removeTwo(list);
        printList(list);
    }
    
    ArrayList<Integer> removeNegaitives(ArrayList<Integer> inputList){
        inputList.removeIf(x -> (x < 0));
        return inputList;
    }

    ArrayList<Integer> removeTwo(ArrayList<Integer> inputList){
        inputList.removeIf(x -> (x == 2));
        return inputList;
    }

    void printList(ArrayList<Integer> inputList){
        for(int i=0; i < inputList.size(); i++){
            if(!(i == inputList.size()-1)){
                System.out.print(inputList.get(i) + ", ");
            } else {
                System.out.println(inputList.get(i));
            }
        }
    }
}


class ProgramTwo{
    Map<String, String> accountsMap = new HashMap<String, String>();
    Map<String, String> quotaMap = new HashMap <String, String>();
    
    ProgramTwo(){
        // add accounts
        accountsMap.put("E123", "Charles");
        accountsMap.put("E156", "Heiko");
        accountsMap.put("E542", "Brendan");
        accountsMap.put("E174", "Tobin");

        // add quotas
        quotaMap.put("Charles", "5MB");
        quotaMap.put("Heiko", "50MB");
        quotaMap.put("Tobin", "4MB");
        quotaMap.put("Bruce", "8MB");

        for(Map.Entry<String, String> map : accountsMap.entrySet()){
            if(quotaMap.containsKey(map.getValue())){
                System.out.print("Employee: " + map.getKey());
                System.out.print(", Name: " + map.getValue());
                System.out.println(", Quota: " + quotaMap.get(map.getValue()));
            } else {
                System.out.print("Employee: " + map.getKey());
                System.out.print(", Name: " + map.getValue());
                System.out.println(", Quota: no quota yet");
            }
        }
    }
}

class ProgramThree{
    class Video extends Media{
        Video(String name){
            super(name);
        }
    }

    class Book extends Media{
        Book(String name){
            super(name);
        }
    }

    class GenericLibrary<E extends Media>{
        ArrayList<GenericLibrary<E>> library = new ArrayList<>();
        GenericLibrary(){}
    }
}
