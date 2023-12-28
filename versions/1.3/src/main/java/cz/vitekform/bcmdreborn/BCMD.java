package cz.vitekform.bcmdreborn;

import cz.vitekform.bcmdreborn.cmds.*;
import cz.vitekform.bcmdreborn.functions.datafunctions;
import cz.vitekform.bcmdreborn.functions.configfunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class BCMD extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        configfunctions.setup();
        configfunctions.get().options().copyDefaults(true);
        configfunctions.save();
        datafunctions.setup();
        datafunctions.get().options().copyDefaults(true);
        datafunctions.save();
        String lang = configfunctions.get().getString("lang");

        if (lang.equalsIgnoreCase("cs_cz")){
            Bukkit.getLogger().log(Level.INFO, ChatColor.YELLOW + "Načítání české verze pluginu...");
        }
        else if (lang.equalsIgnoreCase("en_us")){
            Bukkit.getLogger().log(Level.INFO, ChatColor.YELLOW + "Loading english version of plugin...");
        }
        else {
            Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "ERROR! Language not found");
            configfunctions.get().set("lang", "'en_us'");
            configfunctions.save();
            Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Language set to en_us");
        }
        getCommand("timedstop").setExecutor(new timedstop());
        getCommand("diceroll").setExecutor(new diceroll());
        getCommand("whois").setExecutor(new whois());
        getCommand("feed").setExecutor(new feed());
        getCommand("heal").setExecutor(new heal());
        getCommand("fly").setExecutor(new fly());
        getCommand("gms").setExecutor(new gms());
        getCommand("gmc").setExecutor(new gmc());
        getCommand("gmsp").setExecutor(new gmsp());
        getCommand("gma").setExecutor(new gma());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
