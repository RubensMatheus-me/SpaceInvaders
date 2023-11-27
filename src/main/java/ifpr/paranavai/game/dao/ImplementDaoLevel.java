package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.view.Level;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoLevel implements DaoLevel{
    private Session session;

    public ImplementDaoLevel() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Level> searchLevel() {
        Query<Level> query = this.session.createQuery("from Level", Level.class);
        List<Level> levels = query.getResultList();
        return levels;
    }
    @Override
    public Level searchId(Integer id) {
        return this.session.find(Level.class, id);
    }
    @Override
    public void insert(Level level) {
        try {
            session.beginTransaction();
            session.persist(level);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(Level level) {
        try {
            session.beginTransaction();
            session.merge(level);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Level level) {
        try {
            session.beginTransaction();
            session.remove(level);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveOrUpdateLevel(Level level) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(level);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
