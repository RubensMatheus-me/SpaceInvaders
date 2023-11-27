package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.shoots.Shoot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoShoot implements DaoShoot{
    private Session session;

    public ImplementDaoShoot() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Shoot> searchShoot() {
        Query<Shoot> query = this.session.createQuery("from Shoot", Shoot.class);
        List<Shoot> shoots = query.getResultList();
        return shoots;
    }
    @Override
    public Shoot searchId(Integer id) {
        return this.session.find(Shoot.class, id);
    }
    @Override
    public void insert(Shoot shoot) {
        try {
            session.beginTransaction();
            session.persist(shoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(Shoot shoot) {
        try {
            session.beginTransaction();
            session.merge(shoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Shoot shoot) {
        try {
            session.beginTransaction();
            session.remove(shoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mergeShoot(Shoot shoot) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(shoot);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
