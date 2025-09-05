import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class maintenance implements Listener {
    Component emptyMessage = Component.text("");
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (config.getBoolean("maintenance")){
            if (!e.getPlayer().hasPermission("smidge.maintenance") || !e.getPlayer().isOp()) {
                e.joinMessage(emptyMessage);
                Component kickReason = Component.text("Maintenance Mode Is Enabled, Joining Is Not Allowed").color(TextColor.color(254,63,63));
                e.getPlayer().kick(kickReason);
            }
        }
    }
    public void onPlayerLeaveWhenMaintenanceEnabled(PlayerQuitEvent e) {
        FileConfiguration config = SmidgeThing.getInstance().getConfig();
        if (config.getBoolean("maintenance")) {
            if (!e.getPlayer().hasPermission("smidge.maintenance") || !e.getPlayer().isOp()) {
                e.quitMessage(emptyMessage);
            }
        }
    }
}
