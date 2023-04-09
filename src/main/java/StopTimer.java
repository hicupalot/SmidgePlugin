import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopTimer implements CommandExecutor {
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
        if (args.length > 0) {
            sender.sendMessage(ChatColor.RED + "[Smidge] Command Usage: /stoptimer");
            return false;
        }
        Bukkit.getScheduler().cancelTasks(SmidgeThing.getInstance());
        String cancelled = ChatColor.translateAlternateColorCodes('&', "&c[Smidge] Cancelled the current timer");
        String cancelAnnounce = ChatColor.translateAlternateColorCodes('&',"&c&l[Smidge] "+"&eThe current timer was cancelled by "+
                sender.getName());
        sender.sendMessage(cancelled);
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player!=sender){
                player.sendMessage(cancelAnnounce);
            }
        }
        return true;
    }
}
