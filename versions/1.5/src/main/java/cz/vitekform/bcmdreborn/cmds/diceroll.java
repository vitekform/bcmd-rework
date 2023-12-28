package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.functions.functions;
import cz.vitekform.bcmdreborn.functions.configfunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.logging.Level;

public class diceroll implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String lang = configfunctions.get().getString("lang");
        if (lang.equalsIgnoreCase("en_us")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (args.length == 1){
                    if (functions.isInteger(args[0])){
                        int max_int = Integer.parseInt(args[0]);
                        Random rnd = new Random();
                        int i = rnd.nextInt(max_int);
                        if (i >= 1){
                            //If anybody discovers how to remove this if please DM me on discord (ganamaga) or E-Mail me vitekform@pumpkinmc.x10.mx
                        }
                        else {
                            i = max_int;
                        }
                        p.sendMessage(ChatColor.GREEN + "You have rolled " + i);
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "Argument must be integer!");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "You must provide 1 argument!");
                }
            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "This command must use player!");
            }
        }
        else if (lang.equalsIgnoreCase("cs_cz")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (args.length == 1){
                    if (functions.isInteger(args[0])){
                        int max_int = Integer.parseInt(args[0]);
                        Random rnd = new Random();
                        int i = rnd.nextInt(max_int);
                        if (i >= 1){
                            //If anybody discovers how to remove this if please DM me on discord (ganamaga) or E-Mail me vitekform@pumpkinmc.x10.mx
                        }
                        else {
                            i = max_int;
                        }
                        p.sendMessage(ChatColor.GREEN + "Hodil jsi " + i);
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "Argument musí být celé číslo!");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Musíš zadat 1 argument!");
                }
            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "This command must use player!");
            }
        }
        return false;
    }
}
