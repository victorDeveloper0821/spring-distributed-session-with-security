package idv.victor.web.common.controller.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 針對api error 的物件
 */
@Data
@Builder
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
}
