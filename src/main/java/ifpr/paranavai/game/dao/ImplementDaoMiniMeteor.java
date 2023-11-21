package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.enemies.MiniMeteor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoMiniMeteor implements DaoMiniMeteor {

    private Session session;

    public ImplementDaoMiniMeteor() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<MiniMeteor> searchMiniMeteor() {
        Query<MiniMeteor> query = this.session.createQuery("from MiniMeteor", MiniMeteor.class);
        List<MiniMeteor> miniMeteors = query.getResultList();
        return miniMeteors;
    }

    @Override
    public MiniMeteor searchId(Integer id) {
        return this.session.find(MiniMeteor.class, id);
    }

    @Override
    public void insert(MiniMeteor miniMeteor) {
        try {
            session.beginTransaction();
            session.persist(miniMeteor);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(MiniMeteor miniMeteor) {
        try {
            session.beginTransaction();
            session.merge(miniMeteor);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(MiniMeteor miniMeteor) {
        try {
            session.beginTransaction();
            session.remove(miniMeteor);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}