package ifpr.paranavai.game.dao;

import Connection.HibernateUtil;
import ifpr.paranavai.game.models.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ImplementDaoPlayer implements DaoPlayer {
    private Session session;

    public ImplementDaoPlayer() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Player> searchPlayer() {
        Query<Player> query = this.session.createQuery("from Player", Player.class);
        List<Player> players = query.getResultList();
        return players;
    }
    @Override
    public Player searchId(Integer id) {
        return this.session.find(Player.class, id);
    }
    @Override
    public void insert(Player player) {
        try {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(Player player) {
        try {
            session.beginTransaction();
            session.merge(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Player player) {
        try {
            session.beginTransaction();
            session.remove(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
