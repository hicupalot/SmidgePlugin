import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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
            Component usage = Component.text("[Smidge] Command Usage /convert (time) (units)").color(TextColor.color(190,0,0));
            sender.sendMessage(usage);
            return false;
        }
        if (args.length == 1) {
            Component time = Component.text("[Smidge] Please enter the unit you wish to convert to seconds").color(TextColor.color(190,0,0));
            sender.sendMessage(time);
            return false;
        }
        int time = Integer.parseInt(args[0]);
        String units = args[1];

        if (units.equalsIgnoreCase("minutes")) {
            Component minutes = Component.text("[Smidge] In seconds" + time + " minutes is " + (time*60) + " seconds").color(TextColor.color(217,163,52));
            sender.sendMessage(minutes);
            return false;
        }

        if (units.equalsIgnoreCase("hours")) {
            Component hours = Component.text("[Smidge] In seconds" + time + " hours is " + ((time*60)*60) + " seconds").color(TextColor.color(217,163,52));
            sender.sendMessage(hours);
            return false;
        }

        if (units.equalsIgnoreCase("days")) {
            Component days = Component.text("[Smidge] In seconds" + time + " days is " + (((time*24)*60)*60) + " seconds").color(TextColor.color(217,163,52));
            sender.sendMessage(days);
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
