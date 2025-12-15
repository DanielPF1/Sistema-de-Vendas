package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.ClientePj;
import pweb.aula1509.model.entity.Produto;

import java.util.List;

@Repository
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Produto> produtos(){
        Query query = em.createQuery("from Produto"); //HQL
        return query.getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String hql = "SELECT produto FROM Produto produto " +
                "WHERE LOWER(produto.descricao) LIKE LOWER(:nome)";
        return em.createQuery(hql, Produto.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }


    public void save(Produto produto){
        em.persist(produto);
    }

    public Produto produto(Long id){
        return em.find(Produto.class, id);
    }

    public void remove(Long id){
        Produto p = em.find(Produto.class, id);
        em.remove(p);
    }

    public void update(Produto produto){
        em.merge(produto);
    }

}
