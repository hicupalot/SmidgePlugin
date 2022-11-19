import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class maintenancetoggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        if (Config.maintenaceToggle.isEmpty()) {
            Config.maintenaceToggle.put(((Player) sender).getUniqueId(), true);
            String maintenanceMessage = ChatColor.translateAlternateColorCodes('&', "&cMaintenance Mode Enabled");
            Bukkit.broadcastMessage(maintenanceMessage);
        }
        else{
            Config.maintenaceToggle.clear();
            String maintenanceMessage = ChatColor.translateAlternateColorCodes('&', "&cMaintenance Mode Disabled");
            Bukkit.broadcastMessage(maintenanceMessage);
        }
    return true;
    }
}

