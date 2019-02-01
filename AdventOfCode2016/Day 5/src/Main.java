import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eter on 06/12/2016.
 */
public class Main {
    public static void main(String[] args) {

        String doorId = "cxdnnyjw";
        String passwordSimple = "";
        String passwordPositioned = "";
        Map<Integer, Character> passwordPositionedMap = new HashMap<>();

        for (int i = 0; passwordPositionedMap.size() < 8; i++) {
            String toEncode = doorId + i;
            String encoded = MD5(toEncode);

            if (encoded.substring(0, 5).equals("00000")) {
                if (passwordSimple.length() < 8){
                    passwordSimple += encoded.charAt(5);
                }
                if(encoded.charAt(5) >= '0' && encoded.charAt(5) <= '7' ){
                    passwordPositionedMap.putIfAbsent(Character.getNumericValue(encoded.charAt(5)), encoded.charAt(6));
                }
            }
        }

        for(int i = 0; i < 8; i++){
            passwordPositioned += passwordPositionedMap.get(i);
        }

        System.out.println(passwordSimple);
        System.out.println(passwordPositioned);
    }

    public static String MD5(String toEncode) {
        String encoded = "";
        try {
            encoded = DatatypeConverter.printHexBinary(
                    MessageDigest.getInstance("MD5").digest(toEncode.getBytes("UTF-8")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encoded;
    }
}
