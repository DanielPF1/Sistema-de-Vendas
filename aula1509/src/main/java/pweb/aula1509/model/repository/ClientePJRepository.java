package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.ClientePF;
import pweb.aula1509.model.entity.ClientePj;
import pweb.aula1509.model.entity.Venda;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ClientePJRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(ClientePj clientepj){
        em.persist(clientepj);
    }


    public List<ClientePj> listarTodos() {
        Query query = em.createQuery("from ClientePj ");
        return  query.getResultList();
    }

    public ClientePj clientePj(Long id){
        return em.find(ClientePj.class, id);
    }

    public List<ClientePj> buscarPorNome(String nome) {
        String hql = "SELECT pj FROM ClientePj pj " +
                "WHERE LOWER(pj.razaoSocial) LIKE LOWER(:nome)";
        return em.createQuery(hql, ClientePj.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
