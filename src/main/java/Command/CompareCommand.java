package Command;

import Service.FileComparisonService;
import Service.FileWriterService;
import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CompareCommand {

    private final Options options;
    private final FileWriterService fileWriter;
    private final FileComparisonService fileComparer;

    public CompareCommand(Options options, FileWriterService fileWriter, FileComparisonService fileComparer) {
        this.options = options;
        this.fileWriter = fileWriter;
        this.fileComparer = fileComparer;
    }

    public void run(String[] args){

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(this.options, args);
        } catch (ParseException e) {
            System.out.println("ERROR: Failed to parse command-line arguments");
            return;

        }

        String firstFile = cmd.getOptionValue("f");
        String secondFile = cmd.getOptionValue("s");
        String outputFile = cmd.getOptionValue("o");

        if (firstFile == null || secondFile == null) {
            throw new IllegalArgumentException("Both first (-f) and second (-s) files are required.");
        }


        try{
            String output = fileComparer.compare(firstFile, secondFile, outputFile);
            if(outputFile != null){
                fileWriter.writeToFile(outputFile, output);
                System.out.println("Comparison successfully written to: " + outputFile);
            }else{
                System.out.println(output);
            }
        }catch(IOException e){
            System.out.println("ERROR: Failed to compare files");
        }

    }

}
