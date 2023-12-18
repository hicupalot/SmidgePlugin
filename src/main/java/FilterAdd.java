import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FilterAdd implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /filteradd (word) (filterlist)");
            return false;
        }
        if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /filteradd (word) (filterlist)");
            return false;
        }
        if (args.length > 3) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /filteradd (word) (filterlist)");
            return false;
        }
        String word = args[0].toLowerCase();
        String filterlist = args[1].toLowerCase();
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (!filterlist.equals("general") && !filterlist.equals("stream")) {
            sender.sendMessage(ChatColor.RED + "[Smidge] The filter list should be either 'general' or 'stream'");
            return false;
        }
        if (config.getList("general").contains(word) || config.getList("stream").contains(word)) {
            sender.sendMessage(ChatColor.RED + word + " is already added to the filter!");
            return false;
        }
        String successMessage = ChatColor.translateAlternateColorCodes('&', "&6[Smidge] Successfully added " + "&e" +
                word + " &6to the general filter");
        String streamSuccessMessage = ChatColor.translateAlternateColorCodes('&', "&6[Smidge] Successfully added " + "&e" +
                word + " &6to the stream filter");
        if (filterlist.equals("general")) {
            List<String> configList = config.getStringList("general");
            configList.add(word);
            config.set("general", configList);
            SmidgeThing.getInstance().saveConfig();
            sender.sendMessage(successMessage);
            return true;
        } else {
            List<String> configList = config.getStringList("stream");
            configList.add(word);
            config.set("stream", configList);
            SmidgeThing.getInstance().saveConfig();
            sender.sendMessage(streamSuccessMessage);
            return true;
        }
    }
    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            List<String> lists = new ArrayList<>();
            lists.add("stream");
            lists.add("general");
            return lists;
        }
        return null;
    }
}

