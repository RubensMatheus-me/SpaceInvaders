package ifpr.paranavai.game.dao;

import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.shoots.SuperShoot;

import java.util.List;

public interface DaoPlayer {
    public List<Player> searchPlayer();
    public Player searchId(Integer id);
    public void refresh (Player player);
    public void delete (Player player);
    public void insert (Player player);
    public void saveOrUpdatePlayer (Player player);
}
