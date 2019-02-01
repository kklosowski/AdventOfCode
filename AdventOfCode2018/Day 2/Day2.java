import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
    public static void main(String[] args) {
        try (Stream<String> input = Files.lines(Paths.get("Day 2\\input.txt"))) {
            List<String> in = input.collect(Collectors.toList());

            int resultOneStar = partOne(in);
            String resultTwoStars = partTwo(in);

            System.out.println("One star result: "+ resultOneStar
                        + "\nTwo star result: " + resultTwoStars);

        } catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private static String partTwo(List<String> in) {
        for (String s1 : in) {
            for (String s2 : in) {
                if (similarityIndex(s1, s2) == 1){
                    StringBuilder sb = new StringBuilder(s1);
                    for (int i = 0; i < s1.length(); i++) {
                        if (s1.charAt(i) != s2.charAt(i)) {
                            sb.deleteCharAt(i);
                        }
                    }
                    return sb.toString();
                }
            }
        }
        return null;
    }

    private static int partOne(List<String> in){
        AtomicInteger twoOccurrences = new AtomicInteger(0);
        AtomicInteger threeOccurrences = new AtomicInteger(0);
        in.stream()
                .map(x -> Arrays.stream(x.split(""))
                        .collect(Collectors.groupingBy(c -> c, Collectors.counting())))
                .forEach(x -> {
                    if (x.containsValue(2L)) twoOccurrences.incrementAndGet();
                    if (x.containsValue(3L)) threeOccurrences.incrementAndGet();
                });

        return twoOccurrences.get() * threeOccurrences.get();
    }


    //Returns a number from 0 to s.length(): 0 being perfect match
    //Both strings must be same length
    private static int similarityIndex(String s1, String s2){
        if (s1.length() != s2.length()) return -1;
        else {
            AtomicInteger index = new AtomicInteger(0);
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) index.incrementAndGet();
            }
            return index.get();
        }
    }
}
