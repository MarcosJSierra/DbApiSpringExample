package org.marcos.ApiDbExample.tools;

import org.springframework.beans.factory.annotation.Value;

public class CodeService {
    
    @Value("${response.code.ok}")
    public static Integer okCode;

    @Value("${response.message.ok}")
    public static String okMessage;

    @Value("${response.code.empty}")
    public static Integer emptyCode;

    @Value("${response.message.empty}")
    public static String emptyMessage;

    @Value("${response.code.delete}")
    public static Integer deleteCode;

    @Value("${response.message.delete}")
    public static String deleteMessage;

    @Value("${response.code.updated}")
    public static Integer updatedCode;

    @Value("${response.message.updated}")
    public static String updatedMessage;

    @Value("${response.code.error}")
    public static Integer errorCode;

    @Value("${response.message.error}")
    public static String errorMessage;

    @Value("${response.code.errorNotFound}")
    public static Integer errorNotFoundCode;

    @Value("${response.message.errorNotFound}")
    public static String errorNotFoundMessage;
}
