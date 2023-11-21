package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.DaoLevel;
import ifpr.paranavai.game.dao.ImplementDaoLevel;
import ifpr.paranavai.game.view.Level;

import java.util.List;

public class LevelService {
    private static DaoLevel dao = new ImplementDaoLevel();

    public static List<Level> searchPlayer() {
        return dao.searchLevel();
    }
    public static Level searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(Level level) {
        dao.insert(level);
    }
    public static void refresh(Level level) {
        dao.refresh(level);
    }
    public static void delete(Level level) {
        dao.delete(level);
    }
}

