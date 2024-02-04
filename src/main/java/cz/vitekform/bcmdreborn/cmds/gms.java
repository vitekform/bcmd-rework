package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.functions.configfunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class gms implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String lang = configfunctions.get().getString("lang");
        if (lang.equalsIgnoreCase("cs_cz")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (sender.hasPermission("bcmd.gamemode") || sender.hasPermission("bcmd.admin")){
                    if (args.length != 1){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(ChatColor.GREEN + "Nastavil jsi si gamemode na Survival!");
                    }
                    else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                            Player target = Bukkit.getPlayer(args[0]);
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(ChatColor.GREEN + p.getName() + " ti nastavil gamemode Survival!");
                            p.sendMessage(ChatColor.GREEN + "Nastavil jsi gamemode Survival hráči " + target.getName());
                        }
                        else {
                            p.sendMessage(ChatColor.RED + "Hráč nebyl nalezen!");
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Na toto nemáš práva!\n" + ChatColor.GRAY + "*Takže žádnej gms*");
                    p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                }
            }
            else {
                if (args.length != 1){
                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Pokud tento příkaz používá konzole, musí přidat 1 argument!");
                }
                else {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage(ChatColor.GREEN + "Konzole ti nastavila gamemode Survival!");
                        Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "Nastavil jsi hráči " + target.getName() + " gamemode Survival!");
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Hráč nebyl nalezen!");
                    }
                }
            }
        }
        else if (lang.equalsIgnoreCase("en_us")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (sender.hasPermission("bcmd.gamemode") || sender.hasPermission("bcmd.admin")){
                    if (args.length != 1){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(ChatColor.GREEN + "You have set your gamemode to Survival!");
                    }
                    else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                            Player target = Bukkit.getPlayer(args[0]);
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(ChatColor.GREEN + p.getName() + " set your gamemode to Survival!");
                            p.sendMessage(ChatColor.GREEN + "You have set gamemode Survival to " + target.getName());
                        }
                        else {
                            p.sendMessage(ChatColor.RED + "Player wasn't found!");
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "You don't have permission to do this!\n" + ChatColor.GRAY + "*So no gms*");
                    p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                }
            }
            else {
                if (args.length != 1){
                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "If console uses this command, it must add 1 argument!");
                }
                else {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage(ChatColor.GREEN + "Console set your gamemode to Survival!");
                        Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "You have set gamemode Survival to " + target.getName() + "!");
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Player wasn't found!");
                    }
                }
            }
        }
        return false;
    }
}
