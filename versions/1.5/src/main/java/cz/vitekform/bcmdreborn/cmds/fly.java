package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.functions.configfunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class fly implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String  s, @NotNull String[] args) {
        String lang = configfunctions.get().getString("lang");
        if (lang.equalsIgnoreCase("cs_cz")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (sender.hasPermission("bcmd.fly") || sender.hasPermission("bcmd.admin")){
                    if (args.length != 1){
                        p.setFlying(true);
                        p.sendMessage(ChatColor.GREEN + "Létání zapnuto");
                    }
                    else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                            Player target = Bukkit.getPlayer(args[0]);
                            target.setFlying(true);
                            target.sendMessage(ChatColor.GREEN + p.getName() + " ti zapnul fly!");
                            p.sendMessage(ChatColor.GREEN + "Zapnul jsi fly hráči " + target.getName());
                        }
                        else {
                            p.sendMessage(ChatColor.RED + "Hráč nebyl nalezen!");
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Na toto nemáš práva!\n" + ChatColor.GRAY + "*Takže žádné létání*");
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
                        target.setFlying(true);
                        target.sendMessage(ChatColor.GREEN + "Konzole ti zapla fly!");
                        Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "Zapnul jsi hráči " + target.getName() + " fly!");
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
                if (sender.hasPermission("bcmd.fly") || sender.hasPermission("bcmd.admin")){
                    if (args.length != 1){
                        p.setFlying(true);
                        p.sendMessage(ChatColor.GREEN + "Flying enabled");
                    }
                    else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                            Player target = Bukkit.getPlayer(args[0]);
                            target.setFlying(true);
                            target.sendMessage(ChatColor.GREEN + p.getName() + " enabled fly for you!");
                            p.sendMessage(ChatColor.GREEN + "You enabled fly of " + target.getName());
                        }
                        else {
                            p.sendMessage(ChatColor.RED + "Player wasn't found!");
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "You don't have permission to do this!\n" + ChatColor.GRAY + "*So no flying :(*");
                    p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                }
            }
            else {
                if (args.length != 1){
                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "If this command is used by console, you must add 1 argument!");
                }
                else {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setFlying(true);
                        target.sendMessage(ChatColor.GREEN + "Console enabled fly for you!");
                        Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "You enabled fly for " + target.getName() + "!");
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
