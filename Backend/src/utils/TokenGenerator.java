package utils;

public class ClientTokenGenerator {
    private static final String ALPHA_NUMERIC_STRING = "0123456789";

    //generate unique token
    public static String generateToken() {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while (count < 5) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            count++;
        }
        return builder.toString();
    }
}
