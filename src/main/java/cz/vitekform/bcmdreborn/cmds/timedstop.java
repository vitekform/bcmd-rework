package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.BCMD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class timedstop implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        BCMD plugin = BCMD.getPlugin(BCMD.class);
        FileConfiguration config = plugin.getConfig();
        String lang = config.getString("lang");
        if (sender instanceof ConsoleCommandSender){
            if (sender.hasPermission("bcmd.timedstop") || sender.hasPermission("bcmd.admin")){
                if (lang.equalsIgnoreCase("cs_cz")){
                    if (args.length == 2){
                        int t = Integer.parseInt(args[0]);
                        String type = args[1];
                        if (type.equalsIgnoreCase("d") || type.equalsIgnoreCase("days") || type.equalsIgnoreCase("day") || type.equalsIgnoreCase("dny") || type.equalsIgnoreCase("den")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server se vypne za " + t + " dní");
                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Vypínání server!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * 60L * 60L * 24L * t);
                        }
                        else if (type.equalsIgnoreCase("h") || type.equalsIgnoreCase("hours") || type.equalsIgnoreCase("hour") || type.equalsIgnoreCase("hodiny") || type.equalsIgnoreCase("hodina")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server se vypne za " + t + " hodin");

                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Vypínání server!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * 60L * 60L * t);
                        }
                        else if (type.equalsIgnoreCase("m") || type.equalsIgnoreCase("minutes") || type.equalsIgnoreCase("minute") || type.equalsIgnoreCase("minuta") || type.equalsIgnoreCase("minuty")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server se vypne za " + t + " minut");

                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Vypínání server!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * 60L * t);
                        }
                        else if (type.equalsIgnoreCase("s") || type.equalsIgnoreCase("second") || type.equalsIgnoreCase("seconds") || type.equalsIgnoreCase("sekunda") || type.equalsIgnoreCase("sekundy")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server se vypne za " + t + " sekund");

                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Vypínání server!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * t);
                        }
                        else {
                            Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Neznámý časový typ!");
                        }
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Musíš zadat 2 argumenty!");
                    }
                }
                else {
                    if (args.length == 2){
                        int t = Integer.parseInt(args[0]);
                        String type = args[1];
                        if (type.equalsIgnoreCase("d") || type.equalsIgnoreCase("days") || type.equalsIgnoreCase("day") || type.equalsIgnoreCase("dny") || type.equalsIgnoreCase("den")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server will stop in " + t + " days!");
                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Shuting server down!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * 60L * 60L * 24L * t);
                        }
                        else if (type.equalsIgnoreCase("h") || type.equalsIgnoreCase("hours") || type.equalsIgnoreCase("hour") || type.equalsIgnoreCase("hodiny") || type.equalsIgnoreCase("hodina")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server will stop in " + t + " hours!");
                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Shuting server down!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * 60L * 60L * t);
                        }
                        else if (type.equalsIgnoreCase("m") || type.equalsIgnoreCase("minutes") || type.equalsIgnoreCase("minute") || type.equalsIgnoreCase("minuta") || type.equalsIgnoreCase("minuty")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server will stop in " + t + " minutes!");
                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Shuting server down!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * 60L * t);
                        }
                        else if (type.equalsIgnoreCase("s") || type.equalsIgnoreCase("second") || type.equalsIgnoreCase("seconds") || type.equalsIgnoreCase("sekunda") || type.equalsIgnoreCase("sekundy")){
                            Bukkit.broadcastMessage(ChatColor.RED + "Server will stop in " + t + " seconds!");
                            new BukkitRunnable(){

                                @Override
                                public void run() {
                                    Bukkit.broadcastMessage(ChatColor.RED + "Shuting server down!");
                                    Bukkit.shutdown();
                                }
                            }.runTaskLaterAsynchronously(plugin, 20L * t);
                        }
                        else {
                            Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Unknown time type!");
                        }
                    }
                    else {
                        Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "You must provide 2 arguments!");
                    }
                }
            }
        }
        else {
            Player p = (Player) sender;
            if (lang.equalsIgnoreCase("cs_cz")){
                p.sendMessage(ChatColor.RED + "Tento příkaz může použít pouze konzole!");
            }
            else if (lang.equalsIgnoreCase("en_us")){
                p.sendMessage(ChatColor.RED + "This command can use only console!");
            }
        }

        return false;
    }
}
