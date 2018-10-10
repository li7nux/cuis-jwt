package me.olddriver.cuis.controller;

import me.olddriver.cuis.security.model.role.Role;
import me.olddriver.cuis.security.auth.jwt.extractor.TokenExtractor;
import me.olddriver.cuis.security.auth.jwt.verifier.TokenVerifier;
import me.olddriver.cuis.security.config.JwtSettings;
import me.olddriver.cuis.security.config.WebSecurityConfig;
import me.olddriver.cuis.security.exception.InvalidJwtToken;
import me.olddriver.cuis.security.model.common.UserContext;
import me.olddriver.cuis.security.model.token.JwtToken;
import me.olddriver.cuis.security.model.token.JwtTokenFactory;
import me.olddriver.cuis.security.model.token.RawAccessJwtToken;
import me.olddriver.cuis.security.model.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * @author <a href="mailto:Li7nux@gmail.com">Li7nux</a>
 * Date: 2018-01-20
 * Description:
 */

@RestController
public class RefreshTokenController {

    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private TokenVerifier tokenVerifier;
    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @RequestMapping(value = "/api/auth/token", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(InvalidJwtToken::new);

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        UserContext userContext = UserContext.create(subject, Collections.singletonList(new SimpleGrantedAuthority(Role.PREMIUM_MEMBER.authority())));

        return tokenFactory.createAccessJwtToken(userContext);
    }

}
