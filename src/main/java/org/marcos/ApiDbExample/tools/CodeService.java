package org.marcos.ApiDbExample.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CodeService {
    
    @Value("${response.code.ok}")
    public  Integer okCode;

    @Value("${response.message.ok}")
    public  String okMessage;

    @Value("${response.code.empty}")
    public  Integer emptyCode;

    @Value("${response.message.empty}")
    public  String emptyMessage;

    @Value("${response.code.delete}")
    public  Integer deleteCode;

    @Value("${response.message.delete}")
    public  String deleteMessage;

    @Value("${response.code.updated}")
    public  Integer updatedCode;

    @Value("${response.message.updated}")
    public  String updatedMessage;

    @Value("${response.code.error}")
    public  Integer errorCode;

    @Value("${response.message.error}")
    public  String errorMessage;

    @Value("${response.code.errorNotFound}")
    public  Integer errorNotFoundCode;

    @Value("${response.message.errorNotFound}")
    public  String errorNotFoundMessage;
}
