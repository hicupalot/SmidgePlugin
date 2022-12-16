import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class AdminChat implements Listener {
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onAdminSpeak(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();
        String chatMessage = e.getMessage();
        if (Config.adminToggle.containsKey(playerUUID)) {
        String colorMessage = ChatColor.translateAlternateColorCodes('&', chatMessage);
            String name = ChatColor.translateAlternateColorCodes('&', "&4"+e.getPlayer().getDisplayName());
            Bukkit.broadcast(Config.adminPrefix + " "
                    + name + ": " + ChatColor.GOLD+ colorMessage, "smidge.admin");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAdminQuit(PlayerQuitEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Config.adminToggle.remove(playerUUID);
    }
}

