package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.BCMD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class help implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        BCMD plugin = BCMD.getPlugin(BCMD.class);
        FileConfiguration config = plugin.getConfig();
        String lang = config.getString("lang");
        if (lang.equalsIgnoreCase("cs_cz")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                p.sendMessage(ChatColor.YELLOW + "Příkazy pluginu BCMD:\n/help - ten příkaz co jsi teď použil\n/timedstop <čas> <časový typ> - Vypne server v určeném času\n/diceroll <kolika stěnná je kostka>\n/whois <hráč> - Vypíše některá data o hráči\n/feed <optional hráč> - Nasití tebe (pokud není argument zadán nebo hráče pokud je zadán argument>\n/heal <optional hráč - Uzdraví tebe (pokud není zadán argument)\n/permafly <optional hráč> - Zapne (nebo vypne) fly tobě (pokud není zadán argument)\n/gma <optional hráč> - Nastaví GameMode Adventure\n/gmc <optional hráč> - Nastaví GameMode Creative\n/gms <optional hráč> - Nastaví GameMode Survival\n/gmsp <optional hráč> - Nastaví GameMode Spectator\n/spectate <hráč> - Spectatuj hráče\n /tempfly - Dočasný fly\n/tempfly admin <hráč> <čas> <časový typ> - Přidá zadanému hráči určitý čas na létání");

            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.YELLOW + "Příkazy pluginu BCMD:\n/help - ten příkaz co jsi teď použil\n/timedstop <čas> <časový typ> - Vypne server v určeném času\n/diceroll <kolika stěnná je kostka>\n/whois <hráč> - Vypíše některá data o hráči\n/feed <optional hráč> - Nasití tebe (pokud není argument zadán nebo hráče pokud je zadán argument>\n/heal <optional hráč - Uzdraví tebe (pokud není zadán argument)\n/permafly <optional hráč> - Zapne (nebo vypne) fly tobě (pokud není zadán argument)\n/gma <optional hráč> - Nastaví GameMode Adventure\n/gmc <optional hráč> - Nastaví GameMode Creative\n/gms <optional hráč> - Nastaví GameMode Survival\n/gmsp <optional hráč> - Nastaví GameMode Spectator\n/spectate <hráč> - Spectatuj hráče\n /tempfly - Dočasný fly\n/tempfly admin <hráč> <čas> <časový typ> - Přidá zadanému hráči určitý čas na létání");
            }
        }
        else if (lang.equalsIgnoreCase("en_us")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                p.sendMessage(ChatColor.YELLOW + "Commands of BCMD:\n/help - That command that you used now\n/timedstop <time> <time type> - Shuts down server in selected time\n/diceroll <cap of dice> - rolls a dice\n/whois <player> - Writes out some info about player\n/feed <optional player> - feeds you (if no argument is provided)\n/heal <optional player - Heals you (if no argument is provided)\n/permafly <optional player> - Enables (or disables) permanent fly\n/gma <optional player> - Sets GameMode Adventure\n/gmc <optional player> - Sets GameMode Creative\n/gms <optional player> - Sets GameMode Survival\n/gmsp <optional player> - Sets GameMode Spectator\n/spectate <player> - Spectate somebody\n /tempfly - Temporary fly\n/tempfly admin <player> <time> <time type> - Adds selected player selected time to tempfly");

            }
            else {
                Bukkit.getLogger().log(Level.INFO, ChatColor.YELLOW + "Commands of BCMD:\n/help - That command that you used now\n/timedstop <time> <time type> - Shuts down server in selected time\n/diceroll <cap of dice> - rolls a dice\n/whois <player> - Writes out some info about player\n/feed <optional player> - feeds you (if no argument is provided)\n/heal <optional player - Heals you (if no argument is provided)\n/permafly <optional player> - Enables (or disables) permanent fly\n/gma <optional player> - Sets GameMode Adventure\n/gmc <optional player> - Sets GameMode Creative\n/gms <optional player> - Sets GameMode Survival\n/gmsp <optional player> - Sets GameMode Spectator\n/spectate <player> - Spectate somebody\n /tempfly - Temporary fly\n/tempfly admin <player> <time> <time type> - Adds selected player selected time to tempfly");
            }
        }
        return false;
    }
}
