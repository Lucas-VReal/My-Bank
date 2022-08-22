package br.com.mybank.controllers;

import br.com.mybank.dtos.ClienteDto;
import br.com.mybank.models.Cliente;
import br.com.mybank.services.ClienteService;
import br.com.mybank.services.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    final ClienteService clienteService;
    final ContaService contaService;

    public ContaController(ClienteService clienteService, ContaService contaService) {
        this.clienteService = clienteService;
        this.contaService = contaService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClients (){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAllClients());
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") Long id){
        return clienteService.getOneCient(id);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Object> setClient(@RequestBody @Valid ClienteDto clienteDto){
        return clienteService.createNewClient(clienteDto);
    }

    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id){
        return clienteService.deleteById(id);
    }

    @PutMapping("clientes/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id, @RequestBody Cliente cliente){
        return clienteService.updateClientById(id, cliente);
    }
}
