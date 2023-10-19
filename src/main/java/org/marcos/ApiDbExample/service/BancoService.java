package org.marcos.ApiDbExample.service;

import java.util.ArrayList;

import org.marcos.ApiDbExample.dto.response.BancoResponse;
import org.marcos.ApiDbExample.models.Banco;

public interface BancoService {
    
    public Banco createBanco(Banco banco);

    public Banco getBancoById(String id);

    public BancoResponse nameBancos();

    public ArrayList<Banco> getAllBancos();
}
