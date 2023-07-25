package idv.victor.web.auth.service.impl;

import idv.victor.utils.JWTUtils;
import idv.victor.web.auth.domain.dto.FirstLoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginResDTO;
import idv.victor.web.auth.domain.entity.User;
import idv.victor.web.auth.service.AuthService;
import idv.victor.web.auth.service.UserService;
import idv.victor.web.enums.ErrorCodeEnum;
import idv.victor.web.exception.BussinessException;
import io.jsonwebtoken.Claims;
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

import javax.servlet.http.HttpServletRequest;

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

    /**
     * userService - 搜尋DB
     */
    @Autowired
    private UserService userService;

    @Value("${api.env}")
    private String apiEnv;

    /**
     * user 登入
     *
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
        } catch (BussinessException be) {
            throw be;
        } catch (Exception e) {
            // 其他的錯誤
            throw new BussinessException(ErrorCodeEnum.E9999);
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        Claims claims = jwtUtils.getClaimFromRequest(request);
        String username = claims.getSubject();
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * 註冊使用者
     *
     * @param firstLoginReqDTO
     * @return
     * @throws Exception 建立會員錯誤
     */
    @Override
    public void firstLogin(FirstLoginReqDTO firstLoginReqDTO) throws Exception {
        // check user 有無相同姓名、姓氏
        boolean duplicateRealName =
                userService.findDuplicateName(firstLoginReqDTO.getFirstName(), firstLoginReqDTO.getLastName());
        if (duplicateRealName) {
            throw new BussinessException(ErrorCodeEnum.E0004);
        }
        // check 是否有重複 email


        // check userName 有無重複
        boolean duplicateUserName = userService.findDuplicateUserName(firstLoginReqDTO.getUserName());
        if (duplicateUserName) {
            throw new BussinessException(ErrorCodeEnum.E0005);
        }
        // check 密碼強度 (optional)


        // 儲存到 user
        userService.save(User.builder().email(firstLoginReqDTO.getEmail()).userName(firstLoginReqDTO.getUserName())
                             .password(firstLoginReqDTO.getWhisper()).firstName(firstLoginReqDTO.getFirstName())
                             .lastName(firstLoginReqDTO.getLastName())
                             .memberType((short) 0).build());

    }


}
