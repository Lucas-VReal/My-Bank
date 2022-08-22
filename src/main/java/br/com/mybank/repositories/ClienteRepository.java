package br.com.mybank.repositories;

import br.com.mybank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsBySsn(String ssn);
}
