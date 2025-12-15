package pweb.aula1509.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class ClientePF extends Cliente implements Serializable {

    @NotBlank(message = "Não pode ficar em branco")
    private String nome;

    @NotBlank(message = "Não pode ficar em branco")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 números")
    private String cpf;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}
