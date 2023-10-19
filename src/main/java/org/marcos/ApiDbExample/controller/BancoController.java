package org.marcos.ApiDbExample.controller;

import java.util.ArrayList;

import org.marcos.ApiDbExample.dto.response.BancoResponse;
import org.marcos.ApiDbExample.models.Banco;
import org.marcos.ApiDbExample.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value="api/db/banco")
public class BancoController {
    
    @Autowired
    private BancoService bancoService;

    @PostMapping("/createBanco")
    public Banco createBanco(@RequestBody Banco banco){
        return bancoService.createBanco(banco);
    }

    @GetMapping("/names")
    public BancoResponse listBancosNames(){
        return bancoService.nameBancos();
    }

    @GetMapping("/")
    public ArrayList<Banco> listBancos(){
        return bancoService.getAllBancos();
    }


    @GetMapping("/getBanco")
    public Banco getBancoById(@RequestParam String id){
        return bancoService.getBancoById(id);
    }

    @GetMapping("/{idbanco}")
    public Banco getBancoByIdPath(@PathVariable(name = "idbanco") String id){
        return bancoService.getBancoById(id);
    }

}
