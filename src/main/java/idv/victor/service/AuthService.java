package idv.victor.service;

import idv.victor.domain.dto.LoginReqDTO;
import idv.victor.domain.dto.LoginResDTO;
import idv.victor.security.CustomUserProvider;
import idv.victor.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 將 user 驗證的相關邏輯寫在這邊
 */
@Service
public class AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    /**
     * user 登入
     * @param loginReqDTO 登入資訊
     */
    public LoginResDTO login (LoginReqDTO loginReqDTO){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginReqDTO.getUserName(), loginReqDTO.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginReqDTO.getUserName());
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginReqDTO.getUserName(),loginReqDTO.getPassword(),userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return LoginResDTO.builder().jwtToken(jwtUtils.createToken("member", loginReqDTO.getUserName(), 3)).build();
        }catch (AuthenticationException exception){
            // 認證失敗
            throw exception;
        }catch (Exception e){
            // 其他的錯誤
            throw e;
        }
    }



}
