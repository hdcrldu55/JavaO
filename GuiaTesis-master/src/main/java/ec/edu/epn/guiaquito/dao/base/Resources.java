package ec.edu.epn.guiaquito.dao.base;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    @Produces
    @PersistenceContext(unitName = "GuiaQuitoPU")
    private EntityManager entityManager;
}
