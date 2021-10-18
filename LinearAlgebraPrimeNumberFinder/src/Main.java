import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
    static ArrayList<BigInteger> primes = new ArrayList<>();
    public static void main(String[] args) {
        // for the second number in the pow
        FindProbablyPrime((int)Math.pow(2, 10));
        BigInteger biggestPrime = new BigInteger("1");
        for (int i = 0; i < primes.size(); i++) {
            System.out.println("#" + i + ": " + primes.get(i));
            biggestPrime = biggestPrime.multiply(primes.get(i));
        }
        System.out.println("So the biggest known prime from this set is:");
        biggestPrime = biggestPrime.add(new BigInteger("1"));
        System.out.println(biggestPrime.toString());
        System.out.println("Where using the java probability method is a prime? : " + biggestPrime.isProbablePrime(10));


    }

    public static void FindProbablyPrime(int upToNumber) {
        for (int p = 2 ; p < upToNumber; p++) {
            // If p is a prime then for any a > 1, a^(p-1) = 1 mod(p)
            // (Though this does not guarantee p being a prime)
            BigInteger a2;
            if (p != 2) a2 = new BigInteger("2");
            else a2 = new BigInteger("3");

            BigInteger prime = new BigInteger("" + p);
            BigInteger exp = new BigInteger("" + (p - 1));
            if (a2.modPow(exp, prime).equals(new BigInteger("1"))) CheckProbablyPrime(p);
        }
    }

    public static void CheckProbablyPrime(int probablyPrime) {
        for (int i = 2; i < Math.sqrt(probablyPrime); i++) {
            // If probably is precisely divided by any number up to SQRT(probablePrime) then it's not a prime.
            if (probablyPrime % i == 0) return;
        }
        // If none of the numbers <= SQRT(probablePrime) divide probablePrime then it is a prime
        primes.add(new BigInteger("" + probablyPrime));
    }
}
