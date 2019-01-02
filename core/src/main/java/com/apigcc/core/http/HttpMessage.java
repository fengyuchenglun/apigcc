package com.apigcc.core.http;

import com.apigcc.core.resolver.TypeResolvers;
import com.apigcc.core.resolver.Types;
import com.apigcc.core.resolver.ast.Comments;
import com.apigcc.core.resolver.ast.Tag;
import com.apigcc.core.schema.Group;
import com.apigcc.core.schema.Node;
import com.google.common.base.Strings;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

/**
 * An class that defines a HTTP message,
 * providing common properties and method
 */
@Getter
@Setter
public class HttpMessage extends Node {

    HttpVersion version = HttpVersion.DEFAULT;
    HttpRequest request = new HttpRequest();
    HttpResponse response = new HttpResponse();

    @Ignore
    Group parent;

    @Override
    public void accept(Comments comments) {
        super.accept(comments);

        //解析@return标签
        for (Tag tag : comments.getTags()) {
            if ("return".equals(tag.getName()) && !Strings.isNullOrEmpty(tag.getContent())) {
                Types types = TypeResolvers.tryParse(tag.getContent());
                if(types.isResolved()){
                    response.setBody(types.getValue());
                    response.getCells().addAll(types.getCells());
                }

            }
            if ("errorCode".equals(tag.getName())  && !Strings.isNullOrEmpty(tag.getContent())){
                String[] contents=tag.getContent().split("\\s+");
                HttpErrorCode errorCode=new HttpErrorCode();
                errorCode.setCode(contents[0]);
                if(contents.length>1){
                    errorCode.setName(contents[1]);
                }
                if(contents.length>2){
                    errorCode.setDescription(contents[2]);
                }
                response.getErrorCodes().put(contents[0],errorCode);
            }
        }

    }

}
