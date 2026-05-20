package Utils;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public  class HashingUtils {
    @Inject
    JWTParser parser;
    public static String hashPassword(String password) {
        return BcryptUtil.bcryptHash(password);
    }
    public static boolean checkPassword(String plaintextPassword, String hashedPassword) {
        return BcryptUtil.matches(plaintextPassword, hashedPassword);
    }

    public JsonWebToken getTokenObject(String rawTokenString){
        try {
            return parser.parse(rawTokenString);
        } catch (ParseException e) {
            return null;
        }
    }
}
