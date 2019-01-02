package com.apigcc.core.http;

import com.apigcc.core.common.Cell;
import com.apigcc.core.common.ObjectMappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class HttpResponse {

    HttpResponseStatus status = HttpResponseStatus.DEFAULT;
    HttpHeaders headers = new HttpHeaders();
    Object body;

    List<Cell<String>> cells = new ArrayList<>();
    /**
     * 错误编码列表
     */
    Map<String,HttpErrorCode> errorCodes= Maps.newLinkedHashMap();

    public boolean isEmpty(){
        return cells.isEmpty() && Objects.isNull(body)  && headers.isEmpty() && errorCodes.isEmpty();
    }

    public boolean hasBody(){
        return body != null;
    }

    public String bodyString(){
        if(getBody()!=null && getBody() instanceof JsonNode){
            return ObjectMappers.toPretty(getBody());
        }
        return "";
    }

}
