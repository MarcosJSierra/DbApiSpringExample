package org.marcos.ApiDbExample.service;

import java.util.List;

import org.marcos.ApiDbExample.dto.response.BancoResponse;
import org.marcos.ApiDbExample.models.Banco;

public interface BancoService {

    public boolean bankExists(String id);
    
    public Banco createBanco(Banco banco);

    public Banco getBancoById(String id);

    public Banco updateBanco(Banco banco);

    public Banco parcialUpdateBanco(Banco banco);

    public BancoResponse nameBancos();

    public List<Banco> getAllBancos();

    public Banco deleteBanco(String id);
}
