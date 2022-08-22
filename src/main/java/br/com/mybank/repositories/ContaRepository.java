package br.com.mybank.repositories;

import br.com.mybank.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
