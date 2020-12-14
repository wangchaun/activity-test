package com.kyubi.activitytest.modules.mysteelworkflow.entity;

/**
 * @description: ResultInfo 类
 * @projectName: activity-test
 * @className: ResultInfo
 * @author: wangsiming
 * @createTime: 2020/11/23 14:36
 */

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.springframework.http.HttpStatus;

public class ResultInfo<T> implements Serializable {
    private static final long serialVersionUID = 6793350277616007958L;
    @ApiModelProperty("返回业务状态码")
    private String status;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("用户需要返回的参数")
    private T response;

    public ResultInfo() {
    }

    public ResultInfo success() {
        this.status = String.valueOf(HttpStatus.OK.value());
        this.message = "OK";
        return this;
    }

    public ResultInfo success(String status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public ResultInfo<T> success(T response) {
        this.status = String.valueOf(HttpStatus.OK.value());
        this.message = "OK";
        this.response = response;
        return this;
    }

    public ResultInfo<T> success(String status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
        return this;
    }

    public ResultInfo error(String message) {
        this.status = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.message = message;
        return this;
    }

    public ResultInfo error(String status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return this.response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}