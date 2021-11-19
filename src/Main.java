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
        System.out.println("Task1");
        lyrics.stream().max(Comparator.comparing(String::length)).ifPresent(t -> System.out.println(t.length() + ":" + t));
        System.out.println();
        // Task 2
        System.out.println("Task2");
        List<String> words = lyrics.stream().flatMap(w -> Arrays.stream(w.toLowerCase().replace(",", "").split(" ")))
                .collect(Collectors.toList());
        System.out.println();


        // Task 3
        System.out.println("Task3");
        words.stream().distinct().filter(t -> t.length() == 6).sorted().forEach(System.out::println);
        System.out.println();

        // Task 4
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