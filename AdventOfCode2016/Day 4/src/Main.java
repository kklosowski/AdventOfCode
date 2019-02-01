import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by Eter on 05/12/2016.
 */
public class Main {
    public static void main(String[] args) {

        AtomicInteger sectorIdSum = new AtomicInteger(0);
        AtomicInteger northPoleRoomId = new AtomicInteger();

        try (Stream<String> roomCodes = Files.lines(Paths.get("..\\AdventOfCode\\Day 4\\src\\Input.txt"))) {

            roomCodes.forEach(code -> {

                List<String> codeParts = new LinkedList<>(Arrays.asList(code.split("-")));
                String sectorIdAndChecksum = codeParts.get(codeParts.size() - 1);

                codeParts.remove(codeParts.size() - 1);

                int bracketPosition = sectorIdAndChecksum.indexOf('[');
                int sectorId = Integer.valueOf(sectorIdAndChecksum.substring(0, bracketPosition));
                String checkSum = sectorIdAndChecksum.substring(bracketPosition + 1, bracketPosition + 6);


                if (!isADecoy(codeParts, checkSum)) {
                    sectorIdSum.addAndGet(sectorId);

                    for (String s : codeParts) {
                        if (caesarCypher(s, sectorId).equals("northpole")) {
                            northPoleRoomId.set(sectorId);
                        }
                    }
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(sectorIdSum);
        System.out.println(northPoleRoomId);
    }

    public static String caesarCypher(String encrypted, int key) {
        String decrypted = "";

        for (char c : encrypted.toCharArray()) {
            decrypted += rotate(c, key);
        }

        return decrypted;
    }

    public static char rotate(char c, int key) {
        int move = key % 26;

        if (c + move > 'z') {
            c = (char) ('a' + move - ('z' - c + 1));
        } else {
            c += move;
        }

        return c;
    }

    public static boolean isADecoy(List<String> nameParts, String checkSum) {
        Map<Character, Integer> letterCount = new HashMap<>();

        for (String n : nameParts) {
            for (int i = 0; i < n.length(); i++) {
                char c = n.charAt(i);
                int count = letterCount.containsKey(c) ? letterCount.get(c) : 0;
                letterCount.put(c, count + 1);
            }
        }

        Comparator<Map.Entry<Character, Integer>> valueComparator = Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder());
        Comparator<Map.Entry<Character, Integer>> keyComparator = Comparator.comparing(Map.Entry::getKey);

        String[] actualCheckSum = {""};
        letterCount.entrySet()
                .stream()
                .sorted(valueComparator.thenComparing(keyComparator))
                .limit(5)
                .forEach(entry -> actualCheckSum[0] += entry.getKey());

        return !checkSum.equals(actualCheckSum[0]);
    }
}
