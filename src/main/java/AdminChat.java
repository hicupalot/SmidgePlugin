import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class AdminChat implements Listener {
    @SuppressWarnings("deprecated")
    @EventHandler
    public void onAdminSpeak(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();
        String chatMessage = e.getMessage();
        if (Config.adminToggle.containsKey(playerUUID)){
            Bukkit.broadcast(ChatColor.YELLOW+"["+ChatColor.RED+"ADMIN"+ChatColor.YELLOW+"] "+ player.getDisplayName() +": " + chatMessage, "smidge.adminChat");
        }
    }
}

