import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class maintenance implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!Config.maintenaceToggle.isEmpty()) {
            if (!e.getPlayer().hasPermission("smidge.maintenance")) {
                e.setJoinMessage("");
                String kickReason = ChatColor.translateAlternateColorCodes('&',
                        "&cMaintenance Mode Is Enabled, Joining Is Not Allowed");
                e.getPlayer().kickPlayer(kickReason);
            }
        }
    }
}
