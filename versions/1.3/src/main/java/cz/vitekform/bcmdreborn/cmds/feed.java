package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.functions.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class feed implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String lang = configfunctions.get().getString("lang");
        assert lang != null;
        if (lang.equalsIgnoreCase("en_us")){
            if (sender.hasPermission("bcmd.admin") || sender.hasPermission("bcmd.feed")){
                if (args.length != 1){
                    if (sender instanceof Player){
                        Player p = (Player) sender;
                        p.setHealth(p.getMaxHealth());
                        p.sendMessage(ChatColor.GREEN + "You have been fed");
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "You must provide 1 argument!");
                    }
                }
                else {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        target.setFoodLevel(20);
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.GREEN + "You have fed " + target.getName());
                            target.sendMessage(ChatColor.GREEN + "You have been fed by " + p.getName());
                            target.playSound(target, Sound.ENTITY_PLAYER_BURP, 100, 100);
                        }
                        else {
                            Bukkit.getLogger().log(Level.INFO,ChatColor.GREEN + "You have fed " + target.getName());
                            target.sendMessage(ChatColor.GREEN + "You have been fed by Almighty Console");
                            target.playSound(target, Sound.ENTITY_PLAYER_BURP, 100, 100);
                        }
                    }
                    else {
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.RED + "Target player wasn't found!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                        }
                    }
                }
            }
            else {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "You don't have permission and don't try it!");
            }

        }
        else if (lang.equalsIgnoreCase("cs_cz")){
            if (sender.hasPermission("bcmd.admin") || sender.hasPermission("bcmd.feed")){
                if (args.length != 1){
                    if (sender instanceof Player){
                        Player p = (Player) sender;
                        p.setHealth(p.getMaxHealth());
                        p.sendMessage(ChatColor.GREEN + "Byl jsi nasycen");
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Musíš zadat 1 argument!");
                    }
                }
                else {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        target.setFoodLevel(20);
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.GREEN + "Nasitil jsi " + target.getName());
                            target.sendMessage(ChatColor.GREEN + "Byl jsi nasycen hráčem " + p.getName());
                            target.playSound(target, Sound.ENTITY_PLAYER_BURP, 100, 100);
                        }
                        else {
                            Bukkit.getLogger().log(Level.INFO,ChatColor.GREEN + "You have fed " + target.getName());
                            target.sendMessage(ChatColor.GREEN + "Byl jsi nasycen všemocnou konzolí");
                            target.playSound(target, Sound.ENTITY_PLAYER_BURP, 100, 100);
                        }
                    }
                    else {
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.RED + "Zadaný hráč nebyl nalezen!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                        }
                    }
                }
            }
            else {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "Na tohle nemáš právo a ani to nezkoušej!");
            }
        }
        return false;
    }
}
