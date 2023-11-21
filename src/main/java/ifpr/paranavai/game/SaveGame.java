package ifpr.paranavai.game;

import ifpr.paranavai.game.models.Player;
import ifpr.paranavai.game.models.enemies.Enemy1;
import ifpr.paranavai.game.models.enemies.MiniMeteor;
import ifpr.paranavai.game.models.scenario.Stars;
import ifpr.paranavai.game.models.shoots.Shoot;
import ifpr.paranavai.game.models.shoots.SuperShoot;
import ifpr.paranavai.game.service.*;
import ifpr.paranavai.game.view.Level;

public class SaveGame {
    Enemy1 enemy;

    public void saving() {
        Enemy1Service.insert(enemy);
        LevelService.insert(new Level());
        MiniMeteorService.insert(new MiniMeteor());
        PlayerService.insert(new Player());
        ShootService.insert(new Shoot());
        StarsService.insert(new Stars());
        SuperShootService.insert(new SuperShoot());
    }
}
