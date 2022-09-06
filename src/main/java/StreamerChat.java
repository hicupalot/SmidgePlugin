import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class StreamerChat implements Listener {
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onStreamerSpeak(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();
        String chatMessage = e.getMessage();
        if (Config.streamerToggle.containsKey(playerUUID)){
            Bukkit.broadcast(ChatColor.RED+"["+ChatColor.BLUE+"STREAMER"+ChatColor.RED+"] "+ player.getDisplayName() +": " + chatMessage, "smidge.streamerChat");
            e.setCancelled(true);
        }
    }
}
