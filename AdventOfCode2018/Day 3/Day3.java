import sun.plugin.dom.css.Rect;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 {
    public static void main(String[] args) {
        try (Stream<String> input = Files.lines(Paths.get("Day 3\\input.txt"))) {
            List<String> in = input.collect(Collectors.toList());

            List<Rectangle> rectangles = in.stream().map(x -> {
                String[] coords = x.replaceAll(" ", "")
                        .split("@")[1]
                        .split(":");
                String[] x1 = coords[0].split(",");
                String[] x2 = coords[1].split("x");

                Point p1 = new Point(Integer.valueOf(x1[0]), Integer.valueOf(x1[1]));
                Point p2 = new Point(p1.getX() + Integer.valueOf(x2[0]) - 1, p1.getY() + Integer.valueOf(x2[1]) - 1);

                return new Rectangle(p1, p2);
            }).collect(Collectors.toList());

            Set<Point> overlaps = new HashSet<>();
            Map<Integer, Boolean> doesOverlap = new HashMap<>();
            IntStream.range(1, rectangles.size()).forEach(x -> doesOverlap.put(x, false));

            for (int i = 0; i < rectangles.size(); i++) {
                for (int j = i + 1; j < rectangles.size(); j++) {
                    if (rectangles.get(i).overlaps(rectangles.get(j))){
                        overlaps.addAll(rectangles.get(i).overlappingArea(rectangles.get(j)).toPointList());
                        doesOverlap.put(i + 1, true);
                        doesOverlap.put(j + 1, true);
                    }
                }
            }


            int resultOneStar = overlaps.size();
            int resultTwoStars = doesOverlap.entrySet().stream().filter(x -> !x.getValue()).findFirst().get().getKey();

            System.out.println("One star result: "+ resultOneStar
                        + "\nTwo star result: " + resultTwoStars);

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
