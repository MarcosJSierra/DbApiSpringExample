package org.marcos.ApiDbExample.controller;

import java.util.List;
import java.util.Objects;

import org.marcos.ApiDbExample.dto.modelsDtos.BankDbDto;
import org.marcos.ApiDbExample.dto.response.GeneralResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.BankResponse;
import org.marcos.ApiDbExample.dto.response.bancoDto.ListOfBanksResponse;
import org.marcos.ApiDbExample.models.Banco;
import org.marcos.ApiDbExample.service.BancoService;
import org.marcos.ApiDbExample.tools.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin
@RequestMapping(value = "${api.db.uri}"+"banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @Autowired
    private CodeService codeService;

    // @GetMapping("/{idbanco}")
    @RequestMapping(method = RequestMethod.HEAD, path = "/{idbanco}")
    public ResponseEntity<GeneralResponse> headBancoId(@PathVariable(name = "idbanco") String id) {
        GeneralResponse bankExists = bancoService.bankExists(id);
        if (bankExists.getCode().equals(codeService.okCode)) {
            return ResponseEntity.ok(bankExists);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<ListOfBanksResponse> listBancos() {
        return ResponseEntity.ok(bancoService.getAllBancos());
    }

    @GetMapping("/getBanco")
    public ResponseEntity<BankResponse> getBancoById(@RequestParam String id) {
        var banco = bancoService.getBancoById(id);
        if (banco.getCode().equals(codeService.okCode)) {
            return ResponseEntity.ok(banco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createBanco")
    public ResponseEntity<BankResponse> createBanco(@RequestBody BankDbDto banco) {
        var created = bancoService.createBanco(banco.toModel());

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getBankDbResponse().getBankId()).toUri())
                .body(created);
    }

    @PutMapping("/updateBanco")
    public ResponseEntity<BankResponse> updateBanco(@RequestBody BankDbDto banco) {
        var updated = bancoService.updateBanco(banco.toModel());
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updated.getBankDbResponse().getBankId()).toUri())
            .body(updated);
    }

    @PatchMapping("/updateBanco")
    public ResponseEntity<BankResponse> parcialUpdateBanco(@RequestBody BankDbDto banco) {
        var updated = bancoService.parcialUpdateBanco(banco.toModel());
        if (!updated.getCode().equals(codeService.updatedCode)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updated.getBankDbResponse().getBankId()).toUri())
                .body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankResponse> deleteBanco(@PathVariable(name = "id") String id){
        BankResponse deleted = bancoService.deleteBanco(id);
        if(!deleted.getCode().equals(codeService.deleteCode)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(deleted);
    }

}
