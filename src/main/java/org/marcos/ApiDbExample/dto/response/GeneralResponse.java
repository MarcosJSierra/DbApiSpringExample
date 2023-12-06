package org.marcos.ApiDbExample.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GeneralResponse implements Serializable {
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private Integer code;

}
