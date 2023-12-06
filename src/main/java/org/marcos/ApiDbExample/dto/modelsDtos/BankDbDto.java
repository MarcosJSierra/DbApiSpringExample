package org.marcos.ApiDbExample.dto.modelsDtos;

import java.io.Serializable;

import org.marcos.ApiDbExample.models.Banco;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDbDto implements Serializable{
    
    @JsonProperty("BankId")
    private String bankId;

    @JsonProperty("BankName")
    private String bankName;

    @JsonProperty("BankDescription")
    private String bankDescription;
    

    public Banco toModel(){
        var model = new Banco();


        model.setId(Long.valueOf(this.getBankId()));
        model.setName(this.getBankName());
        model.setDescription(this.getBankDescription());

        return model;
    }

    public BankDbDto(Banco model){
        this.setBankId(model.getId().toString());
        this.setBankDescription(model.getDescription());
        this.setBankName(model.getName());
    }
}
