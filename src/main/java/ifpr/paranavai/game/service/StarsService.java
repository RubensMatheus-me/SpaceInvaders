package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.DaoPlayer;
import ifpr.paranavai.game.dao.DaoStars;
import ifpr.paranavai.game.dao.ImplementDaoPlayer;
import ifpr.paranavai.game.dao.ImplementDaoStars;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.scenario.Stars;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public class StarsService {
    private static DaoStars dao = new ImplementDaoStars();

    public static List<Stars> searchStars() {
        return dao.searchStars();
    }
    public static Stars searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(Stars stars) {
        dao.insert(stars);
    }
    public static void refresh(Stars stars) {
        dao.refresh(stars);
    }
    public static void delete(Stars stars) {
        dao.delete(stars);
    }
    public static void saveOrUpdateStars(Stars stars) {
        dao.saveOrUpdateStars(stars);
    }
}
