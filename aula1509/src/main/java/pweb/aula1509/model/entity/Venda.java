package pweb.aula1509.model.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataVenda = LocalDate.now();

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Venda() {
    }

    public Venda(Long id, LocalDate dataVenda) {
        this.id = id;
        this.dataVenda = dataVenda;
    }

    // GETTERS / SETTERS BÁSICOS

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getDataVenda() { return dataVenda; }

    public void setDataVenda(LocalDate dataVenda) { this.dataVenda = dataVenda; }

    public List<Item> getItems() { return items; }

    public void setItems(List<Item> items) { this.items = items; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    // MÉTODOS DE NEGÓCIO

    public void addItem(Item item) {
        item.setVenda(this);
        this.items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public BigDecimal getTotalVenda() {
        return items.stream()
                .map(Item::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void adicionarProduto(Produto produto) {


        for (Item item : items) {
            if (item.getProduto().getId().equals(produto.getId())) {

                item.setQuantidade(item.getQuantidade() + 1);
                return;
            }
        }


        Item novo = new Item();
        novo.setProduto(produto);
        novo.setQuantidade(1);
        novo.setVenda(this);

        items.add(novo);
    }

}
