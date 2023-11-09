package org.marcos.ApiDbExample.dto.response.bancoDto.response;

import org.marcos.ApiDbExample.dto.response.GeneralResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.BankDbDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BankResponse extends GeneralResponse {

    @JsonProperty("BancoInfo")
    private BankDbDto bankDbResponse;

}
