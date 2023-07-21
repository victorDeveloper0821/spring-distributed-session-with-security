package idv.victor.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 提供 user 帳號密碼認證的相關 token
 */
@Component
public class CustomUserProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService detailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails details = detailsService.loadUserByUsername(userName);
        // 在此進行用戶的驗證邏輯，例如密碼比對等
        if (passwordIsValid(password, details.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userName, password, details.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    private boolean passwordIsValid(String rawPassword, String encodedPassword) {
        // 密碼比對邏輯，可使用Spring Security提供的PasswordEncoder來安全地比對
        // 這裡只是一個示例，實際上應使用PasswordEncoder
        return rawPassword.equals(encodedPassword);
    }
}