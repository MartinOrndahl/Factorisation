import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by ornda on 2016-11-16.
 */
public class Main {
    private static BigInteger N = new BigInteger("392742364277");
    private static int B = 1024;
    private static int L = B + 10;

    public static void main(String[] args){
        Generation g = new Generation(N, B, L);
        HashMap<Integer, Element> smoothNbrs = g.smoothNbrs();
        Matrix m = new Matrix(N, B,smoothNbrs);

    }
}
