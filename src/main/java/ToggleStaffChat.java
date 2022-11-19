import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStaffChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.staffToggle.containsKey(playerUUID)) {
                Config.staffToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFFCHAT" + ChatColor.RED + "] "
                        + ChatColor.GOLD + "ENABLED");
                //-------------------------------------------------------------------------------------------------//
                if (Config.staffToggle.containsKey(playerUUID)) {
                    Config.staffToggle.remove(playerUUID);
                    sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.BLUE + "STAFFCHAT"
                            + ChatColor.YELLOW + "] " + ChatColor.RED + "DISABLED");
                }
                //------------------------------------------------------------------------------------------------//
                if (Config.staffToggle.containsKey(playerUUID)) {
                    Config.staffToggle.remove(playerUUID);
                    sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "STAFFCHAT" + ChatColor.YELLOW + "] "
                            + ChatColor.RED + "DISABLED");
                }
                //------------------------------------------------------------------------------------------------//
                return true;
            } else Config.staffToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFFCHAT" + ChatColor.RED + "] "
                    + ChatColor.RED + "DISABLED");
            return true;
        }
        String message = ChatColor.RED + "[" + ChatColor.YELLOW + "STAFF" + ChatColor.RED + "] "
                + ((Player) sender).getDisplayName() + ": " + ChatColor.GREEN;
        for (String s : args) {
            message = message + s + " ";
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.staffChat");
    }
        return true;
    }
}