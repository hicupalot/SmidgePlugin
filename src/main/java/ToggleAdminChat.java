import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleAdminChat implements CommandExecutor {
    @SuppressWarnings("deprecated")
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
                sender.sendMessage(Config.adminAdd);
                //Remove Player From Chat Channels
                if (Config.streamerToggle.containsKey(((Player) sender).getUniqueId())) {
                    Config.streamerToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.streamerRemove);
                    return true;
                }
                if (Config.staffToggle.containsKey(((Player) sender).getUniqueId())) {
                    Config.staffToggle.remove(((Player) sender).getUniqueId());
                    sender.sendMessage(Config.staffRemove);
                    return true;
                }
                //----------------------------------------------------------------------------
                return true;
            } else Config.adminToggle.remove(playerUUID);
            sender.sendMessage(Config.adminRemove);
            return true;
        }
        String name = ChatColor.translateAlternateColorCodes('&', "&4"+((Player) sender).getDisplayName());
        String message = Config.adminPrefix + " "
                    + name + ": ";
            for (String s : args) {
                message = message + ChatColor.GOLD+s + " ";
            }
                String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
                Bukkit.broadcast(colorMessage, "smidge.admin");
                return true;
        }
    }
