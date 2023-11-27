package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.scenario.Stars;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public interface DaoStars {
    public List<Stars> searchStars();
    public Stars searchId(Integer id);
    public void refresh (Stars stars);
    public void delete (Stars stars);
    public void insert (Stars stars);
    public void mergeStars (Stars stars);
}
