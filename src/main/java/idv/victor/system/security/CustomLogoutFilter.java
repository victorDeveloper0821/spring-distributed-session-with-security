package idv.victor.system.security;

import idv.victor.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (request.getRequestURI().contains("logout") && ObjectUtils.isNotEmpty(authentication)) {
            try {
                Claims claims = jwtUtils.getClaimFromRequest(request);
                String sub = claims.getSubject();
                System.out.println("logout");
                filterChain.doFilter(request, response);
                // clear authentication in context to LogOut
                SecurityContextHolder.getContext().setAuthentication(null);
            } catch (Exception e) {
                resolver.resolveException(request, response, null, e);
            }
        } else {
            doFilter(request, response, filterChain);
        }

    }
}
