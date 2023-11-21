package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;

import ifpr.paranavai.game.models.enemies.Enemy1;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoEnemy1 implements DaoEnemy1{
    private Session session;

    public ImplementDaoEnemy1() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Enemy1> searchEnemy() {
        Query<Enemy1> query = this.session.createQuery("from Enemy1", Enemy1.class);
        List<Enemy1> enemy1 = query.getResultList();
        return enemy1;
    }
    @Override
    public Enemy1 searchId(Integer id) {
        return this.session.find(Enemy1.class, id);
    }
    @Override
    public void insert(Enemy1 enemy1) {
        try {
            session.beginTransaction();
            session.persist(enemy1);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(Enemy1 enemy1) {
        try {
            session.beginTransaction();
            session.merge(enemy1);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Enemy1 enemy1) {
        try {
            session.beginTransaction();
            session.remove(enemy1);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}