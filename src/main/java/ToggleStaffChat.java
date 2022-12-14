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
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.staffToggle.containsKey(playerUUID)) {
                Config.staffToggle.put(playerUUID, true);
                sender.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "STAFFCHAT" + ChatColor.RED + "] " + ChatColor.GOLD + "ENABLED");
                //Remove Player From Chat Channels
                if (Config.streamerToggle.containsKey(((Player) sender).getUniqueId())) {
                    Config.streamerToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.streamerRemove);
                    return true;
                }
                if (Config.adminToggle.containsKey(((Player) sender).getUniqueId())){
                    Config.adminToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.adminRemove);
                    return true;
                }
                //----------------------------------------------------------------------------
                return true;
            } else Config.staffToggle.remove(playerUUID);
            sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "STAFFCHAT" + ChatColor.YELLOW + "] " + ChatColor.RED + "DISABLED");
            return true;
        }
        String message = ChatColor.YELLOW + "[" + ChatColor.RED + "STAFF" + ChatColor.YELLOW + "] "
                + ChatColor.GOLD + ((Player) sender).getDisplayName() + ": ";
        for (String s : args) {
            message = message + s + " ";
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.staff");
            return true;
        }
        return true;
    }
}