import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class maintenancetoggle implements CommandExecutor {
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

