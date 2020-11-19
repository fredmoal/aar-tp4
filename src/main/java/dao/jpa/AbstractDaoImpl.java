package dao.jpa;

import dao.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author fred
 */
public class AbstractDaoImpl<T> implements AbstractDao<T> {

    /** Entity Manager used to talk with the database */
    @PersistenceContext
    private EntityManager em;

    private Class<T> domainClass;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.domainClass = entityClass;
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#create(T)
	 */
    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#edit(T)
	 */
    @Override
    public void edit(T entity) {
        em.merge(entity);
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#remove(T)
	 */
    @Override
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#find(java.lang.Object)
	 */
    @Override
    public Optional<T> find(Object id) {
        return Optional.ofNullable(em.find(domainClass, id));
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#findAll()
	 */
    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(domainClass);
        criteria.select(criteria.from(domainClass));
        return em.createQuery(criteria).getResultList();
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#findRange(int[])
	 */
    @Override
    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(domainClass);
        cq.select(cq.from(domainClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /* (non-Javadoc)
	 * @see dao.AbstractDao#count()
	 */
    @Override
    public int count() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = b.createQuery(Long.class);
        criteria.select(b.count(criteria.from(domainClass)));

        return em.createQuery(criteria).getSingleResult().intValue();
    }

}
