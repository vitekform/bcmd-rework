package cz.vitekform.bcmdreborn.handlers;

import cz.vitekform.bcmdreborn.*;
import cz.vitekform.bcmdreborn.functions.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class NightSkipper implements Listener {

    public void skipNight(Player p){
        long curTime = p.getWorld().getTime();
        System.out.println(curTime);
    }

    @EventHandler
    public void onPlayerUsesBed(PlayerBedEnterEvent event){
        BCMD plugin = BCMD.getPlugin(BCMD.class);
        plugin.addPlayerToSleeping();
        int PlayersInBed = plugin.playerThatAreSleeping();
        datafunctions.reload();
        FileConfiguration config = datafunctions.get();
        boolean isPercent = false;
        if (config.getString("skinNightType").equalsIgnoreCase("REQ_PERCENT")){
            isPercent = true;
        }
        if (isPercent){
            int PlayersOnline = Bukkit.getOnlinePlayers().size();
            int percents = config.getInt("skipNightInt");
            double reqPlayersD = Math.ceil((double) PlayersOnline / (double) percents);
            int reqPlayers = (int) reqPlayersD;
            if (PlayersInBed == reqPlayers){
                skipNight(event.getPlayer());
            }
        }
    }
}
