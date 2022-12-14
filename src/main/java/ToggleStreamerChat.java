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
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.streamer")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.streamerToggle.containsKey(playerUUID)) {
                Config.streamerToggle.put(playerUUID, true);
                String enabled = ChatColor.translateAlternateColorCodes('&', "&c[&2STREAMER&c] &6ENABLED");
                sender.sendMessage(enabled);
                //Remove Player From Chat Channels
                if (Config.staffToggle.containsKey(((Player) sender).getUniqueId())) {
                    Config.staffToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.staffRemove);
                    return true;
                }
                if (Config.adminToggle.containsKey(((Player) sender).getUniqueId())){
                    Config.adminToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.adminRemove);
                    return true;
                }
                //----------------------------------------------------------------------------
                return true;
            } else Config.streamerToggle.remove(playerUUID);
            String disabled = ChatColor.translateAlternateColorCodes('&', "&e[&9STREAMER&e] &cDISABLED");
            sender.sendMessage(disabled);
            return true;
        }
        String message = ChatColor.YELLOW + "[" + ChatColor.BLUE + "STREAMER" + ChatColor.YELLOW + "] "
                + ChatColor.GOLD + ((Player) sender).getDisplayName() + ": ";
        for (String s : args) {
            message = message + s + " ";
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.streamer");
            return true;
        }
 return true;
    }
}