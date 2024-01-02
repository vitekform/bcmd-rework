package cz.vitekform.bcmdreborn.handlers;

import cz.vitekform.bcmdreborn.BCMD;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class spectatedMoveHandler implements Listener {

    @EventHandler
    public void onSpectatorPlayerMoves(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if (p.hasMetadata("spectating")){
            Player spectated = Bukkit.getPlayer(p.getMetadata("spectating").get(0).asString());
            int difference_x = 0;
            int difference_y = 0;
            int difference_z = 0;
            Location spectated_loc = spectated.getLocation();
            Location spectator_loc = p.getLocation();
            difference_x = spectator_loc.getBlockX() - spectated_loc.getBlockX();
            difference_x = Math.abs(difference_x);
            difference_y = spectator_loc.getBlockY() - spectated_loc.getBlockY();
            difference_y = Math.abs(difference_y);
            difference_z = spectator_loc.getBlockZ() - spectated_loc.getBlockZ();
            difference_z = Math.abs(difference_z);
            int totalDifference = difference_x + difference_y + difference_z;
            BCMD plugin = BCMD.getPlugin(BCMD.class);
            FileConfiguration config = plugin.getConfig();
            int maxDistance = config.getInt("spectator_radius");
            if (totalDifference >= maxDistance){
                event.setCancelled(true);
                p.teleport(spectated);
            }
        }
    }
}
