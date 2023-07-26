package idv.victor.web.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import idv.victor.web.enums.ErrorCodeEnum;
import lombok.*;

/**
 * 針對api error 的物件
 */
@Setter
@NoArgsConstructor
public class ErrorResponse {
    /**
     * 錯誤代碼
     */
    private String errCode;

    /**
     * 錯誤訊息
     */
    private String errMsg;

    /**
     * 錯誤訊息細項
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;

    /**
     * 建構子
     *
     * @param errorCodeEnum 錯誤代碼 enum
     */
    public ErrorResponse (ErrorCodeEnum errorCodeEnum){
        this.errCode = errorCodeEnum.getErrorCode();
        this.errMsg = errorCodeEnum.getErrMsg();
    }
}
