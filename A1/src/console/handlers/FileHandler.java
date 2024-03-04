package console.handlers;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileHandler {
    private String basePath = "";

    public ArrayList<String> readCSV(String filename){
        String workingPath = basePath + filename;
        ArrayList<String> fileContents = new ArrayList<String>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(workingPath));

            while((line = reader.readLine()) != null)
            {
                fileContents.add(line);
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return fileContents;
    }
}