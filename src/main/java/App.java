import Command.ExtractCommand;
import Command.HelpCommand;
import Command.VersionCommand;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.ArrayUtils;

public class App {
    public static void main(String[] args) {

        String command = args[0];
        String[] subArgs = ArrayUtils.subarray(args, 1, args.length);

        switch(command){
           case "extract" -> {
               Options extractOptions = new Options();
               extractOptions.addOption("i", "input",
                       true, "Input file");

               extractOptions.addOption("o", "output",
                       true, "Outputted file");

               ExtractCommand conversionCommand = new ExtractCommand(extractOptions);
               conversionCommand.run(subArgs);
           }
           case "compare" -> {
               Options extractOptions = new Options();
               extractOptions.addOption("f", "first",
                       true, "First file for comparison");

               extractOptions.addOption("s", "second",
                       true, "Second file for comparison");
               FileExtractionCommand extractionCommand = new FileExtractionCommand();
           }
           case "help" -> {
               HelpCommand helpCommand = new HelpCommand();
               //displayHelp(subArgs);
           }
           case "version" -> {
               VersionCommand versionCommand = new VersionCommand();
               //printVersion();
           }
           default -> System.out.println("Unknown command. Use 'help' to display possible commands.");
        }
    }
}


