import java.math.BigInteger;

public class Base58Prefix {

    public static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static final BigInteger BASE = BigInteger.valueOf(ALPHABET.length());

    public static final String PREFIX = "Mo";

    public static final int CONTENT_LENGTH = 33;

    public static void main(String[] args) {
        BigInteger sOne = BigInteger.valueOf(ALPHABET.indexOf(PREFIX.toCharArray()[0]));
        BigInteger sTwo = BigInteger.valueOf(ALPHABET.indexOf(PREFIX.toCharArray()[1]));
        System.out.println(sOne);
        System.out.println(sTwo);
        // (20x58+46) x 58^33 to (20x58+46+1) x 58^33 - 1
        // (sOnex58+sTwo) x 58^length to (sOnex58+sTwo+1) x 58^length - 1
        BigInteger leftInterval = sOne.multiply(BASE).add(sTwo).multiply(BASE.pow(CONTENT_LENGTH));
        BigInteger rightInterval = sOne.multiply(BASE).add(sTwo).add(BigInteger.ONE).multiply(BASE.pow(CONTENT_LENGTH)).subtract(BigInteger.ONE);
        System.out.println(leftInterval.toString(16));
        System.out.println(rightInterval.toString(16));
        String leftEncode = Base58.encode(leftInterval.toByteArray());
        String rightEncode = Base58.encode(rightInterval.toByteArray());
        System.out.println(leftEncode);
        System.out.println(rightEncode);
    }

}

