package org.marcos.ApiDbExample.dto.response.bancoDto.response;


import java.util.List;

import org.marcos.ApiDbExample.dto.response.GeneralResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class ListBankNamesResponse extends GeneralResponse{
    @JsonProperty("bankNames")
    List<String> bankNames;
}
