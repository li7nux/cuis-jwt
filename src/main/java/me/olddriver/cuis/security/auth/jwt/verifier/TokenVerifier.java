package me.olddriver.cuis.security.auth.jwt.verifier;

/**
 * @author <a href="mailto:Li7nux@gmail.com">Li7nux</a>
 * Date: 2018-01-20
 * Description:
 */

public interface TokenVerifier {
    public boolean verify(String jti);
}
