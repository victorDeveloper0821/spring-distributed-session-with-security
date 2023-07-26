package idv.victor.system.security;

import idv.victor.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Call API 認證 JWT 是否有效
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    /**
     * jwt 工具類
     */
    @Autowired
    private JWTUtils jwtUtils;

    /**
     * 取得 userData
     */
    private UserDetailsService userDetailsService;

    /**
     * spring異常處理元件
     */
    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver resolver;

    /**
     * JWT 認證所需的過程
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getRequestURI().contains("/user/")){
            filterChain.doFilter(request, response);
        }else{

        try {
            // 取得 JWT 內容
            Claims claims = jwtUtils.getClaimFromRequest(request);

            // JWT 是否 expire


            // 有效則取得 user data
            String userName = claims.get("userName").toString();

            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, userDetails.getPassword(),
                                                                                    userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
        }

    }
}
