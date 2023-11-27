package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.ImplementDaoPlayer;
import ifpr.paranavai.game.dao.DaoPlayer;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;
public class PlayerService {
    private static DaoPlayer dao = new ImplementDaoPlayer();

    public static List<Player> searchPlayer() {
        return dao.searchPlayer();
    }
    public static Player searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(Player player) {
        dao.insert(player);
    }
    public static void refresh(Player player) {
        dao.refresh(player);
    }
    public static void delete(Player player) {
        dao.delete(player);
    }
    public static void saveOrUpdatePlayer(Player player) {
        dao.saveOrUpdatePlayer(player);
    }
}
