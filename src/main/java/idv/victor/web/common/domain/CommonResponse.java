package idv.victor.web.common.domain;

import lombok.Data;

/**
 * CommonResponse - 共用的資料
 */
@Data
public class CommonResponse {
    /**
     * 狀態碼
     */
    private final String status;

    /**
     * 是否成功
     */
    private final boolean success;

    /**
     * 成功/錯誤訊息
     */
    private String message;

    /**
     * 資料列表
     */
    private final Object data;

    public CommonResponse(Object data, boolean success, String status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }
}
