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

public class ToggleStreaming implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.staff") || !sender.hasPermission("smidge.admin") || !sender.hasPermission("smidge.streamer")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (args.length > 1) {
            Component usage = Component.text("[Smidge] Command Usage /togglestreaming").color(TextColor.color(190,0,0));
            sender.sendMessage(usage);
            return false;
        }
        Component streamToggleOff = Component.text(sender.getName() + " has toggled OFF the streaming filter!").color(TextColor.color(254,63,63));
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (config.getBoolean("isStreaming")) {
            config.set("isStreaming", false);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("smidge.staff")) {
                    player.sendMessage(streamToggleOff);
                }
            }
            SmidgeThing.getInstance().saveConfig();
        } else if (!config.getBoolean("isStreaming")) {
            Component streamToggleOn = Component.text(sender.getName() + " has toggled ON the streaming filter!").color(TextColor.color(254,63,63));
            config.set("isStreaming", true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("smidge.staff")) {
                    player.sendMessage(streamToggleOn);
                    SmidgeThing.getInstance().saveConfig();
                }
            }
        }
 return true;
    }
}
