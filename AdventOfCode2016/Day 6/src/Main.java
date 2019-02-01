import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Eter on 09/12/2016.
 */
public class Main {
    public static void main(String[] args) {

        try (Stream<String> messages = Files.lines(Paths.get("..\\AdventOfCode\\Day 6\\src\\Input.txt"))) {

            List<List<Character>> charsInRow = new ArrayList<>();

            for (int i = 0; i < 8; i++){
                charsInRow.add(new ArrayList<>());
            }

            messages.forEach( message -> {
                for (int i = 0; i < message.length(); i++) {
                    charsInRow.get(i).add(message.charAt(i));
                }
            });

            charsInRow.stream().forEach(x -> System.out.print(findMostCommon(x)));
            System.out.print('\n');
            charsInRow.stream().forEach(x -> System.out.print(findLeastCommon(x)));

        } catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public static char findMostCommon(List<Character> characterList) {

        Optional<Character> mostCommon = characterList.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        if (mostCommon.isPresent()){
            return mostCommon.get();
        }
        else {
            //throw new Exception("Character not found");
            return 0;
        }
    }

    public static char findLeastCommon(List<Character> characterList) {

        Optional<Character> mostCommon = characterList.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        if (mostCommon.isPresent()){
            return mostCommon.get();
        }
        else {
            //throw new Exception("Character not found");
            return 0;
        }
    }
}
