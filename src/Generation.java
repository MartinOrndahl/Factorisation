import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by ornda on 2016-11-16.
 */
public class Generation {
    private ArrayList<BigInteger> factorbase;
    private BigInteger N;
    private int B;
    private int L;

    public Generation(BigInteger n, int b, int l) {
        this.N = n;
        this.B = b;
        this.L = l;
        factorbase(BigInteger.valueOf(b));
    }


    /**
     * FACTORBASE FACTORBASE FACTORBASE FACTORBASE FACTORBASE
     **/
    private void factorbase(BigInteger bsmooth) {
        System.out.println("Generating factorbase " + System.currentTimeMillis());
        ArrayList<BigInteger> factorbase = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("prim_2_24.txt"));
            while (sc.hasNextBigInteger() && bsmooth.compareTo(BigInteger.valueOf(factorbase.size())) > 0) {
                factorbase.add(sc.nextBigInteger());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.factorbase = factorbase;
        System.out.println("Size of factorbase: " + factorbase.size());
        System.out.println("Largest prime in factorbase: " + factorbase.get(factorbase.size() - 1));
    }

    public ArrayList<BigInteger> getFactorbase() {
        return factorbase;
    }


    /**
     * BSMOOTH_NBRS BSMOOTH_NBRS BSMOOTH_NBRS BSMOOTH_NBRS
     **/
    public HashMap<Integer, Element> smoothNbrs() {
        System.out.println("Generating smoothnumbers " + System.currentTimeMillis());
        HashMap<Integer, Element> smoothNbrs = new HashMap<>();

        //empty map
        TreeMap<BigInteger, BigInteger> emptyMap = new TreeMap<>();
        for (BigInteger b : factorbase) {
            emptyMap.put(b, BigInteger.ZERO);
        }
        System.out.println("Size of emptyMap: " + emptyMap.size());
        int position = 0;
        for (int k = 1; smoothNbrs.size() < L; k++) {
            for (int j = 1; j < 1000 && smoothNbrs.size() < L; j++) {

                BigInteger r;
                r = BigInteger.valueOf(k).multiply(N);
                r = MathOperations.squareRoot(r);
                r = r.add(BigInteger.valueOf(j));

                TreeMap<BigInteger, BigInteger> primefactors = MathOperations.primefactors(r, N, factorbase);
                boolean crash = false;
                if (primefactors != null) {
                    /**CHECK AV DUBLETTER!**/
                    for(Map.Entry<Integer, Element> item : smoothNbrs.entrySet()){
                        if(item.getValue().getFactors().entrySet().equals(primefactors.entrySet())){
                            crash = true;
                            break;
                        }
                    }
                    if(!crash){
                        smoothNbrs.put(position, new Element(r, primefactors));
                        position++;
                    }

                }
            }
        }
        System.out.println("Generating smoothnumbers - DONE " + System.currentTimeMillis());
        System.out.println("Number of Bsmooth nrbs " + smoothNbrs.size());
        return smoothNbrs;
    }


}
