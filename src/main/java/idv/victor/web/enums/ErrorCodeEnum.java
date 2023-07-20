package idv.victor.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 錯誤代碼 enum
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    /**
     * 登入成功
     */
    A0001("A0001","登入成功"),

    /**
     * 帳號或密碼錯誤
     */
    E0001("E0001", "帳號或密碼錯誤"),
    /**
     * 帳號不存在
     */
    E0002("E0002", "帳號不存在"),
    /**
     * 帳號被鎖定
     */
    E0003("E0003", "該使用者被鎖定"),

    /**
     * 系統內部錯誤
     */
    E9999("E9999", "系統內部錯誤");

    /**
     * 錯誤代碼
     */
    private final String errorCode;

    /**
     * 錯誤訊息
     */
    private final String errMsg;

}
