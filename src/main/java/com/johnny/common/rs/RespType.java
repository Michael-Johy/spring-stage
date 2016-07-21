package com.johnny.common.rs;

/**
 * defind http-response type
 *
 * Created by johnson.yang on 2016/5/11.
 */
public enum RespType {

    SUCCESS("200", "SUCCESS"),
    FAIL("400", "FAIL"),
    PARAMETEREXCEPTION("10177", "PARAMETEREXCEPTION"),
    VISITSOUTOFLIMIT("10222", "VISITS_OUT_OF_LIMITS");

    RespType(String code, String type) {
        this.code = code;
        this.type = type;
    }

    private String code;
    private String type;

    public RespType fetch(String code) {
        for (RespType respType : RespType.values()) {
            if (respType.code.equals(code))
                return respType;
        }
        return RespType.SUCCESS;
    }

    public String getCode(){
        return code;
    }
    public String getType(){
        return type;
    }


}
