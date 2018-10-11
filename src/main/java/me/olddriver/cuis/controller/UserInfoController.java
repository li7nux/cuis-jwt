package me.olddriver.cuis.controller;

import me.olddriver.cuis.security.auth.JwtAuthenticationToken;
import me.olddriver.cuis.security.model.common.UserContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Li7nux@gmail.com">Li7nux</a>
 * Date: 2018-01-20
 * Description:
 */

@RestController
public class UserInfoController {

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    public @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }

    @RequestMapping(value = "/api/skip/", method = RequestMethod.GET)
    public @ResponseBody
    Map unAuthRequest(JwtAuthenticationToken token) {
        return new HashMap<String, String>(1) {{
            put("key", "Hello world!");
        }};
    }

}
