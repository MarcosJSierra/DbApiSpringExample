package org.marcos.ApiDbExample.controller;

import java.util.List;
import java.util.Objects;

import org.marcos.ApiDbExample.models.Banco;
import org.marcos.ApiDbExample.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "api/db/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    // @GetMapping("/{idbanco}")
    @RequestMapping(method = RequestMethod.HEAD, path = "/{idbanco}")
    public ResponseEntity<String> headBancoId(@PathVariable(name = "idbanco") String id) {
        boolean bankExists = bancoService.bankExists(id);
        if (bankExists) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Banco>> listBancos() {
        return ResponseEntity.ok(bancoService.getAllBancos());
    }

    @GetMapping("/getBanco")
    public ResponseEntity<Banco> getBancoById(@RequestParam String id) {
        var banco = bancoService.getBancoById(id);
        if (Objects.nonNull(banco)) {
            return ResponseEntity.ok(banco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createBanco")
    public ResponseEntity<Banco> createBanco(@RequestBody Banco banco) {
        Banco created = bancoService.createBanco(banco);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri())
                .body(created);
    }

    @PutMapping("/updateBanco")
    public ResponseEntity<Banco> updateBanco(@RequestBody Banco banco) {
        Banco updated = bancoService.updateBanco(banco);
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updated.getId()).toUri())
            .body(updated);
    }

    @PatchMapping("/updateBanco")
    public ResponseEntity<Banco> parcialUpdateBanco(@RequestBody Banco banco) {
        Banco updated = bancoService.parcialUpdateBanco(banco);
        if (Objects.isNull(updated)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updated.getId()).toUri())
                .body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Banco> deleteBanco(@PathVariable(name = "id") String id){
        Banco deleted = bancoService.deleteBanco(id);
        if(Objects.isNull(deleted)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(deleted);
    }

}
