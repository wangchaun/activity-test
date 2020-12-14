package com.kyubi.activitytest.modules.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务层的通用异常类.
 *
 * 用于业务流程中抛出的逻辑错误
 */
@Setter
@Getter
public class BizException extends RuntimeException { 
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode = "0";
    private String errorMsg = "ok";
    private Object data;

    public BizException(){}

    public BizException(String errorMsg){
        super(errorMsg);
        this.errorCode = CommonError.BUSINESS_ERROR.getErrorCode();
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(CommonError error){
        super(error.getErrorMsg());
        this.errorCode = error.getErrorCode();
        this.errorMsg = error.getErrorMsg();
    }
    
    public BizException(String errorCode, String errorMsg,Object data){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public void setError(String errorCode){
        setError(errorCode,"fail");
    }

    public void setError(String errorCode, String errerrorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errerrorMsg;
    }
}
