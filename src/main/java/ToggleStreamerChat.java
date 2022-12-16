import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStreamerChat implements CommandExecutor {
    @SuppressWarnings("deprecated")
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
                sender.sendMessage(Config.streamerAdd);
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
            sender.sendMessage(Config.streamerRemove);
            return true;
        }
        String name = ChatColor.translateAlternateColorCodes('&', "&9"+((Player) sender).getDisplayName());
        String message = Config.streamerPrefix +" "
                + name + ": ";
        for (String s : args) {
            message = message + ChatColor.LIGHT_PURPLE+s + " ";
        }
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.streamer");
            return true;
        }
}