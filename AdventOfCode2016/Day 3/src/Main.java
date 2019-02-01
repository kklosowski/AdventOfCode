import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<int[]> trianglesHorizontally = loadTrianglesHorizontally();
        List<int[]> trianglesVertically = loadTrianglesVertically();

        long trianglesHorizontallyCount = trianglesHorizontally.stream().filter(x -> isTriangle(x)).count();
        long trianglesVerticallyCount = trianglesVertically.stream().filter(x -> isTriangle(x)).count();


        System.out.println(trianglesHorizontallyCount);
        System.out.println(trianglesVerticallyCount);
    }

    public static boolean isTriangle(int[] sides){
        Arrays.sort(sides);

        if(sides[0] + sides[1] > sides[2]) {
            return true;
        }
        else {
            return false;
        }
    }

    public static List<int[]> loadTrianglesHorizontally(){
        List<int[]> trianglesHorizontally = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("..\\AdventOfCode\\Day 3\\src\\Input.txt"))){
            stream.forEach(line -> {
                line = line.trim().replaceAll(" +", " ");

                String[] sides = line.split(" ");
                int[] triangle = new int[3];

                for(int i = 0; i < 3; i++){
                    triangle[i] = Integer.valueOf(sides[i]);
                }
                trianglesHorizontally.add(triangle);
            });
            return trianglesHorizontally;

        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static List<int[]> loadTrianglesVertically(){

        List<int[]> trianglesHorizontally = loadTrianglesHorizontally();
        List<int[]> trianglesVertically = new ArrayList<>();
        int[] triangleVertical = null;

        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < trianglesHorizontally.size(); j++){
                if(j % 3 == 0){
                    triangleVertical = new int [3];
                }

                triangleVertical[j % 3] = trianglesHorizontally.get(j)[i];

                if(j % 3 == 2){
                    trianglesVertically.add(triangleVertical);
                }
            }
        }

        return trianglesVertically;
    }
}
