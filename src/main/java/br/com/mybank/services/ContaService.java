package br.com.mybank.services;

import br.com.mybank.dtos.ContaDto;
import br.com.mybank.models.Cliente;
import br.com.mybank.models.Conta;
import br.com.mybank.repositories.ClienteRepository;
import br.com.mybank.repositories.ContaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository, ClienteService clienteService) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }


    public ResponseEntity<Object> createBankAccount(@NotNull ContaDto contaDto) {
        if(!clienteRepository.existsById(contaDto.getClienteId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("É necessário associar a conta a um cliente");
        } else if(!checkIfExists(contaDto)) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(contaDto.getClienteId());
            Cliente cliente = clienteOptional.get();
            Conta conta = new Conta(contaDto.getAgencia(), contaDto.getNumero(), cliente, BigDecimal.valueOf(0), contaDto.isLimite(), contaDto.getTipo());
            contaRepository.save(conta);
            List<Conta> contas = new ArrayList<>();
            contas.add(conta);
            cliente.setContas(contas);
            clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Conta cadastrada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Não foi possível criar a conta com os dados informados");
    }

    private boolean checkIfExists(ContaDto contaDto) {
        if(contaRepository.existsByNumero(contaDto.getNumero())){
            return true;
        }
        return false;
    }

    public ResponseEntity<Object> updateBankAccountById(Long id, Conta conta) {
        if(!contaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
        Optional<Conta> contaOptional = contaRepository.findById(id);
        Conta updatedAccount = contaOptional.get();
        if (conta.getAgencia() != null){
            updatedAccount.setAgencia(conta.getAgencia());
        }if (conta.getNumero() != null){
            updatedAccount.setNumero(conta.getNumero());
        }if (conta.getLimite() != updatedAccount.getLimite()){
            updatedAccount.setLimite(conta.getLimite());
        }if(conta.getTipo() != updatedAccount.getTipo()){
            updatedAccount.setTipo(conta.getTipo());
        }
        contaRepository.save(updatedAccount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedAccount);
    }

    public ResponseEntity<Object> deleteBankAccountById(Long id) {
        if(!contaRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
        Optional<Conta> contaOptional = contaRepository.findById(id);
        Conta deletedAccount = contaOptional.get();

        Cliente cliente = deletedAccount.getClienteId();
        List<Conta> list = cliente.getContas();
        list.remove(deletedAccount);
        cliente.setContas(list);

        contaRepository.delete(deletedAccount);
        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso");
    }
}
