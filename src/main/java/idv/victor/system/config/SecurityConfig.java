package idv.victor.system.config;

import idv.victor.system.security.CustomLogoutFilter;
import idv.victor.system.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * Spring security 設定
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * user 登出 filter
     */
    @Autowired
    private CustomLogoutFilter customLogoutFilter;

    /**
     * Jwt 驗證
     */
    @Autowired
    private JwtFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * filter chain setting with http builder
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/**",
                         "/js/**",
                         "/css/**",
                         "/img/**",
                         "/webjars/**")
            .permitAll()
            .antMatchers("/home/**").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/dummy/**").authenticated()
            .and()
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(customLogoutFilter, LogoutFilter.class)
            .sessionManagement().sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS);
        return http.build();
    }


}
