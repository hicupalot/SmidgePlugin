import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStreamerChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.streamerChat")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length == 0) {
            if (!Config.streamerToggle.containsKey(playerUUID)) {
                Config.streamerToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.RED + "[" + ChatColor.BLUE + "STREAMERCHAT" + ChatColor.RED + "] " + ChatColor.GOLD + "ENABLED");
                return true;
            } else Config.streamerToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.BLUE + "STREAMERCHAT" + ChatColor.RED + "] " + ChatColor.RED + "DISABLED");
            return true;
        }
        String message = args[0];
        if (args.length == 1) {
            Bukkit.broadcast(ChatColor.RED + "[" + ChatColor.BLUE + "STREAMER" + ChatColor.RED + "] " + ((Player) sender).getDisplayName() + ": " + message, "smidge.streamerChat");
            return true;
        }
       return false;
    }
}

