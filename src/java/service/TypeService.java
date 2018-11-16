package service;

import entity.Ttype;
import entity.Tuser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class TypeService {

    @PersistenceContext(unitName = "twitterPU")
    EntityManager em;

    public void register(String name, String description) {
        Ttype type = new Ttype();

        try {
            type.setName(name);
            type.setDescription(description);
            em.persist(type);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }

    public void edit(Integer id, String name, String description) {
        try {
            Ttype type = em.find(Ttype.class, id);
            type.setName(name);
            type.setDescription(description);
            em.merge(type);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            Ttype type = em.find(Ttype.class, id);
            em.remove(type);
            em.flush();
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
    }

    public Ttype find(Integer id) {
        Ttype type = new Ttype();
        try {
            type = em.find(Ttype.class, id);
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
        return type;
    }

    public List<Ttype> getAllTypes() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ttype.class));
        return em.createQuery(cq).getResultList();
    }
}
