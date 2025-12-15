package pweb.aula1509.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    public Item(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Item(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public BigDecimal total(){
        BigDecimal total = new BigDecimal(0);
        total = BigDecimal.valueOf(produto.getValor()*quantidade);
        return total;
    }

}
