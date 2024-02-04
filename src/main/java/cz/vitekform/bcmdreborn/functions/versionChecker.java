package cz.vitekform.bcmdreborn.functions;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

public class versionChecker {

    public void check() throws IOException {
        String version = Bukkit.getPluginManager().getPlugin("BCMD").getDescription().getVersion();
        String latestVersion = getLatestVersion();
        String lang = configfunctions.get().getString("lang");
        if (lang.equalsIgnoreCase("en_us")){
            if (!latestVersion.equalsIgnoreCase(version)){
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Your version of BCMD is outdated!\nLatest version is " + latestVersion + " you can download it at https://url.x10.mx/bcmd-modrinth/ or use command bcmd update!");
            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "Your version of BCMD is up to date!");
            }
        }
        else if (lang.equalsIgnoreCase("cs_cz")){
            if (!latestVersion.equalsIgnoreCase(version)){
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "Vaše verze BCMD je zastaralá!\nNejnovější verze je " + latestVersion + " kterou můžete stáhnout na https://url.x10.mx/bcmd-modrinth/ nebo použít příkaz bcmd update!");
            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "Vaše verze BCMD je nejnovější!");
            }
        }
    }

    public String getLatestVersion() throws IOException{
        String dir = System.getProperty("user.dir");
        String plFolder = dir + "/plugins/BCMD/";
        String tempFolder = plFolder + "/temp";
        String version = Bukkit.getPluginManager().getPlugin("BCMD").getDescription().getVersion();
        if (!new File(tempFolder).isDirectory()){
            new File(tempFolder).mkdirs();
        }
        if (new File(tempFolder + "/latestVersion.txt").exists()){
            new File(tempFolder + "/latestVersion.txt").delete();
        }
        IOUtils.copy(
                new URL("https://dev.vitekform.x10.mx/buildsys/lb-" + version + ".txt"),
                new File(tempFolder + "/latestVersion.txt")
        );
        String latestVersion = functions.getAllLinesFromFile(new File(tempFolder + "/latestVersion.txt"));
        return latestVersion;
    }

    public String getLatestBuild(String version) throws IOException{
        String url = "https://dev.vitekform.x10.mx/buildsys/lb-" + version + ".txt";
        System.out.println(url);
        String dir = System.getProperty("user.dir");
        String plFolder = dir + "/plugins/BCMD/";
        String tempFolder = plFolder + "temp";
        if (!new File(tempFolder).isDirectory()){
            new File(tempFolder).mkdirs();
        }
        if (new File(tempFolder + "/latestBuild.txt").exists()){
            new File(tempFolder + "/latestBuild.txt").delete();
        }
        IOUtils.copy(
                new URL(url),
                new File(tempFolder + "/latestBuild.txt")
        );
        String latestBuild = functions.getAllLinesFromFile(new File(tempFolder + "/latestBuild.txt"));
        return latestBuild;
    }

    public void update() throws IOException{
        String dir = System.getProperty("user.dir");
        String plFolder = dir + "/plugins/BCMD/";
        String downloadURL = "https://dev.vitekform.x10.mx/buildsys/" + getLatestVersion() + "/BCMD-" + getLatestVersion() + "-dev-" + getLatestBuild(getLatestVersion() + ".jar");
        IOUtils.copy(
                new URL(downloadURL),
                new File(plFolder + "BCMD-" + getLatestVersion() + "-dev-" + getLatestBuild(getLatestVersion()) + ".jar")
        );
    }
}
