package dao.jpa;

import dao.CompteDao;
import modele.Compte;

public class CompteDaoImpl
        extends AbstractDaoImpl<Compte>
        implements CompteDao {
    public CompteDaoImpl(Class<Compte> entityClass) {
        super(entityClass);
    }

    public CompteDaoImpl() {
        this(Compte.class);
    }
}
