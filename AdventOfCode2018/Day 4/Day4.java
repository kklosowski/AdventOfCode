import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    public static void main(String[] args) {
        try (Stream<String> input = Files.lines(Paths.get("Day 4\\input.txt"))) {
            List<String> in = input.collect(Collectors.toList());
            Map<Date, String> shiftLogs = new HashMap<>();
            in.stream().map(x -> x.substring(1, x.length()).split("]"))
                    .forEach(x -> shiftLogs.put(stringToDate(x[0]), x[1].trim()));
            Map<Integer, Guard> guards = getGuardsWithShifts(shiftLogs);
            Guard mostAsleep = guards.values().stream()
                    .max(Comparator.comparingLong(Guard::getTotalNapTime))
                    .orElse(null);

//            System.out.println(mostAsleep.getTotalNapTime() * mostAsleep.getId());

            int resultOneStar = 0;
            int resultTwoStars = 0;

            System.out.println("One star result: "+ resultOneStar
                    + "\nTwo star result: " + resultTwoStars);

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static Map<Integer, Guard> getGuardsWithShifts(Map<Date, String> shiftLogs){
        Map<Integer, Guard> guards = new HashMap<>();
        AtomicInteger currentGuardId = new AtomicInteger(
                Integer.valueOf(shiftLogs.entrySet().stream()
                        .min(Map.Entry.comparingByKey())
                        .get()
                        .getValue()
                        .split(" ")[1]
                        .replace("#", ""))
        );
        AtomicReference<Shift> currentShift = new AtomicReference<>();
        AtomicReference<Nap> currentNap = new AtomicReference<>();

        shiftLogs.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(x -> {
                    if(x.getValue().contains("#")){
                        if (currentShift.get() != null){
                            guards.get(currentGuardId.get()).addShift(currentShift.get());
                        }
                        currentGuardId.set(Integer.valueOf(x.getValue().split(" ")[1]
                                .replace("#", "")));
                        currentShift.set(new Shift(x.getKey()));

                        if (!guards.containsKey(currentGuardId.get())){
                            guards.put(currentGuardId.get(), new Guard(currentGuardId.get()));
                        }
                    }
                    if(x.getValue().contains("falls")){
                        currentNap.set(new Nap(x.getKey()));
                    }
                    if(x.getValue().contains("wakes")){
                        currentNap.updateAndGet(y -> {
                            y.setEnd(x.getKey());
                            return y;
                        });
                        currentShift.updateAndGet(y -> {
                            y.addNap(currentNap.get());
                            return y;
                        });
                    }
                });
        return guards;
    }

    public static Date stringToDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
