package com.apigcc.core.http;

import lombok.Builder;
import lombok.Data;

/**
 * The type Http error code.
 *
 * @author duanledexianxianxian
 * @data 2019 /1/2
 */
@Data
public class HttpErrorCode {
    /**
     * 错误编码.
     */
    String code;
    /**
     * 编码名称.
     */
    String name;
    /**
     * 描述.
     */
    String description;
}
