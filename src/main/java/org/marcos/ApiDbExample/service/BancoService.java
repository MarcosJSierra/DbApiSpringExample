package org.marcos.ApiDbExample.service;

import java.util.List;

import org.marcos.ApiDbExample.dto.response.GeneralResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.BankResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.ListBankNamesResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.ListOfBanksResponse;
import org.marcos.ApiDbExample.models.Banco;

public interface BancoService {

    public GeneralResponse bankExists(String id);
    
    public BankResponse createBanco(Banco banco);

    public BankResponse getBancoById(String id);

    public BankResponse updateBanco(Banco banco);

    public BankResponse parcialUpdateBanco(Banco banco);

    public ListBankNamesResponse nameBancos();

    public ListOfBanksResponse getAllBancos();

    public BankResponse deleteBanco(String id);
}
