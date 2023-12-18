import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FilterRemove implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /filterremove (word) (filterlist)");
            return false;
        }
        String word = args[0].toLowerCase();
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (!config.getList("general").contains(word) && !config.getList("stream").contains(word)) {
            sender.sendMessage(ChatColor.RED + word + " isn't currently filtered!");
            return false;
        }
        String successMessage = ChatColor.translateAlternateColorCodes('&', "&6[Smidge] Successfully unfiltered " +
                "&e" + word);
        List<String> configList = config.getStringList("general");
        configList.remove(word);
        config.set("general", configList);
        List<String> configList2 = config.getStringList("stream");
        configList2.remove(word);
        config.set("stream", configList);
        SmidgeThing.getInstance().saveConfig();
        sender.sendMessage(successMessage);

        return true;
    }
}
