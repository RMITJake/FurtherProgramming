import java.util.*;

class Main{
    public static void main(String[] args){
        ProgramOne one = new ProgramOne();
        ProgramTwo two = new ProgramTwo();
        ProgramThree Three = new ProgramThree();
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
    ProgramThree(){
        GenericLibrary<Video> myVideos = new GenericLibrary<Video>();
        GenericLibrary<Book> myBooks = new GenericLibrary<Book>();

        myVideos.addMedia(new Video("Video 1"));
        myVideos.addMedia(new Video("Video 2"));
        myVideos.addMedia(new Video("Video 3"));
        System.out.println(myVideos.retrieveLastMedia().getName());

        myBooks.addMedia(new Book("Book 1"));
        myBooks.addMedia(new Book("Book 2"));
        System.out.println(myBooks.retrieveLastMedia().getName());
    }

    class Media {
        private String name;
    
        public Media(String name){
            this.name = name;
        }
    
        public String getName(){
            return this.name;
        }
    }
    
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
       private List<E> resources = new ArrayList<E>();
    
       public void addMedia(E media) {
           resources.add(media);
       }
    
       public E retrieveLastMedia() {
           int size = resources.size();
           if(size > 0){
               return resources.get(size - 1);
           }
           return null;
       }
    }
}

class ProgramFour{

}

class ProgramFive{}

class ProgramSix{}
