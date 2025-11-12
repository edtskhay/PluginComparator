package Command;

import org.apache.commons.cli.Options;

public class HelpCommand {

    private final Options options;
    private final String helpMessage =
            """
            Plugin Comparator CLI Tool (cutting edge btw)
            ---------------------------------------------
            Usage:
             mvn compile exec:java -Dexec.mainClass=App -Dexec.args="<command> [options>"
            
            Commands:
             extract    Parse a plugin (ZIP/JAR) and output its entries to a file
             compare    Compare two plugin entry files and show commonality/uniqueness
            
            Options for 'extract' command:
             -i, --input     Path to input plugin file (ZIP or JAR) [required]
             -o, --output    Path to output text file where entries will be saved [required]
            
            Options for 'compare' command:
             -f, --first     Path to first entries file [required]
             -s, --second    Path to second entries file [required]
             -o, --output    Path to output text file where result of comparison will be saved [optional]
            
            Examples:
             mvn compile exec:java -Dexec.mainClass=App -Dexec.args="extract -i plugin.zip -o entries.txt"
             mvn compile exec:java -Dexec.mainClass=App -Dexec.args="compare -f entries1.txt -s entries2.txt"
            
            Notes:
             - 'extract' will generate a text file listing all entries in the plugin.
             - 'compare' will calculate common entries and unique entries per file, and print percentages.
             - Percentages indicate how much of each file overlaps or is unique.
            """;

    public HelpCommand(Options options) {
        this.options = options;
    }

    public void run(String[] args){
        if(args.length == 0){
            System.out.println(helpMessage);
        }
    }

}
