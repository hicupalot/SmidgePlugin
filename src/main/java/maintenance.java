import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class maintenance implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (config.getBoolean("maintenance.")){
            if (!e.getPlayer().hasPermission("smidge.maintenance")) {
                e.setJoinMessage("");
                String kickReason = ChatColor.translateAlternateColorCodes('&',
                        "&cMaintenance Mode Is Enabled, Joining Is Not Allowed");
                e.getPlayer().kickPlayer(kickReason);
            }
        }
    }
}
