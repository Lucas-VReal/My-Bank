package br.com.mybank.models;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ssn;
    @Column(nullable = false)
    private String birthDate;
    @OneToMany (mappedBy = "clienteId")
    private List<Conta> contas;

    public Cliente(String name, String ssn, String birthDate, List<Conta> contas) {
        this.name = name;
        this.ssn = ssn;
        this.birthDate = birthDate;
        this.contas = contas;
    }

    public Cliente(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
