package Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ornda on 2016-11-16.
 */
public class Test {
    public static List<Integer> primeFactors(int number) {
        int n = number;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        System.out.println("Primefactors of 225");
        for (Integer integer : primeFactors(225)) {
            System.out.println(integer);
        }
        System.out.println("Primefactors of 3");
        for (Integer integer : primeFactors(3)) {
            System.out.println(integer);
        }
        System.out.println("Primefactors of 32");
        for (Integer integer : primeFactors(32)) {
            System.out.println(integer);
        }
    }
}