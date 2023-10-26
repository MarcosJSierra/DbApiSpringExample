package org.marcos.ApiDbExample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.marcos.ApiDbExample.dto.response.BancoResponse;
import org.marcos.ApiDbExample.models.Banco;
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
    public List<Banco> getAllBancos() {
        return bancoRepository.findAll();
        
    }

    @Override
    public Banco getBancoById(String id) {
        return bancoRepository.findById(Long.parseLong(id)).orElse(null);
    }

    @Override
    public boolean bankExists(String id) {
        var banco = bancoRepository.findById(Long.parseLong(id)).orElse(null);
        return Objects.nonNull(banco);
    }

    @Override
    public Banco updateBanco(Banco banco) { 
       return bancoRepository.save(banco);
    }

    
    @Override
    public Banco parcialUpdateBanco(Banco banco) {
        if(Objects.nonNull(banco.getId())){
            Banco oldBanco = bancoRepository.findById(banco.getId()).orElse(null);

            if(Objects.isNull(oldBanco)){
                return null;
            }

            if(Objects.nonNull(banco.getName()) && !banco.getName().equals(oldBanco.getName())){
                oldBanco.setName(banco.getName());
            }

            if(Objects.nonNull(banco.getDescription()) && !banco.getDescription().equals(oldBanco.getDescription())){
                oldBanco.setDescription(banco.getDescription());
            }


            return bancoRepository.save(oldBanco);
        }

        return null;
    }

    @Override
    public Banco deleteBanco(String id) {

        Banco banco = bancoRepository.findById(Long.parseLong(id)).orElse(null);

        if(Objects.isNull(banco)){
            return null;
        }
        bancoRepository.deleteById(Long.parseLong(id));
        return banco;
    }
    
    
}
