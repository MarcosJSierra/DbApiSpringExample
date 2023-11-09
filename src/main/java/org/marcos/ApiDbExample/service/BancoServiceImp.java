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

    @Autowired
    private CodeService codeService;

    @Override
    @Transactional
    public BankResponse createBanco(Banco banco) {

        var response = new BankResponse();
        try {
            var dbResponse = bancoRepository.save(banco);
            response.setBankDbResponse(new BankDbDto(dbResponse));

            response.setCode(codeService.okCode);
            response.setMessage(codeService.okMessage);
        } catch (Exception e) {
            response.setCode(codeService.errorCode);
            response.setMessage(codeService.errorMessage);
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
            response.setCode(codeService.emptyCode);
            response.setMessage(codeService.emptyMessage);
        } else {
            response.setCode(codeService.okCode);
            response.setMessage(codeService.okMessage);
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
                response.setCode(codeService.emptyCode);
                response.setMessage(codeService.emptyMessage);
            } else {

                BankDbDto data;
                for (var detalle : dataList) {
                    data = new BankDbDto(detalle);
                    response.getListaBancos().add(data);
                }
                response.setCode(codeService.okCode);
                response.setMessage(codeService.okMessage);

            }
        } catch (Exception e) {
            response.setCode(codeService.errorCode);
            response.setMessage(codeService.errorMessage);
        }
        return response;

    }

    @Override
    public BankResponse getBancoById(String id) {

        var response = new BankResponse();

        try {
            Banco data = bancoRepository.findById(Long.parseLong(id)).orElse(null);

            if (Objects.nonNull(data)) {
                response.setBankDbResponse(new BankDbDto(data));

                response.setCode(codeService.okCode);
                response.setMessage(codeService.okMessage);
            } else {
                response.setCode(codeService.errorNotFoundCode);
                response.setMessage(codeService.errorNotFoundMessage);
            }
        } catch (Exception e) {
            response.setCode(codeService.errorCode);
            response.setMessage(codeService.errorMessage);
        }

        return response;
    }

    @Override
    public GeneralResponse bankExists(String id) {
        var banco = bancoRepository.findById(Long.parseLong(id)).orElse(null);
        GeneralResponse response = new GeneralResponse();

        if (Objects.nonNull(banco)) {
            response.setCode(codeService.okCode);
            response.setMessage(codeService.okMessage);
        } else {

            System.out.println(codeService.errorNotFoundCode);
            response.setCode(codeService.errorNotFoundCode);
            response.setMessage(codeService.errorMessage);
        }

        return response;
    }

    @Override
    public BankResponse updateBanco(Banco banco) {

        var responseUpdate = new BankResponse();
        try {

            var dbResponse = bancoRepository.save(banco);

            responseUpdate.setBankDbResponse(new BankDbDto(dbResponse));

            responseUpdate.setCode(codeService.okCode);
            responseUpdate.setMessage(codeService.okMessage);
        } catch (Exception e) {
            responseUpdate.setCode(codeService.errorCode);
            responseUpdate.setMessage(codeService.errorMessage);
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

                    response.setCode(codeService.errorNotFoundCode);
                    response.setMessage(codeService.errorNotFoundMessage);
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

                response.setBankDbResponse(new BankDbDto(updatedBanco));

                response.setCode(codeService.updatedCode);
                response.setMessage(codeService.updatedMessage);

            } catch (Exception e) {

                response.setCode(codeService.errorCode);
                response.setMessage(codeService.errorMessage);
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
                
                response.setCode(codeService.errorNotFoundCode);
                response.setMessage(codeService.errorNotFoundMessage);
                return response;
            }

            bancoRepository.deleteById(Long.parseLong(id));


            response.setCode(codeService.deleteCode);
            response.setMessage(codeService.deleteMessage);

            response.setBankDbResponse(new BankDbDto(banco));

        } catch (Exception e) {
            
                response.setCode(codeService.errorCode);
                response.setMessage(codeService.errorMessage);

        }
        return response;
    }

}
