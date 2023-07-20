package org.marcos.ApiDbExample.service;

import java.util.ArrayList;

import org.marcos.ApiDbExample.models.Banco;
import org.marcos.ApiDbExample.models.response.BancoResponse;

public interface BancoService {
    
    public Banco createBanco(Banco banco);

    public BancoResponse nameBancos();

    public ArrayList<Banco> getAllBancos();
}
