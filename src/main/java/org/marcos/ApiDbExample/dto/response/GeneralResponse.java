package org.marcos.ApiDbExample.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GeneralResponse {
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private Integer code;

}
