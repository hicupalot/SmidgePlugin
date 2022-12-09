import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class DeFollow implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String teleportConfirmation = ChatColor.translateAlternateColorCodes('&',
                "&6[Smidge]: You were teleported back to your previous position");
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (!Config.originalLocation.containsKey(((Player) sender).getUniqueId())){
            String noFollow = ChatColor.translateAlternateColorCodes('&',"&cYou aren't currently following anyone!");
            sender.sendMessage(noFollow);
            return false;
        } else {
            Location playerLoc = Config.originalLocation.get(((Player) sender).getUniqueId());
            ((Player) sender).teleport(playerLoc);
            ((Player) sender).setGameMode(GameMode.CREATIVE);
            sender.sendMessage(teleportConfirmation);
        }
        return true;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Location playerLoc = Config.originalLocation.get(e.getPlayer().getUniqueId());
        e.getPlayer().teleport(playerLoc);
        e.getPlayer().setGameMode(GameMode.CREATIVE);
    }
}
