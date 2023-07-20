package idv.victor.web.auth.service.impl;

import idv.victor.web.auth.domain.dto.LoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginResDTO;
import idv.victor.utils.JWTUtils;
import idv.victor.web.auth.service.AuthService;
import idv.victor.web.enums.ErrorCodeEnum;
import idv.victor.web.exception.BussinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 將 user 驗證的相關邏輯寫在這邊
 */
@Service
public class MemberServiceImpl implements AuthService {

    /**
     * 取得 User 資料
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * JwtUtils - 建立 web token
     */
    @Autowired
    private JWTUtils jwtUtils;

    @Value("${api.env}")
    private String apiEnv ;

    /**
     * user 登入
     * @param loginReqDTO 登入資訊
     * @return LoginResDTO 登入成功的 JSON
     * @Exception 拋出的錯誤
     */
    public LoginResDTO login (LoginReqDTO loginReqDTO) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginReqDTO.getUserName(), loginReqDTO.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginReqDTO.getUserName());
            // InMemory 需要進行比對, VM 則不必
            if(!StringUtils.equals(loginReqDTO.getPassword(),userDetails.getPassword()) && StringUtils.equalsIgnoreCase("local",apiEnv)){
                throw new BussinessException(ErrorCodeEnum.E0001);
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginReqDTO.getUserName(),loginReqDTO.getPassword(),userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return LoginResDTO.builder().jwtToken(jwtUtils.createToken("member", loginReqDTO.getUserName(), 3)).build();
        }catch (AuthenticationException exception){
            // 認證失敗
            throw exception;
        }catch (BussinessException be){
            throw be;
        }catch (Exception e){
            // 其他的錯誤
            throw new BussinessException(ErrorCodeEnum.E9999);
        }
    }

    @Override
    public void logout() {

    }


}
