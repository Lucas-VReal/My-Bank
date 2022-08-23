package br.com.mybank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 4)
    private String agencia;
    @Column(nullable = false)
    private String numero;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clienteId;
    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.valueOf(0);
    @Column
    private boolean limite;
    @Column
    private String tipo;

    public Conta(String agencia, String numero, Cliente clienteId, BigDecimal saldo, boolean limite, String tipo) {
        this.agencia = agencia;
        this.numero = numero;
        this.clienteId = clienteId;
        this.saldo = saldo;
        this.limite = limite;
        this.tipo = tipo;
    }

    public Conta() {
    }

    public Long getId() {
        return id;
    }

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

    public boolean getLimite() {
        return limite;
    }

    public void setLimite(boolean limite) {
        this.limite = limite;
    }

    public boolean isLimite() {
        return limite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
