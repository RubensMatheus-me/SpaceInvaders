package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.DaoMiniMeteor;
import ifpr.paranavai.game.dao.DaoPlayer;
import ifpr.paranavai.game.dao.ImplementDaoMiniMeteor;
import ifpr.paranavai.game.dao.ImplementDaoPlayer;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.enemies.MiniMeteor;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public class MiniMeteorService {
    private static DaoMiniMeteor dao = new ImplementDaoMiniMeteor();

    public static List<MiniMeteor> searchMiniMeteor() {
        return dao.searchMiniMeteor();
    }
    public static MiniMeteor searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(MiniMeteor miniMeteor) {
        dao.insert(miniMeteor);
    }
    public static void refresh(MiniMeteor miniMeteor) {
        dao.refresh(miniMeteor);
    }
    public static void delete(MiniMeteor miniMeteor) {
        dao.delete(miniMeteor);
    }
    public static void saveOrUpdateMiniMeteor(MiniMeteor miniMeteor) {
        dao.saveOrUpdateMiniMeteor(miniMeteor);
    }

}
