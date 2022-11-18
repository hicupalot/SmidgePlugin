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
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        int kickcount = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("smidge.staff")) {
                String kickReason = ChatColor.translateAlternateColorCodes('&',
                        "&cYou were kicked from the Server");
                player.kickPlayer(kickReason);
                kickcount++;
            }
            if (kickcount > 0) {
                String kicknotif = ChatColor.translateAlternateColorCodes('&',
                        "&cKicked " + kickcount + " &cplayers from the server");
                Bukkit.broadcastMessage(kicknotif);
            } else {
                String kickfail = ChatColor.translateAlternateColorCodes('&', "&c[Smidge] " +
                        "&cThis failed as there are no other non-staff players");
                Bukkit.broadcastMessage(kickfail);
            }
        }
        return true;
    }
}
