package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.ClientePF;
import pweb.aula1509.model.entity.ClientePj;
import pweb.aula1509.model.entity.Produto;
import pweb.aula1509.model.entity.Venda;

import java.util.List;

@Repository
public class ClientePFRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(ClientePF clientePF){
        em.persist(clientePF);
    }



    public ClientePF clientePF(Long id){
        return em.find(ClientePF.class, id);
    }

    public List<ClientePF> listarTodos(){
        Query query = em.createQuery("from ClientePF");
        return  query.getResultList();
    }

    public List<ClientePF> buscarPorNome(String nome) {
        String hql = "SELECT pf FROM ClientePF pf " +
                "WHERE LOWER(pf.nome) LIKE LOWER(:nome)";
        return em.createQuery(hql, ClientePF.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
