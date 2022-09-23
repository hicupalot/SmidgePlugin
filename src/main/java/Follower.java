import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Follower implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED+"You Must Be a Player to Do This!");
            return false;
        }
        if (!sender.hasPermission("smidge.spy")) {
            sender.sendMessage(ChatColor.DARK_RED + "[Smidge] You do not have permission");
            return false;
        }
        String username = args[1];
        Player target = Bukkit.getServer().getPlayer(args[1]);

        if (args.length<1){
            sender.sendMessage(ChatColor.RED+"[Smidge] Command Usage /spy (player)");
            return false;
        }
        if (args.length>2){
            sender.sendMessage(ChatColor.RED+"[Smidge] Command Usage /spy (player)");
        }
        if (!Bukkit.getPlayer(username).isOnline() || Bukkit.getPlayer(username)==null){
            sender.sendMessage(ChatColor.RED+"[Smidge] This is not an online player!");
            return false;
        }
        VanishAPI.hidePlayer((Player) sender);
         ((Player) sender).setGameMode(GameMode.SPECTATOR);
          ((Player) sender).setSpectatorTarget(target);
        sender.sendMessage(ChatColor.GOLD+"[Smidge] You are now monitoring " + username);
   return true;
    }
}