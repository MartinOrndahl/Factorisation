import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by ornda on 2016-11-16.
 */
public class MathOperations {


    /**
     * SQUREROOT SQUREROOT SQUREROOT SQUREROOT SQUREROOT
     **/
    public static BigInteger squareRoot(BigInteger x) {
        BigInteger right = x, left = BigInteger.ZERO, mid;
        while (right.subtract(left).compareTo(BigInteger.ONE) > 0) {
            mid = (right.add(left)).shiftRight(1);
            if (mid.multiply(mid).compareTo(x) > 0)
                right = mid;
            else
                left = mid;
        }
        return left;
    }

    /**
     * PRIMEFACTORS PRIMEFACTORS PRIMEFACTORS PRIMEFACTORS PRIMEFACTORS PRIMEFACTORS
     **/

    public static TreeMap<BigInteger, BigInteger> primefactors(BigInteger r, int b, ArrayList<BigInteger> factorbase, TreeMap<BigInteger, BigInteger> emptyMap) {
        TreeMap<BigInteger, BigInteger> primeFactors = new TreeMap<>(emptyMap);

       for(BigInteger factor : factorbase){
           BigInteger exponent = BigInteger.ZERO;
           while(r.mod(factor).equals(BigInteger.ZERO)){
               r = r.divide(factor);
               exponent = exponent.add(BigInteger.ONE);
           }
           primeFactors.put(factor, exponent);
       }
       if(r.equals(BigInteger.ONE)){
          // System.out.println("R FOUND!");
           return primeFactors;
       }
       return null;
    }

}
