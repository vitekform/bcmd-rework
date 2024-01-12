package cz.vitekform.bcmdreborn.cmds;

import cz.vitekform.bcmdreborn.functions.versionChecker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class bcmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            if (args.length == 1){
                String arg = args[0];
                if (arg.equalsIgnoreCase("update")){
                    versionChecker VersionChecker = new versionChecker();
                    try {
                        VersionChecker.update();
                    }   catch (IOException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return false;
    }
}
