package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileComparisonService {



    public String compare(String first, String second, String output) throws IOException {
        Set<String> uniqueFirst = new HashSet<>();
        Set<String> uniqueSecond = new HashSet<>();
        Set<String> common = new HashSet<>();


        try (BufferedReader br1 = new BufferedReader(new FileReader(first));
             BufferedReader br2 = new BufferedReader(new FileReader(second))) {

            String line;
            while ((line = br1.readLine()) != null) {
                uniqueFirst.add(line);
            }

            while ((line = br2.readLine()) != null) {
                if (uniqueFirst.contains(line)) {
                    common.add(line);
                    uniqueFirst.remove(line);
                } else {
                    uniqueSecond.add(line);
                }
            }


        }catch(IOException e){
            throw new RuntimeException("ERROR: Failed to run comparison", e);
        }

        return this.stringifyComparisonResults(uniqueFirst, uniqueSecond, common);
    }

    public String stringifyComparisonResults(Set<String> uniqueFirst, Set<String> uniqueSecond, Set<String> common){
        int totalFirst = uniqueFirst.size() + common.size();
        int totalSecond = uniqueSecond.size() + common.size();

        double commonA = totalFirst == 0 ? 0 : 100.0 * common.size() / totalFirst;
        double uniqueA = totalFirst == 0 ? 0 : 100.0 * uniqueFirst.size() / totalFirst;

        double commonB = totalSecond == 0 ? 0 : 100.0 * common.size() / totalSecond;
        double uniqueB = totalSecond == 0 ? 0 : 100.0 * uniqueSecond.size() / totalSecond;

        StringBuilder outputString = new StringBuilder("===== Comparison Summary =====\n");
        outputString.append(String.format("File A: %.2f%% common, %.2f%% unique%n", commonA, uniqueA));
        outputString.append(String.format("File B: %.2f%% common, %.2f%% unique%n", commonB, uniqueB));
        outputString.append("\n--- Entries exclusive to File A ---\n");

        for(String line : uniqueFirst){
            outputString.append(line).append("\n");
        }

        outputString.append("\n--- Entries exclusive to File B ---\n");

        for(String line : uniqueSecond){
            outputString.append(line).append("\n");
        }

        outputString.append("\n--- Common Entries ---\n");
        for(String line : common){
            outputString.append(line).append("\n");
        }
        outputString.append("==============================");

        return outputString.toString();
    }
}
