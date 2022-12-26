package es.uniovi.apuntesunioviapp.mocks;

import java.util.Random;

/**
 * Class contains methods to generate random data
 */
public class RandomMethods {
    private static final String CODE_LETTER = "TRWAGMYFPDXBNJZSQVHLCKET";

    /**
     * Generate a random username
     */
    public static String randomUsername() {
        return "uo" + randomInteger(10_000, 99_999);
    }

    /**
     * Generate a random dni
     */
    public static String dni() {
        var numberDni = randomInteger(10_000_000, 99_999_999);
        return "" + numberDni + CODE_LETTER.charAt(numberDni % 23);
    }

    /**
     * Generate a random nie
     */
    public static String nie() {
        var numberDni = randomInteger(1_000_000, 29_999_999);
        var letterDni = CODE_LETTER.charAt(numberDni % 23);
        if (numberDni < 10_000_000) {
            return "X" + numberDni + letterDni;
        }
        var stringDni = "" + numberDni;
        return stringDni.charAt(0) == '1' ?
            "Y" + stringDni.substring(1) + letterDni :
            "Z" + stringDni.substring(1) + letterDni;
    }

    private static int randomInteger(int min, int max) {
        int limitMax = max;
        return (int) (new Random().nextFloat() * (++limitMax - min) + min);
    }
}
