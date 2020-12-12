package com.xj.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Data
public class ReponseCode {

    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private Map<String, Object> data = new HashMap();

    private ReponseCode(){};

    public static ReponseCode ok() {

        ReponseCode reponseCode = new ReponseCode();
        reponseCode.setSuccess(true);
        reponseCode.setCode(ResultCode.SUCCESS.getKey());
        reponseCode.setMessage(ResultCode.SUCCESS.getValue());

        return reponseCode;

    }

    public static ReponseCode error() {

        ReponseCode reponseCode = new ReponseCode();
        reponseCode.setSuccess(false);
        reponseCode.setCode(ResultCode.ERROR.getKey());
        reponseCode.setMessage(ResultCode.ERROR.getValue());

        return reponseCode;

    }

    public ReponseCode success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ReponseCode message(String message) {
        this.setMessage(message);
        return this;
    }

    public ReponseCode code(int code) {
        this.setCode(code);
        return this;
    }

    public ReponseCode data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ReponseCode data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
