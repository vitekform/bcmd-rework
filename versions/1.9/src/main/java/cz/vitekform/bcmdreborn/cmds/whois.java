package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.functions.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class whois implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String lang = configfunctions.get().getString("lang");
        if (lang.equalsIgnoreCase("en_us")){
            if (sender.hasPermission("bcmd.whois") || sender.hasPermission("bcmd.admin")){
                if (args.length != 1){
                    if (sender instanceof Player){
                        Player p = (Player) sender;
                        p.sendMessage(ChatColor.RED + "You must provide 1 argument!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "You must provide 1 argument!");
                    }
                }
                else {
                    String arg = args[0];
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arg))){
                        Player target = Bukkit.getPlayer(arg);
                        String ip = target.getAddress().toString();
                        String customname = target.getCustomName();
                        String orig_name = target.getName();
                        boolean isop = target.isOp();
                        boolean isflying = target.isFlying();
                        boolean isgod = functions.isGod(target);
                        double balance;
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.GOLD + "Data of " + orig_name + "\n Is op: " + String.valueOf(isop) + "\n Is flying: " + String.valueOf(isflying) + "\n Is god: " + isgod + "\n IP: " + ip + "\n Custom Name:" + customname);
                        }
                    }
                    else {
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.RED + "Player not found!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                        }
                        else {
                            Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Player not found!");
                        }
                    }
                }
            }
            else {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "You don't have permission to do this and don't try it!");
                p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                return false;
            }
        }
        else if (lang.equalsIgnoreCase("cs_cz")){
            if (sender.hasPermission("bcmd.whois") || sender.hasPermission("bcmd.admin")){
                if (args.length != 1){
                    if (sender instanceof Player){
                        Player p = (Player) sender;
                        p.sendMessage(ChatColor.RED + "Musíš zadat 1 argument!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Musíš zadat 1 argument!");
                    }
                }
                else {
                    String arg = args[0];
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arg))){
                        Player target = Bukkit.getPlayer(arg);
                        String ip = target.getAddress().toString();
                        String customname = target.getCustomName();
                        String orig_name = target.getName();
                        boolean isop = target.isOp();
                        boolean isflying = target.isFlying();
                        boolean isgod = functions.isGod(target);
                        double balance;
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.GOLD + "Data hráče " + orig_name + "\n Je op: " + String.valueOf(isop) + "\n Léta: " + String.valueOf(isflying) + "\n Je v god-modu: " + isgod + "\n IP: " + ip + "\n Upravené jméno:" + customname);
                        }
                    }
                    else {
                        if (sender instanceof Player){
                            Player p = (Player) sender;
                            p.sendMessage(ChatColor.RED + "Hráč nebyl nalezen!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                        }
                        else {
                            Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Hráč nebyl nalezen!");
                        }
                    }
                }
            }
            else {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "Na tohle nemáš právo a nezkoušej to!");
                p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                return false;
            }
        }
        return false;
    }
}
