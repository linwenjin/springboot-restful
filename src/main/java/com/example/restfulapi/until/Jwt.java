package com.example.restfulapi.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.middleware.BaseException;

import java.util.*;

/**
 * Created by Administrator on 2019/10/15 0015.
 */
public class Jwt {
    /**
     * 盐
     */
    private static String salt = "123";

    /**
     * jwt生成token
     * @param loginUsername 登陆用户名，验证的时候，判断菜单权限
     * @return
     */
    public static String getJwtToken(String loginUsername) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Algorithm algorithm = Algorithm.HMAC256(salt);
        // 当前时间
        Date sDate = new Date();
        // 过期时间
        Calendar c = Calendar.getInstance();
        c.setTime(sDate);
        // 一天过期
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date eDate = c.getTime();

        String token = JWT.create()
                .withHeader(map)
                /* 设置 载荷 Payload */
//                .withClaim("username", loginUsername)
                .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
                .withSubject("Login Verification") // 签名的主题
//                 .withNotBefore(new Date()) // 该jwt都是不可用的时间
                .withAudience(loginUsername) // 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(sDate) // 生成签名的时间
                .withExpiresAt(eDate) // 签名过期的时间
                /* 签名 Signature */
                .sign(algorithm);

        return token;
    }

    public static String JwtTokenVerify(String token) {
        // 构建密钥信息
        Algorithm algorithm = Algorithm.HMAC256(salt);
        // 通过密钥信息和签名的发布者的信息生成JWTVerifier (JWT验证类)
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE").build();
        try {
            // 通过JWTVerifier 的verify获取 token中的信息
            DecodedJWT jwt = verifier.verify(token);
            // 获取自定jwt内容
            List audience = jwt.getAudience();

            return audience.get(0).toString();
        } catch (Exception e) {
            throw BaseException.out(ResponseCodeEnum.ILLEGAL_LOGIN, e.getMessage());
        }


    }
}
