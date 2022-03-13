package cn.edu.ecnu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    //签名私钥
    public final static String KEY = "userLogin";

    public final static String ISS = "pkm";
    //签名失效时间
    public final static Long FAILURE_TIME = 3600000L;

    public final static String TOKEN_PREFIX = "Bearer ";

    public final static String TOKEN_HEADER = "Authorization";
    // 添加角色的 key
    public final static String ROLE_CLAIMS = "rol";

    /**
     * 设置认证 token
     */
    public static String createJwt(String subject, String role) {
        //1、设置失效时间
        long now = System.currentTimeMillis();  //毫秒
        long exp = now + FAILURE_TIME;

        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        //setSubject 代表设置这个 JWT 的主体，即它的所有人的唯一标识
        //setIssueAt 设置签发时间为当前时间
        //2、创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder()
                //设置签名防止篡改
                .signWith(SignatureAlgorithm.HS256, KEY)
                .setIssuedAt(new Date())
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(subject)
                .setExpiration(new Date(exp));

        //3、创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析 token,通过密钥反过来通过 token 获取原始的载荷，载荷中存有原本
     * 放入的各种信息，如 subject 可以直接通过 getSubject() 获得
     * @param token
     * @return
     */
    public static Claims parseJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static String getUsername(String token) {
        return parseJwt(token).getSubject();
    }

    /*查看 token 载荷中的 过期时间是否早于当前时间*/
    public static boolean isExpiration(String token) {
        return parseJwt(token).getExpiration().before(new Date());
    }

    public static String getUserRole(String token) {
        return (String) parseJwt(token).get(ROLE_CLAIMS);
    }
}
