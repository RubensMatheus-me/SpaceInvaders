package ifpr.paranavai.game.service;

import ifpr.paranavai.game.models.Gamer;
import ifpr.paranavai.game.models.Player;

import java.util.List;

public class testService {
    public static void main(String[] args) {
        Player local = new Player("dasd323s");
        PlayerService.insert(local);
        PlayerService.refresh(local);
        PlayerService.searchPlayer();
        /*
        List<Player> players = PlayerService.searchPlayer();

        players.stream().forEach(p -> {
            System.out.println(p.getName());
        });
        //System.out.println(players);

         */
    }
}
