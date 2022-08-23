package br.com.mybank.dtos;

import br.com.mybank.models.Cliente;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class ContaDto {

    @NotNull
    private String agencia;
    @NotNull
    private String numero;
    @NotNull
    private Long clienteId;

    private boolean limite;
    @NotNull
    private String tipo;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public boolean isLimite() {
        return limite;
    }

    public void setLimite(boolean limite) {
        this.limite = limite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
