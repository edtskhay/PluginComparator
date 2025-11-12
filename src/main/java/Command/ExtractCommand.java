package Command;

import Service.FileParserService;
import Service.FileWriterService;
import org.apache.commons.cli.*;

import java.util.List;

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
        System.out.println("Successfully extracted zip to: " + outputFile);
    }
}
