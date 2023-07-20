package org.marcos.ApiDbExample.service;

import java.util.ArrayList;

import org.hibernate.mapping.Collection;
import org.marcos.ApiDbExample.models.Banco;
import org.marcos.ApiDbExample.models.response.BancoResponse;
import org.marcos.ApiDbExample.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BancoServiceImp implements BancoService{

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    @Transactional
    public Banco createBanco(Banco banco) {
        return bancoRepository.save(banco);
    }

    @Override
    public BancoResponse nameBancos() {
        var bancosList = bancoRepository.findAll();
        var bancosNameList = new ArrayList<String>();

        for(Banco banco : bancosList){
            bancosNameList.add(banco.getName());
        }

        var response = new BancoResponse();
        response.setBankNames(bancosNameList);

        return response;
    }

    @Override
    public ArrayList<Banco> getAllBancos() {
        return (ArrayList<Banco>) bancoRepository.findAll();
        
    }
    
}
