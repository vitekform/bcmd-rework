package cz.vitekform.bcmdreborn.functions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class configfunctions {

    private static File f;
    private static FileConfiguration config;


    public static void setup(){
        f = new File(Bukkit.getServer().getPluginManager().getPlugin("BCMD").getDataFolder(), "config.yml");

        if (!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = YamlConfiguration.loadConfiguration(f);

    }

    public static FileConfiguration get(){
        return config;
    }

    public static void save(){
        try {
            config.save(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reload(){
        config = YamlConfiguration.loadConfiguration(f);
    }
}
