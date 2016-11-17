import java.math.BigInteger;
import java.util.TreeMap;

/**
 * Created by ornda on 2016-11-16.
 */
public class Element {
    private BigInteger number;
    private TreeMap<BigInteger, BigInteger> factors;


    public Element(BigInteger number, TreeMap<BigInteger, BigInteger> factors) {
        this.number = number;
        this.factors = factors;
    }

    public BigInteger getNumber() {
        return number;
    }


    public TreeMap<BigInteger, BigInteger> getFactors() {
        return factors;
    }
}
