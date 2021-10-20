import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
    static ArrayList<BigInteger> primes = new ArrayList<>();
    static long sysTime = System.currentTimeMillis();
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
//        System.out.println("Where using the java probability method is a prime? : " + biggestPrime.isProbablePrime(10));
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - sysTime));
    }

    // Find a prime using fermat's little theorem where:
    // If p is a prime then for any a > 1, a^(p-1) = 1 mod(p)
    // However this does not guarantee that p is always a prime as there are numbers which give the same result but are not prime.
    // So after check using the definition of primes if it's actually a prime
    public static void FindProbablyPrime(int upToNumber) {
        // take "2" as a seperate case as the only even prime
        primes.add(new BigInteger("2"));
        // Define the base to take the exponent of
        BigInteger a = new BigInteger("2");
        // Only consider odd numbers starting from 3
        for (int p = 3 ; p < upToNumber; p += 2) {
            BigInteger prime = new BigInteger("" + p);
            BigInteger exp = new BigInteger("" + (p - 1));
            if (a.modPow(exp, prime).equals(new BigInteger("1"))) CheckProbablyPrime(p);
        }
    }

    // Use the definition of primes where a prime cannot be divided by any numbers other than 1 or itself
    // And make use of the fact that this has to be only checked up to SQRT(prime), as factors will come in at least pairs and after the SQRT the other side of any possible pair will have been checked
    public static void CheckProbablyPrime(int probablyPrime) {
        // rule out even numbers
        if (probablyPrime % 2 == 0) return;
        // check all odd numbers
        // (mathemathically checking only primes would be more efficient, however keeping track of and accessing a list of primes makes it less efficient than this)
        for (int i = 3; i < Math.sqrt(probablyPrime); i += 2) {
            if (probablyPrime % i == 0) return;
        }
        // If none of the numbers <= SQRT(probablePrime) divide probablePrime then it is a prime
        primes.add(new BigInteger("" + probablyPrime));
    }
}
