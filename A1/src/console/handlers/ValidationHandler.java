package console.handlers;

class ValidationHandler {
    boolean validateInt(String input){
        try {
            Integer.parseInt(input);
        } catch (Exception e){
            System.out.println("Int validation failed");
            return false;
        }
        return true;
    }    
}