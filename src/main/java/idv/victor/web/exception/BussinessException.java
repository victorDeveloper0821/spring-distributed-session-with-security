package idv.victor.web.exception;

/**
 * 提供自定義的例外處理
 */
public class BussinessException extends Exception{

    /**
     * 錯誤代碼
     */
    private String errcode;
    /**
     * 錯誤訊息
     */
    private String errMsg;

    /**
     * 錯誤訊息建構子
     *
     * @param errcode 代碼
     * @param errMsg 訊息
     */
    public BussinessException(String errcode, String errMsg) {
        super(String.format("[%s] %s",errcode, errMsg));
        this.errcode = errcode;
        this.errMsg = errMsg;
    }
}
