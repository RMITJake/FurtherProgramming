package console.handlers;

class RequestHandler {
    FileHandler file = new FileHandler();

    public void listRequests(){
        file.readCSV("requests.csv");
    }

    public void deserialiseRequest(){}
    public void serialiseRequest(){}
    public void requestToString(){}
}
