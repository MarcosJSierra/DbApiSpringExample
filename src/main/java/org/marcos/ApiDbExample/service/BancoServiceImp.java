package org.marcos.ApiDbExample.service;

import java.util.ArrayList;
import java.util.Objects;

import org.marcos.ApiDbExample.dto.response.GeneralResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.BankDbDto;
import org.marcos.ApiDbExample.dto.response.bancoDto.response.BankResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.response.ListBankNamesResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.response.ListOfBanksResponse;
import org.marcos.ApiDbExample.models.Banco;
import org.marcos.ApiDbExample.repository.BancoRepository;
import org.marcos.ApiDbExample.tools.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BancoServiceImp implements BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    @Transactional
    public BankResponse createBanco(Banco banco) {

        var response = new BankResponse();
        try {
            var dbResponse = bancoRepository.save(banco);
            response.setBankDbResponse(new BankDbDto());
            response.getBankDbResponse().setBankId(dbResponse.getId().toString());
            response.getBankDbResponse().setBankDescription(dbResponse.getDescription());
            response.getBankDbResponse().setBankName(dbResponse.getName());

            response.setCode(CodeService.okCode);
            response.setMessage(CodeService.okMessage);
        } catch (Exception e) {
            response.setCode(CodeService.errorCode);
            response.setMessage(CodeService.errorMessage);
        }

        return response;
    }

    @Override
    public ListBankNamesResponse nameBancos() {
        var bancosList = bancoRepository.findAll();
        var bancosNameList = new ArrayList<String>();
        var response = new ListBankNamesResponse();

        for (Banco banco : bancosList) {
            bancosNameList.add(banco.getName());
        }

        if (bancosNameList.isEmpty()) {
            response.setCode(CodeService.emptyCode);
            response.setMessage(CodeService.emptyMessage);
        } else {
            response.setCode(CodeService.okCode);
            response.setMessage(CodeService.okMessage);
        }

        response.setBankNames(bancosNameList);

        return response;
    }

    @Override
    public ListOfBanksResponse getAllBancos() {
        var response = new ListOfBanksResponse();

        try {
            var dataList = bancoRepository.findAll();

            response.setListaBancos(new ArrayList<>());

            if (dataList.isEmpty()) {
                response.setCode(CodeService.emptyCode);
                response.setMessage(CodeService.emptyMessage);
            } else {

                BankDbDto data;
                for (var detalle : dataList) {
                    data = new BankDbDto();
                    data.setBankId(detalle.getId().toString());
                    data.setBankName(detalle.getName());
                    data.setBankDescription(detalle.getDescription());
                    response.getListaBancos().add(data);
                }
                response.setCode(CodeService.okCode);
                response.setMessage(CodeService.okMessage);

            }
        } catch (Exception e) {
            response.setCode(CodeService.errorCode);
            response.setMessage(CodeService.errorMessage);
        }
        return response;

    }

    @Override
    public BankResponse getBancoById(String id) {

        var response = new BankResponse();

        try {
            Banco data = bancoRepository.findById(Long.parseLong(id)).orElse(null);

            if (Objects.nonNull(data)) {
                response.setBankDbResponse(new BankDbDto());
                response.getBankDbResponse().setBankId(data.getId().toString());
                response.getBankDbResponse().setBankDescription(data.getDescription());
                response.getBankDbResponse().setBankName(data.getName());

                response.setCode(CodeService.okCode);
                response.setMessage(CodeService.okMessage);
            } else {
                response.setCode(CodeService.errorNotFoundCode);
                response.setMessage(CodeService.errorNotFoundMessage);
            }
        } catch (Exception e) {
            response.setCode(CodeService.errorCode);
            response.setMessage(CodeService.errorMessage);
        }

        return response;
    }

    @Override
    public GeneralResponse bankExists(String id) {
        var banco = bancoRepository.findById(Long.parseLong(id)).orElse(null);
        GeneralResponse response = new GeneralResponse();

        if (Objects.nonNull(banco)) {
            response.setCode(CodeService.okCode);
            response.setMessage(CodeService.okMessage);
        } else {
            response.setCode(CodeService.errorNotFoundCode);
            response.setMessage(CodeService.errorMessage);
        }

        return response;
    }

    @Override
    public BankResponse updateBanco(Banco banco) {

        var responseUpdate = new BankResponse();
        try {

            var dbResponse = bancoRepository.save(banco);

            responseUpdate.setBankDbResponse(new BankDbDto());

            responseUpdate.getBankDbResponse().setBankId(dbResponse.getId().toString());
            responseUpdate.getBankDbResponse().setBankDescription(dbResponse.getDescription());
            responseUpdate.getBankDbResponse().setBankName(dbResponse.getName());

            responseUpdate.setCode(CodeService.okCode);
            responseUpdate.setMessage(CodeService.okMessage);
        } catch (Exception e) {
            responseUpdate.setCode(CodeService.errorCode);
            responseUpdate.setMessage(CodeService.errorMessage);
        }

        return responseUpdate;
    }

    @Override
    public BankResponse parcialUpdateBanco(Banco banco) {
        var response = new BankResponse();

        if (Objects.nonNull(banco.getId())) {
            try {
                Banco oldBanco = bancoRepository.findById(banco.getId()).orElse(null);

                if (Objects.isNull(oldBanco)) {

                    response.setCode(CodeService.errorNotFoundCode);
                    response.setMessage(CodeService.errorNotFoundMessage);
                    return response;
                }

                if (Objects.nonNull(banco.getName()) && !banco.getName().equals(oldBanco.getName())) {
                    oldBanco.setName(banco.getName());
                }

                if (Objects.nonNull(banco.getDescription())
                        && !banco.getDescription().equals(oldBanco.getDescription())) {
                    oldBanco.setDescription(banco.getDescription());
                }

                var updatedBanco = bancoRepository.save(oldBanco);

                response.setBankDbResponse(new BankDbDto());
                response.getBankDbResponse().setBankId(updatedBanco.getId().toString());
                response.getBankDbResponse().setBankName(updatedBanco.getName());
                response.getBankDbResponse().setBankDescription(updatedBanco.getDescription());

                response.setCode(CodeService.updatedCode);
                response.setMessage(CodeService.updatedMessage);

            } catch (Exception e) {

                response.setCode(CodeService.errorCode);
                response.setMessage(CodeService.errorMessage);
            }

        }

        return response;
    }

    @Override
    public BankResponse deleteBanco(String id) {

        BankResponse response = new BankResponse();
        try {
            Banco banco = bancoRepository.findById(Long.parseLong(id)).orElse(null);

            if (Objects.isNull(banco)) {
                
                response.setCode(CodeService.errorNotFoundCode);
                response.setMessage(CodeService.errorNotFoundMessage);
                return response;
            }

            bancoRepository.deleteById(Long.parseLong(id));


            response.setCode(CodeService.deleteCode);
            response.setMessage(CodeService.deleteMessage);

            response.setBankDbResponse(new BankDbDto());
            response.getBankDbResponse().setBankId(banco.getId().toString());
            response.getBankDbResponse().setBankName(banco.getName());
            response.getBankDbResponse().setBankDescription(banco.getDescription());

        } catch (Exception e) {
            
                response.setCode(CodeService.errorCode);
                response.setMessage(CodeService.errorMessage);

        }
        return response;
    }

}
