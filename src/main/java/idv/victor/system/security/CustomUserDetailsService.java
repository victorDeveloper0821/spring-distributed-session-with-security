package idv.victor.system.security;

import idv.victor.web.auth.domain.CustomUserDetails;
import idv.victor.web.auth.domain.entity.User;
import idv.victor.web.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 取得 user 相關資訊
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails details = new CustomUserDetails();
        User user = userService.findUserByUsername(username);
        details.setUser(user);
        return details;
    }
}
