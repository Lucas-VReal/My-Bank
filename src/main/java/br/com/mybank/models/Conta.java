package br.com.mybank.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.rmi.server.UID;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 4)
    private Integer agencia;
    @Column(nullable = false)
    private String numero;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clienteId;
    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.valueOf(0);
    @Column
    private BigDecimal limite;

    public Conta(Integer agencia, String numero, Cliente clienteId, BigDecimal saldo, BigDecimal limite) {
        this.agencia = agencia;
        this.numero = numero;
        this.clienteId = clienteId;
        this.saldo = saldo;
        this.limite = limite;
    }

    public Conta() {
    }

    public Long getId() {
        return id;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}
