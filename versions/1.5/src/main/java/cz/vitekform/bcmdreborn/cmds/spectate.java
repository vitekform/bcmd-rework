package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.BCMD;
import cz.vitekform.bcmdreborn.functions.configfunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class spectate implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String lang = configfunctions.get().getString("lang");
        if (lang.equalsIgnoreCase("cs_cz")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (p.hasPermission("bcmd.spectate") || p.hasPermission("bcmd.admin")) {
                    if (args.length != 1){
                        p.sendMessage(ChatColor.RED + "Musíš zadat 1 argument!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                    }
                    else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                            Player target = Bukkit.getPlayer(args[0]);
                            BCMD plugin = BCMD.getPlugin(BCMD.class);
                            p.setMetadata("spectating", new FixedMetadataValue(plugin, target.getName()));
                            p.setGameMode(GameMode.SPECTATOR);
                            p.teleport(target);
                            p.sendMessage(ChatColor.YELLOW + "Spectating vypneš příkazem /spectate cancel");
                        }
                        else {
                            if (args[0].equalsIgnoreCase("cancel")){
                                BCMD plugin = BCMD.getPlugin(BCMD.class);
                                p.removeMetadata("spectating", plugin);
                                p.sendMessage(ChatColor.RED + "Spectatování bylo vypnuto!");
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                            p.sendMessage(ChatColor.RED + "Hráč nebyl nalezen!");
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Na tohle nemáš práva!");
                }
            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Tento příkaz může použít pouze hráč!");
            }
        }
        else if (lang.equalsIgnoreCase("en_us")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (p.hasPermission("bcmd.spectate") || p.hasPermission("bcmd.admin")) {
                    if (args.length != 1){
                        p.sendMessage(ChatColor.RED + "You must provide 1 argument!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                    }
                    else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                            Player target = Bukkit.getPlayer(args[0]);
                            BCMD plugin = BCMD.getPlugin(BCMD.class);
                            p.setMetadata("spectating", new FixedMetadataValue(plugin, target.getName()));
                            p.setGameMode(GameMode.SPECTATOR);
                            p.teleport(target);
                            p.sendMessage(ChatColor.YELLOW + "Spectating can be disabled by using command /spectate cancel");
                        }
                        else {
                            if (args[0].equalsIgnoreCase("cancel")){
                                BCMD plugin = BCMD.getPlugin(BCMD.class);
                                p.removeMetadata("spectating", plugin);
                                p.sendMessage(ChatColor.RED + "Spectating was disabled!");
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                            p.sendMessage(ChatColor.RED + "Player wasn't found!");
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                }
            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Only players can use this command!");
            }
        }

        return false;
    }
}
