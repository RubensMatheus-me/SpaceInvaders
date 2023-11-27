package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.shoots.SuperShoot;
import ifpr.paranavai.game.view.Level;

import java.util.List;

public interface DaoLevel {
    public List<Level> searchLevel();
    public Level searchId(Integer id);
    public void refresh (Level level);
    public void delete (Level level);
    public void insert (Level level);
    public void saveOrUpdateLevel (Level level);
}
