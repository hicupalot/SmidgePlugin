import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStaffChat implements CommandExecutor {
    @SuppressWarnings("deprecated")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String staffPrefix = ChatColor.translateAlternateColorCodes('&', "&e[&cSTAFFCHAT&e]");
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (Config.streamMode.containsKey(((Player) sender).getUniqueId())) {
            String streamModeDenial = ChatColor.translateAlternateColorCodes('&',"&cYou are currently in Streaming Mode, you cannot enable staff chat");
            sender.sendMessage(streamModeDenial);
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Config.staffToggle.containsKey(playerUUID)) {
                Config.staffToggle.put(playerUUID, true);
                sender.sendMessage(Config.staffAdd);
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
            sender.sendMessage(Config.staffRemove);
        return true;
        }
        String name = ChatColor.translateAlternateColorCodes('&', "&6"+((Player) sender).getDisplayName());
        String message = staffPrefix + " "
                + name + ": ";
        for (String s : args) {
            message = message + ChatColor.GREEN+s + " ";
        }
            String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcast(colorMessage, "smidge.staff");
            return true;
        }
}