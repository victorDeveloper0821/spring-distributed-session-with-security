package idv.victor.security;

import idv.victor.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 自定義 logout filter
 */
@Component
public class CustomLogoutFilter extends OncePerRequestFilter {

    /**
     * JWT 工具
     */
    @Autowired
    private JWTUtils jwtUtils;

    /**
     * spring異常處理元件
     */
    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            Claims claims = jwtUtils.getClaimFromRequest(request);
            String sub = claims.getSubject();
            System.out.println("logout");
            filterChain.doFilter(request,response);
        }catch (Exception e){
            resolver.resolveException(request,response,null,e);
        }


    }
}
