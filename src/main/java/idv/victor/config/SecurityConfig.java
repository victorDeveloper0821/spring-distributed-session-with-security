package idv.victor.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring security 設定
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 設定 local 時使用 inMemory 的 UserDetailsService
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @ConditionalOnProperty(name = "api.env", havingValue = "local")
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(inMemoryDetailService())
                .passwordEncoder(passwordEncoder()).and().build();
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
            .and();
        return http.build();
    }

    /**
     * In memory users setting there
     * @return
     */
    @Bean
    public UserDetailsService inMemoryDetailService(){
        UserDetails user = User.builder()
                               .username("user")
                               .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                               .roles("USER")
                               .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                                .roles("USER", "ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
