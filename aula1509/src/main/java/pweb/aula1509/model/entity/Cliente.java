package pweb.aula1509.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cliente  {

    //

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    private String email;

    private String telefone;



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Método abstrato para usar quando for colocar de quem foi a venda no venda/list
    public abstract String getNome();

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas = new ArrayList<>();

    public boolean tipoObjeto(String objeto){
        return this.getClass().getSimpleName().toLowerCase().equals(objeto.toLowerCase());
    }

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }
}
