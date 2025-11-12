package Command;

import Service.FileParserService;
import Service.FileWriterService;
import org.apache.commons.cli.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExtractCommand {

    private final Options options;
    private final FileWriterService fileWriter;
    private final FileParserService fileParser;

    public ExtractCommand(Options options, FileWriterService fileWriter, FileParserService fileParser) {
        this.options = options;
        this.fileWriter = fileWriter;
        this.fileParser = fileParser;
    }

    public void run(String[] args) {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(this.options, args);
        } catch (ParseException e) {
            System.out.println("ERROR: Failed to parse command-line arguments");
            return;
        }

        String inputFile = cmd.getOptionValue("i");
        String outputFile = cmd.getOptionValue("o");
        if (inputFile == null || outputFile == null) {
            throw new IllegalArgumentException("Both input (-i) and output (-o) files are required.");
        }

        List<String> zipEntries = fileParser.parseFile(inputFile);

        fileWriter.writeToFile(outputFile, zipEntries);
    }



    public List<String> parseFile(String path) {
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
            throw new RuntimeException("Failed to read file: " + path, e);
        }
        return entryList;
    }
}
