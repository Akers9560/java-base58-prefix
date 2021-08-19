import java.math.BigInteger;

public class Tools {

    public static String toHexString(BigInteger value) {
        if (value == null) {
            System.out.println("\033[31m" + "value不能为空");
        }
        String hex = value.toString(16);
        return hex.length() % 2 == 0 ? hex : "0" + hex;
    }


    public static String zeros(int n) {
        return new String(new char[n]).replace("\0", "0");
    }

}
