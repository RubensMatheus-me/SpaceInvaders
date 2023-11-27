package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.DaoPlayer;
import ifpr.paranavai.game.dao.DaoSuperShoot;
import ifpr.paranavai.game.dao.ImplementDaoPlayer;
import ifpr.paranavai.game.dao.ImplementDaoSuperShoot;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public class SuperShootService {
    private static DaoSuperShoot dao = new ImplementDaoSuperShoot();

    public static List<SuperShoot> searchSuperShoot() {
        return dao.searchSuperShoot();
    }
    public static SuperShoot searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(SuperShoot superShoot) {
        dao.insert(superShoot);
    }
    public static void refresh(SuperShoot superShoot) {
        dao.refresh(superShoot);
    }
    public static void delete(SuperShoot superShoot) {
        dao.delete(superShoot);
    }
    public static void mergeSuperShoot(SuperShoot superShoot) {
        dao.mergeSuperShoot(superShoot);
    }

}
