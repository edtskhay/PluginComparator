package Command;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExtractCommand {

    private Options options;
    //need a zip service
    //need a file service to output with

    public ExtractCommand(Options options){
        this.options = options;
    }


    public void run(String[] args) throws IOException, ParseException {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(this.options, args);

        String inputFile = cmd.getOptionValue("i");
        String outputFile = cmd.getOptionValue("o");


        //parse the file using some sort of stream
        //output the file parsed into some txt file, and save it in a folder?
        //how to define the options?
    }

    public List<String> inputEntries(){

    }


}
