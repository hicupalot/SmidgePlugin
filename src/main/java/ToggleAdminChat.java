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
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.adminToggle.containsKey(playerUUID)) {
                Config.adminToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "ADMINCHAT" + ChatColor.YELLOW + "] "
                        + ChatColor.GOLD + "ENABLED");
                //-----------------------------------------------------------------------------------------//
                if (Config.adminToggle.containsKey(playerUUID)) {
                    Config.adminToggle.remove(playerUUID);
                    sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.BLUE + "ADMINCHAT"
                            + ChatColor.YELLOW + "] " + ChatColor.RED + "DISABLED");
                }
                //-----------------------------------------------------------------------------------------//
                if (Config.adminToggle.containsKey(playerUUID)) {
                    Config.adminToggle.remove(playerUUID);
                    sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "ADMINCHAT" + ChatColor.RED + "] "
                            + ChatColor.RED + "DISABLED");
                }
                //---------------------------------------------------------------------------------------------//
                return true;
            } else Config.adminToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "ADMINCHAT" + ChatColor.YELLOW + "] "
                    + ChatColor.RED + "DISABLED");
            return true;
        }
        String message = ChatColor.YELLOW + "[" + ChatColor.RED + "ADMIN" + ChatColor.YELLOW + "] "
                + ((Player) sender).getDisplayName() + ": " + ChatColor.YELLOW;
        for (String s : args) {
            message = message + s + " ";
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.admin");
            return true;
        }
    return true;
    }
}
