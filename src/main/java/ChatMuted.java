import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatMuted implements Listener {
    @EventHandler
    public void onChatMutedMessageSend(AsyncPlayerChatEvent e) {
        if (!Config.muteChat.isEmpty()) {
            Player player = e.getPlayer();
            if (!player.hasPermission("smidge.staff")) {
                e.setCancelled(true);
                String reason = ChatColor.translateAlternateColorCodes('&',
                        "&cCannot Send Message As Chat Is Muted");
                e.getPlayer().sendMessage(reason);
            }
        }
    }
    @EventHandler
    public void onMuterLeave(PlayerQuitEvent e) {
        if (!Config.muteChat.isEmpty()) {
           if (Config.muteChat.containsKey(e.getPlayer().getUniqueId())) {
                Config.muteChat.clear();
                String alert = ChatColor.translateAlternateColorCodes('&', "&bThe Chat Has Been Unmuted");
                Bukkit.broadcastMessage(alert);
            }
        }
    }
}

