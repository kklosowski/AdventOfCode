/**
 * Created by Eter on 04/12/2016.
 */
public final class Util {

    //Ex. -13%64 == 51
    public static int nonNegativeModulo(int num, int div){
        return (num % div) + (num < 0 ? div : 0);
    }
 
    public static int parseTurn(char turn){
        if (turn == 'L') {
            return -1;
        } else if (turn == 'R') {
            return 1;
        } else {
            return 0;
        }
    }
}
