import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class kickall implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(Config.notPlayerOrConsole);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        int kickcount = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("smidge.staff")) {
                Component kickReason = Component.text("You were kicked from the Server").color(TextColor.color(254,63,63));
                player.kick(kickReason);
                kickcount++;
            }
            if (kickcount > 0) {
                Component kickNotIf = Component.text(kickcount + " were kicked from the server by " + sender.getName()).color(TextColor.color(254,63,63));
                for (Player unKicked : Bukkit.getOnlinePlayers()) {
                    unKicked.sendMessage(kickNotIf);
                }

            } else {
                Component kickFail = Component.text("[Smidge] This failed as there are no other non-staff players").color(TextColor.color(254,63,63));
                sender.sendMessage(kickFail);
            }
        }
        return true;
    }
}
