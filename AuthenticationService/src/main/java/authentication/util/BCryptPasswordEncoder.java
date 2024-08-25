package authentication.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder {

    // Private constructor to prevent instantiation
    private BCryptPasswordEncoder() {}

    // Static inner class - inner classes are not loaded until they are referenced
    private static class SingletonHelper {
        // The inner class is loaded only when getInstance() is called, ensuring lazy loading
        private static final BCryptPasswordEncoder INSTANCE = new BCryptPasswordEncoder();
    }

    // Global access point to get the instance
    public static BCryptPasswordEncoder getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Hashing method
    public String hashpw(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt(10));
    }

    // Password checking method
    public boolean checkpw(String pw, String hashedPw) {
        return BCrypt.checkpw(pw, hashedPw);
    }
}
