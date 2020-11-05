package com.ct.common.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ct.common.bean.User;
import com.ct.common.exception.Assert;
import com.ct.common.exception.ErrorCode;
import org.springframework.stereotype.Service;


/**
 * The type User token service.
 *
 * @author chen.cheng
 */
@Service
public class UserTokenService {
    /**
     * Gets token.
     *
     * @param user the user
     * @return the token
     * @author chen.cheng
     */
    public String getToken(User user) {
        Assert.notNull(user, ErrorCode.PARAM_ERROR);
        String token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     * @author chen.cheng
     */
    public User findUserById(String userId) {
        return new User();
    }
}