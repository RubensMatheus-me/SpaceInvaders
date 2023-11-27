package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.DaoPlayer;
import ifpr.paranavai.game.dao.DaoShoot;
import ifpr.paranavai.game.dao.ImplementDaoPlayer;
import ifpr.paranavai.game.dao.ImplementDaoShoot;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public class ShootService {
    private static DaoShoot dao = new ImplementDaoShoot();

    public static List<Shoot> searchShoot() {
        return dao.searchShoot();
    }
    public static Shoot searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(Shoot shoot) {
        dao.insert(shoot);
    }
    public static void refresh(Shoot shoot) {
        dao.refresh(shoot);
    }
    public static void delete(Shoot shoot) {
        dao.delete(shoot);
    }
    public static void saveOrUpdateShoot(Shoot shoot) {
        dao.saveOrUpdateShoot(shoot);
    }
}

