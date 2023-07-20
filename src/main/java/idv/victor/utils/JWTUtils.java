package idv.victor.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * JWT 操作
 */
@Component
public class JWTUtils {

    /**
     * JWT Secret
     */
    @Value("${jwt.secret}")
    private String secret ;
    /**
     * 取得 Authentication token
     * @param request http request
     * @return Authentication
     */
    public Authentication getTokenFromRequest (HttpServletRequest request){
        String jwtStr = request.getHeader("Authorization").replace("Bearer ","");
        if(ObjectUtils.isEmpty(jwtStr)){
            return null;
        }
        Claims claims = Jwts.parser()
                            // 驗證
                            .setSigningKey(generateSecretKey())
                            // 去掉 Bearer
                            .parseClaimsJws(jwtStr)
                            .getBody();
        Authentication token = new UsernamePasswordAuthenticationToken(claims.get("userName"), null,new ArrayList<>());
        return token;
    }

    public Claims getClaimFromRequest (HttpServletRequest request){
        String jwtStr = request.getHeader("Authorization").replace("Bearer ","");
        if(ObjectUtils.isEmpty(jwtStr)){
            return null;
        }
        Claims claims = Jwts.parser()
                            // 驗證
                            .setSigningKey(generateSecretKey())
                            // 去掉 Bearer
                            .parseClaimsJws(jwtStr)
                            .getBody();
        return claims;
    }

    /**
     * 建立 JWT token
     * @param uuid 使用者類型
     * @param userId 帳號名稱
     * @return token
     */
    public String createToken (String uuid, String userId, int expiration){
        HashMap<String, String> claim = new HashMap<>();
        claim.put("userName", userId);
        claim.put("role",uuid);
        return createToken(claim, uuid, expiration);
    }

    /**
     * 建立 JWT token
     * @param claims payload
     * @param uuid 身份別
     * @param expireHour 有效期限(小時)
     * @return
     */
    public String createToken (HashMap<String,String> claims,String uuid, int expireHour){
        JwtBuilder jwtBuilder = Jwts.builder();

        if (ObjectUtils.isNotEmpty(claims)) {
            jwtBuilder.setClaims(claims);
        }

        long nowMillis = System.currentTimeMillis();

        if (ObjectUtils.isNotEmpty(expireHour)) {
            jwtBuilder.setExpiration(new Date(nowMillis + expireHour * 60 * 1000));
        } else {
            jwtBuilder.setExpiration(new Date(nowMillis + 1000 * 60 * 60 * 1));
        }

        return jwtBuilder.setId(UUID.randomUUID().toString())
                         .setSubject(uuid)
                         .setIssuedAt(new Date(nowMillis))
                         .signWith(generateSecretKey(), SignatureAlgorithm.HS256)
                         .compact();
    }
    /**
     * 取得secretKey
     *
     * @return secretKey
     */
    private SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }



}
