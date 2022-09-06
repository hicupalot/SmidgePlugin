import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleAdminChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.adminChat")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length <1) {
            if (!Config.adminToggle.containsKey(playerUUID)) {
                Config.adminToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.YELLOW+"["+ChatColor.RED+"ADMINCHAT"+ChatColor.YELLOW+"] " + ChatColor.GOLD + "ENABLED");
                return true;
            } else Config.adminToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.YELLOW+"["+ChatColor.RED+"ADMINCHAT"+ChatColor.YELLOW+"] " + ChatColor.RED + "DISABLED");
            return true;
        }
            String message = ChatColor.YELLOW+"["+ChatColor.RED+"ADMIN"+ChatColor.YELLOW+"] " + ((Player) sender).getDisplayName() + ": "+ChatColor.BLUE;
            for (String s : args) {
                message = message + s + " ";
            }
            Bukkit.broadcast(message, "smidge.staffChat");
        return true;
    }
}
