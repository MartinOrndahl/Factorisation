import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by ornda on 2016-11-16.
 */
public class Main {
    private static BigInteger N = new BigInteger("208863203872858491183629");
    private static int B = 2048;
    private static int L = B + 10;
    public static long startTime;

    public static void main(String[] args){
        startTime = System.currentTimeMillis();
        Generation g = new Generation(N, B, L);
        HashMap<Integer, Element> smoothNbrs = g.smoothNbrs();
        Matrix m = new Matrix(N, B,smoothNbrs);
        System.out.println();
        long endTime = System.currentTimeMillis();

    }
}
