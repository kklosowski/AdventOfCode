import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) {
        try (Stream<String> input = Files.lines(Paths.get("Day 1\\input.txt"))) {
            List<String> in = input.collect(Collectors.toList());

            int resultOneStar = in.stream().mapToInt(Integer::valueOf).sum();
            int resultTwoStars = getFirstRepeated(in);

            System.out.println("One star result: "+ resultOneStar
                    + "\nTwo star result: " + resultTwoStars);

        } catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private static int getFirstRepeated(List<String> in){
        List<Integer> history = new ArrayList<>();
        history.add(0);
        while (true){
            for (String x : in) {
                int sum = history.get(history.size() - 1) + Integer.valueOf(x);
                if (!history.contains(sum)) {
                    history.add(sum);
                } else {
                    return sum;
                }
            }
        }
    }
}
