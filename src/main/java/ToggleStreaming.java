import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ToggleStreaming implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage /togglestreaming");
            return false;
        }
        String streamToggle = ChatColor.translateAlternateColorCodes('&', sender.getName() + " &chas toggled the streaming filter:");
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (config.getBoolean("isStreaming")) {
            config.set("isStreaming", false);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("smidge.staff")) {
                    Bukkit.broadcastMessage(streamToggle + " OFF");
                }
            }
            SmidgeThing.getInstance().saveConfig();
        } else if (!config.getBoolean("isStreaming")) {
            config.set("isStreaming", true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("smidge.staff")) {
                    Bukkit.broadcastMessage(streamToggle + " ON");
                    SmidgeThing.getInstance().saveConfig();
                }
            }
        }
 return true;
    }
}
