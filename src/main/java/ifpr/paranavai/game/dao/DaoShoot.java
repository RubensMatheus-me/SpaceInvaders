package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.enemies.MiniMeteor;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public interface DaoShoot {
    public List<Shoot> searchShoot();
    public Shoot searchId(Integer id);
    public void refresh (Shoot shoot);
    public void delete (Shoot shoot);
    public void insert (Shoot shoot);
    public void mergeShoot (Shoot shoot);
}
