package Utils;

import io.quarkus.elytron.security.common.BcryptUtil;

public  class HashingUtils {
    public static String hashPassword(String password) {
        return BcryptUtil.bcryptHash(password);
    }
    public static boolean checkPassword(String plaintextPassword, String hashedPassword) {
        return BcryptUtil.matches(plaintextPassword, hashedPassword);
    }
}
