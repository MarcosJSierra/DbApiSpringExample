package org.marcos.ApiDbExample.repository;


import org.marcos.ApiDbExample.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
    
}
