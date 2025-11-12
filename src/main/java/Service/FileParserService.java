package Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileParserService {


    public FileParserService(){}

    public List<String> parseFile(String path) throws RuntimeException{
        List<String> entryList = new ArrayList<>();
        try (ZipFile zipFile = new ZipFile(path)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    entryList.add(entry.getName());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("ERROR: Failed to read file: " + path, e);
        }

        return entryList;
    }
}
