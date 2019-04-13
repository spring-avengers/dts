package com.bkjk.platform.dts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class APIResult {
    private Result result;
    private Object content;

    APIResult() {
        result = new Result();
        content = new Object();
    }

    APIResult(ErrorCode errorCode, String message, Object content) {
        this.result = new Result(errorCode.getCode(), message);
        this.content = content;
    }

    public APIResult(ErrorCode errorCode, Object content) {
        this(errorCode, errorCode.getDesc(), content);
    }

    public static APIResult success() {
        return success(null);
    }

    public static APIResult success(Object data) {
        return new APIResult(ErrorCode.OK, data);
    }

    public static APIResult success(String message, Object data) {
        return new APIResult(ErrorCode.OK, message, data);
    }

    public static APIResult error(ErrorCode errorCode, String message) {
        return new APIResult(errorCode, message, null);
    }

    public static APIResult error(ErrorCode errorCode) {
        return new APIResult(errorCode, errorCode.getDesc(), null);
    }

    public static APIResult error(ErrorCode errorCode, String message, Object data) {
        return new APIResult(errorCode, message, data);
    }

    public enum ErrorCode {
        OK(200, "OK"),
        SESSION_TIMEOUT(302, "Session Timeout"),
        RULE_PARSE_ERROR(303, "Rule Parsing Error"),
        COMMON_VALIDATION_FAIL(304, "Validation fail"),
        PARAM_MISS(305, "Required Parameter is missing"),
        INTERNAL_ERROR(500, "Internal Error"),
        BAD_REQUEST(400, "Bad Request"),
        SC_UNAUTHORIZED(401, "Unauthorized");

        private int code;
        private String desc;

        ErrorCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static boolean isOk(int code) {
            return OK.getCode() == code;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Result {
        private int code;
        private String message;
    }
}