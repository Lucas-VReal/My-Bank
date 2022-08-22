package br.com.mybank.services;

import br.com.mybank.dtos.ClienteDto;
import br.com.mybank.models.Cliente;
import br.com.mybank.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }

    public ResponseEntity<Object> createNewClient(ClienteDto clienteDto) {
        if (checkSsn(clienteDto.getSsn())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Client already Exists");
        }
        if (clienteDto.getSsn().length() != 14) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The SSN have 11 numbers");
        }
        if (clienteDto.getBirthDate().length() != 10) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The Birth Date is wrong");
        }

        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente salvo com sucesso!");
    }

    private boolean checkSsn(String ssn) {
        return clienteRepository.existsBySsn(ssn);
    }


    @Transactional
    public ResponseEntity<Object> deleteById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        Cliente cliente = clienteOptional.get();
        clienteRepository.delete(cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente removido do My Bank");
    }

    public ResponseEntity<Object> getOneCient(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        Cliente cliente = clienteOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    public ResponseEntity<Object> updateClientById(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        Cliente clienteUpdated = clienteOptional.get();
        if(cliente.getName() != null){
            clienteUpdated.setName(cliente.getName());
        }if(cliente.getSsn() != null){
            if(cliente.getSsn().length() == 14) {
                clienteUpdated.setSsn(cliente.getSsn());
            }
        }if(cliente.getBirthDate() != null){
            if(cliente.getBirthDate().length() == 10) {
                clienteUpdated.setBirthDate(cliente.getBirthDate());
            }
        }
        clienteRepository.save(clienteUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id));
    }

}
