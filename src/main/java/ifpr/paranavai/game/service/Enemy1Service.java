package ifpr.paranavai.game.service;

import ifpr.paranavai.game.dao.DaoEnemy1;
import ifpr.paranavai.game.dao.DaoPlayer;
import ifpr.paranavai.game.dao.ImplementDaoEnemy1;
import ifpr.paranavai.game.dao.ImplementDaoPlayer;
import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.enemies.Enemy1;

import java.util.List;

public class Enemy1Service {
    private static DaoEnemy1 dao = new ImplementDaoEnemy1();

    public static List<Enemy1> searchPlayer() {
        return dao.searchEnemy();
    }
    public static Enemy1 searchId(Integer id) {
        return dao.searchId(id);
    }
    public static void insert(Enemy1 enemy1) {
        dao.insert(enemy1);
    }
    public static void refresh(Enemy1 enemy1) {
        dao.refresh(enemy1);
    }
    public static void delete(Enemy1 enemy1) {
        dao.delete(enemy1);
    }
}

