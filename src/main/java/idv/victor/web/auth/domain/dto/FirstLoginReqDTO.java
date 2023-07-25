package idv.victor.web.auth.domain.dto;

import lombok.Data;

/**
 * 會員註冊 request
 */
@Data
public class FirstLoginReqDTO {

    /**
     * 使用者名稱
     */
    private String userName;

    /**
     * 密碼
     */
    private String whisper;

    /**
     * 密碼確認
     */
    private String confirmWhisper;

    /**
     * 電子信箱
     */
    private String email;

    /**
     * 名字
     */
    private String firstName;

    /**
     * 姓氏
     */
    private String lastName;

}
