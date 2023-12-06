package org.marcos.ApiDbExample.dto.response.bancoDto;

import java.util.List;

import org.marcos.ApiDbExample.dto.modelsDtos.BankDbDto;
import org.marcos.ApiDbExample.dto.response.GeneralResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListOfBanksResponse extends GeneralResponse{
    
    @JsonProperty("Bancos")
    private List<BankDbDto> listaBancos;

}
