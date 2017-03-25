package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.InterestType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class InterestTypeDAO extends BaseDAO<InterestType, String> {

    @Inject
    private EntityManager entityManager;

    public InterestTypeDAO() {
        super(InterestType.class);
    }

    public InterestType findByCode(String code) {
        TypedQuery<InterestType> q = entityManager.createQuery(SELECT_STATEMENT + "InterestType e WHERE e.code like :code", InterestType.class);
        q.setParameter("code", code);
        List<InterestType> interestTypes = q.getResultList();
        if (interestTypes.size() > 0) {
            return interestTypes.get(0);
        } else {
            return null;
        }
    }
}
