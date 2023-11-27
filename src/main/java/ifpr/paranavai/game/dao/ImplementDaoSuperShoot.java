package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoSuperShoot implements DaoSuperShoot{
    private Session session;

    public ImplementDaoSuperShoot() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<SuperShoot> searchSuperShoot() {
        Query<SuperShoot> query = this.session.createQuery("from SuperShoot", SuperShoot.class);
        List<SuperShoot> superShoots = query.getResultList();
        return superShoots;
    }
    @Override
    public SuperShoot searchId(Integer id) {
        return this.session.find(SuperShoot.class, id);
    }
    @Override
    public void insert(SuperShoot superShoot) {
        try {
            session.beginTransaction();
            session.persist(superShoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(SuperShoot superShoot) {
        try {
            session.beginTransaction();
            session.merge(superShoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(SuperShoot superShoot) {
        try {
            session.beginTransaction();
            session.remove(superShoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mergeSuperShoot(SuperShoot superShoot) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(superShoot);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
