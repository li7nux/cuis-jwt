package me.olddriver.cuis.security.auth.jwt.extractor;

/**
 * @author <a href="mailto:Li7nux@gmail.com">Li7nux</a>
 * Date: 2018-01-20
 * Description:
 */

public interface TokenExtractor {
    public String extract(String payload);
}
