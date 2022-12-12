import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class hideall implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.notPlayer);
            return false;
        }
        if (!sender.hasPermission("smidge.staff")) {
            sender.sendMessage(Config.noPermission);
            return false;
        }
        if (!Config.hideAllToggle.containsKey(((Player) sender).getUniqueId())) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("smidge.staff")) {
                    String hideAllFail = ChatColor.translateAlternateColorCodes('&', "&c[Smidge] No players are online");
                    sender.sendMessage(hideAllFail);
                    return false;
                } else {
                    ((Player) sender).hidePlayer(Config.plugin, player);
                }
            }
            String hideAllMessage = ChatColor.translateAlternateColorCodes('&', "&c[Smidge] Hid All Players");
            sender.sendMessage(hideAllMessage);
            Config.hideAllToggle.put(((Player) sender).getUniqueId(), true);
        }
        return true;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (Config.hideAllToggle.isEmpty()){
            return;
        }
       else if (e.getPlayer().hasPermission("smidge.staff")){
            return;
        }
        else {
           for(UUID antisocialPlayer : Config.hideAllToggle.keySet()){
               Bukkit.getPlayer(antisocialPlayer).hidePlayer(Config.plugin, e.getPlayer());
           }
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        if (Config.hideAllToggle.containsKey(e.getPlayer().getUniqueId())){
           Config.hideAllToggle.remove(e.getPlayer().getUniqueId());
        }
    }
}
