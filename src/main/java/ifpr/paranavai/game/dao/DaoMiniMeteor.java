package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.enemies.MiniMeteor;
import ifpr.paranavai.game.models.shoots.SuperShoot;


import java.util.List;

public interface DaoMiniMeteor {
    public List<MiniMeteor> searchMiniMeteor();
    public MiniMeteor searchId(Integer id);
    public void refresh (MiniMeteor miniMeteor);
    public void delete (MiniMeteor miniMeteor);
    public void insert (MiniMeteor miniMeteor);
    public void mergeMiniMeteor (MiniMeteor miniMeteor);
}
