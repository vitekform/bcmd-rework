package cz.vitekform.bcmdreborn;

import cz.vitekform.bcmdreborn.cmds.*;
import cz.vitekform.bcmdreborn.functions.datafunctions;
import cz.vitekform.bcmdreborn.functions.configfunctions;
import cz.vitekform.bcmdreborn.handlers.NightSkipper;
import cz.vitekform.bcmdreborn.handlers.spectatedMoveHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;


public final class BCMD extends JavaPlugin {


    public void addPlayerToSleeping(){
        datafunctions.reload();
        FileConfiguration data = datafunctions.get();
        if(!data.contains("sleeping")){
            data.set("sleeping", 0);
            datafunctions.save();
        }
        datafunctions.reload();
        int PlayersSleeping = data.getInt("sleeping");
        data.set("sleeping", PlayersSleeping + 1);
        datafunctions.save();
    }
    public void removePlayerFromSleeping(){
        datafunctions.reload();
        FileConfiguration data = datafunctions.get();
        if(!data.contains("sleeping")){
            data.set("sleeping", 0);
            datafunctions.save();
        }
        datafunctions.reload();
        int PlayersSleeping = data.getInt("sleeping");
        data.set("sleeping", PlayersSleeping - 1);
        datafunctions.save();
    }
    public int playerThatAreSleeping(){
        datafunctions.reload();
        return datafunctions.get().getInt("sleeping");
    }

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
        getCommand("permafly").setExecutor(new fly());
        getCommand("gms").setExecutor(new gms());
        getCommand("gmc").setExecutor(new gmc());
        getCommand("gmsp").setExecutor(new gmsp());
        getCommand("gma").setExecutor(new gma());
        getCommand("spectate").setExecutor(new spectate());
        getCommand("tempfly").setExecutor(new timedFly());
        getCommand("help").setExecutor(new help());
        getServer().getPluginManager().registerEvents(new spectatedMoveHandler(), this);
        getServer().getPluginManager().registerEvents(new NightSkipper(), this);
        super.onEnable();
        getLogger().log(Level.INFO, ChatColor.GREEN + "[BCMD] Plugin was loaded!");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
