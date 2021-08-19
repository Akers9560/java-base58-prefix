import java.math.BigInteger;

public class Base58Prefix {

    public static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static final BigInteger BASE = BigInteger.valueOf(ALPHABET.length());

    public static final String PREFIX = "Akers";

    public static final int CONTENT_LENGTH = 33;

    public static void main(String[] args) {

        char[] chars = PREFIX.toCharArray();
        BigInteger base = BigInteger.ZERO;
        for (int i = 0; i < chars.length; i++) {
            base = base.add(BigInteger.valueOf(ALPHABET.indexOf(chars[i])).multiply(BASE.pow(chars.length - 1 - i)));
        }
        // (20x58+46) x 58^33 to (20x58+46+1) x 58^33 - 1
        // (sOnex58+sTwo) x 58^length to (sOnex58+sTwo+1) x 58^length - 1
        BigInteger leftInterval = base.multiply(BASE.pow(CONTENT_LENGTH));
        BigInteger rightInterval = base.add(BigInteger.ONE).multiply(BASE.pow(CONTENT_LENGTH)).subtract(BigInteger.ONE);

        System.out.println(leftInterval);
        System.out.println(rightInterval);

        String leftIntervalHex = Tools.toHexString(leftInterval);
        String rightIntervalHex = Tools.toHexString(rightInterval);
        System.out.println(leftIntervalHex);
        System.out.println(rightIntervalHex);


        String leftEncode = Base58.encode(leftInterval.toByteArray());
        String rightEncode = Base58.encode(rightInterval.toByteArray());
        System.out.println(leftEncode);
        System.out.println(rightEncode);

        String basePrefix = "";
        String leftPrefix = "";
        String rightPrefix = "";
        char[] chars1 = leftIntervalHex.toCharArray();
        char[] chars2 = rightIntervalHex.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[i]) {
                basePrefix += chars1[i];
            } else {
                leftPrefix += basePrefix + chars1[i];
                rightPrefix += basePrefix + chars2[i];
                break;
            }
        }

        System.out.println(leftPrefix);
        System.out.println(rightPrefix);
        String hexPrefix = Tools.toHexString(new BigInteger(leftPrefix, 16).add(new BigInteger(rightPrefix, 16)).divide(BigInteger.TWO));
        System.out.println(hexPrefix);

        String key = hexPrefix + Tools.zeros(leftIntervalHex.length() - leftPrefix.length());
        String encode = Base58.encode(new BigInteger(key, 16).toByteArray());
        System.out.println(key);
    }

}

