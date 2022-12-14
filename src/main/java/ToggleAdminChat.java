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
                sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.DARK_RED + "ADMIN" + ChatColor.YELLOW + "] " + ChatColor.GOLD + "ENABLED");
                //Remove Player From Chat Channels
                if (Config.streamerToggle.containsKey(((Player) sender).getUniqueId())) {
                    Config.streamerToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.streamerRemove);
                    return true;
                }
                if (Config.staffToggle.containsKey(((Player) sender).getUniqueId())){
                    Config.staffToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.staffRemove);
                    return true;
                }
                //----------------------------------------------------------------------------
                return true;
            } else Config.adminToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.DARK_RED + "ADMINCHAT" + ChatColor.YELLOW + "] " + ChatColor.RED + "DISABLED");
            return true;
        }
        String message = ChatColor.YELLOW + "[" + ChatColor.DARK_RED + "ADMIN" + ChatColor.YELLOW + "] "
                + ChatColor.BLUE + ((Player) sender).getDisplayName() + ": ";
        for (String s : args) {
            message = message + s + " ";
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.admin");
            return true;
        }
        return true;
    }
}