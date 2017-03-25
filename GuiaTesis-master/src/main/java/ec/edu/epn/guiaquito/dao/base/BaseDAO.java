package ec.edu.epn.guiaquito.dao.base;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * JPA generic class implementing CRUD methods.
 *
 * @param T Generic type of the entity class for the DAO.
 * @param K Generic type for id of the object.
 * @version 1.0
 */
public abstract class BaseDAO<T, K extends String> {

    protected static final String SELECT_STATEMENT = "SELECT e FROM ";

    /**
     * Generic type of the entity class for the DAO.
     */
    private final Class<T> entity;

    /**
     * Name of the class for the DAO.
     */
    private final String entityName;

    @Inject
    private EntityManager entityManager;

    /**
     * Constructor with the concrete entity class for the DAO.
     */
    @SuppressWarnings("unchecked")
    public BaseDAO(Class<T> parametrizedEntity) {
        this.entity = parametrizedEntity;
        this.entityName = entity.getSimpleName();
    }

    public List<T> findAll() throws Exception {
        List<T> results;
        try {
            results = entityManager.createQuery(SELECT_STATEMENT + entityName + " e", entity)
                    .getResultList();
            return results;
        } catch (Exception ex) {
            throw new Exception("findAll: No se ha podido recuperar las entidades: " + entityName, ex);
        }

    }

    public T findById(final K id) throws Exception {
        try {
            return entityManager.find(entity, id);
        } catch (Exception ex) {
            throw new Exception("findById: No se ha podido recuperar la entity '" + entityName
                    + "' con identificador: " + id, ex);
        }
    }

    public T create(final T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

// --Commented out by Inspection START (11/2/16, 17:20):
//    public void delete(final T entity) {
//        T deleted = entityManager.merge(entity);
//        entityManager.remove(deleted);
//    }
// --Commented out by Inspection STOP (11/2/16, 17:20)
}
