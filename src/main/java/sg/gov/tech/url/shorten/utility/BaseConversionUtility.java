package sg.gov.tech.url.shorten.utility;

public class BaseConversionUtility {

    static final char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String encodeTo62(int base10) {
        StringBuilder encodedString = new StringBuilder();
        while (base10 != 0) {
            encodedString.append(map[base10 % 62]);
            base10 /= 62;
        }

        return encodedString.reverse().toString();
    }

    public static String decodeTo10(char[] base62) {
        String mapString = String.valueOf(map);
        int base10 = 0;
        for (int i = base62.length-1, power = 0; i >= 0; i--) {
            base10 += mapString.indexOf(base62[i]) * Math.pow(62, power++);
        }

        return String.valueOf(base10);
    }

}
