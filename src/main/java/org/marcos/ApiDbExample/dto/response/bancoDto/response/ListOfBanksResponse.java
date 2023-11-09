package org.marcos.ApiDbExample.dto.response.bancoDto.response;

import java.util.List;

import org.marcos.ApiDbExample.dto.response.GeneralResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.BankDbDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListOfBanksResponse extends GeneralResponse{
    
    @JsonProperty("Bancos")
    private List<BankDbDto> listaBancos;

}
