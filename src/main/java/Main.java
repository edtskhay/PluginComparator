import Command.CompareCommand;
import Command.ExtractCommand;
import Command.HelpCommand;
import Service.FileComparisonService;
import Service.FileParserService;
import Service.FileWriterService;
import org.apache.commons.cli.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        FileComparisonService fileComparer = new FileComparisonService();
        FileWriterService fileWriter = new FileWriterService();
        FileParserService fileParser = new FileParserService();

        if (args.length == 0) {
            System.out.println("No command provided. Use 'help' for a list of commands.");
            return;
        }

        String command = args[0];
        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        switch(command){
           case "extract" -> {
               Options extractOptions = new Options();
               extractOptions.addOption("i", "input",
                       true, "Input file");

               extractOptions.addOption("o", "output",
                       true, "Outputted file");

               ExtractCommand conversionCommand = new ExtractCommand(extractOptions, fileWriter, fileParser);
               conversionCommand.run(subArgs);
           }

           case "compare" -> {
               Options compareOptions = new Options();
               compareOptions.addOption("f", "first",
                       true, "First file");

               compareOptions.addOption("s", "second",
                       true, "Second file");
               compareOptions.addOption("o", "output", true, "Output file");

               CompareCommand compareCommand = new CompareCommand(compareOptions, fileWriter, fileComparer);
               compareCommand.run(subArgs);
           }

            case "help" -> {
                Options compareOptions = new Options();
                compareOptions.addOption("h", "help",
                        false, "Help command");

                HelpCommand helpCommand = new HelpCommand(compareOptions);
                helpCommand.run(subArgs);
            }
           default -> System.out.println("Unknown command. Use 'help' to display possible commands.");
        }
    }
}


