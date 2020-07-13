package com.yao.trade.common;

import java.io.Serializable;

/**
 * @Author: 周杨
 * @Date: 2018年7月23日
 * @Description: 数据返回格式封装
 */
public class Result implements Serializable {

    /**
     * 状态码
     */
    private Integer code = Code.SUCCESS.getCode();

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 界面显示的错误消息
     */
    private String uiMsg;

    /**
     * 数据
     */
    private Object data;

    /**
     * 详细的错误描述
     */
    private Object errors;

    private boolean success = true;

    public static class Builder {

        private Result result = new Result();

        public Result build() {
            return result;
        }

        public Builder code(Integer code) {
            result.code = code;
            return this;
        }

        public Builder uiMsg(String uiMsg) {
            result.uiMsg = uiMsg;
            return this;
        }

        public Builder msg(String msg) {
            result.msg = msg;
            return this;
        }

        public Builder data(Object data) {
            result.data = data;
            return this;
        }

        public Builder errors(Object errors) {
            result.errors = errors;
            return this;
        }

        public Builder success(boolean success) {
            result.success = success;
            return this;
        }

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUiMsg() {
        return uiMsg;
    }

    public void setUiMsg(String uiMsg) {
        this.uiMsg = uiMsg;
    }
}
