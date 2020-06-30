package com.ct.common.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ct.common.annotation.PassToken;
import com.ct.common.annotation.UserLoginToken;
import com.ct.common.bean.User;
import com.ct.common.enums.HttpCodeMessage;
import com.ct.common.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * The type Authentication interceptor.
 *
 * @author chen.cheng
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    /**
     * The User service.
     *
     * @author chen.cheng
     */
    @Autowired
    private UserTokenService userService;

    /**
     * Pre handle boolean.
     *
     * @param httpServletRequest  the http servlet request
     * @param httpServletResponse the http servlet response
     * @param object              the object
     * @return the boolean
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有pass token注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            return passToken.required();
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException(HttpCodeMessage.PARAM_IS_INVALID.getMessage());
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userService.findUserById(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    /**
     * Post handle.
     *
     * @param httpServletRequest  the http servlet request
     * @param httpServletResponse the http servlet response
     * @param o                   the o
     * @param modelAndView        the model and view
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * After completion.
     *
     * @param httpServletRequest  the http servlet request
     * @param httpServletResponse the http servlet response
     * @param o                   the o
     * @param e                   the e
     * @throws Exception the exception
     * @author chen.cheng
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}