package org.marcos.ApiDbExample.dto.response.bancoDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankDbDto {
    
    @JsonProperty("BankId")
    private String bankId;

    @JsonProperty("BankName")
    private String bankName;

    @JsonProperty("BankDescription")
    private String bankDescription;
    
}
