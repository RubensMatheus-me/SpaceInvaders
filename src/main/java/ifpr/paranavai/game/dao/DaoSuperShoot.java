package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.scenario.Stars;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public interface DaoSuperShoot {
    public List<SuperShoot> searchSuperShoot();
    public SuperShoot searchId(Integer id);
    public void refresh (SuperShoot superShoot);
    public void delete (SuperShoot superShoot);
    public void insert (SuperShoot superShoot);
    public void saveOrUpdateSuperShoot (SuperShoot superShoot);
}
