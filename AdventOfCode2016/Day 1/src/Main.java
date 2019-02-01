import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String INPUT = "L2, L3, L3, L4, R1, R2, L3, R3," +
                " R3, L1, L3, R2, R3, L3, R4, R3, R3," +
                " L1, L4, R4, L2, R5, R1, L5, R1, R3," +
                " L5, R2, L2, R2, R1, L1, L3, L3, R4," +
                " R5, R4, L1, L189, L2, R2, L5, R5, R45," +
                " L3, R4, R77, L1, R1, R194, R2, L5, L3," +
                " L2, L1, R5, L3, L3, L5, L5, L5, R2, L1," +
                " L2, L3, R2, R5, R4, L2, R3, R5, L2, L2," +
                " R3, L3, L2, L1, L3, R5, R4, R3, R2, L1," +
                " R2, L5, R4, L5, L4, R4, L2, R5, L3, L2," +
                " R4, L1, L2, R2, R3, L2, L5, R1, R1, R3," +
                " R4, R1, R2, R4, R5, L3, L5, L3, L3, R5," +
                " R4, R1, L3, R1, L3, R3, R3, R3, L1, R3," +
                " R4, L5, L3, L1, L5, L4, R4, R1, L4, R3," +
                " R3, R5, R4, R3, R3, L1, L2, R1, L4, L4," +
                " L3, L4, L3, L5, R2, R4, L2";

        List<String> steps = new ArrayList<>(Arrays.asList(INPUT.split(", ")));
        List<Point> visited = new ArrayList<>();
        Point startLocation = new Point(0,0);
        Point currentPosition = new Point(0,0);
        Point firstRevisited = null;
        //Direction in range 0-3: N=0, E=1, S=2, W=3
        int direction = 0;
        int turn = 0;
        int distance = 0;

        visited.add(new Point(currentPosition));

        for (String step : steps){
            turn = Util.parseTurn(step.charAt(0));
            direction = Util.nonNegativeModulo(direction + turn, 4);
            distance = Integer.valueOf(step.substring(1));

            /* If first intersection wasn't yet found saves all the points on the path and
            checks if any was revisited else moves to the next position */
            if (firstRevisited == null) {
                for (int i = 0; i < distance; i++){
                    currentPosition.translate(direction, 1);

                    if(visited.stream().anyMatch(point -> point.equals(currentPosition))){
                        firstRevisited = new Point(currentPosition);
                    }

                    visited.add(new Point(currentPosition));
                }
            } else {
                currentPosition.translate(direction, distance);
            }
        }

        System.out.println(firstRevisited.distanceFrom(startLocation));
        System.out.println(currentPosition.distanceFrom(startLocation));

    }
}
