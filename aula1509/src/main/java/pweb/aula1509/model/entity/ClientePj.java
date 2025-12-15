package pweb.aula1509.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Entity
public class ClientePj extends Cliente implements Serializable {

    @NotBlank(message = "Não pode ficar em branco")
    private String razaoSocial;

    @NotBlank(message = "Não pode ficar em branco")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter exatamente 14 números")
    private String cnpj;


    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getNome() {
        return razaoSocial;
    }
}
