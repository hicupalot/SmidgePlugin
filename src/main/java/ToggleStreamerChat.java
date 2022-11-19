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
        if (!sender.hasPermission("smidge.streamer")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.streamerToggle.containsKey(playerUUID)) {
                Config.streamerToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.BLUE + "STREAMERCHAT" + ChatColor.YELLOW + "] "
                        + ChatColor.GOLD + "ENABLED");
                //----------------------------------------------------------------------------------------------------//
                if (Config.streamerToggle.containsKey(playerUUID)) {
                    Config.streamerToggle.remove(playerUUID);
                    sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "STREAMERCHAT" + ChatColor.YELLOW + "] "
                            + ChatColor.RED + "DISABLED");
                }
                //---------------------------------------------------------------------------------------------------//
                if (Config.streamerToggle.containsKey(playerUUID)) {
                    Config.streamerToggle.remove(playerUUID);
                    sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STREAMERCHAT" + ChatColor.RED + "] "
                            + ChatColor.RED + "DISABLED");
                }
                //---------------------------------------------------------------------------------------------------//
                return true;
            } else Config.streamerToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.BLUE + "STREAMERCHAT"
                    + ChatColor.YELLOW + "] " + ChatColor.RED + "DISABLED");
            return true;
        }
        String message = ChatColor.YELLOW + "[" + ChatColor.BLUE + "STREAMER" + ChatColor.YELLOW + "] "
                + ChatColor.GOLD + ((Player) sender).getDisplayName() + ": " + ChatColor.LIGHT_PURPLE;
        for (String s : args) {
            message = message + s + " ";
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.streamer");
            return true;
        }
  return true;
    }
}