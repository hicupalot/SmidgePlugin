import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class maintenancetoggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.admin")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (!config.getBoolean("maintenance")){
            config.set("maintenance",true);
            Component maintenanceMessage = Component.text("Maintenance Mode Enabled").color(TextColor.color(254,63,63));
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(maintenanceMessage);
            }

            SmidgeThing.getInstance().saveConfig();
        }
        else{
            config.set("maintenance",false);
            Component maintenanceMessage = Component.text("Maintenance Mode Disabled").color(TextColor.color(254,63,63));
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(maintenanceMessage);
            }
            SmidgeThing.getInstance().saveConfig();
        }
    return true;
    }
}

