package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.BCMD;
import cz.vitekform.bcmdreborn.functions.datafunctions;
import cz.vitekform.bcmdreborn.functions.functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class timedFly implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        BCMD plugin = BCMD.getPlugin(BCMD.class);
        FileConfiguration config = plugin.getConfig();
        FileConfiguration data = datafunctions.get();
        String lang = config.getString("lang");
        if (lang.equalsIgnoreCase("cs_cz")){
            if (sender instanceof Player){
                if (args.length == 0){
                    Player p = (Player) sender;
                    if (p.isFlying()){
                        p.sendMessage(ChatColor.RED + "Vypnul jsi si tempfly!");
                        p.setFlying(false);
                        p.setAllowFlight(false);
                        data.set(p.getName() + ".isFlying", false);
                    }
                    if (data.contains(p.getName() + ".flyTime")){
                        int flyTime = data.getInt(p.getName() + ".flyTime");
                        if (flyTime == 0){
                            p.sendMessage(ChatColor.RED + "Nemáš žádný zbývající čas na létání");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                        }
                        else {
                            p.sendMessage(ChatColor.GREEN + "Zapnul jsi si fly!");
                            p.sendMessage(ChatColor.YELLOW + "Zbývá ti fly na : " + flyTime / 60D + " minut!");
                            data.set(p.getName() + ".isFlying", true);
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    datafunctions.reload();
                                    FileConfiguration nowData = datafunctions.get();
                                    if (nowData.getBoolean(p.getName() + ".isFlying")){
                                        return;
                                    }
                                    int remTime = nowData.getInt(p.getName() + ".flyTime");
                                    if (remTime == 0){
                                        p.sendMessage(ChatColor.RED + "Došel ti fly!");
                                        p.setFlying(false);
                                        p.setAllowFlight(false);
                                        return;
                                    }
                                    else if (remTime == 5){
                                        p.sendMessage(ChatColor.YELLOW + "Zbývá ti 5 sekund!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    else if (remTime == 15){
                                        p.sendMessage(ChatColor.YELLOW + "Zbývá ti 15 sekund!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    else if (remTime == 30){
                                        p.sendMessage(ChatColor.YELLOW + "Zbývá ti 30 sekund!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    else if (remTime == 60){
                                        p.sendMessage(ChatColor.YELLOW + "Zbývá ti 1 minuta!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    nowData.set(p.getName() + ".flyTime", remTime - 1);
                                    datafunctions.save();
                                }
                            }.runTaskTimerAsynchronously(plugin, 0, 20);
                            return false;
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "Nemáš žádný zbývající čas na létání");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                    }
                }
                else if (args.length == 4){
                    if (sender.hasPermission("bcmd.admin")){
                        Player p = (Player) sender;
                        if (args[0].equalsIgnoreCase("admin")){
                            if (functions.isInteger(args[2])){
                                int t = Integer.parseInt(args[2]);
                                if (args[3].equalsIgnoreCase("s")){

                                }
                                else if (args[3].equalsIgnoreCase("m") || args[3].equalsIgnoreCase("minute") || args[3].equalsIgnoreCase("minutes")){
                                    t = t * 60;
                                }
                                else if (args[3].equalsIgnoreCase("h") || args[3].equalsIgnoreCase("hours") || args[3].equalsIgnoreCase("hour")){
                                    t = t * 60 * 60;
                                }
                                else {
                                    p.sendMessage(ChatColor.RED + "Čtvrtý argument musí být s, m nebo h");
                                    return false;
                                }
                                p.sendMessage(ChatColor.GREEN + "Přidal jsi hráči " + args[1] + " " + t + " sekund na létání!");
                                int curTime = 0;
                                if (data.contains(args[1] + ".flyTime")){
                                    curTime = data.getInt(args[1] + ".flyTime");
                                }
                                int totalTime = curTime + t;
                                data.set(args[1] + ".flyTime", totalTime);
                            }
                            else {
                                p.sendMessage(ChatColor.RED + "Třetí argument musí být číslo!");
                            }
                        }
                    }
                }
            }
            else {
                if (args.length == 4){
                    if (sender.hasPermission("bcmd.admin")){
                        if (args[0].equalsIgnoreCase("admin")){
                            if (functions.isInteger(args[2])){
                                int t = Integer.parseInt(args[2]);
                                if (args[3].equalsIgnoreCase("s")){

                                }
                                else if (args[3].equalsIgnoreCase("m") || args[3].equalsIgnoreCase("minute") || args[3].equalsIgnoreCase("minutes")){
                                    t = t * 60;
                                }
                                else if (args[3].equalsIgnoreCase("h") || args[3].equalsIgnoreCase("hours") || args[3].equalsIgnoreCase("hour")){
                                    t = t * 60 * 60;
                                }
                                else {
                                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Čtvrtý argument musí být s, m nebo h");
                                    return false;
                                }
                                Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "Přidal jsi hráči " + args[1] + " " + t + " sekund na létání!");
                                int curTime = 0;
                                if (data.contains(args[1] + ".flyTime")){
                                    curTime = data.getInt(args[1] + ".flyTime");
                                }
                                int totalTime = curTime + t;
                                data.set(args[1] + ".flyTime", totalTime);
                            }
                            else {
                                Bukkit.getLogger().log(Level.INFO,ChatColor.RED + "Třetí argument musí být číslo!");
                            }
                        }
                    }
                }
                else {
                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Pokud tento příkaz používá konzole, Musí přidat 4 argumenty!");
                }
            }
        }
        else if (lang.equalsIgnoreCase("en_us")){
            if (sender instanceof Player){
                if (args.length == 0){
                    Player p = (Player) sender;
                    if (p.isFlying()){
                        p.sendMessage(ChatColor.RED + "You have turned tempfly off!");
                        p.setFlying(false);
                        data.set(p.getName() + ".isFlying", false);
                    }
                    if (data.contains(p.getName() + ".flyTime")){
                        int flyTime = data.getInt(p.getName() + ".flyTime");
                        if (flyTime == 0){
                            p.sendMessage(ChatColor.RED + "You don't have flight time anymore!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                        }
                        else {
                            p.sendMessage(ChatColor.GREEN + "You have enabled tempfly!");
                            p.sendMessage(ChatColor.YELLOW + String.valueOf(flyTime / 60D) + " minutes of flight left!");
                            data.set(p.getName() + ".isFlying", true);
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    datafunctions.reload();
                                    FileConfiguration nowData = datafunctions.get();
                                    if (nowData.getBoolean(p.getName() + ".isFlying")){
                                        return;
                                    }
                                    int remTime = nowData.getInt(p.getName() + ".flyTime");
                                    if (remTime == 0){
                                        p.sendMessage(ChatColor.RED + "You don!t have flight time anymore!");
                                        p.setFlying(false);
                                        p.setAllowFlight(false);
                                        return;
                                    }
                                    else if (remTime == 5){
                                        p.sendMessage(ChatColor.YELLOW + "5 seconds left!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    else if (remTime == 15){
                                        p.sendMessage(ChatColor.YELLOW + "15 seconds left!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    else if (remTime == 30){
                                        p.sendMessage(ChatColor.YELLOW + "30 seconds left!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    else if (remTime == 60){
                                        p.sendMessage(ChatColor.YELLOW + "1 minute left!");
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 100);
                                    }
                                    nowData.set(p.getName() + ".flyTime", remTime - 1);
                                }
                            }.runTaskTimerAsynchronously(plugin, 0, 20);
                            return false;
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "You don't have flight time!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 100, 100);
                    }
                }
                else if (args.length == 4){
                    if (sender.hasPermission("bcmd.admin")){
                        Player p = (Player) sender;
                        if (args[0].equalsIgnoreCase("admin")){
                            if (functions.isInteger(args[2])){
                                int t = Integer.parseInt(args[2]);
                                if (args[3].equalsIgnoreCase("s")){

                                }
                                else if (args[3].equalsIgnoreCase("m") || args[3].equalsIgnoreCase("minute") || args[3].equalsIgnoreCase("minutes")){
                                    t = t * 60;
                                }
                                else if (args[3].equalsIgnoreCase("h") || args[3].equalsIgnoreCase("hours") || args[3].equalsIgnoreCase("hour")){
                                    t = t * 60 * 60;
                                }
                                else {
                                    p.sendMessage(ChatColor.RED + "4th argument must be s, m or h!");
                                    return false;
                                }
                                p.sendMessage(ChatColor.GREEN + "You have added  " + t + " seconds of flight to " + args[1] + "!");
                                int curTime = 0;
                                if (data.contains(args[1] + ".flyTime")){
                                    curTime = data.getInt(args[1] + ".flyTime");
                                }
                                int totalTime = curTime + t;
                                data.set(args[1] + ".flyTime", totalTime);
                                datafunctions.save();
                            }
                            else {
                                p.sendMessage(ChatColor.RED + "3rd argument must be Integer!");
                            }
                        }
                    }
                }
            }
            else {
                if (args.length == 4){
                    if (sender.hasPermission("bcmd.admin")){
                        if (args[0].equalsIgnoreCase("admin")){
                            if (functions.isInteger(args[2])){
                                int t = Integer.parseInt(args[2]);
                                if (args[3].equalsIgnoreCase("s")){

                                }
                                else if (args[3].equalsIgnoreCase("m") || args[3].equalsIgnoreCase("minute") || args[3].equalsIgnoreCase("minutes")){
                                    t = t * 60;
                                }
                                else if (args[3].equalsIgnoreCase("h") || args[3].equalsIgnoreCase("hours") || args[3].equalsIgnoreCase("hour")){
                                    t = t * 60 * 60;
                                }
                                else {
                                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "4th argument must be s, m or h!");
                                    return false;
                                }
                                Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "You have added  " + t + " seconds of flight to " + args[1] + "!");
                                int curTime = 0;
                                if (data.contains(args[1] + ".flyTime")){
                                    curTime = data.getInt(args[1] + ".flyTime");
                                }
                                int totalTime = curTime + t;
                                data.set(args[1] + ".flyTime", totalTime);
                                datafunctions.save();
                            }
                            else {
                                Bukkit.getLogger().log(Level.INFO,ChatColor.RED + "3rd argument must be Integer!");
                            }
                        }
                    }
                }
                else {
                    Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "If console uses this it must provide 4 arguments!");
                }
            }
        }
        return false;
    }
}
