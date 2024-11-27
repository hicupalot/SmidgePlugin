import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStreamMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff") || !sender.hasPermission("smidge.admin")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.streamMode.containsKey(playerUUID)) {
                Config.streamMode.put(playerUUID, true);
                sender.sendMessage(Config.streamAdd);
                return true;
            }

        } else
             Config.streamMode.remove(playerUUID);
            sender.sendMessage(Config.streamRemove);
            return true;
        }
}
