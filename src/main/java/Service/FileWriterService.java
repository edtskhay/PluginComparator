package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterService {

    public FileWriterService(){

    }

    public void writeToFile(String path, List<String> entries){
        try (FileWriter writer = new FileWriter(path)) {
            for (String str : entries) {
                writer.write(str + System.lineSeparator());
            }
        }catch(IOException e){
            System.out.println("ERROR: Failed to write to output file: " + path);
        }
    }

    public void writeToFile(String path, String entry){
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(entry);
        }catch(IOException e){
            System.out.println("ERROR: Failed to write to output file: " + path);
        }
    }
}
