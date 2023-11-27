package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.enemies.Enemy1;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public interface DaoEnemy1 {
    public List<Enemy1> searchEnemy();
    public Enemy1 searchId(Integer id);
    public void refresh (Enemy1 enemy1);
    public void delete (Enemy1 enemy1);
    public void insert (Enemy1 enemy1);
    public void mergeEnemy1 (Enemy1 enemy1);
}
