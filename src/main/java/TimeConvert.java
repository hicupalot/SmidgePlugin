import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TimeConvert implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /convert (time) (units)");
            return false;
        }
        if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Please enter the unit you wish to convert to seconds");
            return false;
        }
        int time = Integer.parseInt(args[0]);
        String units = args[1];

        if (units.equalsIgnoreCase("minutes")) {
            sender.sendMessage(ChatColor.GOLD + "[Smidge] In seconds " + time + " minutes is " + (time * 60) + " seconds");
            return false;
        }

        if (units.equalsIgnoreCase("hours")) {
            sender.sendMessage(ChatColor.GOLD + "[Smidge] In seconds " + time + " hours is " + ((time * 60)*60) + " seconds");
            return false;
        }

        if (units.equalsIgnoreCase("days")) {
            sender.sendMessage(ChatColor.GOLD + "[Smidge] In seconds " + time + " days is " + (((time * 24)*60)*60) + " seconds");
            return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            List<String> unitNames = new ArrayList<>();
            unitNames.add("minutes");
            unitNames.add("hours");
            unitNames.add("days");
            return unitNames;
        }
        return null;
    }
}
