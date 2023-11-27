package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.scenario.Stars;
import ifpr.paranavai.game.models.shoots.Shoot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoStars implements DaoStars{
    private Session session;

    public ImplementDaoStars() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Stars> searchStars() {
        Query<Stars> query = this.session.createQuery("from Stars", Stars.class);
        List<Stars> stars = query.getResultList();
        return stars;
    }
    @Override
    public Stars searchId(Integer id) {
        return this.session.find(Stars.class, id);
    }
    @Override
    public void insert(Stars stars) {
        try {
            session.beginTransaction();
            session.persist(stars);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(Stars stars) {
        try {
            session.beginTransaction();
            session.merge(stars);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Stars stars) {
        try {
            session.beginTransaction();
            session.remove(stars);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mergeStars(Stars stars) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(stars);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
