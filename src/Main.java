import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {
        List<String> lyrics = getFile("src/dream-lyrics.txt");

        ///////////////////////////////////////////
        // START OF YOUR CODE

        // Task 1
        // Identify the longest line in the list lyrics and print out the longest line and its length in the terminal.
        System.out.println("Task1");
        lyrics.stream().max(Comparator.comparing(String::length)).ifPresent(t -> System.out.println(t.length() + ":" + t));
        System.out.println();

        // Task 2
        // Collect all words in lyrics and store them in a local variable named words of type List<String>
        System.out.println("Task2");
        List<String> words = lyrics.stream().flatMap(w -> Arrays.stream(w.toLowerCase().replace(",", "").split(" ")))
                .collect(Collectors.toList());
        System.out.println();


        // Task 3
        //Print out a list of distinct words with 6 characters in an alphabetical order
        System.out.println("Task3");
        words.stream().distinct().filter(t -> t.length() == 6).sorted().forEach(System.out::println);
        System.out.println();

        // Task 4
        // Calculate the frequency of each unique word in words and then print out the words and their frequencies of all the words that have at    least 4 characters and occur more than 5 times. The output should be ordered by frequency
        System.out.println("Task4");
        words.stream().filter(t -> t.length() >= 4).collect(groupingBy(t -> t, counting())).entrySet()
                .stream().filter(t -> t.getValue() >= 5).sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(t -> System.out.println(t.getKey()));
        System.out.println();

        // END OF YOUR CODE
        ///////////////////////////////////////////
    }

    private static List<String> getFile(String filename) {

        List<String> contents = new ArrayList<>();

        try {
            // Get the file
            Path path = Paths.get(filename);

            // Read the contents of the file
            Stream<String> lines = Files.lines(path);
            contents = lines.collect(Collectors.toList());

            // Close the files
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }

}
