import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by ornda on 2016-11-16.
 */
public class Matrix {
    private HashMap<Integer, Element> smoothNbrs;
    private BigInteger N;
    private int B;

    public Matrix(BigInteger n, int b, HashMap<Integer, Element> smoothNbrs) {
        this.N = n;
        this.B = b;
        this.smoothNbrs = smoothNbrs;

        prepare();
        solve();
    }

    private void prepare() {

        try {
            PrintWriter pw = new PrintWriter("prepared.txt", "UTF-8");
            pw.print(smoothNbrs.size() + " " + B + "\r\n");

            for (Map.Entry<Integer, Element> entry : smoothNbrs.entrySet()) {
                TreeMap<BigInteger, BigInteger> smoothNbrsElement = entry.getValue().getFactors();

                for (Map.Entry<BigInteger, BigInteger> item : smoothNbrsElement.entrySet()) {
                    pw.print(item.getValue() + " ");
                }
                pw.print("\r\n");
            }
            pw.close();

            new File("solved.txt").createNewFile();

            Process proc;
            proc = Runtime.getRuntime().exec("GaussBin.exe prepared.txt solved.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void solve() {


        try {
            Scanner sc = new Scanner(new FileReader(new File("solved.txt")));
            int nbrCandidates = Integer.parseInt(sc.nextLine());
            int counter = 0;
            while (sc.hasNextLine()) {
                TreeMap<BigInteger, BigInteger> emptyMap = new TreeMap<>();
                BigInteger left = BigInteger.ONE;
                String line = sc.nextLine();
                String[] vector = line.split(" ");
                for (int i = 0; i < vector.length; i++) {
                    if (vector[i].equals("1")) {
                        left = left.multiply(smoothNbrs.get(i).getNumber());
                        TreeMap<BigInteger, BigInteger> temp = smoothNbrs.get(i).getFactors();
                        for (Map.Entry<BigInteger, BigInteger> entry : temp.entrySet()) {
                            if (!entry.getValue().equals(BigInteger.ZERO)) {
                                if (emptyMap.get(entry.getKey()) == null) {
                                    emptyMap.put(entry.getKey(), entry.getValue());
                                } else {
                                    emptyMap.put(entry.getKey(), emptyMap.get(entry.getKey()).add(entry.getValue()));
                                }
                            }
                        }
                    }
                }

                BigInteger right = BigInteger.ONE;
                for (Map.Entry<BigInteger, BigInteger> item : emptyMap.entrySet()) {
                    BigInteger factor = item.getKey().pow(item.getValue().intValue());
                    right = right.multiply(factor);
                }
                //
                right = MathOperations.squareRoot(right).mod(N);
                left = MathOperations.squareRoot(left).mod(N);

                System.out.println("Testing candidate: " + counter + " /" + nbrCandidates);
                BigInteger value = right.subtract(left).abs().gcd(N);
                if (!value.equals(BigInteger.ONE) && !value.equals(N)) {
                    System.out.println("Solutions found: " + value);
                    System.exit(1);
                }
                counter++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
