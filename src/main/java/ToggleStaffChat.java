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
        String message = args[1];
        if (!(sender instanceof Player)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.staffChat")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length == 0) {
            if (!Config.staffToggle.containsKey(playerUUID)) {
                Config.staffToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFFCHAT" + ChatColor.RED + "] " + ChatColor.GOLD + "ENABLED");
            } else Config.staffToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFFCHAT" + ChatColor.RED + "] " + ChatColor.RED + "DISABLED");
            return false;
        }
        if (args.length == 1) {
            Bukkit.broadcast(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFF" + ChatColor.RED + "] " + ((Player) sender).getDisplayName() + ": " + message, "smidge.staffChat");
        }
        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFF" + ChatColor.RED + "] " + ChatColor.DARK_RED + "Invalid Arguments, correct usage /sc (message)");

        }
        return false;
    }
}