package idv.victor.web.common.controller.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 針對api error 的物件
 */
@Data
@Builder
public class ErrorResponse {
    /**
     *
     */
    private String errCode;
    private String errMsg;
}
