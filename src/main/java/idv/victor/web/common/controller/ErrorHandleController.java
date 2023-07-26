package idv.victor.web.common.controller;

import idv.victor.web.common.domain.ErrorResponse;
import idv.victor.web.enums.ErrorCodeEnum;
import idv.victor.web.exception.BussinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handle API 錯誤
 */
@RestControllerAdvice
public class ErrorHandleController {

    /**
     * Spring security 驗證失敗
     *
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authenticationError() {
        ErrorResponse response = new ErrorResponse(ErrorCodeEnum.E0001);
        return ResponseEntity.status(401).body(response);
    }

    /**
     * Handle api 業務邏輯錯誤
     */
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<ErrorResponse> BussinessException(BussinessException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setErrCode(exception.getErrcode());
        response.setErrMsg(exception.getErrMsg());
        return ResponseEntity.status(500).body(response);
    }


}
