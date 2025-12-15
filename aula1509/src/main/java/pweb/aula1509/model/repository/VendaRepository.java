package pweb.aula1509.model.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.ClientePj;
import pweb.aula1509.model.entity.Produto;
import pweb.aula1509.model.entity.Venda;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return  query.getResultList();
    }

    public Venda venda(Long id){

        return em.find(Venda.class, id);
    }

    public List<Venda> buscarPorData(LocalDate data) {
        String hql = "SELECT v FROM Venda v WHERE v.dataVenda = :data";
        Query query = em.createQuery(hql);
        query.setParameter("data", data);
        return query.getResultList();
    }

    public Venda save(Venda venda) {
        if (venda.getId() == null) {
            em.persist(venda);
        } else {
            venda = em.merge(venda);
        }
        return venda;
    }

    public List<Venda> buscarPorNome(String nome) {
        String hql = """
        SELECT v FROM Venda v
        JOIN v.cliente c
        WHERE LOWER(
            CASE TYPE(c)
                WHEN ClientePF THEN c.nome
                WHEN ClientePj THEN c.razaoSocial
            END
        ) LIKE LOWER(:nome)
    """;

        return em.createQuery(hql, Venda.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }




}
