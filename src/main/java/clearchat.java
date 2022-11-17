import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class clearchat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("You may not do this!");
            return false;
        }
        if (!sender.hasPermission("smidge.clearchat")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        for(int i=0; i<200; i++) {
            Bukkit.broadcastMessage("");
        }
        String broadCast = ChatColor.translateAlternateColorCodes('&', "&c[&eSTAFF&c] &eChat was cleared by " + sender.getName());
        return true;
    }
}
